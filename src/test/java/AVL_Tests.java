import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AVL_Tests {

    @Test
    public void add_rotate_R() {
        AVL_Tree<Integer> integerAVL_tree = new AVL_Tree<Integer>();
        integerAVL_tree.add(8);
        integerAVL_tree.add(5);
        integerAVL_tree.add(12);
        integerAVL_tree.add(6);
        integerAVL_tree.add(4);
        integerAVL_tree.add(11);
        integerAVL_tree.add(14);
        integerAVL_tree.add(3);
        integerAVL_tree.add(2);
        assertTrue(integerAVL_tree.isAVL());

        AVL_Tree<Integer> avl_tree = new AVL_Tree<Integer>();
        avl_tree.add(5);
        avl_tree.add(6);
        avl_tree.add(4);
        avl_tree.add(3);
        avl_tree.add(2);
        assertTrue(avl_tree.isAVL());

        AVL_Tree<Integer> avl_tree1 = new AVL_Tree<Integer>();
        avl_tree1.add(6);
        avl_tree1.add(7);
        avl_tree1.add(5);
        avl_tree1.add(3);
        avl_tree1.add(2);
        assertTrue(avl_tree1.isAVL());


        AVL_Tree<Integer> avl_tree2 = new AVL_Tree<Integer>();
        avl_tree2.add(8);
        avl_tree2.add(12);
        avl_tree2.add(5);
        avl_tree2.add(4);
        avl_tree2.add(6);
        avl_tree2.add(3);
        assertTrue(avl_tree2.isAVL());
    }

    @Test
    public void add_rotate_L() {
        AVL_Tree<Integer> integerAVL_tree = new AVL_Tree<Integer>();
        integerAVL_tree.add(8);
        integerAVL_tree.add(5);
        integerAVL_tree.add(12);
        integerAVL_tree.add(4);
        integerAVL_tree.add(6);
        integerAVL_tree.add(11);
        integerAVL_tree.add(14);
        integerAVL_tree.add(10);
        integerAVL_tree.add(13);
        integerAVL_tree.add(16);
        integerAVL_tree.add(20);
        assertTrue(integerAVL_tree.isAVL());

        AVL_Tree<Integer> avl_tree = new AVL_Tree<Integer>();
        avl_tree.add(5);
        avl_tree.add(1);
        avl_tree.add(6);
        avl_tree.add(7);
        avl_tree.add(8);
        assertTrue(avl_tree.isAVL());
    }

    @Test
    public void add_rotate_LR() {
        AVL_Tree<Integer> avl_tree = new AVL_Tree<Integer>();
        avl_tree.add(100);
        avl_tree.add(60);
        avl_tree.add(120);
        avl_tree.add(50);
        avl_tree.add(70);
        avl_tree.add(115);
        avl_tree.add(130);
        avl_tree.add(65);
        avl_tree.add(80);
        avl_tree.add(110);
        avl_tree.add(90);
        assertTrue(avl_tree.isAVL());
    }

    @Test
    public void add_rotate_RL() {
        AVL_Tree<Integer> avlTree = new AVL_Tree<Integer>();
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(60);
        avlTree.add(55);
        avlTree.add(32);
        avlTree.add(25);
        avlTree.add(65);
        avlTree.add(24);
        avlTree.add(31);
        avlTree.add(57);
        avlTree.add(62);
        avlTree.add(70);
        avlTree.add(56);
        assertTrue(avlTree.isAVL());
    }

    @Test
    public void add() {
        AVL_Tree<Integer> avl_tree = new AVL_Tree<Integer>();
        avl_tree.add(13);
        avl_tree.add(14);
        avl_tree.add(11);
        avl_tree.add(12);
        avl_tree.add(4);
        avl_tree.add(3);
        assertTrue(avl_tree.isAVL());
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
