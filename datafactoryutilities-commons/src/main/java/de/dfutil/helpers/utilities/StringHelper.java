package de.dfutil.helpers.utilities;

public class StringHelper {

    //TODO Rework string conversion from stringbuffer to a more efficient way
    public static String convertHyphenSnakeToCamelCase(String input) {
        StringBuffer sb = new StringBuffer(input.toLowerCase());
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