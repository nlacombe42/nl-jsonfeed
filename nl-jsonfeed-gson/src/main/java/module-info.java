
module net.nlacombe.jsonfeed.gson {
    requires net.nlacombe.jsonfeed;
    requires com.google.gson;

    provides net.nlacombe.jsonfeed.api.JsonFeedJsonConverter with net.nlacombe.jsonfeed.gson.GsonJsonFeedJsonConverter;
}
