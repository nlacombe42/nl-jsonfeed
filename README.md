# nl-jsonfeed
A java library to create and read <a href="https://jsonfeed.org/version/1.1">json feeds<a/>.

### Example on how to create a feed
```java
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
```

<br />

You can also serialize the `JsonFeed` instance to a `java.io.Writer` or an `java.io.OutputStream`
```java
    jsonFeed.writeAsJson(new FileWriter("feed.json"));
    jsonFeed.writeAsUtf8Json(new FileOutputStream("feed.json"));
    // use a try-with-resources in both cases, this is just for brevity of the example ;)
```

### Example on how to read a feed
```java
  var jsonFeedUri = URI.create("https://jsonfeed.org/feed.json");
  var httpClient = HttpClient.newHttpClient();
  var response = httpClient.send(HttpRequest.newBuilder(jsonFeedUri).build(), HttpResponse.BodyHandlers.ofInputStream());
  var responseBodyInputStream = response.body();

  var jsonFeed = JsonFeed.read(responseBodyInputStream, StandardCharsets.UTF_8);
  var jsonFeedTitle = jsonFeed.getTitle();

  var jsonFeedItem = jsonFeed.getItems().get(0);
  var itemId = jsonFeedItem.getId();
  var itemContent = jsonFeedItem.getContentHtml();
```

<br />

You can also read a `JsonFeed` from a json `String` or a `java.io.Reader`
```java
    var json = """
        {
          "version": "https://jsonfeed.org/version/1.1",
          "title": "Joe's finance blog",
          "home_page_url": "https://example.net/",
          "items": [{
            "id": "https://example.net/my-first-post",
            "url": "https://example.net/my-first-post",
            "content_html": "<p>Welcome to my first post!</p>"
          }]
        }
        """;
    var jsonFeedFromJsonString = JsonFeed.fromJson(json);
    var jsonFeedFromReader = JsonFeed.read(new FileReader("feed.json")); // use a try-with-resources, this is just for brevity of the example ;)
```
