import org.w3c.dom.Node;

public class ListSem3 {
    private Node head;

    private class Node {
        private int value;
        private Node next;
    }

    public void addFirst(int value) {// добавление элемента в начало
        Node node = new Node();
        node.value = value;
        if (head != null) {
            node.next = head;
        }
        head = node;
    }

    // удаление эемента в начале
    public void removeFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    // поиск элемента
    public boolean find(int value) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    public void addLast(int value) { // добавление элемента в конец списка
        Node currentNode = new Node();
        currentNode.value = value;
        if (head == null) {
            head = currentNode;
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = currentNode;
        }

    }

    // удаление последнего элемента списка
    public void removeLast() {
        if (head.next == null) {
            head = null;
            return;
        }
        if (head != null) {
            Node node = head;
            while (node.next != null) {
                if (node.next.next == null) {
                    node.next = null;
                    return;
                } // поймали предпоследнюю ноду
                node = node.next;
            }
        }
    }

    public void revert() {
        Node temp = head;
        revert(head.next, head);
        temp.next = null;
    }

    public void revert(Node node, Node predNode) {
        if (node.next == null) {
            head = node;
        } else {
            revert(node.next, node);
        }
        node.next = node;
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " -> ");
            node = node.next;
        }
    }

}
