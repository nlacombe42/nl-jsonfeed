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
import java.util.Locale;

/**
 * A <a href="https://jsonfeed.org/version/1.1">json feed<a/>.
 * (from <a href="https://github.com/nlacombe42/nl-jsonfeed">https://github.com/nlacombe42/nl-jsonfeed</a></a>)
 *
 * <p>A feed of multiple text and/or html items such as blog posts or social media posts.</p>
 *
 * <h3>Example on how to create a feed</h3>
 * <p>
 *     <pre>
 *         var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("https://example.net/my-first-post", "&lt;p&gt;Welcome to my first post!&lt;/p&gt;")
 *             .url("https://example.net/my-first-post")
 *             .build();
 *         var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "Joe's finance blog")
 *             .homePageUrl("https://example.net/")
 *             .items(jsonFeedItem)
 *             .build();
 *         var json = jsonFeed.toJson();
 *     </pre>
 * </p>
 *
 * <h3>Example on how to read a feed</h3>
 *  <p>
 *      <pre>
 *          var jsonFeedUri = URI.create("https://jsonfeed.org/feed.json");
 *          var httpClient = HttpClient.newHttpClient();
 *          var response = httpClient.send(HttpRequest.newBuilder(jsonFeedUri).build(), HttpResponse.BodyHandlers.ofInputStream());
 *          var responseBodyInputStream = response.body();
 *
 *          var jsonFeed = JsonFeed.read(responseBodyInputStream, StandardCharsets.UTF_8);
 *          var jsonFeedTitle = jsonFeed.getTitle();
 *
 *          var jsonFeedItem = jsonFeed.getItems().get(0);
 *          var itemId = jsonFeedItem.getId();
 *          var itemContent = jsonFeedItem.getContentHtml();
 *      </pre>
 *  </p>
 */
public interface JsonFeed {

    static JsonFeedBuilder builder(JsonFeedVersion version, String title) {
        return JsonFeedBuilder.from(version, title);
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

    static JsonFeed fromJson(String json)  {
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

    Locale getLanguage();

    Boolean isExpired();

    List<JsonFeedHub> getHubs();

    List<JsonFeedAuthor> getAuthors();

    List<JsonFeedItem> getItems();
}
