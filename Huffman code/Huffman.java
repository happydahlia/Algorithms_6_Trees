import java.util.*;

public class Huffman {

    private static class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;
        int order;

        Node(char ch, int freq, int order) {
            this.ch = ch;
            this.freq = freq;
            this.order = order;
        }

        Node(Node left, Node right, int order) {
            this.ch = '\0';
            this.freq = left.freq + right.freq;
            this.left = left;
            this.right = right;
            this.order = order;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node other) {
            if (this.freq != other.freq) {
                return this.freq - other.freq;
            }
            return this.order - other.order;
        }
    }

    private Map<Character, Integer> frequencies = new TreeMap<>();
    private Map<Character, String> codes = new TreeMap<>();
    private Node root;
    private String encodedText = "";
    private int orderCounter = 0;

    public void frequencyCount(String text) {
        frequencies.clear();

        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
    }

    public void buildHuffman() {
        PriorityQueue<Node> heap = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            heap.add(new Node(entry.getKey(), entry.getValue(), orderCounter++));
        }

        if (heap.isEmpty()) {
            root = null;
            return;
        }

        while (heap.size() > 1) {
            Node left = heap.poll();
            Node right = heap.poll();
            Node parent = new Node(left, right, orderCounter++);
            heap.add(parent);
        }

        root = heap.poll();
    }

    public void genCode() {
        codes.clear();

        if (root == null) {
            return;
        }

        if (root.isLeaf()) {
            codes.put(root.ch, "0");
        } else {
            genCode(root, "");
        }
    }

    private void genCode(Node node, String code) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            codes.put(node.ch, code);
            return;
        }

        genCode(node.left, code + "0");
        genCode(node.right, code + "1");
    }

    public String encode(String text) {
        frequencyCount(text);
        buildHuffman();
        genCode();

        StringBuilder sb = new StringBuilder();

        for (char c : text.toCharArray()) {
            sb.append(codes.get(c));
        }

        encodedText = sb.toString();
        return encodedText;
    }

    public void printStats() {
        System.out.println("Frequencies:");
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            if (entry.getKey() == ' ') {
                System.out.println("(space) : " + entry.getValue());
            } else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        System.out.println("\nHuffman Codes:");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            if (entry.getKey() == ' ') {
                System.out.println("(space): " + entry.getValue());
            } else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        System.out.println("\nEncoded:");
        System.out.println(encodedText);
    }

    public String getEncodedText() {
        return encodedText;
    }
}

