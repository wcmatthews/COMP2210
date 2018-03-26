import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImpDoubleEndedList<T> implements DoubleEndedList<T> {

   private class Node {
      
/**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   @Override
   void addFirst(T element);
   
   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   @Override
   void addLast(T element);
      
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
   @Override
   T removeFirst();
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
   @Override
   T removeLast();
   
}
