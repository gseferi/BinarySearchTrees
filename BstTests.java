// Compile with -Xlint for safety.
// Run with -ea option to enable assertions.
//
//     $ javac -Xlint BstTests.java
//     $ Java -ea BstTests 
//
// If any assertion fails, there is a bug in our program.
// If they all succeed, there may still be bugs.



// TODO: Auto-generated Javadoc
/**
 * The Class BstTests.
 */
public class BstTests {

  /**
   * The main method.
   *
   * @param args the arguments
   */
 
@SuppressWarnings({ "unchecked", "rawtypes" })
public static void main(String[] args) {
    Bst<Integer,String> t1 = new Empty<>();
    assert(t1.size() == 0);
    assert(t1.height() == -1);
    assert(t1.isEmpty());
    System.out.println("Test the Height and Size");
    System.out.println("height t1: " +t1.height());
    System.out.println("size t1: " +t1.size());
    
    Bst<Integer,String> t2 = t1.put(30,"John");  
    assert(t1.isEmpty());
    assert(t2.size() == 1);
    assert(t2.height() == 0);
    System.out.println("height t2: " +t2.height());
    System.out.println("size t2: " +t2.size());

    Bst<Integer,String> t3 = t2.put(40,"Mary").put(25,"Peter").put(37,"Monica").put(34,"Nicolas").put(31,"Martin"); 
    assert(t3.size() == 6);
    assert(t2.size() == 1);
    assert(t2.height() == 0);
    System.out.println("size t3: " +t3.size());
   

    Bst<Integer,String> t4 = t3.put(31,"Kathy"); 
    assert(t3.size() == 6);
    assert(t4.size() == 6);
    System.out.println("size t4: " +t4.size());
    
    Bst<Integer,String> t5 = t3.balanced();
    assert(t5.size() == t3.size());
    assert(t5.height() <= t3.height());
    assert(t5.height() <= log2floor(t5.size()));
    System.out.println("height t5: " +t5.height());
    System.out.println("size t5: " +t5.size()+"\n");
    
    System.out.print("The entry with the largest key is: ");
    System.out.println(t4.largest().get().getKey()+","+t4.largest().get().getValue());
    System.out.print("The entry with the smallest key is: ");
    System.out.println(t4.smallest().get().getKey()+","+t4.smallest().get().getValue()+"\n");
    
    System.out.println("The value with key 40 is: "+t4.find(40));
    System.out.println("The value with key 13 is: "+t4.find(13));
    
    Bst<Integer,String> t6=t4.delete(25).get();
    System.out.print("\nThe entry with the smallest key is: ");
    System.out.println(t6.smallest().get().getKey()+","+t6.smallest().get().getValue());
    System.out.print("The value with key 25 is: ");
    
    System.out.println(t6.find(25));
    System.out.println("\nPrinting in order");
    t6.printInOrder();
    
    
    System.out.print("\nPrinting the 2D Tree ");
 
	Entry[] entry = new Entry[t6.size()];
    t6.saveInOrder(entry);
    System.out.println(t6.fancyToString());
    
    System.out.print("The balanced tree: ");
    Bst<Integer, String> t7 = t6.balanced();
    System.out.println(t7.fancyToString());
    
    System.out.println("After deleting an entry that exists");
    System.out.println(t6.delete(31).get().fancyToString());
    System.out.println("After deleting an entry that does not exist");
    System.out.println(t6.delete(25)+"\n");
    System.out.println("Print in Order");
    t6.printInOrder();
    Table<Integer, String> table = new BstTable<Integer, String>();
    table = table.put(40,"Mary").put(25,"Peter").put(37,"Monica").put(34,"Nicolas").put(31,"Martin");
    
    
    System.out.print("\nBstTable Tests \nThe keys are: ");
    System.out.println(table.keys());
    
    System.out.print("The values are: ");
    System.out.println(table.values());
    
    System.out.print("The keys after removing are: ");
    System.out.println(table.remove(37).get().values());
    
    System.out.println("\nDoes the table contain key?");
    System.out.println(table.containsKey(31));
    System.out.println(table.containsKey(12));
    
    System.out.println("\nGet the value of a key");
    System.out.println(table.get(25));
    System.out.println(table.get(10));
   
    System.out.println("\nTests passed");
    // You should add more tests designed by yourself.
  }

  /**
   * Log2floor.
   *
   * @param x the x
   * @return the int
   */
  private static int log2floor(int x) {
    assert(x > 0);
    int y = 0; 
    do {
      y = y + 1;
      x = x / 2;
      } while (x > 0);
    return y;
  }
}
