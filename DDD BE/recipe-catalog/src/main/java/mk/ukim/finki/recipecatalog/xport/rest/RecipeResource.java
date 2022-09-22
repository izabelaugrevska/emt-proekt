package mk.ukim.finki.recipecatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.recipecatalog.domain.models.Recipe;
import mk.ukim.finki.recipecatalog.domain.models.RecipeId;
import mk.ukim.finki.recipecatalog.domain.valueobjects.UserId;
import mk.ukim.finki.recipecatalog.services.RecipeService;
import mk.ukim.finki.recipecatalog.services.form.RecipeForm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeResource {

    private final RecipeService recipeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getAll(){
        return recipeService.findAll();
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getAllByCreatorId(@PathVariable String userId){
        return recipeService.findAllByCreatorId(UserId.of(userId));
    }

    @GetMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe getById(@PathVariable String recipeId){
        return recipeService.findById(RecipeId.of(recipeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe createRecipe(@RequestBody RecipeForm form){
        return recipeService.createRecipe(form);
    }

    @DeleteMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecipe(@PathVariable String recipeId){
        recipeService.deleteRecipe(RecipeId.of(recipeId));
    }

}
