package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.impl.DefaultJsonFeedHub;
import net.nlacombe.jsonfeed.impl.util.UrlUtil;

import java.net.URL;

public interface JsonFeedHub {

    static JsonFeedHub from(String type, URL url) {
        return DefaultJsonFeedHub.newDefaultJsonFeedHub(type, url);
    }

    static JsonFeedHub from(String type, String url) {
        return DefaultJsonFeedHub.newDefaultJsonFeedHub(type, UrlUtil.toUrl(url));
    }

    String getType();

    URL getUrl();

}
