package net.nlacombe.jsonfeed.impl.util;

public class ObjectUtil {

    public static <T> T requireNonNull(T object, String message) {
        if (object == null)
            throw new IllegalArgumentException(message);

        return object;
    }
}
