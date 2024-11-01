package de.dfutil.helpers.utilities;

public class StringHelper {

    public static String convertHyphenSnakeToCamelCase(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException("Invalid input");
        }
        StringBuilder sb = new StringBuilder();
        String[] words = input.split("-");
        for (int j = 0; j < words.length; j++) {
            for (int i = 0; i < words[j].length(); i++) {
                char letter = words[j].charAt(i);
                if (j != 0 && i == 0) {
                    sb.append(Character.toUpperCase(letter));
                } else {
                    sb.append(letter);
                }
            }
        }
        return sb.toString();
    }

}



