package mk.ukim.finki.userservice.xport.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import mk.ukim.finki.userservice.domain.models.User;
import mk.ukim.finki.userservice.domain.models.UserFavoriteRecipes;
import mk.ukim.finki.userservice.domain.models.UserId;
import mk.ukim.finki.userservice.domain.models.UserWeight;
import mk.ukim.finki.userservice.domain.valueobjects.RecipeId;
import mk.ukim.finki.userservice.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getProfile(@PathVariable String userId) {
        return userService.findByUserId(UserId.of(userId));
    }

    @GetMapping("/{userId}/calculate-calories")
    @ResponseStatus(HttpStatus.OK)
    public double calculateCalories(@PathVariable String userId) {
        return userService.calculateCalories(UserId.of(userId));
    }

    @GetMapping("/{userId}/weight-report")
    @ResponseStatus(HttpStatus.OK)
    public List<UserWeight> getReportForPeriod(@PathVariable String userId,
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                               @RequestParam LocalDate startDate) {
        return userService.getWeightReportForPeriodAfter(UserId.of(userId), startDate);
    }

    @PostMapping("/{userId}/add-new-weight")
    @ResponseStatus(HttpStatus.CREATED)
    public UserWeight addWeight(@PathVariable String userId,@NotNull @Min(40) @RequestParam int weight) {
        return userService.addCurrentWeight(UserId.of(userId), weight);
    }

    @PatchMapping("/{userId}/add-favorite-recipe/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public UserFavoriteRecipes addFavoriteRecipe(@PathVariable String userId, @PathVariable String recipeId) {
        return userService.addFavoriteRecipe(userId, recipeId);
    }

    @PatchMapping("/{userId}/remove-favorite-recipe/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeFavoriteRecipe(@PathVariable String userId, @PathVariable String recipeId) {
        userService.removeFavoriteRecipe(userId, recipeId);
    }

}
