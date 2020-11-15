package net.nlacombe.jsonfeed.api;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;

public interface JsonFeedItem {

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

    String getLanguage();

    List<JsonFeedAttachment> getAttachments();

    List<JsonFeedAuthor> getAuthors();

    List<String> getTags();
}
