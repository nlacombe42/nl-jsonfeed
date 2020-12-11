package net.nlacombe.jsonfeed.api;

import java.net.URL;

public interface JsonFeedAttachment {

    static JsonFeedAttachmentBuilder builder(URL url, String mimeType) {
        return JsonFeedAttachmentBuilder.from(url, mimeType);
    }

    URL getUrl();

    String getMimeType();

    String getTitle();

    Long getSizeInBytes();

    Long getDurationInSeconds();

}
