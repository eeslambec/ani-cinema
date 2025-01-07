package uz.pdp.anicinema.exception;

import lombok.Getter;
import uz.pdp.anicinema.utils.enums.Code;

import static uz.pdp.anicinema.utils.contant.MessageKey.ATTACHMENT_NOT_FOUND;
import static uz.pdp.anicinema.utils.contant.MessageKey.FILE_NOT_VALID;
import static uz.pdp.anicinema.utils.enums.Code.DATA_NOT_FOUND;
import static uz.pdp.anicinema.utils.enums.Code.INVALID_DATA;

@Getter
public class BadRequestException extends RuntimeException {

    private final Code code;

    public BadRequestException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public static BadRequestException badRequestException(String key, Code code) {
        return new BadRequestException(key, code);
    }

    public static BadRequestException fileNotValid() {
        return badRequestException(FILE_NOT_VALID, INVALID_DATA);
    }

    public static BadRequestException attachmentNotFound() {
        return badRequestException(ATTACHMENT_NOT_FOUND,DATA_NOT_FOUND);
    }

}
