package com.bsu.triangle.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TriangleValidator {

    private static final String trianglePattern="(\\d+\\s|\\$){6}";

    public static boolean validate(String triangleParams){

        Pattern p = Pattern.compile(trianglePattern);
        Matcher m = p.matcher(triangleParams);
        return m.matches();
    }
}
