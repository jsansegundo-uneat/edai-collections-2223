package edai.practices.tema2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoopListTest {

    LoopList<String> list;

    @BeforeEach
    void setup() {
        list = new LoopList();
    }

    @Test
    void testCreateList(){
        assertNull(list.getFirst());
        assertNull(list.getLast());
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertArrayEquals(new String[0], list.listData());
    }

    @Test
    void testInsertOneAtEnd() {
        list.append("Hello");

        assertEquals("Hello", list.getFirst().getData());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello"}, list.listData());
        assertEquals(list.getLast().getNext(), list.getFirst());
    }

    @Test
    void testInsertOneAtBeginning() {
        list.prepend("Hello");

        assertEquals("Hello", list.getFirst().getData());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello"}, list.listData());
        assertEquals(list.getLast().getNext(), list.getFirst());
    }

    @Test
    void testInsertTwoAtBeginning() {
        list.prepend("Hello")
            .prepend("First");

        assertEquals("First", list.getFirst().getData());
        assertEquals("Hello", list.getFirst().getNext().getData());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"First", "Hello"},
                list.listData());
        assertEquals(list.getLast().getNext(), list.getFirst());
    }

    @Test
    void testInsertTwoAtEnd() {
        list.append("Hello")
            .append("Last");

        assertEquals("Hello", list.getFirst().getData());
        assertEquals("Last", list.getFirst().getNext().getData());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello", "Last"},
                list.listData());
        assertEquals(list.getLast().getNext(), list.getFirst());
    }

    @Test
    void testLastItemPointsToFirst() {
        list.append("First").append("Second").append("Third");

        assertEquals("First", list.getFirst().getData());
        assertEquals("Third", list.getLast().getData());
        assertEquals(list.getLast().getNext(), list.getFirst());
    }
    @Test
    void testRemoveFirstOfOne() {
        list.append("Hello");
        boolean ok =  list.removeFirst();

        assertTrue(ok);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertArrayEquals(new String[0], list.listData());
    }


    @Test
    void testRemoveFirstOfTwo() {
        list.append("First").append("Second");
        boolean ok = list.removeFirst();

        assertTrue(ok);
        assertEquals("Second", list.getFirst().getData());
        assertEquals("Second", list.getLast().getData());
        assertEquals(list.getLast().getNext(), list.getFirst());

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[]{"Second"}, list.listData());
    }

    @Test
    void testRemoveLastOfOne() {
        list.append("Hello");
        boolean ok = list.removeLast();

        assertTrue(ok);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertArrayEquals(new String[] {}, list.listData());
    }

    @Test
    void testRemoveLastOfTwo() {
        list.append("First").append("Second");
        boolean ok = list.removeLast();

        assertTrue(ok);
        assertEquals("First", list.getFirst().getData());
        assertEquals("First", list.getLast().getData());
        assertEquals(list.getLast().getNext(), list.getFirst());

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[]{"First"}, list.listData());
    }

}
