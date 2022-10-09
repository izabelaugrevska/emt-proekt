package mk.ukim.finki.userservice.domain.repository;

import mk.ukim.finki.userservice.domain.models.UserFavoriteRecipes;
import mk.ukim.finki.userservice.domain.models.UserFavoriteRecipesId;
import mk.ukim.finki.userservice.domain.valueobjects.RecipeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserFavoriteRecipesRepository extends JpaRepository<UserFavoriteRecipes, UserFavoriteRecipesId> {

    @Query(value = "select ufr.recipeId from UserFavoriteRecipes ufr where ufr.userId=?1")
    List<RecipeId> findAllFavoriteRecipesForUser(String userId);

    Optional<UserFavoriteRecipes> findByUserIdAndRecipeId(String userId, String recipeId);

}
