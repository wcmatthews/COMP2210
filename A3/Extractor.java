import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-02-08
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws Exception {
      Scanner scanFile = new Scanner(new File(filename));
      int size = scanFile.nextInt();//Integer.parseInt(scanFile.nextLine());
      Point[] points = new Point[size];
      String s = "";
      
      while(scanFile.hasNext()) {
         try {
         s = scanFile.nextLine();
         Scanner scan = new Scanner(s).useDelimiter(" ");
         //for (i = 0; i; i < size; i++) {
            
            int x = scan.nextInt();//Integer.parseInt(scan.next());
            int y = scan.nextInt();//Integer.parseInt(scan.next());
         
            Point p = new Point(x, y);
            for (int i = 0; i < size; i++) {
               points[i] = p;
            }
         }
         catch (Exception e) {
            String result = "Exception caught";
         }
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      Point[] col = new Point[4];
      Line colPoints = new Line();
      Point p, q, r, s;
      
      //if (lines.isEmpty()) {
      //   return null;
      //}
      for (int a = 0; a < points.length; a++) {
         for (int b = a + 1; b < points.length; b++) {
            for (int c = b + 1; c < points.length; c++) {
               for (int d = c + 1; d < points.length; d++) {
                  p = points[a];
                  q = points[b];
                  r = points[c];
                  s = points[d];
                  if (p.slopeTo(s) == q.slopeTo(r)) {
                     colPoints.add(p);
                     colPoints.add(q);
                     colPoints.add(r);
                     colPoints.add(s);
                  }
                  
                  lines.add(colPoints);
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      /**lines = new TreeSet<Line>();
      //TreeSet<Line> lines = new TreeSet<Line>();
    Point[] sortp = new Point[points.length()];
    Arrays.sort(sortp, sortp[0].SLOPE_ORDER);

    ArrayList<Point> sortLine = new ArrayList<Point>();


    if (lines.isEmpty())
    {
     return null;
    }
    else {

    }
    return lines;
   }
  */
     return lines;
  
   }
}
