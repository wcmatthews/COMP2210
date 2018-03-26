import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class DoubletsTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   /****************************
   *   GETHAMMINGDISTANCE TESTS
   *****************************/
   
   // Normal test with same string length.
   @Test
   public void getHamDistTest01() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      String s1 = "tiger";
      String s2 = "eagle";
      int expected = 4;
      int actual = doublets.getHammingDistance(s1, s2);
      assertEquals(expected, actual);
   }
   
   //Test for different string lengths.
   @Test
   public void getHamDistTest02() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      String s1 = "ABBA";
      String s2 = "ABBAS";
      int expected = -1;
      int actual = doublets.getHammingDistance(s1, s2);
      assertEquals(expected, actual);
   }
   
   //Case sensitive test.
   @Test
   public void getHamDistTest03() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      String s1 = "ABBA";
      String s2 = "abba";
      int expected = 0;
      int actual = doublets.getHammingDistance(s1, s2);
      assertEquals(expected, actual);
   }
   
   // Exception test.
   @Test (expected = IllegalArgumentException.class)
      public void getHamDistTest04() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      String s1 = "ABBA";
      String s2 = null;
      doublets.getHammingDistance(s1, s2);
   }
   
   /****************************
   *      ISWORD TESTS
   *****************************/
   
   // Normal Test
   @Test
   public void isWordTest01() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      assertEquals(true, doublets.isWord("TIGER"));
   }
   
   // Case sensitive test
   @Test
   public void isWordTest02() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      assertEquals(true, doublets.isWord("tiGeR"));
   }
   
   // Exception Test 
   @Test (expected = IllegalArgumentException.class)
   public void isWordTest03() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      assertEquals(true, doublets.isWord(null));
   }
   
   /****************************
   *      GETWORDCOUNT TESTS
   *****************************/
   
   @Test
   public void getWordCountTest01() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("tiny.txt")));
      int expected = 7;
      int actual = doublets.getWordCount();
      assertEquals(expected, actual);
   }
   
   public void getWordCountTest02() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("small.txt")));
      int expected = 19912;
      int actual = doublets.getWordCount();
      assertEquals(expected, actual);
   }
   
    /****************************
   *      GETNEIGHBORS TESTS
   *****************************/
   
   @Test
   public void getNeighborsTest(){
   }
   
   @Test
   public void isWordLadderTest() throws FileNotFoundException {      
      ArrayList<String> list = new ArrayList<String>();
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("small.txt")));
      list.add("cat");
      list.add("rat");
      list.add("bat");
      list.add("sat");
      list.add("pat");
      list.add("tat");
      list.add("mat");
      String word = list.get(0);
      assertEquals(true, doublets.isWordLadder(list));
   }
}  

