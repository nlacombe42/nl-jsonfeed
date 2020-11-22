package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.impl.DefaultJsonFeedHub;

import java.net.URL;

public interface JsonFeedHub {

    static JsonFeedHub from(String type, URL url) {
        return DefaultJsonFeedHub.newDefaultJsonFeedHub(type, url);
    }

    String getType();

    URL getUrl();

}
