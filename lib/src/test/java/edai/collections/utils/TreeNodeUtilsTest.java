package edai.collections.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import edai.collections.BinaryTree;

public class TreeNodeUtilsTest {

    BinaryTree<Integer> tree;

    @BeforeEach
    void setup() {
        tree = new BinaryTree<>();
    }

    @Test
    void listPath() {
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
        assertArrayEquals(new Integer[] { 15, 20, 64, 26 }, TreeNodeUtils.listPath(tree.getRoot(), 26));
    }

    @Test
    void listPathNotFound() {
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
        assertArrayEquals(new Integer[] {}, TreeNodeUtils.listPath(tree.getRoot(), 100));
    }

    @Test
    void listPathEmpty() {
        assertArrayEquals(new Integer[] {}, TreeNodeUtils.listPath(tree.getRoot(), 100));
    }

    @Test
    void listLeavesOneTreeNode() {
        tree.insert(15);

        assertArrayEquals(new Integer[] { 15 }, TreeNodeUtils.listLeaves(tree.getRoot()));
    }

    @Test
    void listLeavesEmpty() {
        assertArrayEquals(new Integer[] {}, TreeNodeUtils.listLeaves(tree.getRoot()));
    }

    @Test
    void listLeavesThreeNodes() {
        tree.insert(15);
        tree.insert(9);
        tree.insert(20);

        assertArrayEquals(new Integer[] { 9, 20 }, TreeNodeUtils.listLeaves(tree.getRoot()));
    }
       

    @Test
    void listLeaves() {
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
        tree.insert(16);
        assertArrayEquals(new Integer[] { 6, 13, 16, 26, 72 }, TreeNodeUtils.listLeaves(tree.getRoot()));
    }
}
