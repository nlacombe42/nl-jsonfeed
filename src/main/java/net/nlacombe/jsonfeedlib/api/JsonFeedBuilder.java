package net.nlacombe.jsonfeedlib.api;

import net.nlacombe.jsonfeedlib.impl.DefaultJsonFeed;

import java.util.LinkedList;
import java.util.Objects;

public class JsonFeedBuilder {

    private final DefaultJsonFeed jsonFeed;

    public JsonFeedBuilder(JsonFeedVersion version, String title) {
        jsonFeed = DefaultJsonFeed.newEmpty();
        jsonFeed.setVersion(Objects.requireNonNull(version, "version must not be null"));
        jsonFeed.setTitle(Objects.requireNonNull(title, "title must not be null"));
        jsonFeed.setItems(new LinkedList<>());
    }

    public JsonFeed build() {
        return jsonFeed;
    }
}
