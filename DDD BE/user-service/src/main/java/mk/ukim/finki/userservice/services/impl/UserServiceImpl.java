package mk.ukim.finki.userservice.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.events.recipes.FavoriteRecipeAddedEvent;
import mk.ukim.finki.sharedkernel.domain.events.recipes.FavoriteRecipeRemovedEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.userservice.domain.exceptions.UserFavoriteRecipeNotFoundException;
import mk.ukim.finki.userservice.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.userservice.domain.models.User;
import mk.ukim.finki.userservice.domain.models.UserFavoriteRecipes;
import mk.ukim.finki.userservice.domain.models.UserId;
import mk.ukim.finki.userservice.domain.models.UserWeight;
import mk.ukim.finki.userservice.domain.repository.UserFavoriteRecipesRepository;
import mk.ukim.finki.userservice.domain.repository.UserRepository;
import mk.ukim.finki.userservice.domain.repository.UserWeightRepository;
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
    private final UserFavoriteRecipesRepository userRecipesRepository;
    private final RecipeClient recipeClient;
    private final DomainEventPublisher publisher;

    @Override
    public User findByUserId(UserId id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserWeight addCurrentWeight(UserId userId, int weight) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UserWeight saved = weightRepository.save(UserWeight.build(weight, user));
        return saved;
    }

    @Override
    public List<UserWeight> getWeightReportForPeriodAfter(UserId id, LocalDate date) {
        return weightRepository.findAllByUserAndDate(id, date);
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
        Sex sex = user.getMeasurement().getSex();

        if(weight == 0 || height == 0 || user.getActivityLevel() == null || age <= 0 || sex.equals(Sex.UNKNOWN)) {
            throw new RuntimeException("Need all parameters to calculate calories");
        }

        if (user.getMeasurement().getSex().equals(Sex.MALE)) {
            return activityFactor * (66 + (13.7 * weight) + (5 * height) - (6.8 * age));
        }
        return activityFactor * (655 + (9.6 * weight) + (1.8 * height) - (4.7 * age));
    }

    @Override
    public UserFavoriteRecipes addFavoriteRecipe(String userId, String recipeId) {
        UserFavoriteRecipes favoriteRecipes = UserFavoriteRecipes.build(userId, recipeId);
        UserFavoriteRecipes saved = userRecipesRepository.save(favoriteRecipes);
        publisher.publish(new FavoriteRecipeAddedEvent(recipeId));
        return saved;
    }

    @Override
    public void removeFavoriteRecipe(String userId, String recipeId) {
        UserFavoriteRecipes ufr = userRecipesRepository.findByUserIdAndRecipeId(userId, recipeId)
                .orElseThrow(UserFavoriteRecipeNotFoundException::new);
        userRecipesRepository.deleteById(ufr.getId());
        publisher.publish(new FavoriteRecipeRemovedEvent(recipeId));
    }

    private int getAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(birthday, today);
    }

}
