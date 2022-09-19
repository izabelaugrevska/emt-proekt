package mk.ukim.finki.userservice.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.events.recipes.FavoriteRecipeAddedEvent;
import mk.ukim.finki.sharedkernel.domain.events.recipes.FavoriteRecipeRemovedEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.userservice.domain.exceptions.RecipeNotFoundException;
import mk.ukim.finki.userservice.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.userservice.domain.models.User;
import mk.ukim.finki.userservice.domain.models.UserId;
import mk.ukim.finki.userservice.domain.models.UserWeight;
import mk.ukim.finki.userservice.domain.repository.UserRepository;
import mk.ukim.finki.userservice.domain.repository.UserWeightRepository;
import mk.ukim.finki.userservice.domain.valueobjects.Recipe;
import mk.ukim.finki.userservice.domain.valueobjects.RecipeId;
import mk.ukim.finki.userservice.domain.valueobjects.Sex;
import mk.ukim.finki.userservice.services.UserService;
import mk.ukim.finki.userservice.xport.client.RecipeClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserWeightRepository weightRepository;
    private final RecipeClient recipeClient;
    private final DomainEventPublisher publisher;

    @Override
    public User findByUserId(UserId id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserWeight addCurrentWeight(UserId id, UserWeight userWeight) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.addWeight(userWeight);
        userRepository.save(user);
        return userWeight;
    }

    @Override
    public List<UserWeight> getWeightReportForPeriodAfter(UserId id, LocalDate date) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return weightRepository.findAllByUserAndDate(user, date);
    }

    @Override
    public double calculateCalories(UserId id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<UserWeight> weights = user.getListOfWeights();
        Collections.sort(weights);
        int weight = weights.get(weights.size() - 1).getWeight();
        int height = user.getMeasurement().getHeight();
        double activityFactor = user.getActivityLevel().value;
        int age = getAge(user.getDateOfBirth());

        if (user.getMeasurement().getSex().equals(Sex.MALE)) {
            return activityFactor * (66 + (13.7 * weight) + (5 * height) - (6.8 * age));
        }
        return activityFactor * (655 + (9.6 * weight) + (1.8 * height) - (4.7 * age));
    }

    @Override
    public User addFavoriteRecipe(UserId userId, RecipeId recipeId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Recipe recipe = recipeClient.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        user.addFavoriteRecipe(recipe);
        User saved = userRepository.save(user);
        publisher.publish(new FavoriteRecipeAddedEvent(recipe.getId().getId()));
        return saved;
    }

    @Override
    public User removeFavoriteRecipe(UserId userId, RecipeId recipeId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Recipe recipe = recipeClient.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        user.removeFavoriteRecipe(recipe);
        User saved = userRepository.save(user);
        publisher.publish(new FavoriteRecipeRemovedEvent(recipe.getId().getId()));
        return saved;
    }

    private int getAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(birthday, today);
    }

}
