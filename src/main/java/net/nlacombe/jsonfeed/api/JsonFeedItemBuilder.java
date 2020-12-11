package net.nlacombe.jsonfeed.api;

import net.nlacombe.jsonfeed.impl.DefaultJsonFeedItem;
import net.nlacombe.jsonfeed.impl.util.UrlUtil;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;

public class JsonFeedItemBuilder {

    private final DefaultJsonFeedItem defaultJsonFeedItem;

    private JsonFeedItemBuilder(String id, String contentText, String contentHtml) {
        this.defaultJsonFeedItem = DefaultJsonFeedItem.newDefaultJsonFeedItem(id, contentText, contentHtml);
    }

    public static JsonFeedItemBuilder fromTextContent(String id, String contentText) {
        return new JsonFeedItemBuilder(id, contentText, null);
    }

    public static JsonFeedItemBuilder fromHtmlContent(String id, String contentHtml) {
        return new JsonFeedItemBuilder(id, null, contentHtml);
    }

    public static JsonFeedItemBuilder builderFromTextAndHtmlContent(String id, String contentText, String contentHtml) {
        return new JsonFeedItemBuilder(id, contentText, contentHtml);
    }

    public JsonFeedItemBuilder url(URL url) {
        defaultJsonFeedItem.setUrl(url);

        return this;
    }

    public JsonFeedItemBuilder url(String url) {
        defaultJsonFeedItem.setUrl(UrlUtil.toUrl(url));

        return this;
    }

    public JsonFeedItemBuilder externalUrl(URL externalUrl) {
        defaultJsonFeedItem.setExternalUrl(externalUrl);

        return this;
    }

    public JsonFeedItemBuilder externalUrl(String externalUrl) {
        defaultJsonFeedItem.setExternalUrl(UrlUtil.toUrl(externalUrl));

        return this;
    }

    public JsonFeedItemBuilder title(String title) {
        defaultJsonFeedItem.setTitle(title);

        return this;
    }

    public JsonFeedItemBuilder summary(String summary) {
        defaultJsonFeedItem.setSummary(summary);

        return this;
    }

    public JsonFeedItemBuilder image(URL image) {
        defaultJsonFeedItem.setImage(image);

        return this;
    }

    public JsonFeedItemBuilder image(String image) {
        defaultJsonFeedItem.setImage(UrlUtil.toUrl(image));

        return this;
    }

    public JsonFeedItemBuilder bannerImage(URL bannerImage) {
        defaultJsonFeedItem.setBannerImage(bannerImage);

        return this;
    }

    public JsonFeedItemBuilder bannerImage(String bannerImage) {
        defaultJsonFeedItem.setBannerImage(UrlUtil.toUrl(bannerImage));

        return this;
    }

    public JsonFeedItemBuilder datePublished(OffsetDateTime datePublished) {
        defaultJsonFeedItem.setDatePublished(datePublished);

        return this;
    }

    public JsonFeedItemBuilder dateModified(OffsetDateTime dateModified) {
        defaultJsonFeedItem.setDateModified(dateModified);

        return this;
    }

    public JsonFeedItemBuilder language(Locale language) {
        defaultJsonFeedItem.setLanguage(language);

        return this;
    }

    public JsonFeedItemBuilder language(String language) {
        return language(Locale.forLanguageTag(language));
    }

    public JsonFeedItemBuilder attachments(List<JsonFeedAttachment> attachments) {
        if (attachments == null || attachments.isEmpty())
            return this;

        defaultJsonFeedItem.setAttachments(attachments);

        return this;
    }

    public JsonFeedItemBuilder attachments(JsonFeedAttachment... attachments) {
        if (attachments == null || attachments.length == 0)
            return this;

        defaultJsonFeedItem.setAttachments(List.of(attachments));

        return this;
    }

    public JsonFeedItemBuilder authors(List<JsonFeedAuthor> authors) {
        if (authors == null || authors.isEmpty())
            return this;

        defaultJsonFeedItem.setAuthors(authors);

        return this;
    }

    public JsonFeedItemBuilder authors(JsonFeedAuthor... authors) {
        if (authors == null || authors.length == 0)
            return this;

        defaultJsonFeedItem.setAuthors(List.of(authors));

        return this;
    }

    public JsonFeedItemBuilder tags(List<String> tags) {
        if (tags == null || tags.isEmpty())
            return this;

        defaultJsonFeedItem.setTags(tags);

        return this;
    }

    public JsonFeedItemBuilder tags(String... tags) {
        if (tags == null || tags.length == 0)
            return this;

        defaultJsonFeedItem.setTags(List.of(tags));

        return this;
    }

    public JsonFeedItem build() {
        return defaultJsonFeedItem;
    }
}
