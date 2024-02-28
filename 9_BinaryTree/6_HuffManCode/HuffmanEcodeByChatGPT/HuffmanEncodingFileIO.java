package com.test.huffman.gpt2;

import java.io.*;
import java.util.*;


/*
 * Caution:
 * There is a bug that the symbol of carriage return is not encoded or decoded.
 * */

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = null;
        right = null;
    }

    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class HuffmanEncodingFileIO {
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
        if (root.data != '\0')
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

    public static void main(String[] args) {
        String inputFile = "D://input.txt";
        String encodedFile = "D://encoded.bin";
        String decodedFile = "D://decoded.txt";

        // Read input text from file
        StringBuilder inputText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputText.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate character frequencies
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : inputText.toString().toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        // Build Huffman codes
        Map<Character, String> huffmanCodes = buildHuffmanCodes(frequencies);

        // Encode text using Huffman codes
        String encodedText = encode(inputText.toString(), huffmanCodes);

        // Write encoded data to file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(encodedFile))) {
            outputStream.writeObject(encodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read encoded data from file
        String encodedData = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(encodedFile))) {
            encodedData = (String) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Decode encoded data
        String decodedText = decode(encodedData, buildHuffmanTree(huffmanCodes));

        // Write decoded text to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(decodedFile))) {
            writer.write(decodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Encoding and decoding completed.");
    }
}
