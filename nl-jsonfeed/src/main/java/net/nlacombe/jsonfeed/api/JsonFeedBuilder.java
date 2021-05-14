package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.impl.DefaultJsonFeed;
import net.nlacombe.jsonfeed.impl.util.UrlUtil;

import java.net.URL;
import java.util.List;
import java.util.Locale;

public class JsonFeedBuilder {

    private final DefaultJsonFeed jsonFeed;

    private JsonFeedBuilder(JsonFeedVersion version, String title) {
        jsonFeed = DefaultJsonFeed.newDefaultJsonFeed(version, title);
    }

    public static JsonFeedBuilder from(JsonFeedVersion version, String title) {
        return new JsonFeedBuilder(version, title);
    }

    public JsonFeedBuilder homePageUrl(URL homePageUrl) {
        jsonFeed.setHomePageUrl(homePageUrl);

        return this;
    }

    public JsonFeedBuilder homePageUrl(String homePageUrl) {
        jsonFeed.setHomePageUrl(UrlUtil.toUrl(homePageUrl));

        return this;
    }

    public JsonFeedBuilder feedUrl(URL feedUrl) {
        jsonFeed.setFeedUrl(feedUrl);

        return this;
    }

    public JsonFeedBuilder feedUrl(String feedUrl) {
        jsonFeed.setFeedUrl(UrlUtil.toUrl(feedUrl));

        return this;
    }

    public JsonFeedBuilder description(String description) {
        jsonFeed.setDescription(description);

        return this;
    }

    public JsonFeedBuilder userComment(String userComment) {
        jsonFeed.setUserComment(userComment);

        return this;
    }

    public JsonFeedBuilder nextUrl(URL nextUrl) {
        jsonFeed.setNextUrl(nextUrl);

        return this;
    }

    public JsonFeedBuilder nextUrl(String nextUrl) {
        jsonFeed.setNextUrl(UrlUtil.toUrl(nextUrl));

        return this;
    }

    public JsonFeedBuilder icon(URL icon) {
        jsonFeed.setIcon(icon);

        return this;
    }

    public JsonFeedBuilder icon(String icon) {
        jsonFeed.setIcon(UrlUtil.toUrl(icon));

        return this;
    }

    public JsonFeedBuilder favicon(URL favicon) {
        jsonFeed.setFavicon(favicon);

        return this;
    }

    public JsonFeedBuilder favicon(String favicon) {
        jsonFeed.setFavicon(UrlUtil.toUrl(favicon));

        return this;
    }

    public JsonFeedBuilder language(Locale language) {
        jsonFeed.setLanguage(language);

        return this;
    }

    public JsonFeedBuilder language(String language) {
        return language(Locale.forLanguageTag(language));
    }

    public JsonFeedBuilder expired(Boolean expired) {
        jsonFeed.setExpired(expired);

        return this;
    }

    public JsonFeedBuilder hubs(List<JsonFeedHub> hubs) {
        if (hubs == null || hubs.isEmpty())
            return this;

        jsonFeed.setHubs(hubs);

        return this;
    }

    public JsonFeedBuilder hubs(JsonFeedHub... hubs) {
        if (hubs == null || hubs.length == 0)
            return this;

        jsonFeed.setHubs(List.of(hubs));

        return this;
    }

    public JsonFeedBuilder authors(List<JsonFeedAuthor> authors) {
        if (authors == null || authors.isEmpty())
            return this;

        jsonFeed.setAuthors(authors);

        return this;
    }

    public JsonFeedBuilder authors(JsonFeedAuthor... authors) {
        if (authors == null || authors.length == 0)
            return this;

        jsonFeed.setAuthors(List.of(authors));

        return this;
    }

    public JsonFeedBuilder items(List<JsonFeedItem> items) {
        if (items == null || items.isEmpty())
            return this;

        jsonFeed.setItems(items);

        return this;
    }

    public JsonFeedBuilder items(JsonFeedItem... items) {
        if (items == null || items.length == 0)
            return this;

        jsonFeed.setItems(List.of(items));

        return this;
    }

    public JsonFeed build() {
        return jsonFeed;
    }
}
