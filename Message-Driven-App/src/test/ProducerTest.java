package test;

import com.assignment.motadata.MessageQueue;
import com.assignment.motadata.Producer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test cases for Producer class.
 */
class ProducerTest {

    @Test
    public void testProducer() throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        int messageCount = 10;
        Producer producer = new Producer(messageQueue, messageCount);

        // Start the producer thread
        Thread producerThread = new Thread(producer);
        producerThread.start();
        producerThread.join();

        assertFalse(messageQueue.isEmpty());

        // Check if all messages are produced correctly
        for (int i = 0; i < messageCount; i++) {
            String expectedMessage = "Message " + i;
            assertEquals(expectedMessage, messageQueue.consume());
        }
    }
}