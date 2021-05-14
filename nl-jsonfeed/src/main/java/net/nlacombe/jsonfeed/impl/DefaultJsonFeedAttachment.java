package net.nlacombe.jsonfeed.impl;

import net.nlacombe.jsonfeed.api.JsonFeedAttachment;
import net.nlacombe.jsonfeed.impl.util.ObjectUtil;
import net.nlacombe.jsonfeed.impl.util.StringUtil;

import java.net.URL;

public class DefaultJsonFeedAttachment implements JsonFeedAttachment {

    private URL url;
    private String mimeType;
    private String title;
    private Long sizeInBytes;
    private Long durationInSeconds;

    private DefaultJsonFeedAttachment(URL url, String mimeType) {
        this.url = ObjectUtil.requireNonNull(url, "you must specify a value for url (and mimeType)");
        this.mimeType = StringUtil.requireNotBlank(mimeType, "you must specify a value for mimeType (and url)");
    }

    public static DefaultJsonFeedAttachment from(URL url, String mimeType) {
        return new DefaultJsonFeedAttachment(url, mimeType);
    }

    @Override
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    @Override
    public Long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}
