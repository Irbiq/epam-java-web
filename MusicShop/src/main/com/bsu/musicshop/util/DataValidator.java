package main.com.bsu.musicshop.util;

public class DataValidator {
    private static final String LOGIN_REGEX= "^\\p{L}(\\p{L}|\\p{N}|[_])*$";
    private static final String PASSWORD_REGEX = "^.*(?=.{6,})(?=.*(\\p{Ll})+)(?=.*(\\p{Lu})+)(?=.*(\\p{N})+).*$";

    private DataValidator() {
    }

    public static boolean isLoginCorrect(String login) {
        return login.matches(LOGIN_REGEX);
    }


    public static boolean isPasswordCorrect(String password) {
        return password.matches(PASSWORD_REGEX);
    }

}
