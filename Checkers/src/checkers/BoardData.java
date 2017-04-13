package checkers;

public class BoardData {
	public static final int EMPTY = 0, RED = 1, RED_KING = 2, BLACK = 3, BLACK_KING = 4;

	public int[][] board;

	public BoardData() {
		setUpBoard();
	}

	public void setUpBoard() {
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				if (row % 2 == col % 2) {
					if (row < 3)
						board[row][col] = BLACK;
					else if (row > 4)
						board[row][col] = RED;
					else
						board[row][col] = EMPTY;
				} else {
					board[row][col] = EMPTY;
				}
			}
		}
	}
	public int pieceAt(int row, int col){
		return board[row][col];
	}
	
	public void makeMove(CheckersMove move){
		makeMove(move.fromX, move.fromY, move.toX, move.toY );
	}
	
	public void makeMove(int fromX, int fromY, int toX, int toY)
	{
		board[toX][toY] = board[fromX][fromY];
		board[fromX][fromY] = board[toX][toY];
		
	}
}
