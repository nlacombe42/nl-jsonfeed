package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonFeedExtensionsIntegrationTest {

    @Test
    public void does_not_throw_error_and_can_read_minimal_json_feed_when_extensions_present() {
        var json = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"_blue_shed\":{\"about\":\"https://blueshed-podcasts.com/json-feed-extension-docs\",\"explicit\":false,\"copyright\":\"1948 by George Orwell\",\"owner\":\"Big Brother and the Holding Company\",\"subtitle\":\"All shouting, all the time. Double. Plus. Good.\"},\"title\":\"My Example Feed\",\"items\":[]}";
        var jsonFeed = JsonFeed.fromJson(json);

        assertThat(jsonFeed).isNotNull();
        assertThat(jsonFeed.getVersion()).isEqualTo(JsonFeedVersion.VERSION_1_1);
        assertThat(jsonFeed.getTitle()).isEqualTo("My Example Feed");
        assertThat(jsonFeed.getItems()).isEmpty();
    }

}
