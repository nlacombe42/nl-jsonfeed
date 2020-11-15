package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicIntegrationTest {

    @Test
    public void serialize_json_feed_with_only_version_title_and_no_items_to_json_text() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"My Example Feed\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "My Example Feed").build();
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);
    }

    @Test
    public void serialize_and_deserialize_json_feed_with_only_version_title_and_no_items_to_json_text() {
        var givenJsonFeedVersion = JsonFeedVersion.VERSION_1_1;
        var givenTitle = "My Example Feed";
        var json = JsonFeed.builder(givenJsonFeedVersion, givenTitle).build().toJson();
        var jsonFeed = JsonFeed.from(json);

        assertThat(jsonFeed).isNotNull();
        assertThat(jsonFeed.getVersion()).isNotNull();
        assertThat(jsonFeed.getVersion().getVersionUri()).isEqualTo(givenJsonFeedVersion.getVersionUri());
        assertThat(jsonFeed.getVersion().getVersionText()).isEqualTo(givenJsonFeedVersion.getVersionText());
        assertThat(jsonFeed.getTitle()).isEqualTo(givenTitle);
    }
}
