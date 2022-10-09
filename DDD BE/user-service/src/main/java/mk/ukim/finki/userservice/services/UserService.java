package mk.ukim.finki.userservice.services;

import mk.ukim.finki.userservice.domain.models.User;
import mk.ukim.finki.userservice.domain.models.UserFavoriteRecipes;
import mk.ukim.finki.userservice.domain.models.UserId;
import mk.ukim.finki.userservice.domain.models.UserWeight;
import mk.ukim.finki.userservice.domain.valueobjects.RecipeId;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User findByUserId(UserId id);

    UserWeight addCurrentWeight(UserId userId, int weight);

    List<UserWeight> getWeightReportForPeriodAfter(UserId id, LocalDate date);

    double calculateCalories(UserId id);

    UserFavoriteRecipes addFavoriteRecipe(String userId, String recipeId);

    void removeFavoriteRecipe(String userId, String recipeId);

}
