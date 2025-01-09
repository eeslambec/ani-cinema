package uz.pdp.anicinema.exception;

import lombok.Getter;
import uz.pdp.anicinema.utils.enums.Code;

import static uz.pdp.anicinema.utils.contant.MessageKey.*;
import static uz.pdp.anicinema.utils.enums.Code.*;

@Getter
public class BadRequestException extends RuntimeException {

    private final Code code;

    public BadRequestException(String message, Code code) {
        super(message);
        this.code = code;
    }

    private static BadRequestException badRequestException(String key, Code code) {
        return new BadRequestException(key, code);
    }

    public static BadRequestException fileNotValid() {
        return badRequestException(FILE_NOT_VALID, INVALID_DATA);
    }

    public static BadRequestException attachmentNotFound() {
        return badRequestException(ATTACHMENT_NOT_FOUND,DATA_NOT_FOUND);
    }

    public static BadRequestException userAlreadyExists() {
        return badRequestException(USER_ALREADY_EXISTS,DATA_ALREADY_EXISTS);
    }

    public static BadRequestException userNotFound() {
        return badRequestException(USER_NOT_FOUND,DATA_NOT_FOUND);
    }

    public static BadRequestException emailNoValid() {
        return badRequestException(EMAIL_NOT_VALID,INVALID_DATA);
    }

    public static BadRequestException passwordNoValid() {
        return badRequestException(PASSWORD_NOT_VALID,INVALID_DATA);
    }

    public static BadRequestException tokenIsExpired() {
        return badRequestException(TOKEN_IS_EXPIRED,TOKEN_EXPIRED);
    }

    public static BadRequestException verificationCodeInvalid() {
        return badRequestException(VERIFICATION_CODE_NOT_VALID,INVALID_DATA);
    }



}
