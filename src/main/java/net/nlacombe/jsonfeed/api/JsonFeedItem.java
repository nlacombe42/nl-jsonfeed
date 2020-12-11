package net.nlacombe.jsonfeed.api;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;

public interface JsonFeedItem {

    static JsonFeedItemBuilder builderFromTextContent(String id, String contentText) {
        return JsonFeedItemBuilder.fromTextContent(id, contentText);
    }

    static JsonFeedItemBuilder builderFromHtmlContent(String id, String contentHtml) {
        return JsonFeedItemBuilder.fromHtmlContent(id, contentHtml);
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
