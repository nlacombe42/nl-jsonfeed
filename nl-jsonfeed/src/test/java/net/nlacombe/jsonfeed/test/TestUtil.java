package net.nlacombe.jsonfeed.test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {

    public static void assertObjectStringEquals(Object actualObject, String expectedText) {
        assertThat(actualObject).isNotNull();
        assertThat(actualObject.toString()).isEqualTo(expectedText);
    }
}
