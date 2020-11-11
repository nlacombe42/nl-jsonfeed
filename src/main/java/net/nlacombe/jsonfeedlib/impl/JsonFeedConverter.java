package net.nlacombe.jsonfeedlib.impl;

import net.nlacombe.jsonfeedlib.api.JsonFeed;
import net.nlacombe.jsonfeedlib.api.JsonFeedJsonConverter;
import net.nlacombe.jsonfeedlib.impl.dto.JsonFeedDto;
import net.nlacombe.jsonfeedlib.impl.mapper.JsonFeedMapper;

import java.io.Writer;

public class JsonFeedConverter {

    public void writeJsonFeed(JsonFeed jsonFeed, Writer writer, JsonFeedJsonConverter jsonFeedJsonConverter) {
        var jsonFeedDto = new JsonFeedMapper().mapToDto(jsonFeed);

        jsonFeedJsonConverter.writeBeanToJson(jsonFeedDto, JsonFeedDto.class, writer);
    }
}
