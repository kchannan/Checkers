package checkers;

public class CheckersMove {
	int fromRow;
	int fromCol;
	int toRow;
	int toCol;
CheckersMove(int r1, int c1, int r2, int c2) {
		fromRow = r1;
		fromCol = c1;
		toRow = r2;
		toCol = c2;
	}

public boolean isJump(){
	if (Math.abs(fromRow-toRow)==2)
		return true;
	else
		return false;
}

}
