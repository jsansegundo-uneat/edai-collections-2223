package edai.collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeMapTest {
    
    TreeMap<String, Integer> map;

    @BeforeEach
    void setup(){
        map = new TreeMap<>();
    }

    @Test
    void testNewDictionary(){
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertArrayEquals(new Integer[]{}, map.values());
        assertArrayEquals(new String[]{}, map.keys());
    }

    @Test
    void testPutOneValue(){
        map.put("one", 1);

        assertFalse(map.isEmpty());
        assertEquals(1, map.size());
        assertArrayEquals(new Integer[]{1}, map.values());
        assertArrayEquals(new String[]{"one"}, map.keys());
    }


    @Test
    void testPutThreeValue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertFalse(map.isEmpty());
        assertEquals(3, map.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, map.values());
        assertArrayEquals(new String[]{"1one", "2two", "3three"}, map.keys());
    }

    @Test
    void testReplaceOneValue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 100);

        map.put("1one", 1);

        assertFalse(map.isEmpty());
        assertEquals(3, map.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, map.values());
        assertArrayEquals(new String[]{"1one", "2two", "3three"}, map.keys());
    }

    @Test
    void testContainKey(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertTrue(map.contains("1one"));
    }

    @Test
    void testNotContainKey(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertFalse(map.contains("4four"));
    }

    @Test
    void testGetValue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertEquals(1, map.get("1one"));
    }

    @Test
    void testGetThrowsIfValueDoesNotExists(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertThrows(KeyNotFoundException.class, () -> map.get("4four"));
    }

    @Test
    void removeOneKeyReturnsTrue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertTrue(map.remove("1one"));

        assertFalse(map.isEmpty());
        assertEquals(2, map.size());
        assertArrayEquals(new Integer[]{2, 3}, map.values());
        assertArrayEquals(new String[]{"2two", "3three"}, map.keys());
    }

    @Test
    void removeNoKeyReturnsFalse(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertFalse(map.remove("4four"));

        assertFalse(map.isEmpty());
        assertEquals(3, map.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, map.values());
        assertArrayEquals(new String[]{"1one", "2two", "3three"}, map.keys());
    }

    @Test
    void removeAllKeys(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        map.remove("1one");
        map.remove("2two");
        map.remove("3three");


        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertArrayEquals(new Integer[]{}, map.values());
        assertArrayEquals(new String[]{}, map.keys());
    }

}
