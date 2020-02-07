// Name: Xinang Li
// USC NetID: 4226-5855-53
// CS 455 PA4
// Fall 2019

import java.io.*;
import java.util.*;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
   private Map<String, ArrayList<String>> dictionary;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
         dictionary = new HashMap<>();
         File file = new File(fileName);
         Scanner in = new Scanner(file);
         while (in.hasNextLine()) {
               String word = in.nextLine();
               char[] wordArray = word.toCharArray();
               Arrays.sort(wordArray);
               String key = String.valueOf(wordArray);
               if (dictionary.containsKey(key)) {
                     dictionary.get(key).add(word);
               }
               else {
                     ArrayList<String> anagramList = new ArrayList<>();
                     anagramList.add(word);
                     dictionary.put(key, anagramList);
               }
         }
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      char[] wordArray = s.toCharArray();
      Arrays.sort(wordArray);
      String key = String.valueOf(wordArray);
      
      return dictionary.get(key);   
      
   }

}
