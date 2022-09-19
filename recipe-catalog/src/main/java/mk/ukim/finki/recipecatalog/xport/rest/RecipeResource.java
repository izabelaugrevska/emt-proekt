package mk.ukim.finki.recipecatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.recipecatalog.domain.models.Recipe;
import mk.ukim.finki.recipecatalog.domain.models.RecipeId;
import mk.ukim.finki.recipecatalog.domain.valueobjects.UserId;
import mk.ukim.finki.recipecatalog.services.RecipeService;
import mk.ukim.finki.recipecatalog.services.form.RecipeForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recipes")
@CrossOrigin("http://localhost:3000")
public class RecipeResource {

    private final RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAll(){
        return recipeService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Recipe> getAllByCreatorId(@PathVariable UserId userId){
        return recipeService.findAllByCreatorId(userId);
    }

    @GetMapping("/{recipeId}")
    public Recipe getById(@PathVariable RecipeId recipeId){
        return recipeService.findById(recipeId);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody RecipeForm form){
        return recipeService.createRecipe(form);
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(@PathVariable RecipeId recipeId){
        recipeService.deleteRecipe(recipeId);
    }
}
