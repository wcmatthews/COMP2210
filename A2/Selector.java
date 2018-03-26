import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Your Name (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-01-30
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException("Can't handle null");
      }
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException("Collection is empty.");
      }
      
      Iterator<T> itr = coll.iterator();
      T min = itr.next();
      for (T val : coll) {
         if (comp.compare(val, min) < 0) {
            min = val;
         }
      }
      
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException("Can't handle null");
      }
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException("Collection is empty.");
      }
      
      Iterator<T> itr = coll.iterator();
      T max = itr.next();
      for (T val : coll) {
         if (comp.compare(val, max) > 0) {
            max = val;
         }
      }
      
      return max;
   }
   


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException("Can't handle null");
      }
      if ((k < 1) || (k > coll.size())) {
         throw new NoSuchElementException("K is either to large or less than 1");
      }
      
      List<T> list = new ArrayList<T>();
      list.addAll(coll);
      java.util.Collections.sort(list, comp);
      
      int distinct = 1;
      if (k == 1) {
         return list.get(0);
      }
      
      for (int i = 1; i < list.size(); i++) {
         if (list.get(i) != list.get(i - 1)) {
            distinct++;
            if (distinct == k) {
               return list.get(i);
            }
         }
      }
     
      throw new NoSuchElementException("no kth minimum");
   }
        

   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException("Can't handle null");
      }
      if ((k < 1) || (k > coll.size())) {
         throw new NoSuchElementException("K is either to large or less than 1");
      }
      
      List<T> list = new ArrayList<T>();
      list.addAll(coll);
      java.util.Collections.sort(list, comp);
      
      int distinct = 1;
      if (k == 1) {
         return list.get(list.size() - 1);
      }
      
      if (k == list.size()) {
         list.get(0);
      }
      for (int i = list.size() - 2; i >= list.indexOf(list.get(0)); i--) {
         if (list.get(i) != list.get(i + 1)) {
            distinct++;
            if (distinct == k) {
               return list.get(i);
            }
         }
      }
     
      throw new NoSuchElementException("no kth maximum");
   
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException("Can't handle null");
      }
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException("Collection is empty.");
      }
      
      List<T> list = new ArrayList<T>();
      List<T> list2 = new ArrayList<T>();
      list.addAll(coll);
      
      
      int numCount = 0;
      for (int i = 0; i < list.size(); i++) {
         T val = list.get(i);
         if (comp.compare(val, low) >= 0 && comp.compare(val, high) <= 0) {
            list2.add(list.get(i));
            numCount++;
         }
      }
      if (numCount == 0) {
         throw new NoSuchElementException("No values fall in range");
      }
      
      
      return list2;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException("Can't handle null");
      }
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException("Collection is empty.");
      }
   
      Iterator<T> itr = coll.iterator();
      T smallest = itr.next();
      for (T val : coll) {
         if (comp.compare(val, key) == 0) {
            smallest = val;
            return smallest;
         }
         if (comp.compare(val, key) <= 0) {
            smallest = val;
         }
      }
      if (comp.compare(smallest, key) < 0) {
         throw new NoSuchElementException("No qualifying values");
      }
         
      for (T val : coll) {
         if (comp.compare(val, smallest) == -1) {
            smallest = val;
         }
      
      }
      return smallest;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException("Can't handle null");
      }
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException("Collection is empty.");
      }
   
      Iterator<T> itr = coll.iterator();
      T largest = itr.next();
      for (T val : coll) {
         if (comp.compare(val, key) < 0) {
            largest = val;
         }
      }
      if (comp.compare(largest, key) == 0) {
         return largest;
      }
              
      if (comp.compare(largest, key) > 0) {
         throw new NoSuchElementException("No qualifying values");
      }
         
      for (T val : coll) {
         if ((comp.compare(val, largest) >= 0) && (comp.compare(val, key) <= 0))  {
            largest = val;
         }
      
      }
      return largest;
   }

}
