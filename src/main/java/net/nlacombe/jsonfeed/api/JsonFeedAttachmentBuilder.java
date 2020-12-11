package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.impl.DefaultJsonFeedAttachment;

import java.net.URL;

public class JsonFeedAttachmentBuilder {

    private final DefaultJsonFeedAttachment jsonFeedAttachment;

    public JsonFeedAttachmentBuilder(URL url, String mimeType) {
        this.jsonFeedAttachment = DefaultJsonFeedAttachment.from(url, mimeType);
    }

    public static JsonFeedAttachmentBuilder from(URL url, String mimeType) {
        return new JsonFeedAttachmentBuilder(url, mimeType);
    }

    public JsonFeedAttachmentBuilder title(String title) {
        jsonFeedAttachment.setTitle(title);

        return this;
    }

    public JsonFeedAttachmentBuilder sizeInBytes(long sizeInBytes) {
        jsonFeedAttachment.setSizeInBytes(sizeInBytes);

        return this;
    }

    public JsonFeedAttachmentBuilder durationInSeconds(long durationInSeconds) {
        jsonFeedAttachment.setDurationInSeconds(durationInSeconds);

        return this;
    }

    public JsonFeedAttachment build() {
        return jsonFeedAttachment;
    }
}
