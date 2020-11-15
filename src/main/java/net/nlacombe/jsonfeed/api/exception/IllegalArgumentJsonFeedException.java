package net.nlacombe.jsonfeed.api.exception;

public class IllegalArgumentJsonFeedException extends JsonFeedException {

    public IllegalArgumentJsonFeedException() {
    }

    public IllegalArgumentJsonFeedException(String message) {
        super(message);
    }

    public IllegalArgumentJsonFeedException(String message, Throwable cause) {
        super(message, cause);
    }
}
