import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ImpDoubleEndedListTest {

   
     /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   //***Iterator Tests***//
   @Test public void iteratorTest() { 
      ImpDoubleEndedList<Integer> list = new ImpDoubleEndedList<Integer>();
      list.addFirst(1);
      list.addFirst(2);
      list.addFirst(3);
      list.removeFirst();
      Iterator<Integer> itr = list.iterator();
 
      int expected = 2;
      int actual = list.size();
      assertEquals(expected, actual);
      
      
      
   }
}
