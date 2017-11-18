import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AVL_Tests {
    @Test
    public void add() {
        AVL_Tree<Integer> avl_tree = new AVL_Tree<Integer>();
        avl_tree.add(5);
        avl_tree.add(6);
        avl_tree.add(4);
        avl_tree.add(3);
        avl_tree.add(2);
    }

    @Test
    public void first() {
        AVL_Tree<Integer> avlTree = new AVL_Tree<Integer>();
        avlTree.add(70);
        avlTree.add(100);
        avlTree.add(10);
        avlTree.add(120);
        assertEquals(10, avlTree.first().intValue());
    }

    @Test
    public void last() {
        AVL_Tree<Integer> avlTree = new AVL_Tree<Integer>();
        avlTree.add(70);
        avlTree.add(100);
        avlTree.add(10);
        avlTree.add(120);
        assertEquals(120, avlTree.last().intValue());
    }

    @Test
    public void isEmpty() {
        AVL_Tree<String> a = new AVL_Tree<String>();
        assertTrue(a.isEmpty());
        a.add("A");
        assertFalse(a.isEmpty());
    }
}
