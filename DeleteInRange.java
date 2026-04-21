public class DeleteInRange {
    private class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    public class BRTreeRangeDelete {
        public Node deleteInRange(Node root, int a, int b) {
            if (root == null) return null;
            root.left = deleteInRange(root.left, a, b);
            root.right = deleteInRange(root.right, a, b);
            if (root.val >= a && root.val <= b) {
                return deleteNode(root);
            }
            return root;
        }


        private Node deleteNode(Node node) {
            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node successor = findMin(node.right);
            node.val = successor.val;
            node.right = deleteMin(node.right);
            return node;
        }

        private Node findMin(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private Node deleteMin(Node node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);
            return node;
        }
    }
}
