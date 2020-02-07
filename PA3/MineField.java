// Name:Xinang Li
// USC NetID:4226-5855-53
// CS 455 PA3
// Fall 2019

import java.util.*;
/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
   
   // <put instance variables here>
   private boolean[][] mineData;
   private int numMines;
   private int numRows;
   private int numCols;
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
    * @param mineData  the data for the mines; must have at least one row and one col.
    */
   public MineField(boolean[][] mineData) {
       this.numRows = mineData.length;
       this.numCols = mineData[0].length;
       //We need to get the numMines from the mineData.
       this.mineData = new boolean[numRows][numCols];
       for (int i = 0; i < numRows; i++) {
          for (int j = 0; j < numCols; j++) {
              if (mineData[i][j]) {
                  this.mineData[i][j] = mineData[i][j];
                  numMines++;
              }
          }
       }
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      assert numRows > 0 && numCols > 0 && numMines >= 0 && numMines < numRows*numCols/3;
      
      this.mineData = new boolean[numRows][numCols];
      this.numMines = numMines;
      this.numRows = numRows;
      this.numCols = numCols;
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {
      assert inRange(row, col);
      
      //Remove current mines on the minefield
      resetEmpty();
      Random rand = new Random();

      Set<Integer> s = new HashSet<>();
      //We need the size of this set to become numMines;
      while (s.size() < numMines) {
           //Generate random integers from 0 to 80.
          int n = rand.nextInt(numRows*numCols);
          //If n is already in the set OR n happens to be the location to avoid, we will not add n into the set. 
          if (!s.contains(n) && n != row * numCols + col) {
              s.add(n);
          }
      }
      //After we get our set, we set the corresponding location of each number to be a Mine (true).
      for (int m : s) {
          mineData[m / numCols][m % numCols] = true;
      }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state the minefield is in at the beginning of a game.
    */
   public void resetEmpty() {
      for (int i = 0;i < numRows;i++) {
        for (int j = 0;j < numCols;j++) {
          mineData[i][j] = false;
        }
      }
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      assert inRange(row, col);
      int count = 0;
      for (int i = row - 1;i < row + 2;i++) {
        for (int j = col - 1;j < col + 2;j++) {
          if (!inRange(i, j) || (i == row && j == col)) {
            continue;
          }
          if (hasMine(i, j)) {
            count++;
          }
        }
      }
      return count;
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      return row >= 0 && row <= numRows - 1 && col >= 0 && col <= numCols - 1;
   }
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return numRows;      
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return numCols;       
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      assert inRange(row, col);
      return mineData[row][col];       
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return numMines;       
   }

   
   // <put private methods here>
   
         
}

