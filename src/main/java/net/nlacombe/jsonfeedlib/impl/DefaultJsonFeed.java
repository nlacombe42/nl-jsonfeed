package net.nlacombe.jsonfeedlib.impl;

import net.nlacombe.jsonfeedlib.api.JsonFeed;
import net.nlacombe.jsonfeedlib.api.JsonFeedAuthor;
import net.nlacombe.jsonfeedlib.api.JsonFeedHub;
import net.nlacombe.jsonfeedlib.api.JsonFeedItem;
import net.nlacombe.jsonfeedlib.api.JsonFeedJsonConverter;
import net.nlacombe.jsonfeedlib.api.JsonFeedVersion;
import net.nlacombe.jsonfeedlib.api.exception.JsonFeedException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DefaultJsonFeed implements JsonFeed {

    private static final Charset DEFAULT_SERIALIZATION_CHARSET = StandardCharsets.UTF_8;

    private JsonFeedVersion version;
    private String title;
    private URL homePageUrl;
    private URL feedUrl;
    private String description;
    private String userComment;
    private URL nextUrl;
    private URL icon;
    private URL favicon;
    private String language;
    private Boolean expired;
    private List<JsonFeedHub> hubs;
    private List<JsonFeedAuthor> authors;
    private List<JsonFeedItem> items;

    private DefaultJsonFeed() {
    }

    public static DefaultJsonFeed newEmpty() {
        return new DefaultJsonFeed();
    }

    @Override
    public void writeAsUtf8Json(OutputStream outputStream) {
        try (var writer = new OutputStreamWriter(outputStream, DEFAULT_SERIALIZATION_CHARSET)) {
            writeAsJson(writer);
        } catch (IOException exception) {
            throw new JsonFeedException("Error serializing/writing json feed to json: " + exception.getMessage(), exception);
        }
    }

    @Override
    public void writeAsJson(Writer writer) {
        JsonFeedConverter.getInstance().writeJsonFeed(this, writer);
    }

    @Override
    public String toJson() {
        var writer = new StringWriter();

        writeAsJson(writer);

        return writer.toString();
    }

    @Override
    public JsonFeedVersion getVersion() {
        return version;
    }

    public void setVersion(JsonFeedVersion version) {
        this.version = version;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public URL getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(URL homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    @Override
    public URL getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(URL feedUrl) {
        this.feedUrl = feedUrl;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    @Override
    public URL getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(URL nextUrl) {
        this.nextUrl = nextUrl;
    }

    @Override
    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    @Override
    public URL getFavicon() {
        return favicon;
    }

    public void setFavicon(URL favicon) {
        this.favicon = favicon;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public Boolean isExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @Override
    public List<JsonFeedHub> getHubs() {
        return hubs;
    }

    public void setHubs(List<JsonFeedHub> hubs) {
        this.hubs = hubs;
    }

    @Override
    public List<JsonFeedAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<JsonFeedAuthor> authors) {
        this.authors = authors;
    }

    @Override
    public List<JsonFeedItem> getItems() {
        return items;
    }

    public void setItems(List<JsonFeedItem> items) {
        this.items = items;
    }
}
