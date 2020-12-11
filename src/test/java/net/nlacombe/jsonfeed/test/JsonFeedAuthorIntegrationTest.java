package net.nlacombe.jsonfeed.test;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedAuthor;
import net.nlacombe.jsonfeed.api.JsonFeedVersion;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonFeedAuthorIntegrationTest {

    @Test
    public void test_json_feed_author_with_no_property() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedAuthor.from(null, null, null);
        });
    }

    @Test
    public void test_json_feed_author_with_null_name() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedAuthor.fromName(null);
        });
    }

    @Test
    public void test_json_feed_author_with_null_url() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedAuthor.fromUrl(null);
        });
    }

    @Test
    public void test_json_feed_author_with_null_avatar() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonFeedAuthor.fromAvatar(null);
        });
    }

    @Test
    public void test_json_feed_author_with_all_property() throws MalformedURLException {
        var url = URI.create("https://nlacombe.net/").toURL();
        var avatar = URI.create("https://nlacombe.net/avatar.png").toURL();
        var author = JsonFeedAuthor.from("Nicolas Lacombe", url, avatar);
        var expectedJsonText = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"authors\":[{\"name\":\"Nicolas Lacombe\",\"url\":\"https://nlacombe.net/\",\"avatar\":\"https://nlacombe.net/avatar.png\"}],\"items\":[]}";
        var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "test title")
            .authors(author)
            .build();

        var json = jsonFeed.toJson();

        assertThat(json).isEqualTo(expectedJsonText);

        var deserializedJsonFeed = JsonFeed.fromJson(json);

        assertThat(deserializedJsonFeed.getAuthors()).hasSize(1);

        var deserializedJsonFeedAuthor = deserializedJsonFeed.getAuthors().get(0);
        assertThat(deserializedJsonFeedAuthor).isNotNull();
        assertThat(deserializedJsonFeedAuthor.getName()).isEqualTo("Nicolas Lacombe");
        assertThat(deserializedJsonFeedAuthor.getUrl()).isNotNull();
        assertThat(deserializedJsonFeedAuthor.getUrl().toString()).isEqualTo("https://nlacombe.net/");
        assertThat(deserializedJsonFeedAuthor.getAvatar()).isNotNull();
        assertThat(deserializedJsonFeedAuthor.getAvatar().toString()).isEqualTo("https://nlacombe.net/avatar.png");
    }

    @Test
    public void test_json_feed_legacy_author_from_v1() {
        var json = "{\"version\":\"https://jsonfeed.org/version/1\",\"title\":\"test title\",\"author\":{\"name\":\"Nicolas Lacombe\",\"url\":\"https://nlacombe.net/\",\"avatar\":\"https://nlacombe.net/avatar.png\"},\"items\":[]}";

        var jsonFeed = JsonFeed.fromJson(json);

        assertThat(jsonFeed.getAuthors()).hasSize(1);

        var author = jsonFeed.getAuthors().get(0);
        assertThat(author).isNotNull();
        assertThat(author.getName()).isEqualTo("Nicolas Lacombe");
        assertThat(author.getUrl()).isNotNull();
        assertThat(author.getUrl().toString()).isEqualTo("https://nlacombe.net/");
        assertThat(author.getAvatar()).isNotNull();
        assertThat(author.getAvatar().toString()).isEqualTo("https://nlacombe.net/avatar.png");
    }

    @Test
    public void test_json_feed_legacy_author_from_v1_and_authors_v1_1_prefer_authors() {
        var json = "{\"version\":\"https://jsonfeed.org/version/1.1\",\"title\":\"test title\",\"authors\":[{\"name\":\"Legacy author name\",\"url\":\"https://nlacombe.net/legacyAuthor\",\"avatar\":\"https://nlacombe.net/legacyAuthorAvatar.png\"}],\"authors\":[{\"name\":\"Nicolas Lacombe\",\"url\":\"https://nlacombe.net/\",\"avatar\":\"https://nlacombe.net/avatar.png\"}],\"items\":[]}";

        var jsonFeed = JsonFeed.fromJson(json);

        assertThat(jsonFeed.getAuthors()).hasSize(1);

        var author = jsonFeed.getAuthors().get(0);
        assertThat(author).isNotNull();
        assertThat(author.getName()).isEqualTo("Nicolas Lacombe");
        assertThat(author.getUrl()).isNotNull();
        assertThat(author.getUrl().toString()).isEqualTo("https://nlacombe.net/");
        assertThat(author.getAvatar()).isNotNull();
        assertThat(author.getAvatar().toString()).isEqualTo("https://nlacombe.net/avatar.png");
    }
}
