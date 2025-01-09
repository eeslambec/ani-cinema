package uz.pdp.anicinema.utils.validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexValidator {

    private static final String EMAIL_REGEX = "";
    private static final String PASSWORD_REGEX = "";

    public static boolean email(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean password(String password) {
        return password.matches(PASSWORD_REGEX);
    }

}
