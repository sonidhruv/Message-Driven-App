package com.assignment.motadata;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author Dhruv Soni
 *
 * MessageQueue class represents a thread-safe queue for storing and processing messages.
 */
public class MessageQueue {

    // A blocking queue to hold the messages.
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    /**
     * Adds a message to the queue.
     * @param message The message to add.
     * @throws InterruptedException If interrupted while waiting.
     */
    public void produce(String message) throws InterruptedException {
        queue.put(message);
    }

    /**
     * Retrieves and removes a message from the queue.
     * @return The message from the queue.
     * @throws InterruptedException If interrupted while waiting.
     */
    public String consume() throws InterruptedException {
        return queue.take();
    }

    /**
     * Checks if the queue is empty.
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
