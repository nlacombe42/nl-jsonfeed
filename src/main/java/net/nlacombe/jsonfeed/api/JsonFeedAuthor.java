package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.impl.DefaultJsonFeedAuthor;

import java.net.URL;

public interface JsonFeedAuthor {

    static JsonFeedAuthor from(String name, URL url, URL avatar) {
        return DefaultJsonFeedAuthor.newDefaultJsonFeedAuthor(name, url, avatar);
    }

    static JsonFeedAuthor fromName(String name) {
        return DefaultJsonFeedAuthor.newDefaultJsonFeedAuthor(name, null, null);
    }

    static JsonFeedAuthor fromUrl(URL url) {
        return DefaultJsonFeedAuthor.newDefaultJsonFeedAuthor(null, url, null);
    }

    static JsonFeedAuthor fromAvatar(URL avatar) {
        return DefaultJsonFeedAuthor.newDefaultJsonFeedAuthor(null, null, avatar);
    }

    String getName();

    URL getUrl();

    URL getAvatar();

}
