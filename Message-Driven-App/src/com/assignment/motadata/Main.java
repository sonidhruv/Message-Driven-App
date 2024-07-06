package com.assignment.motadata;

/**
 * @author Dhruv Soni
 * <p>
 * Main class to run the message-driven application.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Initialize the message queue and logger
        MessageQueue messageQueue = new MessageQueue();
        Logger logger = new Logger();

        // Create producer and consumer
        Producer producer = new Producer(messageQueue, 50);
        Consumer consumer1 = new Consumer(messageQueue, logger);
        Consumer consumer2 = new Consumer(messageQueue, logger);

        // Create and start producer and consumer threads
        Thread producerThread = new Thread(producer);
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();

        // Wait for the producer to finish producing messages
        producerThread.join();
        Thread.sleep(1000); // Give consumers time to finish processing

        // Interrupt consumer threads to stop processing
        consumerThread1.interrupt();
        consumerThread2.interrupt();

        // Wait for consumers to finish
        consumerThread1.join();
        consumerThread2.join();

        // Print statistics of processed messages
        logger.printStats();
    }
}