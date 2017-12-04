import org.junit.Test;

import java.util.*;

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

    @Test
    public void remove() {

        /*
                  Binary Tree Visualisation

                              60
                            28   80
                             59 68
                            40    70
                          30  45    75
                                  74  76
        */


        AVL_Tree<Integer> binaryTree = new AVL_Tree<>();
        binaryTree.addAll(Arrays.asList(60, 28, 59, 40, 30, 45, 80, 68, 70, 75, 74, 76));

        binaryTree.remove(28);
        assertTrue(binaryTree.isAVL());
        assertTrue(!binaryTree.contains(28));

        assertEquals(11, binaryTree.size());

        binaryTree.remove(74);
        assertTrue(binaryTree.isAVL());
        assertTrue(!binaryTree.contains(74));

        assertEquals(10, binaryTree.size());


        binaryTree.remove(60);
        assertTrue(binaryTree.isAVL());
        assertTrue(!binaryTree.contains(60));

        assertEquals(9, binaryTree.size());


        binaryTree.remove(80);
        assertTrue(!binaryTree.contains(80));
        assertTrue(binaryTree.isAVL());

        assertEquals(8, binaryTree.size());


        binaryTree.remove(40);
        assertTrue(binaryTree.isAVL());
        assertTrue(!binaryTree.contains(40));

        assertEquals(7, binaryTree.size());

        assertTrue(!binaryTree.remove(1));

        assertEquals(76, binaryTree.last().intValue());

        /*
        Корневые тесты
         */

        AVL_Tree<Integer> treeWithoutChildren = new AVL_Tree<>();
        treeWithoutChildren.add(1);
        treeWithoutChildren.remove(1);
        assertEquals(0, treeWithoutChildren.size());
        assertFalse(treeWithoutChildren.isAVL());
        assertTrue(!treeWithoutChildren.contains(1));

        AVL_Tree<Integer> treeWithOneChild = new AVL_Tree<>();
        treeWithOneChild.add(5);
        treeWithOneChild.add(1);
        treeWithOneChild.remove(5);
        assertEquals(1, treeWithOneChild.size());
        assertTrue(treeWithOneChild.isAVL());
        assertTrue(!treeWithOneChild.contains(5));


        AVL_Tree<Integer> treeWithTwoChildren = new AVL_Tree<>();
        treeWithTwoChildren.addAll(Arrays.asList(15, 8, 20, 19, 21, 7, 9));

        treeWithTwoChildren.remove(15);
        assertEquals(6, treeWithTwoChildren.size());
        assertTrue(treeWithTwoChildren.isAVL());
        assertTrue(!treeWithTwoChildren.contains(15));


        AVL_Tree<Integer> secondTree = new AVL_Tree<>();
        secondTree.addAll(Arrays.asList(80, 60, 100, 50, 40, 65, 62, 68, 66, 90, 95, 110, 105, 120));

        secondTree.remove(65);
        assertTrue(secondTree.isAVL());
        assertFalse(secondTree.contains(65));

        secondTree.remove(100);
        assertTrue(secondTree.isAVL());
        assertFalse(secondTree.contains(100));

        secondTree.remove(80);
        assertTrue(secondTree.isAVL());
        assertFalse(secondTree.contains(80));


        List removeList = Arrays.asList(100, 120, 90, 115, 130);
        List addList = Arrays.asList(100, 80, 120, 70, 90, 115, 130, 60, 110, 117, 125, 140, 105, 118, 150);
        List trueList = Arrays.asList(80, 70, 110, 117, 125, 105, 140, 118, 150);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(addList);
        avl_tree.remove(60);
        assertFalse(avl_tree.contains(60));
        assertTrue(avl_tree.isAVL());
        avl_tree.removeAll(removeList);
        assertTrue(avl_tree.containsAll(trueList));
        assertTrue(avl_tree.isAVL());
        assertFalse(avl_tree.containsAll(removeList));
    }

    @Test
    public void clear() {
        List<Integer> list1 = new ArrayList<>();
        List list = Arrays.asList(120, 2, 4, 5, 6, 7, 1, 110);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(list);
        avl_tree.clear();
        assertFalse(avl_tree.containsAll(list));
        assertTrue(avl_tree.containsAll(list1));
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        avl_tree.addAll(list2);
        assertTrue(avl_tree.containsAll(list2));
        assertTrue(avl_tree.isAVL());
    }

    @Test
    public void retainAll() {
        List list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List secondList = Arrays.asList(2, 3, 4, 5);
        List list1 = Arrays.asList(1, 6, 7);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(list);
        avl_tree.retainAll(secondList);
        assertTrue(avl_tree.containsAll(secondList));
        assertFalse(avl_tree.containsAll(list1));
    }

    @Test
    public void iteratorTests() {
        List<Integer> listForBinaryTree = Arrays.asList(60, 50, 45, 40, 55, 51, 80, 75, 77, 85, 82, 90);
        List<Integer> iteratorList = new ArrayList<>();
        AVL_Tree<Integer> binaryTree = new AVL_Tree<>();

        binaryTree.addAll(listForBinaryTree);
        Collections.sort(listForBinaryTree);

        Iterator<Integer> iterator = binaryTree.iterator();

        while (iterator.hasNext()) {
            iteratorList.add(iterator.next());
        }
        assertEquals(listForBinaryTree, iteratorList);
    }

   /* @Test
    public void iteratorRemove() {
       List list = Arrays.asList(10, 5, 15, 2, 6, 11, 16);
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        avl_tree.addAll(list);
        Iterator<Integer> iterator = avl_tree.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertTrue(avl_tree.isEmpty());

        List list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        AVL_Tree<Integer> avl_tree1 = new AVL_Tree<>();
        avl_tree1.addAll(list1);
        Iterator<Integer> iterator1 = avl_tree1.iterator();

        while (iterator1.hasNext()) {
            int i = iterator1.next();
            if (i % 2 != 0) iterator1.remove();
        }
    }
    */

    @Test
    public void toArray() {
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        List list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer[] b = {1, 2, 3, 4, 5, 6};
        avl_tree.addAll(list);
        Object[] a = avl_tree.toArray();
        assertTrue(Arrays.equals(b, a));
    }

    @Test
    public void toArr() {
        AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
        List list = Arrays.asList(1, 2, 7, 5, 6);
        avl_tree.addAll(list);
        Integer[] a = {1, 2, 5, 6, 7};
        Integer[] b = avl_tree.toArray(new Integer[5]);
        assertTrue(Arrays.equals(a, b));
        Integer[] c = avl_tree.toArray(new Integer[1]);
        assertTrue(Arrays.equals(a, c));
    }

}
