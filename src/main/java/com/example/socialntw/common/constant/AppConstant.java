package com.example.socialntw.common.constant;

import java.util.regex.Pattern;

public class AppConstant {
    public static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9\\s]");
    public static final String PHONE_NUMBER_REGEX = "^\\d{10}$";
    public static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
    public static final String PATTERN = "dd-MM-yyyy";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String CONTAINS_NUMBER_REGEX = ".*\\d.*";
    public static final Pattern CONTAINS_NUMBER_PATTERN = Pattern.compile(CONTAINS_NUMBER_REGEX);
    private static final String NAME_REGEX = "^[A-Za-z]+(([' -][A-Za-z ])?[A-Za-z]*)*$";
    public static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    public static final String ADMIN_PERMISSION = "hasRole('ADMIN')";
    public static final String CREATOR_PERMISSION = "hasRole('CREATOR')";
    public static final String USER_PERMISSION = "hasRole('USER')";
}
