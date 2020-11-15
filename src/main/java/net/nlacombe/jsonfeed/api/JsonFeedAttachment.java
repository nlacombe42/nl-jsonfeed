package net.nlacombe.jsonfeed.api;

import java.net.URL;

public interface JsonFeedAttachment {

    URL getUrl();

    String getMimeType();

    String getTitle();

    Long getSizeInBytes();

    Long getDurationInSeconds();

}
