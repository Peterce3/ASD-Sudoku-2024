/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #17
 * 1 - 5026231138 - Peter Christian Erastus
 */
package sudoku;
import java.util.Random;
/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle { 
   // All variables have package access
   // The numbers on the puzzle
	int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
   // The clues - isGiven (no need to guess) or need to guess
	boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

   // Constructor
	public Puzzle() {
		super();
	}

   // Generate a new puzzle given the number of cells to be guessed, which can be used
   //  to control the difficulty level.
   // This method shall set (or update) the arrays numbers and isGiven
	public void newPuzzle(int cellsToGuess) {
		// I hardcode a puzzle here for illustration and testing.
		int[][] hardcodedNumbers =
			{{5, 3, 4, 6, 7, 8, 9, 1, 2},
			{6, 7, 2, 1, 9, 5, 3, 4, 8},
			{1, 9, 8, 3, 4, 2, 5, 6, 7},
			{8, 5, 9, 7, 6, 1, 4, 2, 3},
			{4, 2, 6, 8, 5, 3, 7, 9, 1},
			{7, 1, 3, 9, 2, 4, 8, 5, 6},
			{9, 6, 1, 5, 3, 7, 2, 8, 4},
			{2, 8, 7, 4, 1, 9, 6, 3, 5},
			{3, 4, 5, 2, 8, 6, 1, 7, 9}};


		Random rand = new Random();

		// Shuffle rows within each band of 3 rows
		for (int band = 0; band < 3; band++) {
			shuffleRows(hardcodedNumbers, band * 3, rand);
		}

		// Shuffle columns within each stack of 3 columns
		for (int stack = 0; stack < 3; stack++) {
			shuffleColumns(hardcodedNumbers, stack * 3, rand);
		}

		// Shuffle entire bands of rows
		shuffleBands(hardcodedNumbers, rand);

		// Shuffle entire stacks of columns
		shuffleStacks(hardcodedNumbers, rand);

		// Copy the randomized grid into the `numbers` array
		for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
			for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
				numbers[row][col] = hardcodedNumbers[row][col];
			}
		}
		Random rand1 = new Random();
		for (int gridRow = 0; gridRow < 3; gridRow++) {
   	    	for (int gridCol = 0; gridCol < 3; gridCol++) {
   	        	boolean hasTrue = false;

   	        // Iterate through each cell in the 3x3 sub-grid
   	        	for (int row = 0; row < 3; row++) {
   	        		for (int col = 0; col < 3; col++) {
   	        			// Calculate the global row and column indices
   	        			int globalRow = gridRow * 3 + row;
   	        			int globalCol = gridCol * 3 + col;

   	        			// Randomly set isGiven to true or false
   	        			isGiven[globalRow][globalCol] = rand1.nextBoolean();

   	        			// Track if there's at least one true in this grid
   	        			if (isGiven[globalRow][globalCol]) {
   	        				hasTrue = true;
   	        			}
   	        		}
   	        	}

   	        	// Ensure at least one true in this grid
   	        	if (!hasTrue) {
   	        		// Randomly pick one cell in this grid to set to true
   	        		int randomRow = gridRow * 3 + rand1.nextInt(3);
   	        		int randomCol = gridCol * 3 + rand1.nextInt(3);
   	        		isGiven[randomRow][randomCol] = true;
   	        	}
   	    	}
		}
	}

	// Helper to shuffle rows within a band (group of 3 rows)
	private void shuffleRows(int[][] grid, int startRow, Random rand) {
		for (int i = 0; i < 3; i++) {
			int swapRow = startRow + rand.nextInt(3);
			int[] temp = grid[startRow + i];
			grid[startRow + i] = grid[swapRow];
			grid[swapRow] = temp;
		}
	}

	// Helper to shuffle columns within a stack (group of 3 columns)
	private void shuffleColumns(int[][] grid, int startCol, Random rand) {
		for (int i = 0; i < 3; i++) {
			int swapCol = startCol + rand.nextInt(3);
			for (int row = 0; row < SudokuConstants.GRID_SIZE; row++) {
				int temp = grid[row][startCol + i];
				grid[row][startCol + i] = grid[row][swapCol];
				grid[row][swapCol] = temp;
			}
		}
	}

	// Helper to shuffle bands of rows
	private void shuffleBands(int[][] grid, Random rand) {
		for (int i = 0; i < 3; i++) {
			int swapBand = rand.nextInt(3);
			for (int row = 0; row < 3; row++) {
				int[] temp = grid[i * 3 + row];
				grid[i * 3 + row] = grid[swapBand * 3 + row];
				grid[swapBand * 3 + row] = temp;
			}
		}
	}

	// Helper to shuffle stacks of columns
	private void shuffleStacks(int[][] grid, Random rand) {
		for (int i = 0; i < 3; i++) {
			int swapStack = rand.nextInt(3);
			for (int row = 0; row < SudokuConstants.GRID_SIZE; row++) {
				for (int col = 0; col < 3; col++) {
					int temp = grid[row][i * 3 + col];
					grid[row][i * 3 + col] = grid[row][swapStack * 3 + col];
					grid[row][swapStack * 3 + col] = temp;
				}
			}
		}
	}
}