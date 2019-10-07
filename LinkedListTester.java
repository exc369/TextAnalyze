import org.junit.*;
import static org.junit.Assert.*;

/**
 * Testing class for HW4
 * @author Eric Chang
 */
public class LinkedListTester {
  
  /**
   * Testing the append method
   */
  @Test
  public void testAppend() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.addToFront(3);
    list.addToFront(2);
    list.addToFront(1);
    LinkedList<Integer> list2 = new LinkedList<Integer>();
    list2.addToFront(7);
    list2.addToFront(6);
    list2.addToFront(5);
    list.append(list2);
    assertEquals("list: 1 2 3 5 6 7", list.toString());
  }
  
  /**
   * Testing the WordCount class
   */
  @Test
  public void testWordCount() {
    WordCount a = new WordCount("hello");
    assertEquals("hello", a.getWord());
    assertEquals(1, a.getCount());
    a.setCount(2);
    assertEquals(2, a.getCount());
    assertEquals(4, a.getLastIndex());
    a.setWord("loo");
    assertEquals("loo", a.getWord());
    assertEquals(3, a.getWord().length());
  }
  
  
  
  /**
   * Testing the radixSort method
   */
  @Test
  public void testRadixSort() {
    LinkedList<WordCount> list = new LinkedList<WordCount>();
    list.addToFront(new WordCount("cat"));
    list.addToFront(new WordCount("bit"));
    list.addToFront(new WordCount("cop"));
    list.addToFront(new WordCount("cap"));
    list.addToFront(new WordCount("be"));
    list.addToFront(new WordCount("car"));    
    list.addToFront(new WordCount("dart"));    
    list.addToFront(new WordCount("bat"));   
    list.addToFront(new WordCount("cart"));    
    assertEquals("bat", TextAnalyze.radixSort(list).getFront().getElement().getWord());
    assertEquals("be", TextAnalyze.radixSort(list).getFront().getNext().getElement().getWord());
    assertEquals("bit", TextAnalyze.radixSort(list).getFront().getNext().getNext().getElement().getWord());
    assertEquals("cap", TextAnalyze.radixSort(list).getFront().getNext().getNext().getNext().getElement().getWord());
    assertEquals("car", TextAnalyze.radixSort(list).getFront().getNext().getNext().getNext().getNext().getElement().getWord());
  } 
  
  /**
   * Testing the toString Method
   */
  @Test
  public void testToString() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("hello");
    assertEquals("list: hello", list.toString());
    list.addToFront("I'm");
    assertEquals("list: I'm hello", list.toString());
    list.addToFront("IHateSocialSciences");
    assertEquals("list: IHateSocialSciences I'm hello", list.toString());
  }
}