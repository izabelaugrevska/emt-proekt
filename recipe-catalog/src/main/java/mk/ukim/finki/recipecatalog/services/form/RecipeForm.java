package mk.ukim.finki.recipecatalog.services.form;

import lombok.Data;
import mk.ukim.finki.recipecatalog.domain.valueobjects.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RecipeForm {

    @NotNull
    private UserId creatorId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;

}
