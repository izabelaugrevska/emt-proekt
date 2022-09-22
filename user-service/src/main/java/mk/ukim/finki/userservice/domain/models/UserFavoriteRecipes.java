package mk.ukim.finki.userservice.domain.models;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.userservice.domain.valueobjects.RecipeId;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user_favorite_recipes")//,
//        uniqueConstraints = { @UniqueConstraint(columnNames = {"userId", "recipeId"})
//        })
public class UserFavoriteRecipes extends AbstractEntity<UserFavoriteRecipesId> {

    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    @AttributeOverride(name = "id", column = @Column(name = "recipe_id", nullable = false))
    private RecipeId recipeId;

    public UserFavoriteRecipes() {
        super(UserFavoriteRecipesId.randomId(UserFavoriteRecipesId.class));
    }

    public static UserFavoriteRecipes build(UserId userId, RecipeId recipeId) {
        UserFavoriteRecipes ufr = new UserFavoriteRecipes();
        ufr.userId = userId;
        ufr.recipeId = recipeId;
        return ufr;
    }

}
