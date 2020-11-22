package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyIntegrationTest {

    @Test
    public void test_json_feed_home_page_url() throws MalformedURLException {
        var homePageUri = URI.create("http://therecord.co").toURL();
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"The Record\",\"home_page_url\":\"http://therecord.co\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "The Record")
            .homePageUrl(homePageUri)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getHomePageUrl()).isEqualTo(homePageUri));
    }

    private void testPropertySerializationAndDeserialization(String expectedJsonText, JsonFeed jsonFeed, Consumer<JsonFeed> testDeserializedProperty) {
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var deserializedJsonFeed = JsonFeed.from(json);
        testDeserializedProperty.accept(deserializedJsonFeed);
    }
}
