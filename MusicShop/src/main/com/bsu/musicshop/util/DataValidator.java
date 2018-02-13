package main.com.bsu.musicshop.util;

public class DataValidator {
    private static final String LOGIN_REGEX= "^[a-zA-Z0-9]{2,14}";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";

    private DataValidator() {
    }

    public static boolean isLoginCorrect(String login) {
        return login.matches(LOGIN_REGEX);
    }


    public static boolean isPasswordCorrect(String password) {
        return password.matches(PASSWORD_REGEX);
    }

}
