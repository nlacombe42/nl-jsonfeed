package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedAttachment;
import net.nlacombe.jsonfeed.api.JsonFeedItem;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonFeedAttachmentIntegrationTest {

    @Test
    public void test_json_feed_attachment() throws MalformedURLException {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_text\":\"This is an item.\",\"attachments\":[{\"url\":\"http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a\",\"mime_type\":\"audio/x-m4a\",\"size_in_bytes\":89970236,\"duration_in_seconds\":6629}]}]}";
        var attachmentUrl = URI.create("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a").toURL();
        var attachmentBuilder = JsonFeedAttachment.builder(attachmentUrl, "audio/x-m4a")
            .sizeInBytes(89970236)
            .durationInSeconds(6629)
            .build();
        var jsonFeedItem = JsonFeedItem
            .builderFromTextContent("1", "This is an item.")
            .attachments(attachmentBuilder)
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var deserializedJsonFeed = JsonFeed.fromJson(json);

        assertThat(deserializedJsonFeed.getItems()).hasSize(1);

        var deserializedJsonFeedItem = deserializedJsonFeed.getItems().get(0);
        assertThat(deserializedJsonFeedItem).isNotNull();
        assertThat(deserializedJsonFeedItem.getAttachments()).hasSize(1);

        var deserializedJsonFeedAttachment = deserializedJsonFeedItem.getAttachments().get(0);
        assertThat(deserializedJsonFeedAttachment).isNotNull();
        assertThat(deserializedJsonFeedAttachment.getUrl()).isNotNull();
        assertThat(deserializedJsonFeedAttachment.getUrl().toString()).isEqualTo("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a");
        assertThat(deserializedJsonFeedAttachment.getMimeType()).isEqualTo("audio/x-m4a");
        assertThat(deserializedJsonFeedAttachment.getSizeInBytes()).isEqualTo(89970236);
        assertThat(deserializedJsonFeedAttachment.getDurationInSeconds()).isEqualTo(6629);
    }
}
