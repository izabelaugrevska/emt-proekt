package mk.ukim.finki.recipecatalog.services;

import mk.ukim.finki.recipecatalog.domain.models.Recipe;
import mk.ukim.finki.recipecatalog.domain.models.RecipeId;
import mk.ukim.finki.recipecatalog.domain.valueobjects.UserId;
import mk.ukim.finki.recipecatalog.services.form.RecipeForm;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    List<Recipe> findAllByCreatorId(UserId userId);

    Recipe findById(RecipeId id);

    Recipe createRecipe(RecipeForm form);

    void deleteRecipe(RecipeId id);

    void increaseLikes(RecipeId id);

    void decreaseLikes(RecipeId id);

}
