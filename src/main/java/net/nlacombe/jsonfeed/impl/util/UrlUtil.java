package net.nlacombe.jsonfeed.impl.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class UrlUtil {

    public static URL toUrl(String url) {
        try {
            return URI.create(url).toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
