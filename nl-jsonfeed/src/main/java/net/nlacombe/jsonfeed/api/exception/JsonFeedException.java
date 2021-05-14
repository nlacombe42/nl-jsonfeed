package net.nlacombe.jsonfeed.api.exception;

public class JsonFeedException extends RuntimeException {

    public JsonFeedException() {
    }

    public JsonFeedException(String message) {
        super(message);
    }

    public JsonFeedException(String message, Throwable cause) {
        super(message, cause);
    }
}
