import org.junit.Assert;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ExtractorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   // 2 lines no garbage
   @Test public void bruteTest() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(1, 2),
         new Point(2, 3),
         new Point(3, 4),
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(2, numLines);
   }
   

   // all garbage
   @Test public void bruteTest3() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(2, 2),
         new Point(5, 3),
         new Point(8, 4),
         new Point(7, 0), 
         new Point(20, 1),
         new Point(18, 2),
         new Point(13, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
    // empty
   @Test public void bruteTest4() {
      Point[] p = new Point[0];
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
   // intersecting lines
   @Test public void bruteTest5() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         //
         new Point(0, 1), 
         new Point(1, 1),
         new Point(2, 1),
         new Point(3, 1),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      Line testL = new Line(Arrays.asList(p));
      
   
      Assert.assertEquals(testL, bruteLines.first());
   }
}
   
