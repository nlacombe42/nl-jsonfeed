package net.nlacombe.jsonfeedlib.api;

import net.nlacombe.jsonfeedlib.api.exception.JsonFeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class JsonFeedVersion {

    private static final Logger logger = LoggerFactory.getLogger(JsonFeedVersion.class);

    private static final int MAX_COMPATIBLE_MAJOR_VERSION = 1;
    private static final Pattern VERSION_PATTERN = Pattern.compile("^https://jsonfeed\\.org/version/((\\d+)(\\.\\d+){0,2})$");
    private static final Set<JsonFeedVersion> JSON_FEED_VERSIONS = new HashSet<>();

    public static final JsonFeedVersion VERSION_1 = new JsonFeedVersion("1", "https://jsonfeed.org/version/1");
    public static final JsonFeedVersion VERSION_1_1 = new JsonFeedVersion("1.1", "https://jsonfeed.org/version/1.1");

    private final String versionText;
    private final String versionUri;

    private JsonFeedVersion(String versionText, String versionUri) {
        this.versionText = versionText;
        this.versionUri = versionUri;

        JSON_FEED_VERSIONS.add(this);
    }

    public static JsonFeedVersion parse(String versionUriText) {
        Objects.requireNonNull(versionUriText, "versionUriText must not be null");

        var existingJsonFeedVersion = JSON_FEED_VERSIONS.stream()
            .filter(jsonFeedVersion -> jsonFeedVersion.getVersionUri().equals(versionUriText))
            .findAny()
            .orElse(null);

        if (existingJsonFeedVersion != null)
            return existingJsonFeedVersion;

        var matcher = VERSION_PATTERN.matcher(versionUriText);

        if (!matcher.matches())
            throw new JsonFeedException("Unknown and probably incompatible json feed version: " + versionUriText);

        var versionPart = matcher.group(1);
        var majorVersion = Integer.parseInt(matcher.group(2));

        if (majorVersion != MAX_COMPATIBLE_MAJOR_VERSION) {
            var message = "Probably incompatible json feed major version \"$actualMajorVersion\"."
                + " Maximum major version for compatibility: $maxMajorVersion"
                .replace("$actualMajorVersion", Integer.valueOf(majorVersion).toString())
                .replace("$maxMajorVersion", Integer.valueOf(MAX_COMPATIBLE_MAJOR_VERSION).toString());

            throw new JsonFeedException(message);
        }

        var message = "This version of jsonfeedlib does not officially support json feed version \"$version\"" +
            " but should be compatible since this library was written to support json feed major version $maxMajorVersion"
                .replace("$version", versionUriText)
                .replace("$maxMajorVersion", Integer.valueOf(MAX_COMPATIBLE_MAJOR_VERSION).toString());
        logger.warn(message);

        return new JsonFeedVersion(versionPart, versionUriText);
    }

    public String getVersionText() {
        return versionText;
    }

    public String getVersionUri() {
        return versionUri;
    }
}
