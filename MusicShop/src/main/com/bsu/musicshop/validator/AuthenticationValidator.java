package main.com.bsu.musicshop.validator;

public class AuthenticationValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public static boolean validatePassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }
}
