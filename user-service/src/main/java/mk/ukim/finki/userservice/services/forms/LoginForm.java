package mk.ukim.finki.userservice.services.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}