package net.nlacombe.jsonfeedlib.api;

import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.util.List;

public interface JsonFeed {

    static JsonFeedBuilder builder(JsonFeedVersion version, String title) {
        return new JsonFeedBuilder(version, title);
    }

    void writeAsUtf8Json(OutputStream outputStream, JsonFeedJsonConverter jsonFeedJsonConverter);

    void writeAsJson(Writer writer, JsonFeedJsonConverter jsonFeedJsonConverter);

    String toJson(JsonFeedJsonConverter jsonFeedJsonConverter);

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

    Boolean isExpired();

    List<JsonFeedHub> getHubs();

    List<JsonFeedAuthor> getAuthors();

    List<JsonFeedItem> getItems();
}
