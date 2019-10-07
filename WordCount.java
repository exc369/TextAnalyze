
/**
 * A class to represent the word count which holds a word.
 * @author Eric Chang
 */
public class WordCount {
  
  /**
   * Field to hold the current word
   */
  private String word = "";
  
  /**
   * Field to hold the word count
   * Initialized at 1
   */
  private int count = 1;
  
  /**
   * Constructor for WordCount class
   * Takes a String parameter that is set to the word field
   * @param word The word inputted when a WordCount is initialized that is set to the word field
   */
  public WordCount(String word){
    this.word = word;
  }
  
  /**
   * Getter method to retrieve the word stored in the word field
   * @return The world currently being held
   */
  public String getWord(){
    return this.word;
  }
  
  /**
   * Setter method to set a new word to a WordCount object
   * @param word The new word to be set
   */
  public void setWord(String word) {
    this.word = word;
  }
  /**
   * Setter method to change the count value
   * @param count The value that becomes the new count value
   */
  public void setCount(int count){
    this.count = count;
  }
  
  /**
   * Getter method that returns the current count of the word
   * @return The current count of the word
   */
  public int getCount(){
    return this.count;
  }
  
  /**
   * Getter method that returns the length/last index of the word of a WordCount object
   * @return The length of the word of a WordCount object
   */
  public int getLastIndex() {
    return word.length() - 1;
  }
}