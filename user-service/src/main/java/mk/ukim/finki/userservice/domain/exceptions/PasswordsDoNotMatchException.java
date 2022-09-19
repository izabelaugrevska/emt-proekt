package mk.ukim.finki.userservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super();
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
