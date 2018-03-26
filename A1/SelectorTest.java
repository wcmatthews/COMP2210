import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {
   // Typical test for minimum.
   @Test
   public void minTest01() {
      int[] a = {2, 8, 7, 3, 4};
      assertEquals(Selector.min(a), 2);
   }
   
   //Test for kmin.
   @Test
   public void kminTest01() {
      int[] a = {2, 8, 7, 3, 4};
      assertEquals(Selector.kmin(a, 1), 2);
   }
   
   //Test for kmin with duplicates.
   @Test
   public void kminTestDup() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      assertEquals(Selector.kmin(a, 3), 4);
   }
   
   //Test for kmin with negatives and dupes.
   @Test
   public void kminTest_Neg_Dup() {
      int[] a = {23, -5, -5, -5, -5, 37, 21, 100};
      assertEquals(Selector.kmin(a, 4), 37);
   }
   
   //Typical test for kmax.
   @Test
   public void kmaxTest01() {
      int[] a = {2, 8, 7, 3, 4};
      assertEquals(Selector.kmax(a, 1), 8);
   }
   
   //Typical test 2 for kmax.
   @Test
   public void kmaxTest02() {
      int[] a = {5, 9, 1, 7, 3};
      assertEquals(Selector.kmax(a, 3), 5);
   }
  //Test for kmax with dupes.
  @Test
  public void kmaxTest_Dup() {
     int[] a = {2, 8, 8, 7, 3, 3, 4};     
     assertEquals(Selector.kmax(a, 3), 4);
  }
   
   @Test public void rangeNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      int low = 1;
      int high = 5;
      int[] expected = {2, 3, 4};
      Assert.assertArrayEquals(expected, Selector.range(a, low, high));
      }
      
      @Test 
   public void floorIllegalTest3() {
      int[] a = {-3, 3, 9, 7, 0};
      int key = -11;
      try {
         Selector.floor(a, key);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.assertFalse(false);
      }
   }
   
   @Test
   public void ceilingIllegalTest3() {
      int[] a = {7};
      int key = 9;
      try {
         Selector.ceiling(a, key);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.assertFalse(false);
      }
   }

}

