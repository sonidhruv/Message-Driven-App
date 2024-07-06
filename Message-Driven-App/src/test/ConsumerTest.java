package test;

import com.assignment.motadata.Consumer;
import com.assignment.motadata.Logger;
import com.assignment.motadata.MessageQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for Consumer class.
 */
class ConsumerTest {

    private Logger logger;

    @BeforeEach
    public void setUp() {
        logger = new Logger();
    }

    @Test
    public void testConsumerSuccess() throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        int messageCount = 10;

        // Producing messages
        for (int i = 0; i < messageCount; i++) {
            messageQueue.produce("Message " + i);
        }

        Consumer consumer = new Consumer(messageQueue, logger);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        // Allowing consumer to process messages
        Thread.sleep(2000); // This delay ensures the consumer has time to process messages
        consumerThread.interrupt();
        consumerThread.join();

        assertEquals(messageCount, logger.getSuccessCount() + logger.getErrorCount());
    }

    @Test
    public void testConsumerError() throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        int messageCount = 10;

        // Producing messages including some that will generate errors
        for (int i = 0; i < messageCount; i++) {
            messageQueue.produce("Message " + i);
        }

        Consumer consumer = new Consumer(messageQueue, logger);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        // Allowing consumer to process messages
        Thread.sleep(2000);
        consumerThread.interrupt();
        consumerThread.join();

        // Calculate expected counts
        int expectedErrorCount = 0;
        int expectedSuccessCount = 0;

        for (int i = 0; i < messageCount; i++) {
            String message = "Message " + i;
            if (message.endsWith("5") || Math.random() < 0.2) {
                expectedErrorCount++;
            } else {
                expectedSuccessCount++;
            }
        }

        // Assert counts
        assertEquals(expectedSuccessCount, logger.getSuccessCount());
        assertEquals(expectedErrorCount, logger.getErrorCount());
    }
}