package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.api.exception.JsonFeedException;
import net.nlacombe.jsonfeed.impl.JsonFeedConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public interface JsonFeed {

    static JsonFeedBuilder builder(JsonFeedVersion version, String title) {
        return new JsonFeedBuilder(version, title);
    }

    static JsonFeed read(InputStream inputStream, Charset charset) {
        try (var reader = new InputStreamReader(inputStream, charset)) {
            return JsonFeed.read(reader);
        } catch (IOException exception) {
            throw new JsonFeedException("Error reading json feed from input stream: " + exception.getMessage(), exception);
        }
    }

    static JsonFeed read(Reader reader)  {
        return JsonFeedConverter.getInstance().readJsonFeed(reader);
    }

    static JsonFeed from(String json)  {
        try (var reader = new StringReader(json)) {
            return JsonFeed.read(reader);
        }
    }

    void writeAsUtf8Json(OutputStream outputStream);

    void writeAsJson(Writer writer);

    String toJson();

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
