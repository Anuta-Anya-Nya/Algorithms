public class Lin2List {
    private Node head;
    private Node tail;

    private class Node {
        private int value;
        private Node next;
        private Node previous;
    }

    public void addFirst(int value) {// добавление элемента в начало
        Node node = new Node();
        node.value = value;
        if (head != null) {
            node.next = head;
            head.previous = node;
        } else {
            tail = node;
        }
        head = node;
    }

    public void removeFirst() {
        if (head != null && head.next != null) {
            head.next.previous = null;
            head = head.next;
        } else {
            head = null;
            tail = null;
        }
    }

    public void addLast(int value) { // добавление элемента в конец списка
        Node currentNode = new Node();
        currentNode.value = value;
        if (tail != null) {
            currentNode.previous = tail;
            tail.next = currentNode;
        } else {
            head = currentNode;
        }
        tail = currentNode;
    }

    // удаление последнего элемента списка
    public void removeLast() {
        if (tail != null && tail.previous != null) {
            tail.previous.next = null;
            tail = tail.previous;
        } else {
            tail = null;
            head = null;
        }
    }

    public void revert() {
        if (head != null && head != tail) {
            Node node = head;
            while (node != null) {
                Node next = node.next;
                Node prev = node.previous;
                if (prev == null) {
                    tail = node;
                }
                if (next == null) {
                    head = node;
                }
                node.previous = next;
                node.next = prev;
                node = next;
            }
        }
    }

    public void sort() { 
        if (head != null) {
            boolean needsort;
            do {
                needsort = false;
                Node node = head;
                while (node.next != null) {
                    Node nextNode = node.next;                    
                    if (node.value > nextNode.value) {
                        int temp = node.value;
                        node.value = nextNode.value;
                        nextNode.value = temp;
                        needsort = true;
                    }
                    node = nextNode;
                        // Node predNode = node.previous;
                        // if (predNode == null) {
                        //     head = nextNode;
                        // } 
                        // if (nextNode.next == null) {
                        //     tail = node;
                        // }
                        // node.next = nextNode.next;
                        // node.previous = nextNode;
                        // nextNode.next = node;
                        // nextNode.previous = predNode;
                        // needsort = true;
                        // if(predNode != null){
                        //     predNode.next = nextNode;
                        // }
                    // } else {
                    //     node = nextNode;
                    // }
                }
            } while (needsort);
        } 
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println();
        System.out.println(head.value);
        System.out.println(tail.value);
    }
}
