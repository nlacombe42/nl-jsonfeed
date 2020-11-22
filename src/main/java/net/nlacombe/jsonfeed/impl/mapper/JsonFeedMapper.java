package net.nlacombe.jsonfeed.impl.mapper;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;
import net.nlacombe.jsonfeed.impl.DefaultJsonFeed;
import net.nlacombe.jsonfeed.impl.dto.JsonFeedDto;

import java.net.MalformedURLException;
import java.net.URI;

public class JsonFeedMapper extends AbstractBeanMapper<JsonFeedDto, JsonFeed> {

    private final JsonFeedItemMapper jsonFeedItemMapper;

    public JsonFeedMapper() {
        this.jsonFeedItemMapper = new JsonFeedItemMapper();
    }

    @Override
    public JsonFeedDto mapToDto(JsonFeed jsonFeed) {
        var jsonFeedDto = new JsonFeedDto();
        jsonFeedDto.setVersion(jsonFeed.getVersion().getVersionUri());
        jsonFeedDto.setTitle(jsonFeed.getTitle());
        jsonFeedDto.setItems(jsonFeedItemMapper.mapToDtos(jsonFeed.getItems()));
        jsonFeedDto.setHome_page_url(jsonFeed.getHomePageUrl() == null ? null : jsonFeed.getHomePageUrl().toString());
        jsonFeedDto.setFeed_url(jsonFeed.getFeedUrl() == null ? null : jsonFeed.getFeedUrl().toString());
        jsonFeedDto.setDescription(jsonFeed.getDescription());
        jsonFeedDto.setUser_comment(jsonFeed.getUserComment());
        jsonFeedDto.setNext_url(jsonFeed.getNextUrl() == null ? null : jsonFeed.getNextUrl().toString());
        jsonFeedDto.setIcon(jsonFeed.getIcon() == null ? null : jsonFeed.getIcon().toString());
        jsonFeedDto.setFavicon(jsonFeed.getFavicon() == null ? null : jsonFeed.getFavicon().toString());
        jsonFeedDto.setLanguage(jsonFeed.getLanguage());
        jsonFeedDto.setExpired(jsonFeed.isExpired());

        return jsonFeedDto;
    }

    @Override
    public JsonFeed mapToDomainObject(JsonFeedDto jsonFeedDto) {
        try {
            var jsonFeed = DefaultJsonFeed.newEmpty();
            jsonFeed.setVersion(JsonFeedVersion.parse(jsonFeedDto.getVersion()));
            jsonFeed.setTitle(jsonFeedDto.getTitle());
            jsonFeed.setItems(jsonFeedItemMapper.mapToDomainObjects(jsonFeedDto.getItems()));
            jsonFeed.setHomePageUrl(jsonFeedDto.getHome_page_url() == null ? null : URI.create(jsonFeedDto.getHome_page_url()).toURL());
            jsonFeed.setFeedUrl(jsonFeedDto.getFeed_url() == null ? null : URI.create(jsonFeedDto.getFeed_url()).toURL());
            jsonFeed.setDescription(jsonFeedDto.getDescription());
            jsonFeed.setUserComment(jsonFeedDto.getUser_comment());
            jsonFeed.setNextUrl(jsonFeedDto.getNext_url() == null ? null : URI.create(jsonFeedDto.getNext_url()).toURL());
            jsonFeed.setIcon(jsonFeedDto.getIcon() == null ? null : URI.create(jsonFeedDto.getIcon()).toURL());
            jsonFeed.setFavicon(jsonFeedDto.getFavicon() == null ? null : URI.create(jsonFeedDto.getFavicon()).toURL());
            jsonFeed.setLanguage(jsonFeedDto.getLanguage());
            jsonFeed.setExpired(jsonFeedDto.getExpired());

            return jsonFeed;
        } catch (MalformedURLException e) {
            throw new JsonFeedException("Error deserializing: " + e.getMessage(), e);
        }
    }
}
