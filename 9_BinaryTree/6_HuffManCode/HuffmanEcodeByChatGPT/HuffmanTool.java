package com.test.huffman.gpt2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTool {

    public static Map<Character, String> buildHuffmanCodes(Map<Character, Integer> frequencies) {

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        HuffmanNode root = pq.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodesHelper(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void buildHuffmanCodesHelper(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) return;
        if (root.data != '\0')   //ASCII: 0=null
            huffmanCodes.put(root.data, code);
        buildHuffmanCodesHelper(root.left, code + "0", huffmanCodes);
        buildHuffmanCodesHelper(root.right, code + "1", huffmanCodes);
    }

    public static String encode(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }
        return encodedText.toString();
    }

    public static String decode(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (int i = 0; i < encodedText.length(); i++) {
            char bit = encodedText.charAt(i);
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                decodedText.append(current.data);
                current = root;
            }
        }
        return decodedText.toString();
    }

    public static HuffmanNode buildHuffmanTree(Map<Character, String> huffmanCodes) {
        HuffmanNode root = new HuffmanNode('\0', 0);
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            char character = entry.getKey();
            String code = entry.getValue();
            HuffmanNode current = root;
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '0') {
                    if (current.left == null) {
                        current.left = new HuffmanNode('\0', 0);
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new HuffmanNode('\0', 0);
                    }
                    current = current.right;
                }
            }
            current.data = character;
        }
        return root;
    }

}
