package net.nlacombe.jsonfeed.impl;

import net.nlacombe.jsonfeed.api.JsonFeedHub;

import java.net.URL;

public class DefaultJsonFeedHub implements JsonFeedHub {

    private final String type;
    private final URL url;

    private DefaultJsonFeedHub(String type, URL url) {
        this.type = type;
        this.url = url;
    }

    public static DefaultJsonFeedHub newDefaultJsonFeedHub(String type, URL url) {
        return new DefaultJsonFeedHub(type, url);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public URL getUrl() {
        return url;
    }
}
