import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.lang.*;
/*
 * Class to analyze .txt files of text
 * @author Eric Chang
 */
public class TextAnalyze {
  
  /**
   * Radix Sort algorithm to sort a linked list of WordCount objects
   * @param list The list of WordCount objects to be sorted
   * @return The sorted linked list
   */
  public static LinkedList<WordCount> radixSort(LinkedList<WordCount> list) { 
    //new linked list to hold all lower case words
    LinkedList<WordCount> lowerCased = new LinkedList<WordCount>();
    
    //variable to hold the max length of all the words in the linked list
    int maxLength = 0;
    
    //node pointer to help convert all words to lower case
    LLNode<WordCount> lowercaseptr = list.getFront();
    
    //while loop to convert linked list to lower case
    while (lowercaseptr != null){
      lowercaseptr.getElement().setWord(lowercaseptr.getElement().getWord().toLowerCase());
      lowerCased.addToBack(lowercaseptr.getElement());
      lowercaseptr = lowercaseptr.getNext();
    }
    
    //pointer node to find the max length of all words in the linked list
    LLNode<WordCount> nodeptr = lowerCased.getFront();
    //while loop to find the max length of all words in the linked list
    //used in radix sort algorithm
    while (nodeptr != null){
      WordCount wc = new WordCount(nodeptr.getElement().getWord());
      nodeptr = nodeptr.getNext();
      if(wc.getWord().length() > maxLength){
        maxLength = wc.getWord().length() - 1;
      }
    }
    //while loop to perform the radix sort
    while(maxLength != -1) { 
      //array list to hold the words being sorted
      ArrayList<LinkedList<WordCount>> aList = new ArrayList<LinkedList<WordCount>>(); 
      for(int i = 1; i < 28; i++) { 
        LinkedList<WordCount> l = new LinkedList<WordCount>(); 
        aList.add(l); 
      } 
      nodeptr = lowerCased.getFront();
      //while loop to sort each word into alphabetical order with radix algorithm
      while(nodeptr != null) { 
        WordCount currentWord = nodeptr.getElement();
        //if statement checks to see if the length of the word being added is less than the current index being checked
        if(currentWord.getLastIndex() < maxLength) {
          //if statement to try to consolidate repeated words by changing the wordcount
          //does not work correctly
          if (aList.get(0).getBack() != null && aList.get(0).getBack().getElement().getWord().equals(currentWord.getWord())) {
            aList.get(0).getBack().getElement().setCount(aList.get(0).getBack().getElement().getCount() + 1);
          }
          //else the word is added to the linked list with words of lengths less than the current index being checked
          else {
            aList.get(0).addToBack(currentWord); 
          }
        } 
        //else the word is being alphabetized and sorted
        else { 
          int index = ((int) currentWord.getWord().charAt(maxLength)) - 97; 
          //if statement to try to consolidate repeated words by changing the wordcount
          //does not work correctly
          if (aList.get(index + 1).getBack() != null && aList.get(index + 1).getBack().getElement().getWord().equals(currentWord.getWord())) {
            aList.get(index + 1).getBack().getElement().setCount(aList.get(index + 1).getBack().getElement().getCount() + 1);
          }
          //else the word is added to the respective array list
          else{
            aList.get(index+1).addToBack(currentWord);
          }
        }
        nodeptr = nodeptr.getNext();
      }
     
      LinkedList<WordCount> currentList = new LinkedList<WordCount>(); 
      //for loop to append the individual arraylists that are sorted
      for(int i = 0; i < 27; i++) { 
        currentList.append(aList.get(i)); 
      } 
      lowerCased = currentList; 
      maxLength--; 
    } 
    return lowerCased; 
  }
  
  /**
   * Method that returns a boolean depending on if every letter in the string is an english letter or not
   * @param The string to be checked for only english letters
   * @return A boolean value depending on if the string has only english letters or not
   */
  public static boolean onlyEnglishLetters(String a) {
    // I needed to go through each character of my string input to see if they were english letters
    for (int index = 0; index < a.length(); index++) {
      // If the character at the index is not an english letter, return false
      if ( Character.isDigit(a.charAt(index)) || !Character.isLetter(a.charAt(index))) {
        return false;
      }
    }
    // Else return true
    return true;
  }
  
  /*
   * Method to read all the words in a text file
   * @param fileName The name of the .txt file 
   * @return Linked list of all the words in the .txt file
   */
  public static LinkedList<WordCount> loadFile(String fileName) throws FileNotFoundException {
    //list that is returned with the WordCount objects
    LinkedList<WordCount> list = new LinkedList<WordCount>();
    //scanner object to read each individual word
    Scanner scanner = new Scanner(new File(fileName));
    //while loop to parse through the .txt file using scanner methods from the api
    while(scanner.hasNext()){
      String word = scanner.next();
      //removes any punctuation from the string
      word = word.replaceAll("\\p{Punct}","");
      //if statement checks to make sure all letters are english letters in the word before it appends the word to the list
      if (onlyEnglishLetters(word)) {
        list.addToBack(new WordCount(word));
      }
    }
    scanner.close();
    return list;
  }
  
  /**
   * Method that creates a new file with the individual words, word counts, and a percentage of all the words that appear in the file
   * @param saveFile The name of the new text file being created
   * @param list The linked list of the words being analyzed
   */
  public static void writeData(String saveFile, LinkedList<WordCount> list) throws IOException {
    //creating a new file with the name of the param inputted
    File file = new File(saveFile);
    //if statement to check if the file already exists
    if (file.exists()) {
      throw new IOException("File already exists.");
    }
    //writer class to edit the file created
    FileWriter editer = new FileWriter(file, true);
    //count to hold the length of the linked list
    int count = list.length();
    //holds the number of times a particular word appears 
    double totalCount = 0;
    //A linked list variable to hold the inputted linked list sorted by the radix sort
    LinkedList<WordCount> sortedList = TextAnalyze.radixSort(list);
    //node ptr to traverse the list
    LLNode<WordCount> nodeptr = sortedList.getFront();
    //while loop to traverse the list to sum all the word counts
    while (nodeptr != null) {
      WordCount wc = nodeptr.getElement();
      //adding the word counts of each WordCount object to obtain the total
      totalCount += wc.getCount();
      nodeptr = nodeptr.getNext();
    }
    nodeptr = sortedList.getFront();
    //while loop to traverse the sorted list to calculate and print the percentage of the occurance of the word
    while (nodeptr != null) {
      WordCount wc = nodeptr.getElement();
      String percent = String.format("%.2f", ((wc.getCount() / totalCount) * 100)) + "%";
      String line = wc.getWord() + " " + wc.getCount() + " " + percent + "\n";
      System.out.println(wc.getWord());
      editer.write(line);
      nodeptr = nodeptr.getNext();
    }
    editer.close();
  }
  /**
   * A main method to take an input file, sort it, and output a file that has the words, count of each word, and percentage of each word
   */
  public static void main(String[] args) throws IOException{
    
    TextAnalyze.writeData(args[1], TextAnalyze.loadFile(args[0]));
  }
}