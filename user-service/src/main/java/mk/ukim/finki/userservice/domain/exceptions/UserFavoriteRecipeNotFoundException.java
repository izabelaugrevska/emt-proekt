package mk.ukim.finki.userservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserFavoriteRecipeNotFoundException extends RuntimeException {

    public UserFavoriteRecipeNotFoundException() {
        super();
    }

    public UserFavoriteRecipeNotFoundException(String message) {
        super(message);
    }

}
