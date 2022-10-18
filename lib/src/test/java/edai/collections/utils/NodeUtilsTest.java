package edai.collections.utils;

import edai.collections.Node;
import edai.collections.utils.NodeUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeUtilsTest {

    private Node<String>[] arrayOfNodes;
    private Node<String> first;

    @BeforeEach
    void buildNodeChain(){
        arrayOfNodes = new Node[]{
                new Node("First"),
                new Node("Second"),
                new Node("Third")
        };

        for(int i = 1; i < arrayOfNodes.length; ++i){
            arrayOfNodes[i-1].setNext(arrayOfNodes[i]);
        }

        first = arrayOfNodes[0];
    }

    @Test
    void countNodes(){
        assertEquals(3, NodeUtils.count(first));
    }

    @Test
    void countZeroIfFirstIsNull(){
        assertEquals(0, NodeUtils.count(null));
    }

    @Test
    void getSecondToLastNode(){
        Node<String> expected = arrayOfNodes[1];

        assertSame(expected, NodeUtils.getSecondToLast(first));
    }

    @Test
    void getLastNode(){
        Node<String> expected = arrayOfNodes[2];

        assertSame(expected, NodeUtils.getLast(first));
    }

    @Test
    void getNodeByIndex(){
        assertSame(arrayOfNodes[0], NodeUtils.getNodeByIndex(first, 0));
        assertSame(arrayOfNodes[1], NodeUtils.getNodeByIndex(first, 1));
        assertSame(arrayOfNodes[2], NodeUtils.getNodeByIndex(first, 2));
    }

    @Test
    void throwWhenIndexAboveSize(){
        assertThrows(IndexOutOfBoundsException.class, () -> NodeUtils.getNodeByIndex(first, 3));
    }

    @Test
    void throwThenIndexBelowSize(){
        assertThrows(IndexOutOfBoundsException.class, () -> NodeUtils.getNodeByIndex(first, -1));
    }

    @Test
    void getDataOfNodesAsArray(){
        String[] expected = new String[]{
                "First", "Second", "Third"
        };

        assertArrayEquals(expected, NodeUtils.listData(first));
    }

    @Test
    void getEmptyArrayIfFirstIsNull(){
        String[] expected = new String[0];

        assertArrayEquals(expected, NodeUtils.listData(null));
    }

}
