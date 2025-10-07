package com.test.huffman.gpt2;

import java.io.*;
import java.util.*;

import static com.test.huffman.gpt2.HuffmanTool.*;

/*
 * Caution:
 * There is a bug which is that the symbol of carriage return is neither encoded nor decoded.
 * Reason of that:
 * The readLine() doesn't read carriage return.
 * */

public class HuffmanEncodingFileIO {




    public static void main(String[] args) {
        String inputFile = "D://input.txt";
        String encodedFile = "D://encoded.bin";
        String decodedFile = "D://decoded.txt";

        // Read input text from file
        StringBuilder inputText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {   //try with resources
            String line;
            while ((line = reader.readLine()) != null) {
                inputText.append(line);
                String separator = System.getProperty("line.separator");   //append "carriage return". note by me.
                inputText.append(separator);
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
