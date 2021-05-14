
module net.nlacombe.jsonfeed.jackson {
    requires net.nlacombe.jsonfeed;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    provides net.nlacombe.jsonfeed.api.JsonFeedJsonConverter with net.nlacombe.jsonfeed.jackson.JacksonJsonFeedJsonConverter;
}
