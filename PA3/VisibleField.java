// Name:Xinang Li
// USC NetID:4226-5855-53
// CS 455 PA3
// Fall 2019


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   private MineField mineField;
   private int[][] visibleField;
   private int numRows;
   private int numCols;

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      numRows = mineField.numRows();
      numCols = mineField.numCols();
      visibleField = new int[numRows][numCols];
      //Set the whole visibleField to the status of COVERED to initialize.
       for (int i = 0; i < numRows; i++) {
           for (int j = 0; j < numCols; j++) {
               visibleField[i][j] = COVERED;
           }
       }
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
          visibleField[i][j] = COVERED;
        }
      }
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineField;
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      assert getMineField().inRange(row, col);
      return visibleField[row][col];
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      return mineField.numMines() - numMinesGuessed();       

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      assert getMineField().inRange(row, col);

      if (!isUncovered(row, col)) {
          if (getStatus(row, col) == COVERED) {
              visibleField[row][col] = MINE_GUESS;
          }
          else if (getStatus(row, col) == MINE_GUESS) {
              visibleField[row][col] = QUESTION;
          }
          else {
              visibleField[row][col] = COVERED;
          }
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      assert getMineField().inRange(row, col);
      // If the player has uncovered a MINE, we need to set the status into EXPLODED_MINE.

      if (mineField.hasMine(row, col)) {
          visibleField[row][col] = EXPLODED_MINE;
          //Don't forget to show the result if the game is over. 
          showResult(row, col);
          return false;
      }
      // In general, when the player uncovers an empty square, we will set the status into numAdjacentMines.
      visibleField[row][col] = mineField.numAdjacentMines(row,col);
      //We do the DFS search iff the numAdjacentMines equals zero.
      if (getStatus(row, col) == 0) {
        for (int i = row - 1;i < row + 2;i++) {
            for (int j = col -1;j < col + 2;j++) {
                //And we do the uncover recursively iff [i,j]'s inRange and the status of [i,j] is COVERED or QUESTION.
                if (getMineField().inRange(i, j) && (getStatus(i,j) == COVERED || getStatus(i,j) == QUESTION)) {
                  uncover(i, j);
                }
            }
        }
      }
      return true;
   }
 
   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game over
    */
   public boolean isGameOver() {
      int count = 0;
      for (int i = 0;i < numRows;i++) {
        for (int j = 0;j < numCols;j++) {
            if (getStatus(i, j) == EXPLODED_MINE) {
                return true;
            }
            if (isUncovered(i, j)) {
                count++;
            }
        }
      }
      //Return true iff all the non-MINE locations have been uncovered.
      return count == numRows * numCols - mineField.numMines();
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
       assert getMineField().inRange(row, col);
       
       return getStatus(row, col) >= 0;
   }
   
 
   // <put private methods here>

    /**
      Return the number of MineGuessed
     */
   private int numMinesGuessed() {
      int count = 0;
      for (int i = 0;i < numRows;i++) {
        for (int j = 0;j < numCols;j++) {
          if (getStatus(i, j) == MINE_GUESS) {
            count++;
          }
        }
      }
      return count;
   }
   
    /**
      This method is called only when we have uncovered the MINE.
      And we will need the location of this EXPLODE_MINE so that we can skip the EXPLODE_MINE
      when we try to set status for the whole visibleField.
      @param row of the EXPLODED_MINE
      @param col of the EXPLODED_MINE
    */
   private void showResult(int row,int col) {
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
            // We need to skip the EXPLODED_MINE.
          if (i == row && j == col) {
              continue;
          }
          if (getStatus(i, j) == MINE_GUESS && !getMineField().hasMine(i, j)) {
                   visibleField[i][j] = INCORRECT_GUESS;
          }
          if (getStatus(i, j) == COVERED && getMineField().hasMine(i, j)) {
                   visibleField[i][j] = MINE;
          }
        }
      }
  }
}
