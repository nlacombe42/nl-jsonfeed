# nl-jsonfeed
A java library to create and read <a href="https://jsonfeed.org/version/1.1">json feeds<a/>.

### Example on how to create a feed
```java
    var jsonFeedItem = JsonFeedItem.builderFromHtmlContent("https://example.net/my-first-post", "&lt;p&gt;Welcome to my first post!&lt;/p&gt;")
        .url("https://example.net/my-first-post")
        .build();
    var jsonFeed = JsonFeed.builder(JsonFeedVersion.VERSION_1_1, "Joe's finance blog")
        .homePageUrl("https://example.net/")
        .items(jsonFeedItem)
        .build();
    var json = jsonFeed.toJson();
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
