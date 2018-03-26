import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;



public class WordSearch implements WordSearchGame {

   private String[][] board;
   private boolean[][] visited;
   private int rows;
   private int cols;
   private int rootlen;
   private static final int MAX_NEIGHBORS = 8;
   private TreeSet<String> dict;
   private List<Integer> pathList = new ArrayList<Integer>();
   private List<Integer> finalPath = new ArrayList<Integer>();
   private SortedSet<String> validWords;
   private int minLength;
    
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   @Override
    public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      // instantiate your lexicon object here
      dict = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
      try {
         Scanner s = 
            new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (s.hasNext()) {
            String str = s.next();
            // add str to your lexicon object here
            dict.add(str);
            s.nextLine();
         }
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   @Override
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException("Cannot accept null array");
      }
      
            
      if (letterArray.length % Math.sqrt(letterArray.length) != 0) {
         throw new IllegalArgumentException("Board not square. Must have NxN dimension");
      }
      
      int rootlen = (int) Math.sqrt(letterArray.length);
      board = new String[rootlen][rootlen];
      int count = 0;
      for (int i = 0; i < rootlen; i++) {
         for (int j = 0; j < rootlen; j++) { 
            board[i][j] = letterArray[count];
            markAllUnvisited();
            count++;
         }
      }
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   @Override
   public String getBoard() {
      StringBuilder builder = new StringBuilder();
    
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[i].length; j++) {
            builder.append(board[i][j] + " ");
         }
         builder.append("\n");
      }    
      return builder.toString();
   }
         
   
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   @Override
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (dict == null) {
         throw new IllegalStateException("Empty board");
      }
      minLength = minimumWordLength;
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Word does not meet length requirement.");
      }
      
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[i].length; j++) {
            Position ps = new Position(i, j);
            //String word = board[i][j];
            depthF1(board[i][j], ps);
            
         }
      }
      return validWords;
   }
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   @Override
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      return -99;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   @Override
   public boolean isValidWord(String wordToCheck) {
      if (dict == null) {
         throw new IllegalStateException("Empty board");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Word cannot be null");
      }
      
      if (dict.contains(wordToCheck)) {
         return true;
      }
      return false;
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   @Override
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("prefixToCheck cannot be null");
      }
      if (dict == null) {
         throw new IllegalStateException("Empty board");
      }
      
      String check = dict.ceiling(prefixToCheck);
      if (check != null && check.toLowerCase().startsWith(prefixToCheck.toLowerCase())) {
         return true;
      }
      
      return false;
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   @Override
   public List<Integer> isOnBoard(String wordToCheck) {
      if (dict == null) {
         throw new IllegalStateException("loadLexicon not called");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("wordToCheck cannot be null.");
      }
      pathList.clear();
      finalPath.clear();
      
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[i].length; j++) {
            if (board[i][j].charAt(0) == wordToCheck.charAt(0)) {
               Position ps = new Position(i, j);
               int inPath = ((i * board.length) + j);
               pathList.add(inPath);
               depthF2(wordToCheck, board[i][j], ps);
               if (!finalPath.isEmpty()) {
                  return finalPath;
               }
               pathList.clear();
               finalPath.clear();
               
            }
         }
      }
     
      return pathList;
   }
   
   /**
    * Sets visited[][] to false.
    */
   public void markAllUnvisited() {
      rows = board.length;
      cols = board[0].length;
      visited = new boolean[rows][cols];
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   }
   
   /**
    * @param word Word checked.
    * @param p Position of word.
    */
   public void depthF1(String word, Position p) {
      
      if (!isValidPrefix(word)) { 
         return;
      }
      visit(p);
      //word = board[p.x][p.y];
      if (isValidWord(word) && word.length() >= minLength) {
         validWords.add(word); 
      }
      for (Position neighbor : p.neighbors()) { 
         if (isValid(neighbor)) {
            visit(neighbor);
            depthF1(word + board[neighbor.x][neighbor.y], neighbor);
            unvisit(neighbor);
         }
      }
      unvisit(p);
   }
   
   /**
    * @param wordToCheck Word to check.
    * @param word Word.
    * @param p Position of word.
    */ 
   public void depthF2(String wordToCheck, String word, Position p) {
      if (isVisited(p)) {
         return;
      }
      visit(p);
      if (!isValidPrefix(word)) { 
         return;
      }
      if (word.equals(wordToCheck)) { 
         finalPath = new ArrayList<Integer>(pathList);
         return;
      }
      for (Position neighbor : p.neighbors()) {
         if (word.equals(wordToCheck)) {
            return;
         }
         if (isValid(neighbor)) {
            //visit(neighbor);// = true;
            pathList.add(neighbor.x * board.length + neighbor.y);
            depthF2(wordToCheck, word + board[neighbor.x][neighbor.y], neighbor); 
            //unvisit(neighbor);
            pathList.remove(pathList.size() - 1);
         }
      }
      markAllUnvisited();
      return;
   }
   
   private class Position {   
      int x;
      int y;
      
   
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
         
      }
      
      public Position[] neighbors() { 
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }
   
   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < board.length) && (p.y >= 0) && (p.y < board[0].length);
   }
   
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }
   
   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }
   
   private void unvisit(Position p) {
      visited[p.x][p.y] = false;
   }
   

}