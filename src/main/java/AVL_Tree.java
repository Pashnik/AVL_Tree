import java.util.*;

public class AVL_Tree<T extends Comparable<T>> implements SortedSet<T> {

    private Node<T> root = null;
    private int size = 0;

    /*
    Если показатель балансировки равен 0, то поддеревья вершины равны
    Если он равен -1, то вершина утяжелена слева (левое поддерево больше правого на 1)
    Если он равен 1, то вершина утяжелена справа (правое поддерево больше левого на 1)
     */

    private static class Node<T> {
        final T value;
        int balance;
        Node<T> left = null;
        Node<T> right = null;

        Node(T value, int balance) {
            this.value = value;
            this.balance = balance;
        }
    }

    /*
    Нахождения родителя заданного узла
     */

    private Node<T> getParent(Node<T> child) {
        if (child == root) return null;
        Node<T> intermediateNode = root;
        Node<T> parent = new Node<T>(null, 0);
        while (intermediateNode != null) {
            if (intermediateNode.left == child) {
                parent = intermediateNode;
                break;
            }
            if (intermediateNode.right == child) {
                parent = intermediateNode;
                break;
            }
            if (child.value.compareTo(intermediateNode.value) > 0) {
                intermediateNode = intermediateNode.right;
            } else {
                if (child.value.compareTo(intermediateNode.value) < 0) {
                    intermediateNode = intermediateNode.left;
                }
            }
        }
        return parent;
    }

    /*
    Добавление узла в дерево
     */

    public boolean add(T t) {
        Node<T> findNode = find(t);
        Node<T> current;
        int comparison = findNode == null ? -1 : t.compareTo(findNode.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<T>(t, 0);
        if (findNode == null)
            root = newNode;
        else {
            if (comparison < 0) {
                assert findNode.left == null;
                findNode.left = newNode;
            } else {
                assert findNode.right == null;
                findNode.right = newNode;
            }
            current = newNode;
            while (current != null) {
                setBalance(current);
                if (Math.abs(current.balance) > 1) {
                    restructuring(current, newNode);
                }
                current = getParent(current);
            }
        }
        size++;
        return true;
    }

    /*
    Нахождение высоты заданного узла
     */

    private int height(Node<T> node) {
        int treeHeight = 0;
        int left;
        int right;
        if (node != null) {
            left = height(node.left);
            right = height(node.right);
            if (left > right) treeHeight = left + 1;
            else treeHeight = right + 1;
        }
        return treeHeight;
    }

    /*
    Вычисление показателя балансировки вершины
     */

    private void setBalance(Node<T> node) {
        int left_height = height(node.left);
        int right_height = height(node.right);
        node.balance = right_height - left_height;
    }

    /*
    Перестройка дерева
     */

    private void restructuring(Node<T> node, Node<T> addedNode) {
        Node<T> rightNode = node.right;
        Node<T> leftNode = node.left;
        SubTreesAndSons son = SubTreesAndSons.LEFT_SON;
        SubTreesAndSons subTree = SubTreesAndSons.LEFT_SUBTREE;

        /*
        Определяем сына и поодерево
         */

        if (node.value.compareTo(addedNode.value) < 0) {
            son = SubTreesAndSons.RIGHT_SON;
            if (rightNode.value.compareTo(addedNode.value) < 0)
                subTree = SubTreesAndSons.RIGHT_SUBTREE;
        } else {
            if (leftNode.value.compareTo(addedNode.value) < 0)
                subTree = SubTreesAndSons.RIGHT_SUBTREE;
        }

        /*
        Определяем случай
         */

        if (son == SubTreesAndSons.LEFT_SON) {
            if (subTree == SubTreesAndSons.LEFT_SUBTREE) rotate_R(node);
            else rotate_LR(node);
        } else {
            if (subTree == SubTreesAndSons.RIGHT_SUBTREE) rotate_L(node);
            else rotate_RL(node);
        }
    }

    /*
    Повороты: Поворачиваем и пересчитываем показатели балансировки
     */

    private void rotate_R(Node<T> node) {
        Node<T> left_Node = node.left;
        node.left = left_Node.right;
        left_Node.right = node;
        setBalance(node);
        setBalance(left_Node);
    }

    private void rotate_L(Node<T> node) {
        Node<T> right_Node = node.right;
        node.right = right_Node.left;
        right_Node.left = node;
        setBalance(node);
        setBalance(right_Node);
    }

    private void rotate_LR(Node<T> node) {
        rotate_L(node.left);
        rotate_R(node);
        setBalance(node);
        setBalance(node.left);
        setBalance(node.right);
    }

    private void rotate_RL(Node<T> node) {
        rotate_R(node.right);
        rotate_L(node);
        setBalance(node);
        setBalance(node.left);
        setBalance(node.right);
    }

    public boolean remove(Object o) {
        return false;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }


    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }


    public SortedSet<T> subSet(T fromElement, T toElement) {
        return null;
    }

    public SortedSet<T> headSet(T toElement) {
        return null;
    }

    public SortedSet<T> tailSet(T fromElement) {
        return null;
    }

    /*
    Возвращение минимального элемента
     */
    public T first() {
        Node<T> node = root;
        if (node == null) throw new NoSuchElementException();
        while (node.left != null)
            node = node.left;
        return node.value;
    }

    /*
   Возвращение максимального элемента
    */
    public T last() {
        Node<T> node = root;
        if (node == null) throw new NoSuchElementException();
        while (node.right != null)
            node = node.right;
        return node.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public void clear() {
    }

    public Comparator<? super T> comparator() {
        return null;
    }

}
