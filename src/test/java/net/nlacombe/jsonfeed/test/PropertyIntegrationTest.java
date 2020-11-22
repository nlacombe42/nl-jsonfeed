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

    @Test
    public void test_json_feed_feed_url() throws MalformedURLException {
        var feedUrl = URI.create("http://therecord.co/feed.json").toURL();
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"The Record\",\"feed_url\":\"http://therecord.co/feed.json\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "The Record")
            .feedUrl(feedUrl)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getFeedUrl()).isEqualTo(feedUrl));
    }

    @Test
    public void test_json_feed_description() {
        var description = "this is a test description";
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"description\":\"this is a test description\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .description(description)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getDescription()).isEqualTo(description));
    }

    @Test
    public void test_json_feed_user_comment() {
        var userComment = "This is a podcast feed. You can add this feed to your podcast client using the following URL: http://therecord.co/feed.json";
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"The Record\",\"user_comment\":\"This is a podcast feed. You can add this feed to your podcast client using the following URL: http://therecord.co/feed.json\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "The Record")
            .userComment(userComment)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getUserComment()).isEqualTo(userComment));
    }

    @Test
    public void test_json_feed_next_url() throws MalformedURLException {
        var nextUrl = URI.create("https://example.com/feed?page=2").toURL();
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"next_url\":\"https://example.com/feed?page=2\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .nextUrl(nextUrl)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getNextUrl()).isEqualTo(nextUrl));
    }

    @Test
    public void test_json_feed_icon() throws MalformedURLException {
        var icon = URI.create("https://example.com/feedIcon.png").toURL();
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"icon\":\"https://example.com/feedIcon.png\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .icon(icon)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getIcon()).isEqualTo(icon));
    }

    @Test
    public void test_json_feed_favicon() throws MalformedURLException {
        var favicon = URI.create("https://example.com/favicon.ico").toURL();
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"favicon\":\"https://example.com/favicon.ico\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .favicon(favicon)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getFavicon()).isEqualTo(favicon));
    }

    @Test
    public void test_json_feed_language() {
        var language = "en-US";
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"language\":\"en-US\",\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .language(language)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.getLanguage()).isEqualTo(language));
    }

    @Test
    public void test_json_feed_expired() {
        var expired = false;
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"expired\":false,\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .expired(expired)
            .build();

        testPropertySerializationAndDeserialization(expectedJsonText, jsonFeed,
            jsonDeserializedJsonFeedFeed -> assertThat(jsonDeserializedJsonFeedFeed.isExpired()).isEqualTo(expired));
    }

    private void testPropertySerializationAndDeserialization(String expectedJsonText, JsonFeed jsonFeed, Consumer<JsonFeed> testDeserializedProperty) {
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var deserializedJsonFeed = JsonFeed.from(json);
        testDeserializedProperty.accept(deserializedJsonFeed);
    }
}
