package net.nlacombe.jsonfeedlib.impl.dto;

public class JsonFeedAttachmentDto {

    private String url;
    private String mime_type;
    private String title;
    private long size_in_bytes;
    private long duration_in_seconds;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSize_in_bytes() {
        return size_in_bytes;
    }

    public void setSize_in_bytes(long size_in_bytes) {
        this.size_in_bytes = size_in_bytes;
    }

    public long getDuration_in_seconds() {
        return duration_in_seconds;
    }

    public void setDuration_in_seconds(long duration_in_seconds) {
        this.duration_in_seconds = duration_in_seconds;
    }
}
