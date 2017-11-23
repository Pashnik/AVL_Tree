import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AVL_Tests {

    @Test
    public void add_rotate_R() {
        List list = Arrays.asList(8, 5, 12, 6, 4, 11, 14, 3, 2);
        AVL_Tree<Integer> integerAVL_tree = new AVL_Tree<>();
        integerAVL_tree.addAll(list);
        assertTrue(integerAVL_tree.isAVL());
        assertTrue(integerAVL_tree.containsAll(list));
        assertEquals(9, integerAVL_tree.size());

        List list1 = Arrays.asList(5, 6, 4, 3, 2);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(list1);
        assertTrue(avl_tree.isAVL());
        assertTrue(avl_tree.containsAll(list1));

        List list2 = Arrays.asList(6, 7, 5, 3, 2);
        AVL_Tree<Integer> avl_tree1 = new AVL_Tree<>();
        avl_tree1.addAll(list2);
        assertTrue(avl_tree1.isAVL());
        assertTrue(avl_tree1.containsAll(list2));


        List list3 = Arrays.asList(8, 12, 5, 4, 6, 3);
        AVL_Tree<Integer> avl_tree2 = new AVL_Tree<>();
        avl_tree2.addAll(list3);
        assertTrue(avl_tree2.isAVL());
        assertTrue(avl_tree2.containsAll(list3));
    }

    @Test
    public void add_rotate_L() {
        List list = Arrays.asList(8, 5, 12, 4, 6, 11, 14, 10, 13, 16, 20);
        AVL_Tree<Integer> integerAVL_tree = new AVL_Tree<>();
        integerAVL_tree.addAll(list);
        assertTrue(integerAVL_tree.containsAll(list));
        assertEquals(11, integerAVL_tree.size());
        assertTrue(integerAVL_tree.isAVL());

        List list1 = Arrays.asList(5, 1, 6, 7, 8);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(list1);
        assertTrue(avl_tree.isAVL());
        assertTrue(avl_tree.containsAll(list1));
    }

    @Test
    public void add_rotate_LR() {
        List list = Arrays.asList(100, 60, 120, 50, 70, 115, 130, 65, 80, 110, 90);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(list);
        assertTrue(avl_tree.containsAll(list));
        assertTrue(avl_tree.isAVL());
        assertEquals(11, avl_tree.size());
    }

    @Test
    public void add_rotate_RL() {
        List list = Arrays.asList(40, 30, 60, 55, 32, 25, 65, 24, 31, 57, 62, 70, 56);
        AVL_Tree<Integer> avlTree = new AVL_Tree<>();
        avlTree.addAll(list);
        assertTrue(avlTree.isAVL());
        assertTrue(avlTree.containsAll(list));
    }

    @Test
    public void add() {
        List returnList = Arrays.asList(13, 14, 11, 4, 3, 100, 45, 10, 15, 2, 6);
        List list = Arrays.asList(100, 14, 11, 3, 4, 13);
        List list1 = Arrays.asList(15, 45, 10, 6, 2);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(list);
        avl_tree.addAll(list1);
        assertTrue(avl_tree.containsAll(returnList));
        assertTrue(avl_tree.isAVL());
    }

    @Test
    public void first() {
        List list = Arrays.asList(70, 100, 10, 120);
        AVL_Tree<Integer> avlTree = new AVL_Tree<>();
        avlTree.addAll(list);
        assertEquals(10, avlTree.first().intValue());
    }

    @Test
    public void last() {
        List list = Arrays.asList(70, 100, 10, 120);
        AVL_Tree<Integer> avlTree = new AVL_Tree<>();
        avlTree.addAll(list);
        assertEquals(120, avlTree.last().intValue());
    }

    @Test
    public void isEmpty() {
        AVL_Tree<String> a = new AVL_Tree<>();
        assertTrue(a.isEmpty());
        a.add("A");
        assertFalse(a.isEmpty());
    }
}
