package net.nlacombe.jsonfeedlib.api;

import net.nlacombe.jsonfeedlib.impl.JsonFeedItemDto;

import java.util.List;

public class JsonFeedBuilder {

    private List<JsonFeedItemDto> items;

    public JsonFeedBuilder(String version, String title) {
        items = List.of();
    }

    public JsonFeed build() {
        return null;
    }
}
