import java.util.*;

public class AVL_Tree<T extends Comparable<T>> implements SortedSet<T> {

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

    private Node<T> root = null;
    private int size = 0;

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
                getBalance(current);
                if (Math.abs(current.balance) > 1) {
                    restructuring(current);
                    break;
                }
                current = getParent(current);
            }
        }
        size++;
        return true;
    }

    /*
    Нахождение высоты заданной вершины
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

    private void getBalance(Node<T> node) {
        int left_height = height(node.left);
        int right_height = height(node.right);
        node.balance = right_height - left_height;
    }

    /*
    Перестройка дерева
     */

    private void restructuring(Node<T> node) {
        System.out.println("Нужна перестройка");
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

    public T first() {
        return null;
    }

    public T last() {
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root != null;
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
