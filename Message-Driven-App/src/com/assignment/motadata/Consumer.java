package com.assignment.motadata;

/**
 * @author Dhruv Soni
 *
 * Consumer class represents a consumer that processes messages from the queue.
 */
public class Consumer implements Runnable {

    private final MessageQueue messageQueue;
    private final Logger logger;

    /**
     * Constructs a Consumer with the given message queue and logger.
     * @param messageQueue The message queue.
     * @param logger The logger.
     */
    public Consumer(MessageQueue messageQueue, Logger logger) {
        this.messageQueue = messageQueue;
        this.logger = logger;
    }

    /**
     * Runs the consumer thread to consume and process messages.
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String message = messageQueue.consume();
                processMessage(message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Processes a message and logs success or error.
     * @param message The message to process.
     */
    private void processMessage(String message) {
        try {
            System.out.println("Consumed: " + message);
            // Simulate message processing
            if (message.endsWith("5") || Math.random() < 0.2) { // Fail messages ending with 5 or 20% chance of error
                throw new RuntimeException("Error processing message: " + message);
            }
            logger.incrementSuccessCount();
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
            logger.incrementErrorCount();
        }
    }
}
