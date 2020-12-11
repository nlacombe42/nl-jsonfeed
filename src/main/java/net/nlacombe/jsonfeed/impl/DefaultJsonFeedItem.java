package net.nlacombe.jsonfeed.impl;

import net.nlacombe.jsonfeed.api.JsonFeedAttachment;
import net.nlacombe.jsonfeed.api.JsonFeedAuthor;
import net.nlacombe.jsonfeed.api.JsonFeedItem;
import net.nlacombe.jsonfeed.impl.util.StringUtil;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;

public class DefaultJsonFeedItem implements JsonFeedItem {

    private String id;
    private URL url;
    private URL externalUrl;
    private String title;
    private String contentText;
    private String contentHtml;
    private String summary;
    private URL image;
    private URL bannerImage;
    private OffsetDateTime datePublished;
    private OffsetDateTime dateModified;
    private Locale language;
    private List<JsonFeedAttachment> attachments;
    private List<JsonFeedAuthor> authors;
    private List<String> tags;

    private DefaultJsonFeedItem(String id, String contentText, String contentHtml) {
        this.id = StringUtil.requireNotBlank(id, "you must specify the id (and either contentText or contentHtml)");
        this.contentText = contentText;
        this.contentHtml = contentHtml;

        if (StringUtil.isBlank(contentText) && StringUtil.isBlank(contentHtml))
            throw new IllegalArgumentException("you must specify either contentText or contentHtml");
    }

    public static DefaultJsonFeedItem newDefaultJsonFeedItem(String id, String contentText, String contentHtml) {
        return new DefaultJsonFeedItem(id, contentText, contentHtml);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public URL getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(URL externalUrl) {
        this.externalUrl = externalUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    @Override
    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    @Override
    public URL getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(URL bannerImage) {
        this.bannerImage = bannerImage;
    }

    @Override
    public OffsetDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(OffsetDateTime datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public OffsetDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(OffsetDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    @Override
    public List<JsonFeedAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<JsonFeedAttachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public List<JsonFeedAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<JsonFeedAuthor> authors) {
        this.authors = authors;
    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
