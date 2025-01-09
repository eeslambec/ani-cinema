package uz.pdp.anicinema.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.anicinema.utils.enums.Gender;

import static uz.pdp.anicinema.utils.contant.MessageKey.*;

@AllArgsConstructor
@Getter
public class RegisterRequest {

    @NotNull(message = USERNAME_NOT_VALID)
    @NotBlank(message = USERNAME_NOT_VALID)
    private String username;

    @NotNull(message = PASSWORD_NOT_VALID)
    @NotBlank(message = PASSWORD_NOT_VALID)
    private String password;

    @NotNull(message = EMAIL_NOT_VALID)
    @NotBlank(message = EMAIL_NOT_VALID)
    private String email;

    @NotNull(message = GENDER_NOT_VALID)
    private Gender gender;

}
