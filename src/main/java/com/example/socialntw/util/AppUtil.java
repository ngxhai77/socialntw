package com.example.socialntw.util;


import java.util.regex.Matcher;

import static com.example.socialntw.common.constant.AppConstant.*;

public class AppUtil {
    public static boolean containsSpecialCharacters(String s) {
        Matcher matcher = SPECIAL_CHAR_PATTERN.matcher(s);
        if (s.isEmpty()) {
            return true;
        }
        return matcher.find();
    }


    public static boolean containsNumber(String str) {
        return str != null && CONTAINS_NUMBER_PATTERN.matcher(str).find();
    }

    public static boolean containsSpecialCharacter(String str) {
        return str != null && SPECIAL_CHAR_PATTERN.matcher(str).find();
    }

}


