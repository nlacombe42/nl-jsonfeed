package net.nlacombe.jsonfeed.impl.mapper;

import net.nlacombe.jsonfeed.api.JsonFeedHub;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;
import net.nlacombe.jsonfeed.impl.DefaultJsonFeedHub;
import net.nlacombe.jsonfeed.impl.dto.JsonFeedHubDto;

import java.net.MalformedURLException;
import java.net.URI;

public class JsonFeedHubMapper extends AbstractBeanMapper<JsonFeedHubDto, JsonFeedHub> {

    @Override
    public JsonFeedHubDto mapToDto(JsonFeedHub jsonFeedHub) {
        var jsonFeedHubDto = new JsonFeedHubDto();
        jsonFeedHubDto.setType(jsonFeedHub.getType());
        jsonFeedHubDto.setUrl(jsonFeedHub.getUrl() == null ? null : jsonFeedHub.getUrl().toString());

        return jsonFeedHubDto;
    }

    @Override
    public JsonFeedHub mapToDomainObject(JsonFeedHubDto jsonFeedHubDto) {
        try {
            var type = jsonFeedHubDto.getType();
            var url = URI.create(jsonFeedHubDto.getUrl()).toURL();

            return DefaultJsonFeedHub.newDefaultJsonFeedHub(type, url);
        } catch (MalformedURLException e) {
            throw new JsonFeedException("Error deserializing: " + e.getMessage(), e);
        }
    }
}
