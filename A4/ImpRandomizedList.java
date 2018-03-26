import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ImpRandomizedList<T> implements RandomizedList<T> {
   private static final int DEFAULT_CAPACITY = 5;
   private T[] elements;
   private int size;
   private int count;
   private int randSize;
   
      
   public ImpRandomizedList() {
      this(DEFAULT_CAPACITY);
   }
   
   @SuppressWarnings("unchecked")
   public ImpRandomizedList(int capacity) {
      size = 0;
      elements = (T[]) new Object[capacity];
   }
   /**
    * Adds the specified element to this list. If the element is null, this
    * method throws an IllegalArgumentException.
    */
   @Override
   public void add(T element) {
      if (element == null) {
         throw new IllegalArgumentException("Element is null");
      }
      if (isFull()) {
         resize(elements.length * 2);
      }
      elements[size] = element;
      size++;
      randSize++;
      
   }
  
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    */
   @Override
   public T remove() {
      if (isEmpty()) {
         return null;
      }
      Random rand = new Random();
      int choice = rand.nextInt(size);
      size--;
      elements[choice] = elements[size];
      elements[size] = null;
      
      return elements[size];
   }
   
   /**
    * Selects but does not remove an element selected uniformly at random from
    * the elements currently in the list. If the list is empty this method
    * return null.
    */
   @Override 
   public T sample() {
      if (isEmpty()) {
         return null;
      }
      Random rand = new Random();
      int choice = rand.nextInt(size);
      return elements[choice];
   }
   
   /**
    * Returns the number of elements in this list.
    */
   @Override
   public int size() {
      return size;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   @Override
   public boolean isEmpty() {
      return (size == 0);
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   @Override
   public Iterator<T> iterator() {
       return new RandIterator();
   }
   
   private boolean isFull() {
      return (DEFAULT_CAPACITY == size);
   }
   
   private void resize(int newSize) {
      assert newSize > 0;
      @SuppressWarnings("unchecked")
      T[] newArray = (T[]) new Object[newSize];
      System.arraycopy(elements, 0, newArray, 0, elements.length);
      elements = newArray;
   }
   
   private class RandIterator implements Iterator<T> {
      //private T[] items = elements;
      private int count = size;
      private int current = 0;
     /** @SuppressWarnings("unchecked")
      T[] copy = (T[]) new Object[size];
      System.arraycopy(elements, 0, copy, 0, size);
      elements = copy;*/
      //Random rand = new Random();
      //int r = rand.nextInt(size);
      public boolean hasNext() {
         if (isEmpty()) {
            return false;
         }
         return (current < count);
      }
      
      public T next() {
         if (isEmpty()) {
            throw new NoSuchElementException("Empty List");
         }
         if (!hasNext()) {
            throw new NoSuchElementException("Nothing next");
         }
         @SuppressWarnings("unchecked")
         T[] copy = (T[]) new Object[size];
         System.arraycopy(elements, 0, copy, 0, size);
         elements = copy;
         shuffle(copy);
         return copy[current++];
      }     
      public void remove() {
         throw new UnsupportedOperationException("Unsupported Operation");
      }
   }
   
   public void shuffle(T[] ar) {
      Random rng = new Random();
      for (int i = ar.length - 1; i > 0; i--) {
         int j = rng.nextInt(i + 1);
         swap(ar, i, j);
      }
   }
   
   public void swap(T[] arr, int i, int j) {
      T tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
   }
}
