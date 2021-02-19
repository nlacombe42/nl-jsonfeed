package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedAuthor;
import net.nlacombe.jsonfeed.api.JsonFeedItem;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonFeedItemIntegrationTest {

    @Test
    public void test_json_feed_item_id_and_null_text_content() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedItem.builderFromTextContent("1", null);
        });
    }

    @Test
    public void test_json_feed_item_id_and_null_html_content() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedItem.builderFromHtmlContent("1", null);
        });
    }

    @Test
    public void test_json_feed_item_null_id_and_text_content() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedItem.builderFromTextContent(null, "asd");
        });
    }

    @Test
    public void test_json_feed_item_blank_id_and_text_content() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedItem.builderFromTextContent("  ", "asd");
        });
    }

    @Test
    public void test_json_feed_item_id_and_text_content() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_text\":\"This is an item.\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromTextContent("1", "This is an item.").build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> {
                assertThat(deserializedJsonFeedItem.getId()).isEqualTo("1");
                assertThat(deserializedJsonFeedItem.getContentText()).isEqualTo("This is an item.");
            });
    }

    @Test
    public void test_json_feed_item_id_and_html_content() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>Hello, world!</p>\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>Hello, world!</p>").build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> {
                assertThat(deserializedJsonFeedItem.getId()).isEqualTo("1");
                assertThat(deserializedJsonFeedItem.getContentHtml()).isEqualTo("<p>Hello, world!</p>");
            });
    }

    @Test
    public void test_json_feed_item_url() throws MalformedURLException {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"url\":\"https://example.org/initial-post\",\"content_html\":\"<p>test html content</p>\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .url(URI.create("https://example.org/initial-post").toURL())
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> TestUtil.assertObjectStringEquals(deserializedJsonFeedItem.getUrl(), "https://example.org/initial-post"));
    }

    @Test
    public void test_json_feed_item_external_url() throws MalformedURLException {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"external_url\":\"https://other-website.org/original-post\",\"content_html\":\"<p>test html content</p>\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .externalUrl(URI.create("https://other-website.org/original-post").toURL())
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> TestUtil.assertObjectStringEquals(deserializedJsonFeedItem.getExternalUrl(), "https://other-website.org/original-post"));
    }

    @Test
    public void test_json_feed_item_title() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"title\":\"Special #1 - Chris Parrish\",\"content_html\":\"<p>test html content</p>\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .title("Special #1 - Chris Parrish")
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> assertThat(deserializedJsonFeedItem.getTitle()).isEqualTo("Special #1 - Chris Parrish"));
    }

    @Test
    public void test_json_feed_item_summary() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"summary\":\"Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled.\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .summary("Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled.")
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> assertThat(deserializedJsonFeedItem.getSummary()).isEqualTo("Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled."));
    }

    @Test
    public void test_json_feed_item_image() throws MalformedURLException {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"image\":\"https://example.org/initial-post.png\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .image(URI.create("https://example.org/initial-post.png").toURL())
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> TestUtil.assertObjectStringEquals(deserializedJsonFeedItem.getImage(), "https://example.org/initial-post.png"));
    }

    @Test
    public void test_json_feed_item_banner_image() throws MalformedURLException {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"banner_image\":\"https://example.org/initial-post-banner.png\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .bannerImage(URI.create("https://example.org/initial-post-banner.png").toURL())
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> TestUtil.assertObjectStringEquals(deserializedJsonFeedItem.getBannerImage(), "https://example.org/initial-post-banner.png"));
    }

    @Test
    public void test_json_feed_item_date_published() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"date_published\":\"2010-02-07T14:04-05:00\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .datePublished(OffsetDateTime.parse("2010-02-07T14:04:00-05:00:00"))
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> TestUtil.assertObjectStringEquals(deserializedJsonFeedItem.getDatePublished(), "2010-02-07T14:04-05:00"));
    }

    @Test
    public void test_json_feed_item_read_date_published_with_zero_seconds_without_offset_seconds() {
        var json = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"date_published\":\"2010-02-07T14:04:00-05:00\"}]}";
        var jsonFeed = JsonFeed.fromJson(json);

        assertThat(jsonFeed).isNotNull();
        assertThat(jsonFeed.getItems()).hasSize(1);

        var jsonFeedItem = jsonFeed.getItems().get(0);
        assertThat(jsonFeedItem).isNotNull();
        assertThat(jsonFeedItem.getDatePublished()).isEqualTo(OffsetDateTime.parse("2010-02-07T14:04:00-05:00"));
    }

    @Test
    public void test_json_feed_item_date_modified() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"date_modified\":\"2014-05-09T14:04-07:00\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .dateModified(OffsetDateTime.parse("2014-05-09T14:04:00-07:00"))
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> TestUtil.assertObjectStringEquals(deserializedJsonFeedItem.getDateModified(), "2014-05-09T14:04-07:00"));
    }

    @Test
    public void test_json_feed_item_language() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"language\":\"zh-Hant-TW\"}]}";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .language("zh-Hant-TW")
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> assertThat(deserializedJsonFeedItem.getLanguage().toLanguageTag()).isEqualTo("zh-Hant-TW"));
    }

    @Test
    public void test_json_feed_item_tags() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"tags\":[\"java\",\"art\",\"coffee\"]}]}";
        var tags = List.of("java", "art", "coffee");
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .tags(tags)
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> assertThat(deserializedJsonFeedItem.getTags()).containsExactly(tags.toArray(new String[0])));
    }

    @Test
    public void test_json_feed_item_authors() throws MalformedURLException {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"id\":\"1\",\"content_html\":\"<p>test html content</p>\",\"authors\":[{\"name\":\"Nicolas Lacombe\",\"url\":\"https://nlacombe.net/\",\"avatar\":\"https://nlacombe.net/avatar.png\"}]}]}";
        var url = URI.create("https://nlacombe.net/").toURL();
        var avatar = URI.create("https://nlacombe.net/avatar.png").toURL();
        var expectedAuthor = JsonFeedAuthor.from("Nicolas Lacombe", url, avatar);
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("1", "<p>test html content</p>")
            .authors(expectedAuthor)
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .items(jsonFeedItem)
            .build();

        testPropertySerializationAndDeserializationForSingleItem(expectedJsonText, jsonFeed,
            deserializedJsonFeedItem -> {
                assertThat(deserializedJsonFeedItem.getAuthors()).hasSize(1);

                var author = deserializedJsonFeedItem.getAuthors().get(0);
                assertThat(author).isNotNull();
                assertThat(author.getName()).isEqualTo(expectedAuthor.getName());
                assertThat(author.getUrl()).isEqualTo(expectedAuthor.getUrl());
                assertThat(author.getAvatar()).isEqualTo(expectedAuthor.getAvatar());
            });
    }

    @Test
    public void throw_exception_when_parsing_jsonfeed_item_without_id() {
        assertThrows(IllegalArgumentException.class, () -> {
            var invalidJson = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"items\":[{\"content_html\":\"<p>test html content</p>\"}]}";
            JsonFeed.fromJson(invalidJson);
        });
    }

    @Test
    public void create_simple_json_feed_example_in_nlJsonFeed_doc() throws IOException {
        var jsonFeedItemId = "https://example.net/my-first-post";
        var jsonFeedItemHtmlContent = "<p>Welcome to my first post!</p>";
        var jsonFeedItem = JsonFeedItem.builderFromHtmlContent(jsonFeedItemId, jsonFeedItemHtmlContent)
            .url("https://example.net/my-first-post")
            .build();
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "Joe's finance blog")
            .homePageUrl("https://example.net/")
            .items(jsonFeedItem)
            .build();
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo("{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"Joe's finance blog\",\"home_page_url\":\"https://example.net/\",\"items\":[{\"id\":\"https://example.net/my-first-post\",\"url\":\"https://example.net/my-first-post\",\"content_html\":\"<p>Welcome to my first post!</p>\"}]}");
    }

    @Test
    public void read_simple_json_feed_example_in_nlJsonFeed_doc() throws IOException, InterruptedException {
        var jsonFeedUri = URI.create("https://jsonfeed.org/feed.json");
        var httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(HttpRequest.newBuilder(jsonFeedUri).build(), HttpResponse.BodyHandlers.ofInputStream());
        var responseBodyInputStream = response.body();

        var jsonFeed = JsonFeed.read(responseBodyInputStream, StandardCharsets.UTF_8);
        var jsonFeedTitle = jsonFeed.getTitle();

        var jsonFeedItem = jsonFeed.getItems().get(0);
        var itemId = jsonFeedItem.getId();
        var itemContent = jsonFeedItem.getContentHtml();

        assertThat(jsonFeedTitle).isEqualTo("JSON Feed");
        assertThat(itemId).isEqualTo("http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html");
        assertThat(itemContent).isEqualTo("<p>We&rsquo;ve updated the spec to <a href=\"https://jsonfeed.org/version/1.1\">version 1.1</a>. It’s a minor update to JSON Feed, clarifying a few things in the spec and adding a couple new fields such as <code>authors</code> and <code>language</code>.</p>\n\n<p>For version 1.1, we&rsquo;re starting to move to the more specific MIME type <code>application/feed+json</code>. Clients that parse HTML to discover feeds should prefer that MIME type, while still falling back to accepting <code>application/json</code> too.</p>\n\n<p>The <a href=\"https://jsonfeed.org/code/\">code page</a> has also been updated with several new code libraries and apps that support JSON Feed.</p>\n");
    }

    private void testPropertySerializationAndDeserializationForList(String expectedJsonText, JsonFeed jsonFeed, Consumer<List<JsonFeedItem>> testDeserializedProperty) {
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var deserializedJsonFeed = JsonFeed.fromJson(json);
        testDeserializedProperty.accept(deserializedJsonFeed.getItems());
    }

    private void testPropertySerializationAndDeserializationForSingleItem(String expectedJsonText, JsonFeed jsonFeed, Consumer<JsonFeedItem> testDeserializedProperty) {
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var deserializedJsonFeed = JsonFeed.fromJson(json);
        assertThat(deserializedJsonFeed.getItems()).hasSize(1);

        var jsonFeedItem = deserializedJsonFeed.getItems().get(0);
        assertThat(jsonFeedItem).isNotNull();

        testDeserializedProperty.accept(jsonFeedItem);
    }

}
