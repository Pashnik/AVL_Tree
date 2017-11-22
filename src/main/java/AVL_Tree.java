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
        Node<T> parent = getParent(node);
        Node<T> left_Node = node.left;
        node.left = left_Node.right;
        left_Node.right = node;

        if (parent != null) {
            if (parent.value.compareTo(left_Node.value) > 0)
                parent.left = left_Node;
            else parent.right = left_Node;
        } else {
            root = left_Node;
        }

        setBalance(node);
        setBalance(left_Node);
    }

    private void rotate_L(Node<T> node) {
        Node<T> parent = getParent(node);
        Node<T> right_Node = node.right;
        node.right = right_Node.left;
        right_Node.left = node;

        if (parent != null) {
            if (parent.value.compareTo(right_Node.value) > 0)
                parent.left = right_Node;
            else parent.right = right_Node;
        } else {
            root = right_Node;
        }

        setBalance(node);
        setBalance(right_Node);
    }

    private void rotate_LR(Node<T> node) {
        Node<T> parent = getParent(node);
        rotate_L(node.left);
        rotate_R(node);
        parent.left = getParent(node);
    }

    private void rotate_RL(Node<T> node) {
        Node<T> parent = getParent(node);
        rotate_R(node.right);
        rotate_L(node);
        parent.left = getParent(node);
    }

    public boolean remove(Object o) {
        /*
        TODO()
         */
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

    public boolean isAVL() {
        return root != null && checkBalance(root) && checkInvariant(root);
    }

    /*
    Проверка показателя балансировки каждой вершины дерева (обход в прямом порядке)
     */

    private boolean checkBalance(Node<T> node) {
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> intermediate;
        Node<T> current = node;
        stack.push(current);
        while (!stack.isEmpty()) {
            if (current.left != null) {
                current = current.left;
                stack.push(current);
            } else {
                intermediate = stack.pop();
                if (Math.abs(intermediate.balance) > 1) return false;
                if (intermediate.right != null)
                    stack.push(intermediate.right);
            }

        }
        return true;
    }

    /*
    Проверка на бинарное дерево поиска
     */

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
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
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> findNode = find(t);
        return findNode != null && t.compareTo(findNode.value) == 0;
    }

    public class AVLTreeIterator implements Iterator<T> {
        private Stack<Node<T>> treeNodes;
        private Node<T> node;
        private Node<T> currentNode;
        private Node<T> stackNode;

        private AVLTreeIterator() {
            treeNodes = new Stack<Node<T>>();
            node = root;
            while (node != null) {
                treeNodes.push(node);
                node = node.left;
            }
        }

        private Node<T> findNext() {
            stackNode = treeNodes.pop();
            if (stackNode.right != null) {
                currentNode = stackNode.right;
                while (currentNode != null) {
                    treeNodes.push(currentNode);
                    currentNode = currentNode.left;
                }
            }
            return stackNode;
        }

        public boolean hasNext() {
            return !treeNodes.isEmpty();
        }

        public T next() {
            findNext();
            if (stackNode == null) throw new NoSuchElementException();
            return stackNode.value;
        }

        public void remove() {

        }
    }

    public Iterator<T> iterator() {
        return new AVLTreeIterator();
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
