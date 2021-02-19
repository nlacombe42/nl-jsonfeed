package net.nlacombe.jsonfeed.api;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;

/**
 * A <a href="https://jsonfeed.org/version/1.1">json feed<a/> item.
 * (from <a href="https://github.com/nlacombe42/nl-jsonfeed">https://github.com/nlacombe42/nl-jsonfeed</a></a>)
 *
 * <p>A text and/or html feed item such as blog posts or social media posts.</p>
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
public interface JsonFeedItem {

    static JsonFeedItemBuilder builderFromTextContent(String id, String contentText) {
        return JsonFeedItemBuilder.fromTextContent(id, contentText);
    }

    static JsonFeedItemBuilder builderFromHtmlContent(String id, String contentHtml) {
        return JsonFeedItemBuilder.fromHtmlContent(id, contentHtml);
    }

    static JsonFeedItemBuilder builderFromTextAndHtmlContent(String id, String contentText, String contentHtml) {
        return JsonFeedItemBuilder.builderFromTextAndHtmlContent(id, contentText, contentHtml);
    }

    String getId();

    URL getUrl();

    URL getExternalUrl();

    String getTitle();

    String getContentText();

    String getContentHtml();

    String getSummary();

    URL getImage();

    URL getBannerImage();

    OffsetDateTime getDatePublished();

    OffsetDateTime getDateModified();

    Locale getLanguage();

    List<JsonFeedAttachment> getAttachments();

    List<JsonFeedAuthor> getAuthors();

    List<String> getTags();
}
