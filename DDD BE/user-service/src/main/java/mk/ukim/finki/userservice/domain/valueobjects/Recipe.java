package mk.ukim.finki.userservice.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import java.time.LocalDate;

@Getter
public class Recipe implements ValueObject {

    private final RecipeId id;
    private final String title;
    private final LocalDate dateCreated;
    private final String description;
    private final int likes;

    public Recipe() {
        this.id = RecipeId.randomId(RecipeId.class);
        this.title = "";
        this.dateCreated = null;
        this.description = "";
        this.likes = 0;
    }

    @JsonCreator
    public Recipe(@JsonProperty("id") RecipeId id,
                  @JsonProperty("title") String title,
                  @JsonProperty("dateCreated") LocalDate dateCreated,
                  @JsonProperty("description") String description,
                  @JsonProperty("likes") int likes) {
        this.id = id;
        this.title = title;
        this.dateCreated = dateCreated;
        this.description = description;
        this.likes = likes;
    }

}
