public class BinaryTree<T extends Comparable<T>> { // дерево обобщенного типа. прописываем, что значение поддерживает
                                                   // сравнение, т.к. дерево бинарное(интерфейс comparable)
    private Node root; // корень в любом дереве

    public boolean contains(T value) {// метод поиска элемента по значению
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            }
            if (node.value.compareTo(value) > 0) {// метод compareTo() возвращает значение положительное или
                                                  // отрицательное. если оно положительное, то нода больше значения и
                                                  // смотрим левого ребенка(который всегда меньше родителя) если
                                                  // наоборот - то смотрим правого, потому что он всегда больше родителя
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    public boolean add(T value) {
        if (root != null) {
            Boolean result = addNode(value, root);
            root = rebalancing(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.value = value;
            root.color = Color.BLACK;
            return true;
        }
    }

    private boolean addNode(T value, Node node) {
        Node newNode = new Node();
        newNode.value = value;
        newNode.color = Color.RED;
        if (node.value == value) {
            return false;
        } else {
            if (node.value.compareTo(value) > 0) {
                // идем влево
                if (node.left != null) {
                    boolean result = addNode(value, node.left);
                    node.left = rebalancing(node.left);
                    return result;
                } else {
                    node.left = newNode;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = addNode(value, node.right);
                    node.right = rebalancing(node.right);
                    return result;
                } else {
                    node.right = newNode;
                    return true;
                }
            }
        }

    }

    private Node rebalancing(Node node) {
        boolean needRebal;
        do {
            needRebal = false;
            if ((node != null && node.right.color == Color.RED)
                    && (node.left == null || node.left.color == Color.BLACK)) {
                node = rightTurn(node);
                needRebal = true;
            }
            if ((node.left != null && node.left.color == Color.RED)
                    && (node.left.left != null && node.left.left.color == Color.RED)) {
                node = leftTurn(node);
                needRebal = true;
            }
            if (node.left != null && node.left.color == Color.RED && node.right != null
                    && node.right.color == Color.RED) {
                changingColor(node);
                needRebal = true;
            }

        } while (needRebal);
        return node;
    }

    private Node leftTurn(Node parentNode) {
        Node leftChild = parentNode.left;
        Node leftRightChild = leftChild.right;
        parentNode.left = leftRightChild;
        leftChild.right = parentNode;
        leftChild.color = parentNode.color;
        parentNode.color = Color.RED;
        return leftChild;
    }

    private Node rightTurn(Node parentNode) {
        Node rightChild = parentNode.right;
        Node rightLeftChild = rightChild.left;
        parentNode.right = rightLeftChild;
        rightChild.left = parentNode;
        rightChild.color = parentNode.color;
        parentNode.color = Color.RED;
        return rightChild;
    }

    private void changingColor(Node parent) {
        parent.color = Color.RED;
        parent.left.color = Color.BLACK;
        parent.right.color = Color.BLACK;
    }

    private class Node {
        private T value; // значение может быть типа Т
        private Node left;
        private Node right;
        private Color color;
    }

    private enum Color {
        RED, BLACK
    }
}
