import java.io.*;
import java.util.*;

public class Solution {
    // Maximum length of the word can be upto 256
    // Total number of words: 4069308, 7 digits
    // Match count is upto 11 characters
    // So we set the right side field width to 12
    // The total length of the line is 256 + 12 + 1 space = 269
    private static final String HEAD_FORMAT = "%-256s %12s%n";
    private static final String OUTPUT_FORMAT = "%-256s %12d%n";

    public static void main(String[] args) throws IOException {
        String rawFilePath = "/Users/fengsp/Desktop/raw.txt";
        String pureFilePath = "/Users/fengsp/Desktop/pure.txt";
        String predefinedFilePath = "/Users/fengsp/Desktop/predefined.txt";
        String resultFilePath = "/Users/fengsp/Desktop/result.txt";

        // process raw file into pure file with only English letters and spaces
        /* try {
            processFile(rawFilePath, pureFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } */

        // Total number of lines: 74596
        // Total number of words: 4069308
        // check if the pure file contains only English letters and spaces
        // System.out.println(checkAllEnglishLetters(pureFilePath));

        // Select words randomly from the pure file and construct predefined file
        // Total number of selected words: 12744
        // Maximum word length: 42
        // selectWords(pureFilePath, predefinedFilePath);

        try {
            Map<String, Integer> wordFrequencyMap = calculateWordFrequencies(pureFilePath, predefinedFilePath);
            writeFrequenciesToFile(wordFrequencyMap, resultFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> calculateWordFrequencies(String pureFilePath, String predefinedFilePath) throws IOException {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        Map<String, String> lowerOriginalMap = new HashMap<>();

        // Initialize word frequency map with predefined words
        try (BufferedReader candidateReader = new BufferedReader(new FileReader(predefinedFilePath))) {
            String candidateWord;
            while ((candidateWord = candidateReader.readLine()) != null) {
                candidateWord = candidateWord.trim();
                String lowerCandidateWord = candidateWord.toLowerCase();
                if (!candidateWord.isEmpty()) {
                    wordFrequencyMap.put(candidateWord, 0);
                    lowerOriginalMap.put(lowerCandidateWord, candidateWord);
                }
            }
        }

        // Count word frequencies in the pure file
        try (BufferedReader outputReader = new BufferedReader(new FileReader(pureFilePath))) {
            String line;
            while ((line = outputReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    String lowerWord = word.toLowerCase();
                    if (lowerOriginalMap.containsKey(lowerWord)) {
                        String originalWord = lowerOriginalMap.get(lowerWord);
                        wordFrequencyMap.put(originalWord, wordFrequencyMap.get(originalWord) + 1);
                    }
                }
            }
        }

        return wordFrequencyMap;
    }

    public static void writeFrequenciesToFile(Map<String, Integer> wordFrequencyMap, String resultFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFilePath))) {
            writer.write(String.format(HEAD_FORMAT, "Predefined word", "Match count"));
            for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
                writer.write(String.format(OUTPUT_FORMAT, entry.getKey(), entry.getValue()));
            }
        }
    }

    public static void selectWords(String pureFilePath, String predefinedFilePath) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Random random = new Random();
        int lineCount = 0;
        int selectedWordCount = 0;
        int maxWordLength = 0;
        Set<String> visited = new HashSet<>();
        try {
            reader = new BufferedReader(new FileReader(pureFilePath));
            writer = new BufferedWriter(new FileWriter(predefinedFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                // Select words randomly with 10% probability
                if (!line.isEmpty()) {
                    String[] words = line.split(" ");
                    String word = words[random.nextInt(words.length)];
                    String lowerWord = word.toLowerCase();
                    if (visited.contains(lowerWord)) {
                        continue;
                    }
                    visited.add(lowerWord);
                    if (word.length() > maxWordLength) {
                        maxWordLength = word.length();
                    }
                    writer.write(word);
                    writer.newLine();
                    selectedWordCount++;
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
        System.out.println("Total number of lines: " + lineCount);
        System.out.println("Total number of selected words: " + selectedWordCount);
        System.out.println("Maximum word length: " + maxWordLength);
    }

    public static boolean checkAllEnglishLetters(String pureFilePath) throws IOException {
        BufferedReader reader = null;
        int lineCount = 0;
        int wordCount = 0;
        try {
            reader = new BufferedReader(new FileReader(pureFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                // Check if the line contains only English letters and spaces
                if (!line.matches("[a-zA-Z\\s]*")) {
                    return false;
                }
                if (!line.isEmpty()) {
                    String[] words = line.split(" ");
                    wordCount += words.length;
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        System.out.println("Total number of lines: " + lineCount);
        System.out.println("Total number of words: " + wordCount);
        return true;
    }

    public static void processFile(String rawFilePath, String pureFilePath) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        int wordCount = 0;
        int lineCount = 0;

        try {
            reader = new BufferedReader(new FileReader(rawFilePath));
            writer = new BufferedWriter(new FileWriter(pureFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                // Process the line
                line = line.replaceAll("[^a-zA-Z\\s]", ""); // Remove non-letter characters
                line = line.trim().replaceAll("\\s+", " "); // Remove redundant spaces

                if (!line.isEmpty()) {
                    String[] words = line.split(" ");
                    wordCount += words.length;

                    writer.write(line);
                    writer.newLine();
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

        System.out.println("Total number of words: " + wordCount);
        System.out.println("Total number of lines: " + lineCount);
    }
}
