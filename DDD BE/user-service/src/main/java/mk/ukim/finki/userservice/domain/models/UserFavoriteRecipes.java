package mk.ukim.finki.userservice.domain.models;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Entity
@Table(name = "user_favorite_recipes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "recipe_id"})
        })
public class UserFavoriteRecipes extends AbstractEntity<UserFavoriteRecipesId> {

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "recipe_id", nullable = false)
    private String recipeId;

    public UserFavoriteRecipes() {
        super(UserFavoriteRecipesId.randomId(UserFavoriteRecipesId.class));
    }

    public static UserFavoriteRecipes build(String userId, String recipeId) {
        UserFavoriteRecipes ufr = new UserFavoriteRecipes();
        ufr.userId = userId;
        ufr.recipeId = recipeId;
        return ufr;
    }

}
