package net.nlacombe.jsonfeedlib.api;

public class JsonFeedVersion {

    public static final JsonFeedVersion VERSION_1 = new JsonFeedVersion("1", "https://jsonfeed.org/version/1");
    public static final JsonFeedVersion VERSION_1_1 = new JsonFeedVersion("1.1", "https://jsonfeed.org/version/1.1");

    private final String versionText;
    private final String versionUri;

    JsonFeedVersion(String versionText, String versionUri) {
        this.versionText = versionText;
        this.versionUri = versionUri;
    }

    public String getVersionText() {
        return versionText;
    }

    public String getVersionUri() {
        return versionUri;
    }
}
