package net.nlacombe.jsonfeed.impl.dto;

import java.util.List;

public class JsonFeedDto {

    private String version;
    private String title;
    private String home_page_url;
    private String feed_url;
    private String description;
    private String user_comment;
    private String next_url;
    private String icon;
    private String favicon;
    private String language;
    private Boolean expired;
    private List<JsonFeedHubDto> hubs;
    private List<JsonFeedAuthorDto> authors;
    private JsonFeedAuthorDto author;
    private List<JsonFeedItemDto> items;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHome_page_url() {
        return home_page_url;
    }

    public void setHome_page_url(String home_page_url) {
        this.home_page_url = home_page_url;
    }

    public String getFeed_url() {
        return feed_url;
    }

    public void setFeed_url(String feed_url) {
        this.feed_url = feed_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public List<JsonFeedHubDto> getHubs() {
        return hubs;
    }

    public void setHubs(List<JsonFeedHubDto> hubs) {
        this.hubs = hubs;
    }

    public List<JsonFeedAuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<JsonFeedAuthorDto> authors) {
        this.authors = authors;
    }

    public List<JsonFeedItemDto> getItems() {
        return items;
    }

    public void setItems(List<JsonFeedItemDto> items) {
        this.items = items;
    }

    public JsonFeedAuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(JsonFeedAuthorDto author) {
        this.author = author;
    }
}
