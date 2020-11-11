package net.nlacombe.jsonfeedlib.test;

import net.nlacombe.jsonfeedlib.api.JsonFeed;
import net.nlacombe.jsonfeedlib.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicIntegrationTest {

    @Test
    public void serialize_json_feed_with_only_version_title_and_no_items_to_json_text() {
        var expectedJsonText = """
            {"version":"https://jsonfeed.org/version/1.1","title":"My Example Feed","items":[]}\
            """;
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "My Example Feed").build();
        var json = jsonFeed.toJson(new JacksonJsonFeedJsonConverter());

        assertThat(json).isEqualTo(expectedJsonText);
    }
}
