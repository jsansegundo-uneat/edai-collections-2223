package edai.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import edai.collections.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueTest {
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<Integer>();
    }

    @Test
    void testQueue() {
        assertThrows(EmptyStackException.class, () -> queue.dequeue());
        assertThrows(EmptyStackException.class, () -> queue.head());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertArrayEquals(new Integer[0], queue.listData());
    }

    @Test
    void testEnqueueOne() {
        queue.enqueue(10);
        assertEquals(10, queue.head());
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertArrayEquals(new Integer[]{10}, queue.listData());
    }

    @Test
    void testEnqueueThree() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(10, queue.head());
        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertArrayEquals(new Integer[]{10, 20, 30}, queue.listData());
    }

    @Test
    void testDequeueOneFromThree() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        final Integer elem = queue.dequeue();
        assertEquals(10, elem);
        assertEquals(20, queue.head());
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
        assertArrayEquals(new Integer[]{20, 30}, queue.listData());
    }

    @Test
    void testDequeueThreeFromThree() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        final Integer elem1 = queue.dequeue();
        final Integer elem2 = queue.dequeue();
        final Integer elem3 = queue.dequeue();
        assertEquals(10, elem1);
        assertEquals(20, elem2);
        assertEquals(30, elem3);
        assertThrows(EmptyStackException.class, () -> queue.head());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertArrayEquals(new Integer[0], queue.listData());
    }

    @Test
    void testDequeueAllAndEnqueueAgain(){
        queue.enqueue(10);
        queue.enqueue(20);
        queue.dequeue();
        queue.dequeue();

        queue.enqueue(30);
        queue.enqueue(40);

        assertEquals(30, queue.dequeue());
        assertEquals(40, queue.dequeue());
    }

}
