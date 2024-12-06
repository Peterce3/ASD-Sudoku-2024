/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #17
 * 1 - 5026231138 - Peter Christian Erastus
 */
package sudoku;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame { 
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // private variables
   GameBoardPanel board = new GameBoardPanel();
   JButton btnNewGame = new JButton("New Game");
   JButton btnCheck = new JButton("Check");

   // Constructor
   public SudokuMain() {
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());

      cp.add(board, BorderLayout.CENTER);
      cp.add(btnCheck, BorderLayout.SOUTH);
      cp.add(btnNewGame, BorderLayout.NORTH);

      // Add a button to the south to re-start the game via board.newGame()
      // ......

      // Initialize the game board to start the game
      board.newGame();
      btnNewGame.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent evt) {
    	        board.newGame();
    	    }
    	});
      
      btnCheck.addActionListener(new ActionListener() {
    	  @Override
    	  public void actionPerformed(ActionEvent evt) {
    	      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
    	          for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
    	        	  if (GameBoardPanel.input[row][col]==GameBoardPanel.getCells()[row][col].number&&GameBoardPanel.input[row][col]!=0) {
    	        		  GameBoardPanel.getCells()[row][col].status = CellStatus.CORRECT_GUESS;
    	        		  GameBoardPanel.getCells()[row][col].paint();   // re-paint this cell based on its status
    	              } else if(GameBoardPanel.input[row][col]!=GameBoardPanel.getCells()[row][col].number&&GameBoardPanel.input[row][col]!=0 ){
    	            	  GameBoardPanel.getCells()[row][col].status = CellStatus.WRONG_GUESS;
    	            	  GameBoardPanel.getCells()[row][col].paint();   // re-paint this cell based on its status
    	              }
    	          }
    	       }
    	  }
      });

      pack();     // Pack the UI components, instead of using setSize()
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
      setTitle("Sudoku");
      setVisible(true);
   }

   /** The entry main() entry method */
   public static void main(String[] args) {
      // [TODO 1] Check "Swing program template" on how to run
      //  the constructor of "SudokuMain"
      SudokuMain a = new SudokuMain();
   }
}