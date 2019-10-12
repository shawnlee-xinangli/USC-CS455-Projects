// Name:
// USC NetID:
// CSCI455 PA2
// Fall 2019

import java.util.*;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total 
  number of cards is for the game by changing NUM_FINAL_PILES, below.  
  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.  (See comments 
  below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt 
   // description for details.
   
   
   /**
      Representation invariant:

      <put rep. invar. comment here>
      a[i]>0 && a[i]<=CARD_TOTAL;
      size>0 && size<=CARD_TOTAL;
      a[0]+a[1]+a[2]+...a[size-1] == CARD_TOTAL;

   */
   
   // <add instance variables here>
   private int size = 0;
   private int[] a = new int[CARD_TOTAL];
   private Random rand = new Random();
  
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of 
      cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to 
      SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {

      // sample assert statement (you will be adding more of these calls)
      // this statement stays at the end of the constructor.
      for (int i = 0;i<piles.size();i++) {
         a[i] = piles.get(i);
         size ++;
      }
      assert isValidSolitaireBoard();

   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
      
      //First we need a random size between [1,45].
      size = rand.nextInt(CARD_TOTAL)+1;
      int sum = CARD_TOTAL;
      //Give random number to a[0:size-2]
      for (int i = 0;i<size-1;i++) {
         a[i] = rand.nextInt(sum-size+i+1) + 1;
         sum -= a[i]; 
      }
      a[size-1] = sum;
      
      assert isValidSolitaireBoard();
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration 
      according to the rules of Bulgarian solitaire: Takes one card from each
      pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
      //Set two pointers i and j, i for write and j for read.
      int i = 0;
      int j = 0;
      //The last number would always be the size of the previous array.
      int lastnum = size;
      while (i<lastnum){
         //If a[i] is 1 we will shrink the size of the array by 1 and skip this element.
         if (a[i] == 1) {
            i++;
            size--;
         }
         //If a[i] is greater than 1, we will set a[j] to be a[i] and deduct 1 from a[j] and both i and j will move forward. 
         else {
            a[j] = a[i];
            a[j] --;
            i++;
            j++;

         }
      }

      a[size] = lastnum;
      size ++;

      assert isValidSolitaireBoard();

   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, 
      there are NUM_FINAL_PILES piles that are of sizes 
      1, 2, 3, . . . , NUM_FINAL_PILES, 
      in any order.
   */
   
   public boolean isDone() {
      /* 
         Obviously, the final size will always equal NUM_FINAL_PILES, which means that
         if the size is not equal to NUM_FINAL_PILES, it is not done yet.
      */
      if (size != NUM_FINAL_PILES) {
         return false;
      }

      /* After we have checked the size, the only thing we will have to check 
         is whether each element from 1 to size has appeared in the array only once.
      */
      int[] c = new int[CARD_TOTAL];

      for (int i = 0;i<size;i++) {
         if (c[a[i]-1] == 0) {
            c[a[i]-1]++;
         }
         else {
            return false;
         }
      }

      return true;  
      
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
      String res = "";
      for (int i = 0;i < size;i++) {
         res += " " + a[i];
      }
      return res;   
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      int sum = 0;
      if (size == 0 || size > CARD_TOTAL) {
         return false;
      }

      for (int i = 0; i < size; i++) {
         if (a[i] <= 0 || a[i] > CARD_TOTAL) {
            return false;
         }
         sum += a[i];
      }
      return sum == CARD_TOTAL; 

   }
   

   // <add any additional private methods here>

}
