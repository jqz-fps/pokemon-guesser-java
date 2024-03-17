package jqz.fps.Utilities;

import java.util.ArrayList;

public class StringManager {

    /**
     * This method calculates the edit distance between two text strings using the Levenshtein algorithm
     * @param s1 is the first string to be compared
     * @param s2 is the second string to be compared
     * @return the edition distance of the two strings
     */

    public static int levenshtein_distance(String s1, String s2) {
        int[][] distance = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= s2.length(); j++) {
            distance[0][j] = j;
        }

        // Don't try this at home 😭

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost);
            }
        }

        return distance[s1.length()][s2.length()];
    }

    /**
     * This method will search in a list of words, an inserted word
     * @param word the word we want to search
     * @param words the list of words for search
     * @return the closest word in the list
     */

    public static String search_similar_word(String word, ArrayList<String> words) {
        String similarWord = null;
        int minDistance = Integer.MAX_VALUE;

        for (String w : words) {
            int distance = levenshtein_distance(word, w);
            if (distance < minDistance) {
                minDistance = distance;
                similarWord = w;
            }
        }

        return similarWord;
    }

}
