package mk.ukim.finki.recipecatalog.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.recipecatalog.domain.valueobjects.UserId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recipes")
@Getter
@Setter
public class Recipe extends AbstractEntity<RecipeId> {
    private int likes = 0;

    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;
    @Lob
    private String description;
    @AttributeOverride(name = "id", column = @Column(name = "creator_id", nullable = false))
    private UserId creatorId;

    public Recipe() {
        super(RecipeId.randomId(RecipeId.class));
        dateCreated = LocalDate.now();
    }

    public static Recipe build(String title, String description, UserId creatorId) {
        Recipe r = new Recipe();
        r.title = title;
        r.description = description;
        r.creatorId = creatorId;
        return r;
    }

    public void increaseLikes() {
        likes++;
    }

    public void decreaseLikes() {
        likes--;
    }

}
