import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


  /***************************HANDOUT EXAMPLE CODE***********************************************************************/

/** Collections used in the various examples. */
   static Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{2,8,7,3,4});
   static Collection<Integer> c2 = Arrays.<Integer>asList(new Integer[]{5,9,1,7,3});
   static Collection<Integer> c3 = Arrays.<Integer>asList(new Integer[]{8,7,6,5,4});


   /**
    * Defines a total order on integers as ascending natural order.
    */
   static Comparator<Integer> ascendingInteger =
      new Comparator<Integer>() {
         public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
         }
      };


   /**
    * Defines a total order on integers as descending natural order.
    * This is the reverse of ascendingInteger above.
    */
   static Comparator<Integer> descendingInteger =
      new Comparator<Integer>() {
         public int compare(Integer i1, Integer i2) {
            return i2.compareTo(i1);
         }
      };


   /**
    * Defines an exmple composite "data" value with two fields.
    */
   static class Data {
      String  stringVal;
      Integer integerVal;
   
      public Data(String sval, Integer ival) {
         stringVal = sval;
         integerVal = ival;
      }
   
      @Override
      /**
       * Returns a string representation of this Data.
       * @return a formatted string with s and i values
       */
      public String toString() {
         return "(" + stringVal + ", " + integerVal + ")";
      }
   
      @Override
      /**
       * Returns true if the provided object is
       * equal to this Data, false otherwise.
       */
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         }
         if (obj == null) {
            return false;
         }
         if (obj.getClass() != this.getClass()) {
            return false;
         }
         Data that = (Data) obj;
         return (this.stringVal.equals(that.stringVal))
            && (this.integerVal.equals(that.integerVal));
      }
   }


   /** An array of Data used in various examples. */
   static Collection<Data> c4 = Arrays.<Data>asList(new Data[]{
      new Data("A",5), new Data("B", 4), new Data("C", 3), new Data("D", 2), new Data("E", 1)});


   /**
    * Defines a total order on Data as ascending natural order of
    * the String field s.
    */
   static Comparator<Data> ascendingStringData =
      new Comparator<Data>() {
         public int compare(Data d1, Data d2) {
            return d1.stringVal.compareTo(d2.stringVal);
         }
      };


   /**
    * Defines a total order on Data as ascending natural order of
    * the Integer field i.
    */
   static Comparator<Data> ascendingIntegerData =
      new Comparator<Data>() {
         public int compare(Data d1, Data d2) {
            return d1.integerVal.compareTo(d2.integerVal);
         }
      };


   /**
    * Returns a string representation of the given collection.
    * @param  c the provided collection
    * @return   a formatted string with each element of the collection
    */
   static String asString(Collection c) {
      StringBuilder s = new StringBuilder();
      s.append("[");
      for (Object o : c) {
         s.append(o.toString() + ",");
      }
      s.delete(s.length() - 1, s.length());
      s.append("]");
      return s.toString();
   }
   
   /*
 From here, I used this to make test cases. I'll provide min and maximum as examples, and let other people provide the others :).
 /*
 /****************************************************************************/   
/*******************************MIN TESTS************************************/
/****************************************************************************/
   //tests for invalid cases
   @Test (expected = IllegalArgumentException.class)
    public void minInvalid1() {
      Collection invalid = null;
      Selector.<Integer>min(invalid, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void minInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>min(c1, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void minInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>min(empty, ascendingInteger);
   }
   
   //Tests normal Cases
   @Test public void minNormal1() {
      Integer actual = Selector.<Integer>min(c1, ascendingInteger);
      Integer expected = 2;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal2() {
      Integer actual = Selector.<Integer>min(c2, descendingInteger);
      Integer expected = 9;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal3() {
      Integer actual = Selector.<Integer>min(c3, ascendingInteger);
      Integer expected = 4;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal4() {
      Data actual = Selector.<Data>min(c4, ascendingStringData);
      Data expected = new Data("A", 5);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal5() {
      Data actual = Selector.<Data>min(c4, ascendingIntegerData);
      Data expected = new Data("E", 1);
      Assert.assertEquals(expected, actual);
   }
/****************************************************************************/   
/*******************************MAX TESTS************************************/
/****************************************************************************/
   @Test (expected = IllegalArgumentException.class)
    public void maxInvalid1() {
      Collection invalid = null;
      Selector.<Integer>max(invalid, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void maxInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>max(c1, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void maxInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>max(empty, ascendingInteger);
   }
   
   //Tests normal Cases
   @Test public void maxNormal1() {
      Integer actual = Selector.<Integer>max(c1, ascendingInteger);
      Integer expected = 8;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal2() {
      Integer actual = Selector.<Integer>max(c2, descendingInteger);
      Integer expected = 1;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal3() {
      Integer actual = Selector.<Integer>max(c3, ascendingInteger);
      Integer expected = 8;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal4() {
      Data actual = Selector.<Data>max(c4, ascendingStringData);
      Data expected = new Data("E", 1);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal5() {
      Data actual = Selector.<Data>max(c4, ascendingIntegerData);
      Data expected = new Data("A", 5);
      Assert.assertEquals(expected, actual);
   }
}