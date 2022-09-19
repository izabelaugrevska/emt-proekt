package mk.ukim.finki.recipecatalog.domain.models;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class RecipeId extends DomainObjectId {

    private RecipeId() {
        super(RecipeId.randomId(RecipeId.class).getId());
    }

    public RecipeId(@NonNull String uuid) {
        super(uuid);
    }

    public static RecipeId of(String uuid) {
        RecipeId id = new RecipeId(uuid);
        return id;
    }

}
