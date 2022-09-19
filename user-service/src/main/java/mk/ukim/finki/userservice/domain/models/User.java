package mk.ukim.finki.userservice.domain.models;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.userservice.domain.valueobjects.BodyMeasurement;
import mk.ukim.finki.userservice.domain.valueobjects.Recipe;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User extends AbstractEntity<UserId> {

    private String username;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

    @Embedded
    private BodyMeasurement measurement;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserWeight> listOfWeights = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_favorite_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> favoriteRecipes = new ArrayList<>();

    private User() {
        super(UserId.randomId(UserId.class));
    }

    public static User build(String username, String email, String password, LocalDate dateOfBirth,
                             BodyMeasurement measurement, ActivityLevel activityLevel) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.password = password;
        user.dateOfBirth = dateOfBirth;
        user.measurement = measurement;
        user.activityLevel = activityLevel;
        return user;
    }

    public void addWeight(UserWeight userWeight) {
        listOfWeights.add(userWeight);
    }

    public void addFavoriteRecipe(Recipe recipe) {
        favoriteRecipes.add(recipe);
    }

    public void removeFavoriteRecipe(Recipe recipe) {
        favoriteRecipes.remove(recipe);
    }

}
