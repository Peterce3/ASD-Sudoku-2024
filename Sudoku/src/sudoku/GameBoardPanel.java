/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #17
 * 1 - 5026231138 - Peter Christian Erastus
 */
package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel { 
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for UI sizes
   public static final int CELL_SIZE = 60;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * SudokuConstants.GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;
                                             // Board width/height in pixels

   // Define properties
   /** The game board composes of 9x9 Cells (customized JTextFields) */
   private static Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
   /** It also contains a Puzzle with array numbers and isGiven */
   private Puzzle puzzle = new Puzzle();
   static int[][] input = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

   /** Constructor */
   public GameBoardPanel() {
      super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));  // JPanel

      // Allocate the 2D array of Cell, and added into JPanel.
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]);   // JPanel
         }
      }
      
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
          for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
        	  input[row][col]=0;
          }
      }

      // [TODO 3] Allocate a common listener as the ActionEvent listener for all the
      //  Cells (JTextFields)
      CellInputListener listener = new CellInputListener();

      // [TODO 4] Adds this common listener to all editable cells
      for (int row = 0; row < SudokuConstants.GRID_SIZE; row++) {
         for (int col = 0 ; col < SudokuConstants.GRID_SIZE; col++) {
            if (cells[row][col].isEditable()) {
               cells[row][col].addKeyListener(listener);   // For all editable rows and cols
            }
         }
      }

      super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
   }

   /**
    * Generate a new puzzle; and reset the game board of cells based on the puzzle.
    * You can call this method to start a new game.
    */
   public void newGame() {
      // Generate a new puzzle
      puzzle.newPuzzle(2);

      // Initialize all the 9x9 cells, based on the puzzle.
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
         }
      }
   }

   /**
    * Return true if the puzzle is solved
    * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
    */
   public static boolean isSolved() {
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }
   
   public static Cell[][] getCells(){
	   return cells;
   }

   // [TODO 2] Define a Listener Inner Class for all the editable Cells
   private class CellInputListener implements KeyListener {
      @Override
      public void keyTyped(KeyEvent e) {
         // Get a reference of the JTextField that triggers this action event
         Cell sourceCell = (Cell)e.getSource();
         
         sourceCell.setText("");
		 
         // Retrieve the int entered
         char keyChar = e.getKeyChar();
         if (Character.isDigit(keyChar)) {
             int numberIn = Character.getNumericValue(keyChar);

             // Debugging output
             System.out.println("You entered " + numberIn);
             /*
              * [TODO 5] (later - after TODO 3 and 4)
              * Check the numberIn against sourceCell.number.
              * Update the cell status sourceCell.status,
              * and re-paint the cell via sourceCell.paint().
              */
             input[sourceCell.row][sourceCell.col]=numberIn;
             
             /*
              * [TODO 6] (later)
              * Check if the player has solved the puzzle after this move,
              *   by calling isSolved(). Put up a congratulation JOptionPane, if so.
              */
         } else {
        	 e.consume();
         } 
      }
      @Override
      public void keyPressed(KeyEvent e) {
          // No action needed for this example
      }

      @Override
      public void keyReleased(KeyEvent e) {
          // No action needed for this example
      }
   }
}