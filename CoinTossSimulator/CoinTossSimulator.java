// Name: Xinang Li
// USC NetID: 4226-5855-53
// CS 455 PA1
// Fall 2019

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;
public class CoinTossSimulator {
      private int totalTrials = 0;
      private int numOfTwoHeads = 0;
      private int numOfTwoTails = 0;
      private int numOfHeadTails = 0;


   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {

   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
        Random generator = new Random();
        totalTrials += numTrials;
        for (int i =0;i<numTrials;i++) {
          // Let 0 be the tail and 1 be the head
          int a1 = generator.nextInt(2);
          int a2 = generator.nextInt(2);
          
          if (a1 == 0 & a2 == 0) {
            numOfTwoTails++;
          }
          else if (a1 == 1 & a2 == 1) {
            numOfTwoHeads++;
          }else { numOfHeadTails++; }

        }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return totalTrials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return numOfTwoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return numOfTwoTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numOfHeadTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
        totalTrials = 0;
        numOfHeadTails = 0;
        numOfTwoTails = 0;
        numOfTwoHeads = 0;
   }

}
