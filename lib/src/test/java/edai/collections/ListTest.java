package edai.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListTest {
	private List<String> list;
	
	@BeforeEach
	void setUp() {
		list = new List();
	}
	
	@Test
	void testList() {
		assertEquals(null, list.getFirst());
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertArrayEquals(new String[0], list.listData());
	}
	
	@Test
	void testInsertOneAtBeginning() {
		list.insert("Hello", 0);
		
		assertEquals("Hello", list.getFirst().getData());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"Hello"}, list.listData());
	}
	
	@Test
	void testInsertTwoAtBeginning() {
		list.insert("Hello", 0)
			.insert("First", 0);
			
		assertEquals("First", list.getFirst().getData());
		assertEquals("Hello", list.getFirst().getNext().getData());
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"First", "Hello"},
list.listData());
	}
	
	@Test
	void testInsertOneAtEnd() {
		list.insert("Hello", -1);
		
		assertEquals("Hello", list.getFirst().getData());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"Hello"}, list.listData());
	}
	
	@Test
	void testInsertTwoAtEnd() {
		list.insert("Hello", -1)
			.insert("Last", -1);
			
		assertEquals("Hello", list.getFirst().getData());
		assertEquals("Last", list.getFirst().getNext().getData());
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"Hello", "Last"},
list.listData());
	}
	
	@Test
	void testInsertAtPosition() {
		list.insert("First", 0)
				.insert("Second", 1)
				.insert("Third", 2);

		assertEquals("First", list.getFirst().getData());
		assertEquals("Second", list.getFirst().getNext().getData());
		assertEquals("Third", list.getFirst().getNext().getNext().getData());
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"First", "Second", "Third"},
			list.listData());
	}
	
	@Test
	void testInsertInMiddle() {
		list.insert("First", 0)
			.insert("Second", 1)
			.insert("Third", 2)
			.insert("Middle", 2);

		assertEquals("First", list.getFirst().getData());
		assertEquals("Second", list.getFirst().getNext().getData());
		assertEquals("Middle", list.getFirst().getNext().getNext().getData());
		assertEquals("Third", list.getFirst().getNext().getNext().getNext().getData());
		assertEquals(4, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"First", "Second", "Middle", "Third"},
				list.listData());
	}
	
	@Test
	void testInsertAtIndexBelowBounds() {
		list.insert("Hello", -1);

		assertThrows(
				IndexOutOfBoundsException.class,
				() -> list.insert("Invalid", -2));
	}
	
	@Test
	void testInsertAtIndexAboveBounds() {
		list.insert("Hello", -1);
		assertThrows(IndexOutOfBoundsException.class,
			() -> list.insert("Invalid", 2));
	}
	
	@Test
	void testInsertInEmptyAtInvalidIndex() {
		assertThrows(IndexOutOfBoundsException.class,
() -> list.insert("Invalid", 2));
	}

	@Test
	void testRemoveFirst() {
		list.insert("Hello", 0);
		list.remove(0);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertArrayEquals(new String[0], list.listData());
	}

	@Test
	void testRemoveLast() {
		list.insert("Hello", -1)
			.insert("Last", -1);

		list.remove(-1);
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"Hello"}, list.listData());
	}
	
	@Test
	void testRemoveLastWithOne() {
		list.insert("Hello", -1);
		list.remove(-1);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertArrayEquals(new String[0], list.listData());
	}
	
	@Test
	void testRemoveAtPosition() {
		list.insert("First", -1)
			.insert("Second", -1)
			.insert("Third", -1);
		list.remove(1);
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());
		assertArrayEquals(new String[] {"First", "Third"},
list.listData());
	}
	
	@Test
	void testRemoveBelowBounds() {
		list.insert("Hello", -1);
		assertThrows(IndexOutOfBoundsException.class,
			() -> list.remove(-2));
	}
	
	@Test
	void testRemoveBelowBoundsEmpty() {
		assertThrows(IndexOutOfBoundsException.class,
			() -> list.remove(-2));
	}
	
	@Test
	void testRemoveAboveBounds() {
		list.insert("Hello", -1);
		assertThrows(IndexOutOfBoundsException.class,
			() -> list.remove(1));
		assertThrows(IndexOutOfBoundsException.class,
			() -> list.remove(2));
	}
}
