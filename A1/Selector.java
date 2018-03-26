import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Walter Matthews (wcm0034@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-01-19
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("Can't handle null"
                                 + "or zero length arrays.");
      }
      int min = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
      
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("Can't handle null"
                                 + "or zero length arrays.");
      }
   
      int max = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;
   
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("Can't handle null"
                                 + "or zero length arrays.");
      }
      if ((k < 1) || (k > a.length)) {
         throw new IllegalArgumentException("k is either too large or zero");
      }
      
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int distinct = 1;
      
      if (k == 1) {
         return b[0];
      }     
      for (int i = 1; i < b.length; i++) {
        
          if (b[i] != b[i-1]) {
            distinct++;
            if (distinct == k) {
               return b[i];
            }
            
         } 
         
      }
      throw new IllegalArgumentException("No kth minimum");
            
    }
      /**
      Arrays.sort(a);
      int min = a[0];
      for (int i = 0; i < k; i++) {
         if (a[i] < a[a.length - 1]) {
            if (a[i] == a[i + 1]) {
               k++;
            }
         }
         min = a[i];
      }
      return min;
   }*/


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
     if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("Can't handle null"
                                 + "or zero length arrays.");
      }
      if ((k < 1) || (k > a.length)) {
         throw new IllegalArgumentException("k is either too large or zero");
      }
      
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int distinct = 1;
      
      if (k == 1) {
         return b[b.length - 1];
      }
      if (k == b.length) {
         return b[0];
      }     
      for (int i = b.length - 2; i >= b[0]; i--) {
        
          if (b[i] != b[i+1]) {
            distinct++;
            if (distinct == k) {
               return b[i];
            }
            
         } 
         
      }
      throw new IllegalArgumentException("No kth maximum");
      
}
    /**
      Arrays.sort(a);
      int max = a[a.length - 1];
      for (int i = a.length - 1; i >= a.length - k; i--) {
         if (a[i] > a[0]) {
            if (a[i] == a[i - 1]) {
               k++;
            }
         }
         max = a[i];
      }
      return max;
   }
*/
   
   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("Can't handle null"
                                 + "or zero length arrays.");
      }
      int numCount = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            numCount++;
         }
      }
      int[] b = new int[numCount];
      int index = -1;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            index++;
            b[index] = a[i];
         }
      }
      
      if (index == -1) {
         b = new int[0];
      }
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("Can't handle null"
                                 + "or zero length arrays.");
      }
      int[] b = new int[a.length];
      int smallest = 0;
      int index = -1;
      for (int i = 0; i < a.length; i++) {
      
         if (a[i] >= key) {
            index++;
            b[index] = a[i];
         }  
          
      }
      
      smallest = b[0];
      
      for (int i = 0; i <= index; i++) {
      
         if (b[i] < smallest) {
            smallest = b[i];
         }
      }
      if (smallest < key) {
         throw new IllegalArgumentException("No qualifying values");
      }
         
      return smallest;
   
   }

   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("Can't handle null"
                                 + "or zero length arrays.");
      }
      
      int[] b = new int[a.length];
      int largest = 0;
      int index = -1;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            index++;
            b[index] = a[i];
         }   
      }
      
      largest = b[0];
      for (int i = 0; i <= index; i++) {
         if (b[i] > largest) {
            largest = b[i];
         }
         
      }
      if (largest > key) {
         throw new IllegalArgumentException("No qualifying values");
      } 
       
         
      return largest;
   
   }

}
