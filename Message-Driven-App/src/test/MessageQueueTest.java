package test;

import com.assignment.motadata.MessageQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test cases for MessageQueue class.
 */
class MessageQueueTest {

    @Test
    public void testProduceConsume() throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        String message = "Test Message";

        messageQueue.produce(message);
        assertEquals(message, messageQueue.consume());
    }

    @Test
    public void testQueueEmpty() throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        assertTrue(messageQueue.isEmpty());

        messageQueue.produce("Test Message");
        assertFalse(messageQueue.isEmpty());

        messageQueue.consume();
        assertTrue(messageQueue.isEmpty());
    }

}