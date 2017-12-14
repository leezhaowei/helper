package com.zwli.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Given a start word and an end word of the same length, perform one letter change to get from the start word to the
 * end word. Use only a dictionary of valid words. Find all possible word chains with the minimum number of one letter
 * changes. Hint: think "graph theory":-)<br>
 * Example: to get from "BUSH" to "CASE", one possible word chain with the minimum number of 1 letter change (3 times)
 * is : "BUSH" -> "BASH" -> "BASE" -> "CASE" <br>
 * <p>
 * For example: w_morph "bush" "case" "http://tech.buuteeq.com/quiz/dm- 1/4letterdict.html"
 * </p>
 */
public class TransformWord {

    private final Set<String> dictionary;

    public TransformWord(final Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> transform(String startWord, String endWord) {
        if (startWord == null || endWord == null || startWord.length() != endWord.length()) {
            throw new IllegalArgumentException("Arguments invalid - [startWord, endWord]");
        }

        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();

        final Queue<String> checkingWordsQueue = new LinkedList<String>();
        final Set<String> visitedWords = new TreeSet<String>();
        final Map<String, String> backtrackVisitedWords = new TreeMap<String, String>();

        checkingWordsQueue.add(startWord);
        visitedWords.add(startWord);

        while (!checkingWordsQueue.isEmpty()) {
            final String word = checkingWordsQueue.remove();
            for (final String newWord : getEditedWordsViaEachLetter(word)) {
                if (!visitedWords.contains(newWord)) {
                    visitedWords.add(newWord);
                    backtrackVisitedWords.put(newWord, word);
                    if (newWord.equals(endWord)) {
                        return backtrackVisitedWords(newWord, backtrackVisitedWords);
                    }
                    checkingWordsQueue.add(newWord);
                }
            }
        }
        return null;
    }

    private Set<String> getEditedWordsViaEachLetter(final String word) {
        final Set<String> editedWords = new TreeSet<String>();
        for (int i = 0; i < word.length(); i++) {
            editingWords(editedWords, i, word);
        }
        return editedWords;
    }

    private void editingWords(final Set<String> editedWords, final int index, final String word) {
        final char[] wordInLetters = word.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != word.charAt(index)) {
                wordInLetters[index] = c;
                final String newWord = String.valueOf(wordInLetters);
                if (dictionary.contains(newWord)) {
                    editedWords.add(newWord);
                }
            }
        }
    }

    private List<String> backtrackVisitedWords(String editedWord, final Map<String, String> visitedWords) {
        final List<String> result = new LinkedList<String>();
        result.add(editedWord);
        while (editedWord != null && visitedWords.containsKey(editedWord)) {
            editedWord = visitedWords.get(editedWord);
            result.add(0, editedWord);
        }
        return result;
    }

    public static void main(final String[] args) throws Exception {
        // final String str = "bash,base,cash,much,mesh,hand,foot,bird,case";
        // final String[] words = str.split(",");
        // final Set<String> dictionary = new TreeSet<String>(Arrays.asList(words));

        // final Set<String> dictionary = loadDictionaryFromFile();

        String sUrl = null;
        String startWord = null;
        String endWord = null;
        if (args.length == 3) {
            startWord = args[0];
            endWord = args[1];
            sUrl = args[2];
        } else {
            System.out.println("Example: java -jar transformword.jar startWord endWord letterDictUrl");
            System.exit(1);
        }

        // sUrl = "http://tech.buuteeq.com/quiz/dm-1/4letterdict.html";
        // startWord = "bush";
        // endWord = "case";
        // sUrl = "http://tech.buuteeq.com/quiz/dm-1/5letterdict.html";
        // startWord = "watch";
        // endWord = "clock";

        final Set<String> dictionary = loadDictionary(sUrl);
        // System.out.println(dictionary.size());

        final TransformWord transform = new TransformWord(dictionary);
        System.out.println(transform.transform(startWord, endWord));
    }

    // public static Set<String> loadDictionaryFromFile() throws Exception {
    // BufferedReader br = null;
    // final Set<String> dictionary = new TreeSet<String>();
    // try {
    // br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/ZhaoweiLi/Temp/words.txt")));
    // String t;
    // while (br.ready()) {
    // t = br.readLine();
    // if (!t.equals("") && !t.startsWith("<")) {
    // final String[] words = t.split(" ");
    // if (words.length < 17) {
    // dictionary.addAll(Arrays.asList(words));
    // }
    // }
    // }
    // return dictionary;
    // } finally {
    // if (br != null) {
    // br.close();
    // }
    // }
    // }

    public static Set<String> loadDictionary(final String sUrl) throws Exception {
        final URL url = new URL(sUrl);
        BufferedReader br = null;
        final Set<String> dictionary = new TreeSet<String>();
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String t;
            while (br.ready()) {
                t = br.readLine();
                if (!t.equals("") && !t.startsWith("<")) {
                    final String[] words = t.split(" ");
                    if (words.length < 17) {
                        dictionary.addAll(Arrays.asList(words));
                    }
                }
            }
            return dictionary;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
