import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImpDoubleEndedList<T> implements DoubleEndedList<T> {

   private class Node {
      private T data;
      private Node next;
      private Node prev;  
   
   }
   
   private Node head;
   private Node tail;
      
/**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   @Override
   public void addFirst(T data) {
      if (data == null) {
         throw new IllegalArgumentException("No element");
      }
      
      Node temp = new Node();
      temp.data = data;
      
      if (isEmpty()) {
         head = temp;
         tail = temp;
      }
      else {
      temp.prev = null;
      temp.next = head;
      head.prev = temp;
      head = temp;
               }    
   }
   
   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   @Override
   public void addLast(T data) {
      if (data == null) {
         throw new IllegalArgumentException("No element");
      }
      
      Node temp = new Node();
      temp.data = data;
      
      if (isEmpty()) {
         head = temp;
         tail = temp;
      }
      else {
      temp.prev = tail;
      temp.next = null;
      tail.next = temp;
      tail = temp;
     }
   }
      
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
   @Override
   public T removeFirst() {
      if (isEmpty()) {
         return null;
      }
      
      if (head == null) {
         throw new NoSuchElementException("Cannot delete"); 
      }
      
      if (head == tail) {
         T data = head.data;
         head = null;
         tail = null;
         return data;
      }
      else {
         T data = head.data;
         head = head.next;
         return data;
      }
   }
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
   @Override
   public T removeLast(){      
      if (isEmpty()) {
         return null;
      }
      
      if (head == null) {
         throw new NoSuchElementException("Cannot delete");
      }
      if (head == tail) {
         T data = head.data;
         head = null;
         tail = null;
         return data;
      }
      else {
         Node temp = tail;
         tail = tail.prev;
         T data = tail.data;
         tail.next = null;
         return   temp.data;
      }
         
      
   }
   
    /**
    * Returns the number of elements in this list.
    */
   @Override
   public int size() {
      int size = 0;
      Node n = head;
      while (n != null) {
         size++;
         n = n.next;
      }
      return size;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   @Override
   public boolean isEmpty() {
      return (head == null);
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   @Override
   public Iterator<T> iterator() {
      return new DoubIterator();
   }
   
   private class DoubIterator implements Iterator<T> {
      Node n = head;
      
      public boolean hasNext() {
         if (isEmpty()) {
            return false;
         }
         return (n != null);
      }
      
      public T next() {
         if (isEmpty()) {
            throw new NoSuchElementException("Empty List");
         }
         if (!hasNext()) {
            throw new NoSuchElementException("Nothing next");
         }
            T value = n.data;
            n = n.next;
            return value;
      }
      public void remove() {
         throw new UnsupportedOperationException("Unsupported Operation");
      }
   }
   
}
