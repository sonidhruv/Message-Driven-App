package com.assignment.motadata;

/**
 * @author Dhruv Soni
 *
 * Logger class to track and log the number of successful and failed message processing attempts.
 */
public class Logger {

    private int successCount = 0;
    private int errorCount = 0;

    /**
     * Increments the count of successfully processed messages.
     */
    public synchronized void incrementSuccessCount() {
        successCount++;
    }

    /**
     * Increments the count of errors encountered during message processing.
     */
    public synchronized void incrementErrorCount() {
        errorCount++;
    }

    /**
     * Gets the count of successfully processed messages.
     * @return The success count.
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * Gets the count of errors encountered.
     * @return The error count.
     */
    public int getErrorCount() {
        return errorCount;
    }

    /**
     * Prints the statistics of message processing.
     */
    public synchronized void printStats() {
        System.out.println("Total messages processed successfully: " + successCount);
        System.out.println("Total errors encountered: " + errorCount);
    }
}
