package net.nlacombe.jsonfeed.impl.util;

public class StringUtil {

    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public static boolean isBlank(String text) {
        return isEmpty(text) || "".equals(text.trim());
    }

    public static String requireNotBlank(String text, String message) {
        if (isBlank(text))
            throw new IllegalArgumentException(message);

        return text;
    }

}
