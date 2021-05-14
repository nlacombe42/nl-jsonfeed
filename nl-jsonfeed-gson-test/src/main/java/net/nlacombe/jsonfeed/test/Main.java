package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;

public class Main {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"My Example Feed\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "My Example Feed").build();
        var json = jsonFeed.toJson();

        logger.info("jsonFeed: " + jsonFeed);
        logger.info("json: " + json);
        logger.info("expectedJsonText.equals(json): " + expectedJsonText.equals(json));
    }
}
