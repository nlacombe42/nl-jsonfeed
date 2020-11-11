package net.nlacombe.jsonfeedlib.api;

import java.net.URL;

public interface JsonFeedAttachment {

    URL getUrl();

    String getMimeType();

    String getTitle();

    long getSizeInBytes();

    long getDurationInSeconds();

}
