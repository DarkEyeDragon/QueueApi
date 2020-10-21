package me.darkeyedragon.queueapi.queue;

/**
 * Thrown when a full {@link Queue} has a player added to it.
 */
public class MaxSizeExceeded extends Exception {

    /**
     * Creates a {@link MaxSizeExceeded} without specifying a maximum size.
     */
    public MaxSizeExceeded() {
        super("The maximum size of the queue has been exceeded. This is a fatal error and should be handled by the game.");
    }

    /**
     * Creates a {@link MaxSizeExceeded} with a specified maximum size, which may help with debugging later on.
     * @param maxSize The maximum size of the queue
     */
    public MaxSizeExceeded(int maxSize) {
        super("The maximum size of the queue (" + maxSize + ") has been exceeded. This is a fatal error and should be handled by the game.");
    }
}
