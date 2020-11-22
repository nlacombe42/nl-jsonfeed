package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.impl.DefaultJsonFeed;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class JsonFeedBuilder {

    private final DefaultJsonFeed jsonFeed;

    public JsonFeedBuilder(JsonFeedVersion version, String title) {
        jsonFeed = DefaultJsonFeed.newEmpty();
        jsonFeed.setVersion(Objects.requireNonNull(version, "version must not be null"));
        jsonFeed.setTitle(Objects.requireNonNull(title, "title must not be null"));
        jsonFeed.setItems(new LinkedList<>());
    }

    public JsonFeedBuilder homePageUrl(URL homePageUrl) {
        jsonFeed.setHomePageUrl(homePageUrl);

        return this;
    }

    public JsonFeedBuilder feedUrl(URL feedUrl) {
        jsonFeed.setFeedUrl(feedUrl);

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

    public JsonFeedBuilder icon(URL icon) {
        jsonFeed.setIcon(icon);

        return this;
    }

    public JsonFeedBuilder favicon(URL favicon) {
        jsonFeed.setFavicon(favicon);

        return this;
    }

    public JsonFeedBuilder language(String language) {
        jsonFeed.setLanguage(language);

        return this;
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

    public JsonFeed build() {
        return jsonFeed;
    }
}
