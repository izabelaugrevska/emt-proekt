package mk.ukim.finki.userservice.domain.valueobjects;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class RecipeId extends DomainObjectId {

    public RecipeId() {
        super(RecipeId.randomId(RecipeId.class).getId());
    }

    public RecipeId(String uuid) {
        super(uuid);
    }

    public static RecipeId of(String uuid) {
        return new RecipeId(uuid);
    }

}
