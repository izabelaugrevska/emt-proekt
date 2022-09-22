package mk.ukim.finki.userservice.domain.models;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class UserFavoriteRecipesId extends DomainObjectId {

    public UserFavoriteRecipesId() {
        super(UserFavoriteRecipesId.randomId(UserFavoriteRecipesId.class).getId());
    }

    public UserFavoriteRecipesId(@NonNull String uuid) {
        super(uuid);
    }

    public static UserFavoriteRecipesId of(String uuid) {
        return new UserFavoriteRecipesId(uuid);
    }

}