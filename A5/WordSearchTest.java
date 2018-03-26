import org.junit.Assert;
import java.util.ArrayList;
import java.util.SortedSet;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.TreeSet;



public class WordSearchTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


  
/**
 * Share A5 test cases here.
 */
  /**************************************************************************/
/*****************************ISVALIDWORD**********************************/
/**************************************************************************/

 //Should throw IllegalArgumentException if word is null.
   @Test (expected = IllegalArgumentException.class)
    public void validWordTest1() {
      WordSearch game = new WordSearch();
      game.loadLexicon("OWL.txt");
      game.isValidWord(null);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void validWordTest2() {
      WordSearch game = new WordSearch();
      game.isValidWord("Meow");
   }
   
   //normal tests
   @Test public void validWordTest3() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words_tiny.txt");
      Assert.assertEquals(true, game.isValidWord("lexicon"));
   }
   
   @Test public void validWordTest4() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words_tiny.txt");
      Assert.assertEquals(false, game.isValidWord("meow"));
   }
   
   @Test public void validWordTest5() {
      WordSearch game = new WordSearch();
      game.loadLexicon("OWL.txt");
      Assert.assertEquals(true, game.isValidWord("MeOw"));
   }
   
   
/**************************************************************************/
/*****************************ISVALIDPREFIX********************************/
/**************************************************************************/

//Should throw IllegalArgumentException if prefix is null.
   @Test (expected = IllegalArgumentException.class)
    public void validPrefixTest1() {
      WordSearch game = new WordSearch();

      game.loadLexicon("OWL.txt");
      game.isValidPrefix(null);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void validPrefixTest2() {
      WordSearch game = new WordSearch();

      game.isValidPrefix("Meow");
   }
   
   //normal tests
   @Test public void validPrefixTest3() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words_tiny.txt");
      Assert.assertEquals(false, game.isValidPrefix("pre"));
   }
   
   @Test public void validPrefixTest4() {
      WordSearch game = new WordSearch();
      game.loadLexicon("OWL.txt");
      Assert.assertEquals(true, game.isValidPrefix("homo"));
   }
   
   @Test public void validPrefixTest5() {
      WordSearch game = new WordSearch();
      game.loadLexicon("OWL.txt");
      Assert.assertEquals(true, game.isValidPrefix("HoMo"));
   }
   @Test public void validPrefixTest6() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words.txt");
      Assert.assertEquals(true, game.isValidPrefix("lee"));
   }
 
 
 /* The next ones focus on creating/setting the board. I test if it worked by using getBoard.
 * My get Board might return it differnetly than yours, so, you may have to edit your expected 
 * to fit whatever your format is.
 */
 /**********************************************************************/
/*************************BOARDCREATION********************************/
/**********************************************************************/
   //Checks to see if default board works.
   @Test public void boardTest1() {
      WordSearch game = new WordSearch();
      String expected = "E E C A \nA L E P \nH N B O \nQ T T Y ";
      Assert.assertEquals(expected, game.getBoard());
   }   
   
   //Tries loading in boards.
   //Should throw exception because null
   @Test (expected = IllegalArgumentException.class)
    public void boardTest2() {
     WordSearch game = new WordSearch();
      game.setBoard(null);
   }
   
   //Should throw exception because not square.
   @Test (expected = IllegalArgumentException.class)
    public void boardTest3() {
      WordSearch game = new WordSearch();
      String[] notSquare = new String[]{"A", "B", "C"};
      game.setBoard(notSquare);
   }
   
   //Regular board loading
   @Test public void boardTest4() {
      WordSearch game = new WordSearch();
      String[] board = new String[]{"A", "B", "C", "D",};
      String expected = "A B \nC D ";
      game.setBoard(board);
      Assert.assertEquals(expected, game.getBoard());
   }
   
   //Regular board loading
   @Test public void boardTest5() {
      WordSearch game = new WordSearch();
      String[] board = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
      String expected = "A B C \nD E F \nG H I ";
      game.setBoard(board);
      Assert.assertEquals(expected, game.getBoard());
   }
   
   /**********************************************************************/
/*************************ISONBOARD************************************/
/**********************************************************************/
   //Regular test
   @Test public void isOnBoardTest1() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words.txt");
      ArrayList<Integer> expected = new ArrayList<Integer>();
      expected.add(5); expected.add(6); expected.add(9); expected.add(13);
      Assert.assertEquals(expected, game.isOnBoard("lent")); 
   } 
   
   //Exception Tests
   @Test (expected = IllegalArgumentException.class)
    public void isOnBoardTest2() {
      WordSearch game = new WordSearch();
      game.loadLexicon("OWL.txt");
      game.isOnBoard(null);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void isOnBoardTest3() {
      WordSearch game = new WordSearch();
      game.isOnBoard("Meow");
   }
   
   //More regular
   @Test public void isOnBoardTest4() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words.txt");
      ArrayList<Integer> expected = new ArrayList<Integer>();
      Assert.assertEquals(expected, game.isOnBoard("meow")); 
   }
   
   
/**********************************************************************/
/*************************ALLVALIDWORDS********************************/
/**********************************************************************/   
   //Regular Test
   @Test public void allValidWordsTest1() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> expected = new TreeSet<String>();
      expected.add("ALEPOT"); expected.add("BENTHAL"); expected.add("PELEAN"); expected.add("TOECAP");
      Assert.assertEquals(expected, game.getAllValidWords(6)); 
   }
   
   //Exception Tests
   @Test (expected = IllegalArgumentException.class)
    public void allValidWordsTest2() {
      WordSearch game = new WordSearch();
      game.loadLexicon("OWL.txt");
      game.getAllValidWords(0);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void allValidWordsTest3() {
      WordSearch game = new WordSearch();
      game.getAllValidWords(2);
   }

   //More Regular
   @Test public void allValidWordsTest4() {
      WordSearch game = new WordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> expected = new TreeSet<String>();
      Assert.assertEquals(expected, game.getAllValidWords(10));
   }
   
  /* The next ones are for scoring words. There is two test cases that rely on the use
  * of getAllValidWords. If this isn't working, there's plenty others that do not rely on them. :)
  * -- Sloan Kiechel 2017-04-02 21:46 UTC-5
  */ 
   


}
