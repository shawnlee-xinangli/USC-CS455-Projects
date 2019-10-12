// Name:
// USC NetID:
// CSCI455 PA2
// Fall 2019


/**
  The main program deals with four kinds of circumstances 
*/
import java.util.*;

public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }
      

      if (userConfig && singleStep) {
         System.out.println("Number of total cards is 45");
         System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
         userWithPause();
      }
      else if (userConfig && !singleStep) {
         System.out.println("Number of total cards is 45");
         System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
         userWithOutPause();
      }
      else if (!userConfig && singleStep) {
         randWithPause();
      }
      else {
         randWithOutPause();
      }

   }
   
   // <add private static methods here>
   
/** This randWithOutPause() method below is for random config and No single step
    "java -ea BulgarianSolitaireSimulator" 
*/
   private static void randWithOutPause() {
      SolitaireBoard sb = new SolitaireBoard();
      playWithOutPause(sb);

   }

/** This userWithOutPause() method below is for user config and No single step 
    "java -ea BulgarianSolitaireSimulator -u"
*/
   private static void userWithOutPause() {
      SolitaireBoard sb = SB_generator();
      playWithOutPause(sb);
   }    

/** This randWithPause() method below is for random config and No single step 
    "java -ea BulgarianSolitaireSimulator -s"
*/
   private static void randWithPause() {
      SolitaireBoard sb = new SolitaireBoard();
      playWithPause(sb);
   }

/** This userWithPause() method below is for the Command-line Argument 
   "java -ea BulgarianSolitaireSimulator -u -s"
*/
   private static void userWithPause() {
      SolitaireBoard sb = SB_generator();
      playWithPause(sb);

   }
/** This generator is to generator an SolitaireBoard with inout from the user.
    "Try catch" is used here to handle the Assertion error.
*/
   private static SolitaireBoard SB_generator(){
      System.out.println("Please enter a space-separated list of positive integers followed by newline:");
      Scanner in = new Scanner(System.in);
      String s = in.nextLine();

      while (!isValidInput(s)){
         System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
         System.out.println("Please enter a space-separated list of positive integers followed by newline:");
         s = in.nextLine();
      
      }
      
      ArrayList<Integer> a = new ArrayList<>();
      
      Scanner line = new Scanner(s);
      while (line.hasNextInt()){
         a.add(line.nextInt());
      }

      return new SolitaireBoard(a);
   }

   private static void playWithOutPause(SolitaireBoard sb) {
      System.out.println("Initial configuration: "+ sb.configString());
      int round = 0;
      while (!sb.isDone()) {
         sb.playRound();
         round ++;
         System.out.println("["+round+"]"+ " Current configuration: " + sb.configString());
      }
      System.out.println("Done!");
   }
   
   private static void playWithPause(SolitaireBoard sb) {
      System.out.println("Initial configuration: "+ sb.configString());
      int round = 0;
      Scanner in = new Scanner(System.in);
      while (!sb.isDone()) {
         sb.playRound();
         round ++;
         System.out.println("["+round+"]"+ " Current configuration: " + sb.configString());
         System.out.print("<Type return to continue>");
         in.nextLine();
      }
      System.out.println("Done!");
   }

    /*
    This method is to check the validness of the user's input
   */
   public static boolean isValidInput (String s) {
      int sum = 0;
      Scanner in = new Scanner(s);
      while (in.hasNext()) {
        String e = in.next();
        
        //First we make sure the input are numbers.
        if (!isNumeric(e)) {
            return false;
        }
        //Then we make sure that each number is between 1 and 45.
        int n =Integer.parseInt(e);
        if ( n <=0 || n > SolitaireBoard.CARD_TOTAL) {
         return false;
        }
        sum += n;
      } 

      //Finally, we make sure the sum of the input numbers are 45.
      return sum == SolitaireBoard.CARD_TOTAL;
    }

    /*This method is to check whether the String is an integer
    */
   public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}