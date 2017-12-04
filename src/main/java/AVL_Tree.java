import java.util.*;

public class AVL_Tree<T extends Comparable<T>> implements SortedSet<T> {

    private Node<T> root = null;
    private int size = 0;

    /**
     * Если показатель балансировки равен 0, то поддеревья вершины равны
     * Если он равен -1, то вершина утяжелена слева (левое поддерево больше правого на 1)
     * Если он равен 1, то вершина утяжелена справа (правое поддерево больше левого на 1)
     */

    private static class Node<T> {
        T value;
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
        Node<T> parent = new Node<>(null, 0);
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

    @Override
    public boolean add(T t) {
        Node<T> findNode = find(t);
        Node<T> current;
        int comparison = findNode == null ? -1 : t.compareTo(findNode.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t, 0);
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
                    addRestructuring(current, newNode);
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

    private void addRestructuring(Node<T> node, Node<T> addedNode) {
        Node<T> rightNode = node.right;
        Node<T> leftNode = node.left;
        SubTreesAndSons son = SubTreesAndSons.LEFT_SON;
        SubTreesAndSons subTree = SubTreesAndSons.LEFT_SUBTREE;

        /*
        Определяем сына и поддерево
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

    /**
     * Добавление в ЛЕВОЕ поддерерво ЛЕВОГО сына опорного узла - rotate_R()
     * Добавление в ПРАВОЕ поддерево ЛЕВОГО сына опорного узла - rotate_LR()
     * Добавление в ЛЕВОЕ поддерево ПРАВОГО сына опорного узла - rotate_RL()
     * Добавление в ПРАВОЕ поддерево ПРАВОГО поддерева сына опорного узла - rotate_L()
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
        if (parent != null) {
            if (parent.value.compareTo(getParent(node).value) > 0)
                parent.left = getParent(node);
            else parent.right = getParent(node);
        }
    }

    private void rotate_RL(Node<T> node) {
        Node<T> parent = getParent(node);
        rotate_R(node.right);
        rotate_L(node);
        if (parent != null) {
            if (parent.value.compareTo(getParent(node).value) > 0)
                parent.left = getParent(node);
            else parent.right = getParent(node);
        }
    }

    private void removeRestructuring(Node<T> node) {
        Node<T> rightNode = node.right;
        Node<T> leftNode = node.left;
        if (node.balance == 2) {
            if (rightNode.balance == 0 || rightNode.balance == 1)
                rotate_L(node);
            else rotate_RL(node);
        } else {
            if (node.balance == -2) {
                if (leftNode.balance == -1 || leftNode.balance == 0)
                    rotate_R(node);
                else rotate_LR(node);
            }
        }
    }

    /**
     * Рассматриваем два случая: 1) ЛЕВОЕ поддерево стало короче ПРАВОГО на 2 уровня
     * 2) ПРАВОЕ поддерево стало короче ЛЕВОГО на 2 уровня
     * В первом случае: а) У ПРАВОГО сына высота ПРАВОГО поддерева >= высоте ЛЕВОГО поддерева
     * б) У ПРАВОГО сына высота ПРАВОГО поддерева < высоты ЛЕВОГО поддерева
     * Во втором случае: а) У ЛЕВОГО сына высота ЛЕВОГО поддерева >= высоте ПРАВОГО
     * б) у ЛЕВОГО сына высота ЛЕВОГО поддерева < высоты ПРАВОГО поддерева
     */

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        Node<T> currentNode = find(root, (T) o);
        if (!o.equals(currentNode.value))
            return false;
        Node<T> parentNode = getParent(currentNode);
        boolean isHeader = false;
        if (parentNode == null)
            isHeader = true;
        /*
         Когда currentNode лист
         */

        if (currentNode.left == currentNode.right && isHeader) {
            root = null;
        } else if (currentNode.left == currentNode.right) {
            if (parentNode.value.compareTo(currentNode.value) > 0)
                parentNode.left = null;
            else
                parentNode.right = null;
        } else {

        /*
        Обычные сучаи
         */
            if (currentNode.right == null && isHeader || currentNode.left == null && isHeader) {
                root = currentNode.right == null ? currentNode.left : currentNode.right;
            } else if (currentNode.right == null) {
                if (parentNode.value.compareTo(currentNode.value) > 0)
                    parentNode.left = currentNode.left;
                else
                    parentNode.right = currentNode.left;
            } else if (currentNode.left == null) {
                if (parentNode.value.compareTo(currentNode.value) > 0)
                    parentNode.left = currentNode.right;
                else
                    parentNode.right = currentNode.right;
            } else {
                Node<T> node = getParent(minNode(currentNode.right));
                Node<T> minLeft = minNode(currentNode.right);
                if (node.value.compareTo(minLeft.value) > 0)
                    node.left = minLeft.right;
                else
                    node.right = minLeft.right;
                currentNode.value = minLeft.value;
            }
        }

        Node<T> current = parentNode;
        while (current != null) {
            setBalance(current);
            if (Math.abs(current.balance) > 1) {
                removeRestructuring(current);
            }
            current = getParent(current);
        }
        size--;
        return true;
    }

    /*
    Нахождение минимального элемента
     */

    private Node<T> minNode(Node<T> startNode) {
        if (startNode.left == null)
            return startNode;
        else return minNode(startNode.left);
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

    boolean isAVL() {
        return root != null && checkBalance(root) && checkInvariant(root);
    }

    /*
    Проверка показателя балансировки каждой вершины дерева (обход в прямом порядке)
     */

    private boolean checkBalance(Node<T> node) {
        Stack<Node<T>> stack = new Stack<>();
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


    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return null;
    }

    @Override
    public T first() {
        Node<T> node = root;
        if (node == null) throw new NoSuchElementException();
        while (node.left != null)
            node = node.left;
        return node.value;
    }

    @Override
    public T last() {
        Node<T> node = root;
        if (node == null) throw new NoSuchElementException();
        while (node.right != null)
            node = node.right;
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> findNode = find(t);
        return findNode != null && t.compareTo(findNode.value) == 0;
    }

    public class AVLTreeIterator implements Iterator<T> {
        private Stack<Node<T>> treeNodes;
        private Node<T> stackNode;

        private AVLTreeIterator() {
            treeNodes = new Stack<>();
            stackRebuild();
        }

        private Node<T> findNext() {
            stackNode = treeNodes.pop();
            if (stackNode.right != null) {
                Node<T> currentNode = stackNode.right;
                while (currentNode != null) {
                    treeNodes.push(currentNode);
                    currentNode = currentNode.left;
                }
            }
            return stackNode;
        }

        private void stackRebuild() {
            Node<T> node = root;
            while (node != null) {
                if (treeNodes.search(node) == -1) treeNodes.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !treeNodes.isEmpty();
        }

        @Override
        public T next() {
            findNext();
            if (stackNode == null) throw new NoSuchElementException();
            return stackNode.value;
        }

        @Override
        public void remove() {
            /*
            TODO();
             */
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new AVLTreeIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Iterator<T> iterator = this.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        T1[] array = a.length >= size ? a :
                (T1[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size);
        Iterator<T> iterator = this.iterator();
        if (a.length > size) array = Arrays.copyOf(array, size);
        int i = 0;
        while (iterator.hasNext()) {
            array[i] = (T1) iterator.next();
            i++;
        }
        return array;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o)) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c)
            if (!add(t)) return false;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> c) {
        List<T> list = new ArrayList<>();
        for (Object o : c) {
            if (contains(o))
                list.add((T) o);
        }
        clear();
        addAll(list);
        return true;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c)
            if (!remove(o)) return false;
        return true;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

}
