package net.nlacombe.jsonfeedlib.api;

import java.util.HashSet;
import java.util.Set;

public class JsonFeedVersion {

    public static final Set<JsonFeedVersion> JSON_FEED_VERSIONS = new HashSet<>();

    public static final JsonFeedVersion VERSION_1 = new JsonFeedVersion("1", "https://jsonfeed.org/version/1");
    public static final JsonFeedVersion VERSION_1_1 = new JsonFeedVersion("1.1", "https://jsonfeed.org/version/1.1");

    private final String versionText;
    private final String versionUri;

    private JsonFeedVersion(String versionText, String versionUri) {
        this.versionText = versionText;
        this.versionUri = versionUri;

        JSON_FEED_VERSIONS.add(this);
    }

    public String getVersionText() {
        return versionText;
    }

    public String getVersionUri() {
        return versionUri;
    }
}
