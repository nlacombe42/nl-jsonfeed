
module net.nlacombe.jsonfeed {
    uses net.nlacombe.jsonfeed.api.JsonFeedJsonConverter;

    requires org.slf4j;

    exports net.nlacombe.jsonfeed.api;
    exports net.nlacombe.jsonfeed.api.exception;

    opens net.nlacombe.jsonfeed.impl.dto to com.fasterxml.jackson.databind;
}
