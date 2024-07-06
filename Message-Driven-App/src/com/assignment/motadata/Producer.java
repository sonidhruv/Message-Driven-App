package com.assignment.motadata;

/**
 * @author Dhruv Soni
 *
 * Producer class represents a producer that generates messages and adds them to the queue.
 */
public class Producer implements Runnable {

    private final MessageQueue messageQueue;
        private final int messageCount;


    /**
     * Constructs a Producer with the given message queue and message count.
     * @param messageQueue The message queue.
     * @param messageCount The number of messages to produce.
     */
    public Producer(MessageQueue messageQueue, int messageCount) {
        this.messageQueue = messageQueue;
        this.messageCount = messageCount;
    }

    /**
     * Runs the producer thread to generate and produce messages.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < messageCount; i++) {
                String message = "Message " + i;
                messageQueue.produce(message);
                System.out.println("Produced: " + message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
