package net.nlacombe.jsonfeed.impl.dto;

import java.util.List;

public class JsonFeedItemDto {

    private String id;
    private String url;
    private String external_url;
    private String title;
    private String content_text;
    private String content_html;
    private String summary;
    private String image;
    private String banner_image;
    private String date_published;
    private String date_modified;
    private String language;
    private List<JsonFeedAttachmentDto> attachments;
    private List<JsonFeedAuthorDto> authors;
    private List<String> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_text() {
        return content_text;
    }

    public void setContent_text(String content_text) {
        this.content_text = content_text;
    }

    public String getContent_html() {
        return content_html;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<JsonFeedAttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<JsonFeedAttachmentDto> attachments) {
        this.attachments = attachments;
    }

    public List<JsonFeedAuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<JsonFeedAuthorDto> authors) {
        this.authors = authors;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
