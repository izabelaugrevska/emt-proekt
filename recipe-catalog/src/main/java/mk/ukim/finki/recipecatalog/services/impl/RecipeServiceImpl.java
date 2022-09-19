package mk.ukim.finki.recipecatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.recipecatalog.domain.exceptions.RecipeNotFoundException;
import mk.ukim.finki.recipecatalog.domain.models.Recipe;
import mk.ukim.finki.recipecatalog.domain.models.RecipeId;
import mk.ukim.finki.recipecatalog.domain.repository.RecipeRepository;
import mk.ukim.finki.recipecatalog.domain.valueobjects.UserId;
import mk.ukim.finki.recipecatalog.services.RecipeService;
import mk.ukim.finki.recipecatalog.services.form.RecipeForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> findAllByCreatorId(UserId userId) {
        return recipeRepository.findAllByCreatorIdEquals(userId);
    }

    @Override
    public Recipe findById(RecipeId id) {
        return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    @Override
    public Recipe createRecipe(RecipeForm form) {
        Recipe r = Recipe.build(form.getTitle(), form.getDescription(), form.getCreatorId());
        return recipeRepository.save(r);
    }

    @Override
    public void deleteRecipe(RecipeId id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void increaseLikes(RecipeId id) {
        Recipe r = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        r.increaseLikes();
        recipeRepository.save(r);
    }

    @Override
    public void decreaseLikes(RecipeId id) {
        Recipe r = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        r.decreaseLikes();
        recipeRepository.save(r);
    }
}
