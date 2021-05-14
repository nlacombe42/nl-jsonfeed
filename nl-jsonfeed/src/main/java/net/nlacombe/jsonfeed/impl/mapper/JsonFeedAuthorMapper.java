package net.nlacombe.jsonfeed.impl.mapper;

import net.nlacombe.jsonfeed.api.JsonFeedAuthor;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;
import net.nlacombe.jsonfeed.impl.DefaultJsonFeedAuthor;
import net.nlacombe.jsonfeed.impl.dto.JsonFeedAuthorDto;

import java.net.MalformedURLException;
import java.net.URI;

public class JsonFeedAuthorMapper extends AbstractBeanMapper<JsonFeedAuthorDto, JsonFeedAuthor> {

    @Override
    public JsonFeedAuthorDto mapToDto(JsonFeedAuthor jsonFeedAuthor) {
        var jsonFeedAuthorDto = new JsonFeedAuthorDto();
        jsonFeedAuthorDto.setName(jsonFeedAuthor.getName());
        jsonFeedAuthorDto.setUrl(jsonFeedAuthor.getUrl() == null ? null : jsonFeedAuthor.getUrl().toString());
        jsonFeedAuthorDto.setAvatar(jsonFeedAuthor.getAvatar() == null ? null : jsonFeedAuthor.getAvatar().toString());

        return jsonFeedAuthorDto;
    }

    @Override
    public JsonFeedAuthor mapToDomainObject(JsonFeedAuthorDto jsonFeedAuthorDto) {
        try {
            var name = jsonFeedAuthorDto.getName();
            var url = URI.create(jsonFeedAuthorDto.getUrl()).toURL();
            var avatar = URI.create(jsonFeedAuthorDto.getAvatar()).toURL();

            return DefaultJsonFeedAuthor.newDefaultJsonFeedAuthor(name, url, avatar);
        } catch (MalformedURLException e) {
            throw new JsonFeedException("Error deserializing: " + e.getMessage(), e);
        }
    }
}
