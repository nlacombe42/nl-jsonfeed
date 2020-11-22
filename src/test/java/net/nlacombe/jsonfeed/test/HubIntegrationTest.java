package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedHub;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class HubIntegrationTest {

    @Test
    public void test_json_feed_hubs() throws MalformedURLException {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"hubs\":[{\"type\":\"WebSub\",\"url\":\"https://example.com/jsonFeedHub\"}],\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .hubs(JsonFeedHub.from("WebSub", URI.create("https://example.com/jsonFeedHub").toURL()))
            .build();

        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var deserializedJsonFeed = JsonFeed.from(json);

        assertThat(deserializedJsonFeed.getHubs()).hasSize(1);

        var deserializedJsonFeedHub = deserializedJsonFeed.getHubs().get(0);
        assertThat(deserializedJsonFeedHub).isNotNull();
        assertThat(deserializedJsonFeedHub.getType()).isEqualTo("WebSub");
        assertThat(deserializedJsonFeedHub.getUrl()).isNotNull();
        assertThat(deserializedJsonFeedHub.getUrl().toString()).isEqualTo("https://example.com/jsonFeedHub");
    }
}
