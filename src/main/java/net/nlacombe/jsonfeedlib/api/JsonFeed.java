package net.nlacombe.jsonfeedlib.api;

import java.net.URL;
import java.util.List;

public interface JsonFeed {

    static JsonFeedBuilder builder(String version, String title) {
        return new JsonFeedBuilder(version, title);
    }

    JsonFeedVersion getVersion();

    String getTitle();

    URL getHomePageUrl();

    URL getFeedUrl();

    String getDescription();

    String getUserComment();

    URL getNextUrl();

    URL getIcon();

    URL getFavicon();

    String getLanguage();

    boolean isExpired();

    List<JsonFeedHub> getHubs();

    List<JsonFeedAuthor> getAuthors();

    List<JsonFeedItem> getItems();
}
