package com.city.util;

public class TextUtils {
   
    public static String firstLetterCap (String input) {
        if (input != null) {
            String[] words = input.toUpperCase().split(" ");
            String str = "";
            for (String word : words) {
                str += word.toUpperCase().replace(word.substring(1), word.substring(1).toLowerCase()) + " ";
            }
            input = str.trim();
        }
        return input;
    }


}

