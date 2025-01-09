package uz.pdp.anicinema.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static uz.pdp.anicinema.utils.contant.MessageKey.EMAIL_NOT_VALID;
import static uz.pdp.anicinema.utils.contant.MessageKey.VERIFICATION_CODE_NOT_VALID;

@AllArgsConstructor
@Getter
public class EmailVerificationRequest {

    @NotNull(message = EMAIL_NOT_VALID)
    @NotBlank(message = EMAIL_NOT_VALID)
    private String email;

    @NotNull(message = VERIFICATION_CODE_NOT_VALID)
    @NotBlank(message = VERIFICATION_CODE_NOT_VALID)
    private String code;

}
