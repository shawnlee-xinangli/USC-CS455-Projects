// Name: Xinang Li
// USC NetID: 4226-5855-53
// CS 455 PA4
// Fall 2019

import java.util.*;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   
    private String letters;
    private int[] mult;
    private String unique ="";
    
    /**
      The constructor passes the user's input to the Rack.
      And we call the findUnique Method to get the unique string and the mult array; 
       @param str rack input made by the user
    */

    public Rack(String str) {
        this.letters = str;
        findUnique(letters);
    }


   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

    /**
      We use HashMap to keep a record of each letter's frequence
      and construct the mult and unique
      @param str rack input made by the user
    */

   private void findUnique(String str) {
        Map<String, Integer> letterOccur = new HashMap<>();
        for (int i = 0;i<str.length();i++) {
            String s = String.valueOf(str.charAt(i));
            letterOccur.put(s,letterOccur.getOrDefault(s,0) + 1);
          
        }
        this.mult = new int[letterOccur.keySet().size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry: letterOccur.entrySet()) {
            this.unique += entry.getKey();
            this.mult[i] = entry.getValue();
            i++;
        }
   }
  
  /**
      We have to write another getSubsets method 
      because the output of the allSubsets method will contain "".
  */
   public ArrayList<String> getSubsets(){
    ArrayList<String> list = allSubsets(unique, mult, 0);
    list.remove("");
    return list;
  }

}
