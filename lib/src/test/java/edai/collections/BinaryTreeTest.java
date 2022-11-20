package edai.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {
    private BinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<Integer>();
    }

    @Test
    void testBinaryTree() {
        TreeNode<Integer> searchResult = tree.search(0);

        assertNull(searchResult);

        assertNull(tree.getRoot());
        assertEquals(0, tree.size());
        assertArrayEquals(new Integer[]{}, tree.listData());
    }

    @Test
    void testInsertFirst() {
        tree.insert(25);

        assertEquals(25, tree.getRoot().getData());
        assertEquals(1, tree.size());
        assertArrayEquals(new Integer[]{25}, tree.listData());
    }

    @Test
    void testInsertLesser() {
        tree.insert(25);
        tree.insert(10);

        assertEquals(25, tree.getRoot().getData());
        assertEquals(2, tree.size());
        assertArrayEquals(new Integer[]{10, 25}, tree.listData());
    }

    @Test
    void testInsertBigger() {
        tree.insert(25);
        tree.insert(50);

        assertEquals(25, tree.getRoot().getData());
        assertEquals(2, tree.size());
        assertArrayEquals(new Integer[]{25, 50}, tree.listData());
    }

    @Test
    void testInsertComplex() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);

        assertEquals(15, tree.getRoot().getData());
        assertEquals(10, tree.size());
        assertArrayEquals(new Integer[]{6, 9, 13, 14, 15, 17, 20, 26,
                64, 72}, tree.listData());
    }

    @Test
    void testSearchOk() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);
        assertNotNull(tree.search(64));
    }

    @Test
    void testSearchKo() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);
        assertNull(tree.search(100));
    }

    @Test
    void testRemoveRootNode() {
        tree.insert(20);
        tree.remove(20);
        assertEquals(0, tree.size());
        assertArrayEquals(new Integer[]{}, tree.listData());
    }

    @Test
    void testRemoveLeafNode() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);
        tree.remove(6);
        assertEquals(9, tree.size());
        assertArrayEquals(new Integer[]{9, 13, 14, 15, 17, 20, 26, 64,
                72}, tree.listData());
    }

    @Test
    void testRemoveOneChildNode() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);

        tree.remove(14);

        assertEquals(9, tree.size());
        assertArrayEquals(new Integer[]{6, 9, 13, 15, 17, 20, 26, 64,
                72}, tree.listData());
    }

    @Test
    void testRemoveTwoChildrenNode() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);

        tree.remove(20);

        assertEquals(9, tree.size());
        assertArrayEquals(new Integer[]{6, 9, 13, 14, 15, 17, 26, 64,
                72}, tree.listData());
    }

    @Test
    void testRemoveTwoChildrenRootNode() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);
        tree.remove(15);
        assertEquals(9, tree.size());
        assertArrayEquals(new Integer[]{6, 9, 13, 14, 17, 20, 26, 64,
                72}, tree.listData());
    }

    void testRemoveAndReplaceWithNodeAndRightTree() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
          tree.insert(17); // Node to replace 15 and
          tree.insert(18); // its left right subtree
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);

        tree.remove(15);

        assertEquals(10, tree.size());
        assertArrayEquals(new Integer[]{6, 9, 13, 14, 17, 18, 20, 26, 64,
                72}, tree.listData());
    }

    @Test
    void testRemoveKo() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);

        tree.remove(100);
        
        assertEquals(10, tree.size());
        assertArrayEquals(new Integer[]{6, 9, 13, 14, 15, 17, 20, 26,
                64, 72}, tree.listData());
    }

}
