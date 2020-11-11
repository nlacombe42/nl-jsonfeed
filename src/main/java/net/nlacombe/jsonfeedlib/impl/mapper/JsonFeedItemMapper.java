package net.nlacombe.jsonfeedlib.impl.mapper;

import net.nlacombe.jsonfeedlib.api.JsonFeedItem;
import net.nlacombe.jsonfeedlib.impl.dto.JsonFeedItemDto;

public class JsonFeedItemMapper extends AbstractBeanMapper<JsonFeedItemDto, JsonFeedItem> {
    @Override
    public JsonFeedItemDto mapToDto(JsonFeedItem jsonFeedItem) {
        var jsonFeedItemDto = new JsonFeedItemDto();
        //TODO

        return jsonFeedItemDto;
    }

    @Override
    public JsonFeedItem mapToDomainObject(JsonFeedItemDto jsonFeedItemDto) {
        return null; //TODO
    }
}
