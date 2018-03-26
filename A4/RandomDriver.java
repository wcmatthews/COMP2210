import java.util.Iterator;

public class RandomDriver {
   public static void main(String[] args) { 
      
      ImpRandomizedList<Integer> list = new ImpRandomizedList<Integer>();
      list.add(1);
      list.add(2);
      list.add(3);
      list.add(4);
      list.add(5);
      list.add(6);
      list.remove();
      list.remove();
      list.remove();
      list.remove();
      list.remove();
      list.remove();
      list.remove();
   }
}