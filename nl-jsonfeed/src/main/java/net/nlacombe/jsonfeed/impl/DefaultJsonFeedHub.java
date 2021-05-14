package net.nlacombe.jsonfeed.impl;

import net.nlacombe.jsonfeed.api.JsonFeedHub;
import net.nlacombe.jsonfeed.impl.util.ObjectUtil;
import net.nlacombe.jsonfeed.impl.util.StringUtil;

import java.net.URL;

public class DefaultJsonFeedHub implements JsonFeedHub {

    private final String type;
    private final URL url;

    private DefaultJsonFeedHub(String type, URL url) {
        this.type = StringUtil.requireNotBlank(type, "you must specify a value for type (and url)");
        this.url = ObjectUtil.requireNonNull(url, "you must specify a value for url (and type)");
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
