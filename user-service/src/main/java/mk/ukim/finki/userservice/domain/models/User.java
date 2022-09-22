package mk.ukim.finki.userservice.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.userservice.domain.valueobjects.BodyMeasurement;
import mk.ukim.finki.userservice.domain.valueobjects.Recipe;
import mk.ukim.finki.userservice.domain.valueobjects.RecipeId;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private BodyMeasurement measurement;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserWeight> listOfWeights = new ArrayList<>();

    public User() {
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

}
