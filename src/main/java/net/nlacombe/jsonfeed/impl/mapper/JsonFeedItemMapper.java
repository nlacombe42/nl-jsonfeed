package net.nlacombe.jsonfeed.impl.mapper;

import net.nlacombe.jsonfeed.api.JsonFeedItem;
import net.nlacombe.jsonfeed.impl.dto.JsonFeedItemDto;

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
