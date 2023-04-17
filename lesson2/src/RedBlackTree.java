public class RedBlackTree {
    private Node root;


    private class Node {
        private int value;
        private Color color;
        private Node leftChild;
        private Node rigthChild;

        @Override
        public String toString() {
            return "Node{value=" + value + ", color=" + color + "}";
        }
    }

    private enum Color {
        RED, BLACK
    }

}
