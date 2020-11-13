package net.nlacombe.jsonfeedlib.impl.mapper;

import net.nlacombe.jsonfeedlib.api.JsonFeed;
import net.nlacombe.jsonfeedlib.api.JsonFeedVersion;
import net.nlacombe.jsonfeedlib.impl.DefaultJsonFeed;
import net.nlacombe.jsonfeedlib.impl.dto.JsonFeedDto;

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

        return jsonFeedDto;
    }

    @Override
    public JsonFeed mapToDomainObject(JsonFeedDto jsonFeedDto) {
        var jsonFeed = DefaultJsonFeed.newEmpty();
        jsonFeed.setVersion(JsonFeedVersion.parse(jsonFeedDto.getVersion()));
        jsonFeed.setTitle(jsonFeedDto.getTitle());
        jsonFeed.setItems(jsonFeedItemMapper.mapToDomainObjects(jsonFeedDto.getItems()));

        return jsonFeed;
    }
}
