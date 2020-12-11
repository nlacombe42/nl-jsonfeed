package net.nlacombe.jsonfeed.impl;

import net.nlacombe.jsonfeed.api.JsonFeedAuthor;
import net.nlacombe.jsonfeed.impl.util.StringUtil;

import java.net.URL;

public class DefaultJsonFeedAuthor implements JsonFeedAuthor {

    private final String name;
    private final URL url;
    private final URL avatar;

    private DefaultJsonFeedAuthor(String name, URL url, URL avatar) {
        this.name = name;
        this.url = url;
        this.avatar = avatar;

        if (StringUtil.isBlank(name) && url == null && avatar == null)
            throw new IllegalArgumentException("You must specify at least one of the following properties: name, url, avatar");
    }

    public static DefaultJsonFeedAuthor newDefaultJsonFeedAuthor(String name, URL url, URL avatar) {
        return new DefaultJsonFeedAuthor(name, url, avatar);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public URL getAvatar() {
        return avatar;
    }

}
