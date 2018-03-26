import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashSet;

/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Collin Matthews (wcm0034@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2017-04-28
 */
public class Doublets implements WordLadderGame {

   private final int MAX_NEIGHBORS = 8;
   private List<String> neighbors;
   private List<Node> pathList;
   private List<String> ladder;
   private HashSet<String> visited;
   private Deque<String> solution;
   //private HashSet<String> 
   //private boolean[][] visited;

    ////////////////////////////////////////////
    // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
    ////////////////////////////////////////////

    // A word ladder with no words. Used as the return value for the ladder methods
    // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            //str = str.toUpperCase();
            lexicon.add(str.toUpperCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
   @Override
    public int getHammingDistance(String str1, String str2) {
      if (str1 == null || str2 == null) {
         throw new IllegalArgumentException("Null string argument");
      }
      if (str1.length() != str2.length()) {
         return -1;
      }
      String s1 = str1.toLowerCase();
      String s2 = str2.toLowerCase();
      
      int ham = 0;
      for (int i = 0; i < s1.length(); i++) {
         if (s1.charAt(i) != s2.charAt(i)) {
            ham++;
         }
      }
      return ham;
   }


   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    *
    * Breadth-first search must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
   @Override
    public List<String> getMinLadder(String start, String end) {
      ladder = new ArrayList<String>();
      if (start == end) {
         ladder.add(start);
         return ladder;
      }
      if (getHammingDistance(start, end) == 1) {
         ladder.add(start);
         ladder.add(end);
         return ladder;
      }
      if (getHammingDistance(start, end) == -1) {
         return EMPTY_LADDER;
      }
      if (isWord(start) && isWord(end)) {
         bfs(start, end, lexicon);
         for (String helper : solution) {
            ladder.add(helper);         
         }
         return ladder;
      }
      
      return EMPTY_LADDER;
   }


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
    
   @Override
    public List<String> getNeighbors(String word) {
         neighbors = new ArrayList<String>();
         
         for (int i = 0; i < word.length(); i++) {
         for (char let = 'a'; let <= 'z'; let++) {
            StringBuilder newWord = new StringBuilder(word);
            newWord.setCharAt(i, let);
            String potWord = "" + newWord;
            potWord = potWord.toLowerCase();
            if (isWord(potWord)) {
               if (getHammingDistance(word, potWord) == 1) {
                  neighbors.add(potWord);
               }
            }
         }
      }
      return neighbors;
   }
      /**for (String words : lexicon) {
         if (getHammingDistance(words, word) == 1) {
            neighbors.add(words);
         }
      }
      return neighbors;*/
   


    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
   @Override
    public int getWordCount() {
      return lexicon.size();
   }


    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
   @Override
    public boolean isWord(String str) {
      if (lexicon == null) {
         throw new IllegalStateException("Empty lexicon");
      }
      if (str == null) {
         throw new IllegalArgumentException("No word");
      }
      
      String wordCheck = str.toUpperCase();
      
      if (lexicon.contains(wordCheck)) {
         return true;
      }
      return false;
   }


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
   @Override
    public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
      if (sequence.size() == 1) {
         return true;
      }
      
      for (int j = 0; j < sequence.size() - 1; j++) {
         if (getHammingDistance(sequence.get(j), sequence.get(j + 1)) != 1) {
            return false;
         }
      }
      for (int i = 0; i < sequence.size(); i++) {
         if (!lexicon.contains(sequence.get(i).toUpperCase())) {
            return false;
         }
      }
      return true;
   }
      /**if (sequence.size() == 1) {
         return true;
      }
      if (pathList != null) {
         return true;
      }
      return false;
   }*/
   
   private void bfs(String start, String target, TreeSet<String> set) {
      Deque<Node> queue = new ArrayDeque<Node>();
      pathList = new ArrayList<Node>();
      visited = new HashSet<String>();
      visited.add(start);
      queue.addLast(new Node(start, null));
      Node neighbor = null;
      
      while (!queue.isEmpty()) {
         Node n = queue.removeFirst();
         String currWord = n.word;
         for (String nbr : getNeighbors(currWord)) {
            neighbor = new Node(nbr, n);
            if (!visited.contains(neighbor)) {
               visited.add(neighbor.word);
               queue.addLast(neighbor);
               if (neighbor.word.equalsIgnoreCase(target)) {
                  //pathList.add(n);
                  pathList.add(neighbor);
                  queue.clear();
                  break;
               }
            } 
         }
         //brea;
      }
      solution = new ArrayDeque<String>();
      while (neighbor.parent != null) {
         solution.addFirst(neighbor.word);
         neighbor = neighbor.parent;
      }
      solution.addFirst(start);
   }
   private class Node {
      String word;
      Node parent;
      //Node prev;
   
      public Node() {
         word = null;
         parent = null;
         //prev = null;
      }
      
      public Node(String element, Node pred) {
         word = element;
         parent = pred;
         //prev = null;
      }
   }
}
   
