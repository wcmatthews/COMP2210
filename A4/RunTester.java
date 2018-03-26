/**
The code bellow is to test the add/removes in DoubleEndedList for repetability, i.e. to make sure that you can add stuff then empty the list and add stuff again as many times as you want with out error. The code is not written as a junit test, rather as a driver program, the intended use being to run in canvas and watch what exactly is happening to nodes, to see where exactly a node is being created/deleted when it shouldn't be. 
-- Jacob Hall 2017-03-08 13:00 UTC-6
*/
import java.util.Iterator;
public class RunTester {
   public static void main(String[] args) {
      ImpDoubleEndedList<Integer> n = new ImpDoubleEndedList<Integer>();
      n.addFirst(5);
      n.addFirst(7);
      n.addFirst(8);
      n.addFirst(25);
      n.removeFirst();
      n.removeFirst();
      n.removeFirst();
      n.removeFirst();
      n.addFirst(5);
      n.addFirst(7);
      n.addFirst(8);
      n.addFirst(25);
     // n.iterator();
      n.removeFirst();
      n.removeFirst();
      n.removeFirst();
      n.removeFirst();
      n.addFirst(5);
      n.addFirst(7);
      n.addFirst(8);
      n.addFirst(25);
      n.removeFirst();
      n.removeFirst();
      n.removeFirst();
      n.removeFirst();
      ImpDoubleEndedList<Integer> m = new ImpDoubleEndedList<Integer>();
      m.addLast(5);
      m.addLast(7);
      m.addLast(8);
      m.addLast(25);
      m.removeLast();
      m.removeLast();
      m.removeLast();
      m.removeLast();
      m.addLast(5);
      m.addLast(7);
      m.addLast(8);
      m.addLast(25);
      m.removeLast();
      m.removeLast();
      m.removeLast();
      m.removeLast();
   }
}