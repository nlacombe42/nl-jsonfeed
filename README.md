# nl-jsonfeed
A java library to create and read <a href="https://jsonfeed.org/version/1.1">json feeds<a/>.

## Installation
You can find the relevant artifacts on [maven central](https://mvnrepository.com/artifact/net.nlacombe),
see below sections for the specific artefacts you need.

### With jackson
Gradle:
```groovy
implementation 'net.nlacombe:nl-jsonfeed:1.0.0'
implementation 'net.nlacombe:nl-jsonfeed-jackson:1.0.0'
```
Maven:
```xml
<dependency>
    <groupId>net.nlacombe</groupId>
    <artifactId>nl-jsonfeed</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>net.nlacombe</groupId>
    <artifactId>nl-jsonfeed-jackson</artifactId>
    <version>1.0.0</version>
</dependency>
```

### With gson
Gradle:
```groovy
implementation 'net.nlacombe:nl-jsonfeed:1.0.0'
implementation 'net.nlacombe:nl-jsonfeed-gson:1.0.0'
```
Maven:
```xml
<dependency>
    <groupId>net.nlacombe</groupId>
    <artifactId>nl-jsonfeed</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>net.nlacombe</groupId>
    <artifactId>nl-jsonfeed-gson</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Getting started

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

## Design decisions
- only allow to create a valid/recommended json feed v1.1
- support reading json feed v1
- allow the consumer to choose which json serialization/deserialization library they want
- have the minimum number of dependencies possible
- try to stay true to the doc as much as possible while offering a convenient api to java consumers
