import org.junit.Test;

public class AVL_Tests {
    @Test
    public void add(){
        AVL_Tree<Integer> avl_tree = new AVL_Tree<Integer>();
        avl_tree.add(5);
        avl_tree.add(6);
        avl_tree.add(4);
        avl_tree.add(3);
        avl_tree.add(2);
    }
}
