package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedAttachment;
import net.nlacombe.jsonfeed.api.JsonFeedAuthor;
import net.nlacombe.jsonfeed.api.JsonFeedItem;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonFeedSpecExamplesIntegrationTest {

    @Test
    public void simple_example() {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"My Example Feed\",\"home_page_url\":\"https://example.org/\",\"feed_url\":\"https://example.org/feed.json\",\"items\":[{\"id\":\"2\",\"url\":\"https://example.org/second-item\",\"content_text\":\"This is a second item.\"},{\"id\":\"1\",\"url\":\"https://example.org/initial-post\",\"content_html\":\"<p>Hello, world!</p>\"}]}";
        var item1 = JsonFeedItem
            .builderFromHtmlContent("1", "<p>Hello, world!</p>")
            .url("https://example.org/initial-post")
            .build();
        var item2 = JsonFeedItem
            .builderFromTextContent("2", "This is a second item.")
            .url("https://example.org/second-item")
            .build();
        var jsonFeed = JsonFeed
            .builder(JsonFeedVersion.VERSION_1_1, "My Example Feed")
            .homePageUrl("https://example.org/")
            .feedUrl("https://example.org/feed.json")
            .items(item2, item1)
            .build();
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var jsonFeedDeserialized = JsonFeed.fromJson(json);

        assertThat(jsonFeedDeserialized).isNotNull();
        assertThat(jsonFeedDeserialized.getVersion()).isEqualTo(JsonFeedVersion.VERSION_1_1);
        assertThat(jsonFeedDeserialized.getTitle()).isEqualTo("My Example Feed");
        assertThat(jsonFeedDeserialized.getItems()).hasSize(2);

        var itemsDeserialized = jsonFeedDeserialized.getItems();

        var item2Deserialized = itemsDeserialized.get(0);
        assertThat(item2Deserialized).isNotNull();
        assertThat(item2Deserialized.getId()).isEqualTo("2");
        assertThat(item2Deserialized.getContentText()).isEqualTo("This is a second item.");
        TestUtil.assertObjectStringEquals(item2Deserialized.getUrl(), "https://example.org/second-item");

        var item1Deserialized = itemsDeserialized.get(1);
        assertThat(item1Deserialized).isNotNull();
        assertThat(item1Deserialized.getId()).isEqualTo("1");
        assertThat(item1Deserialized.getContentHtml()).isEqualTo("<p>Hello, world!</p>");
        TestUtil.assertObjectStringEquals(item1Deserialized.getUrl(), "https://example.org/initial-post");
    }

    @Test
    public void podcast_example() throws Exception {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"The Record\",\"home_page_url\":\"http://therecord.co/\",\"feed_url\":\"http://therecord.co/feed.json\",\"user_comment\":\"This is a podcast feed. You can add this feed to your podcast client using the following URL: http://therecord.co/feed.json\",\"items\":[{\"id\":\"http://therecord.co/chris-parrish\",\"url\":\"http://therecord.co/chris-parrish\",\"title\":\"Special #1 - Chris Parrish\",\"content_text\":\"Chris has worked at Adobe and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped Napkin, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on Bainbridge Island, a quick ferry ride from Seattle.\",\"content_html\":\"Chris has worked at <a href=\\\"http://adobe.com/\\\">Adobe</a> and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped <a href=\\\"http://aged-and-distilled.com/napkin/\\\">Napkin</a>, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on <a href=\\\"http://www.ci.bainbridge-isl.wa.us/\\\">Bainbridge Island</a>, a quick ferry ride from Seattle.\",\"summary\":\"Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled.\",\"date_published\":\"2014-05-09T14:04-07:00\",\"attachments\":[{\"url\":\"http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a\",\"mime_type\":\"audio/x-m4a\",\"size_in_bytes\":89970236,\"duration_in_seconds\":6629}]}]}";
        var contentText = "Chris has worked at Adobe and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped Napkin, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on Bainbridge Island, a quick ferry ride from Seattle.";
        var contentHtml = "Chris has worked at <a href=\"http://adobe.com/\">Adobe</a> and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped <a href=\"http://aged-and-distilled.com/napkin/\">Napkin</a>, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on <a href=\"http://www.ci.bainbridge-isl.wa.us/\">Bainbridge Island</a>, a quick ferry ride from Seattle.";
        var attachment = JsonFeedAttachment
            .builder(URI.create("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a").toURL(), "audio/x-m4a")
            .sizeInBytes(89970236)
            .durationInSeconds(6629)
            .build();
        var item = JsonFeedItem
            .builderFromTextAndHtmlContent("http://therecord.co/chris-parrish", contentText, contentHtml)
            .title("Special #1 - Chris Parrish")
            .url(URI.create("http://therecord.co/chris-parrish").toURL())
            .summary("Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled.")
            .datePublished(OffsetDateTime.parse("2014-05-09T14:04:00-07:00"))
            .attachments(attachment)
            .build();
        var jsonFeed = JsonFeed
            .builder(JsonFeedVersion.VERSION_1_1, "The Record")
            .userComment("This is a podcast feed. You can add this feed to your podcast client using the following URL: http://therecord.co/feed.json")
            .homePageUrl(URI.create("http://therecord.co/").toURL())
            .feedUrl(URI.create("http://therecord.co/feed.json").toURL())
            .items(item)
            .build();
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var jsonFeedDeserialized = JsonFeed.fromJson(json);

        assertThat(jsonFeedDeserialized).isNotNull();
        assertThat(jsonFeedDeserialized.getVersion()).isEqualTo(JsonFeedVersion.VERSION_1_1);
        assertThat(jsonFeedDeserialized.getTitle()).isEqualTo("The Record");
        assertThat(jsonFeedDeserialized.getUserComment()).isEqualTo("This is a podcast feed. You can add this feed to your podcast client using the following URL: http://therecord.co/feed.json");
        TestUtil.assertObjectStringEquals(jsonFeedDeserialized.getHomePageUrl(), "http://therecord.co/");
        TestUtil.assertObjectStringEquals(jsonFeedDeserialized.getFeedUrl(), "http://therecord.co/feed.json");
        assertThat(jsonFeedDeserialized.getItems()).hasSize(1);

        var itemDeserialized = jsonFeedDeserialized.getItems().get(0);
        assertThat(itemDeserialized).isNotNull();
        assertThat(itemDeserialized.getId()).isEqualTo("http://therecord.co/chris-parrish");
        assertThat(itemDeserialized.getContentText()).isEqualTo(contentText);
        assertThat(itemDeserialized.getContentHtml()).isEqualTo(contentHtml);
        assertThat(itemDeserialized.getTitle()).isEqualTo("Special #1 - Chris Parrish");
        TestUtil.assertObjectStringEquals(itemDeserialized.getUrl(), "http://therecord.co/chris-parrish");
        assertThat(itemDeserialized.getSummary()).isEqualTo("Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled.");
        assertThat(itemDeserialized.getDatePublished()).isEqualTo(OffsetDateTime.parse("2014-05-09T14:04:00-07:00"));
        assertThat(itemDeserialized.getAttachments()).hasSize(1);

        var attachmentDeserialized = itemDeserialized.getAttachments().get(0);
        assertThat(attachmentDeserialized).isNotNull();
        TestUtil.assertObjectStringEquals(attachmentDeserialized.getUrl(), "http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a");
        assertThat(attachmentDeserialized.getMimeType()).isEqualTo("audio/x-m4a");
        assertThat(attachmentDeserialized.getSizeInBytes()).isEqualTo(89970236);
        assertThat(attachmentDeserialized.getDurationInSeconds()).isEqualTo(6629);
    }

    @Test
    public void microblog_example() throws Exception {
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"Brent Simmons’s Microblog\",\"home_page_url\":\"https://example.org/\",\"feed_url\":\"https://example.org/feed.json\",\"user_comment\":\"This is a microblog feed. You can add this to your feed reader using the following URL: https://example.org/feed.json\",\"authors\":[{\"name\":\"Brent Simmons\",\"url\":\"http://example.org/\",\"avatar\":\"https://example.org/avatar.png\"}],\"items\":[{\"id\":\"2347259\",\"url\":\"https://example.org/2347259\",\"content_text\":\"Cats are neat. \\n\\nhttps://example.org/cats\",\"date_published\":\"2016-02-09T14:22-07:00\"}]}";
        var item = JsonFeedItem
            .builderFromTextContent("2347259", "Cats are neat. \n\nhttps://example.org/cats")
            .url(URI.create("https://example.org/2347259").toURL())
            .datePublished(OffsetDateTime.parse("2016-02-09T14:22:00-07:00"))
            .build();
        var jsonFeed = JsonFeed
            .builder(JsonFeedVersion.VERSION_1_1, "Brent Simmons’s Microblog")
            .userComment("This is a microblog feed. You can add this to your feed reader using the following URL: https://example.org/feed.json")
            .homePageUrl(URI.create("https://example.org/").toURL())
            .feedUrl(URI.create("https://example.org/feed.json").toURL())
            .authors(JsonFeedAuthor.from("Brent Simmons", URI.create("http://example.org/").toURL(), URI.create("https://example.org/avatar.png").toURL()))
            .items(item)
            .build();
        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var jsonFeedDeserialized = JsonFeed.fromJson(json);

        assertThat(jsonFeedDeserialized).isNotNull();
        assertThat(jsonFeedDeserialized.getVersion()).isEqualTo(JsonFeedVersion.VERSION_1_1);
        assertThat(jsonFeedDeserialized.getTitle()).isEqualTo("Brent Simmons’s Microblog");
        assertThat(jsonFeedDeserialized.getUserComment()).isEqualTo("This is a microblog feed. You can add this to your feed reader using the following URL: https://example.org/feed.json");
        TestUtil.assertObjectStringEquals(jsonFeedDeserialized.getHomePageUrl(), "https://example.org/");
        TestUtil.assertObjectStringEquals(jsonFeedDeserialized.getFeedUrl(), "https://example.org/feed.json");
        assertThat(jsonFeedDeserialized.getAuthors()).hasSize(1);
        assertThat(jsonFeedDeserialized.getItems()).hasSize(1);

        var authorDeserialized = jsonFeedDeserialized.getAuthors().get(0);
        assertThat(authorDeserialized).isNotNull();
        assertThat(authorDeserialized.getName()).isEqualTo("Brent Simmons");
        TestUtil.assertObjectStringEquals(authorDeserialized.getUrl(), "http://example.org/");
        TestUtil.assertObjectStringEquals(authorDeserialized.getAvatar(), "https://example.org/avatar.png");

        var itemDeserialized = jsonFeedDeserialized.getItems().get(0);
        assertThat(itemDeserialized).isNotNull();
        assertThat(itemDeserialized.getId()).isEqualTo("2347259");
        assertThat(itemDeserialized.getContentText()).isEqualTo("Cats are neat. \n\nhttps://example.org/cats");
        TestUtil.assertObjectStringEquals(itemDeserialized.getUrl(), "https://example.org/2347259");
        assertThat(itemDeserialized.getDatePublished()).isEqualTo(OffsetDateTime.parse("2016-02-09T14:22:00-07:00"));
    }

}
