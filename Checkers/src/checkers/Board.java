package checkers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

import checkers.Checker.CheckerType;

public class Board extends JPanel implements ActionListener {

	Checker[][] boardSet = new Checker[8][8];
	// for turnInProgress, 0 is beginning of turn, 1 is turnInProgress, 2 is
	// subsequent jumps
	int turnInProgress = 0;
	CheckersMove[] legalJumps;
	BoardData theBoard;
	GridBagConstraints gbc = new GridBagConstraints();
	Checker temp = new Checker();
	CheckersMove[] legalMoves;

	/*
	 * For whoseTurn, 0 is Black 1 is Red
	 */
	private int whoseTurn = BoardData.BLACK;

	public Board() {
		theBoard = new BoardData();
		theBoard.setUpBoard();
		setLayout(new GridBagLayout());
		refreshBoard();

	}

	public void refreshBoard() {
		removeAll();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (theBoard.getBoard()[row][col] == BoardData.RED)
					boardSet[row][col] = new Checker(CheckerType.RED);
				else if (theBoard.getBoard()[row][col] == BoardData.BLACK)
					boardSet[row][col] = new Checker(CheckerType.BLACK);
				else if (theBoard.getBoard()[row][col] == BoardData.RED_KING)
					boardSet[row][col] = new Checker(CheckerType.RED_KING);
				else if (theBoard.getBoard()[row][col] == BoardData.BLACK_KING)
					boardSet[row][col] = new Checker(CheckerType.BLACK_KING);
				else
					boardSet[row][col] = new Checker(CheckerType.BLANK);

				if (row % 2 == col % 2)
					boardSet[row][col].setBackground(Color.lightGray);
				else
					boardSet[row][col].setBackground(Color.gray);

				gbc.gridx = col;
				gbc.gridy = row;

				boardSet[row][col].setRowCol(row, col);
				add(boardSet[row][col], gbc);
				boardSet[row][col].addActionListener(this);

			}
		}
		revalidate();
		repaint();
	}

	public boolean isLegalMove(CheckersMove a, CheckersMove b) {
		if (a.fromRow == b.fromRow && a.fromCol == b.fromCol && a.toRow == b.toRow && a.toCol == b.toCol)
			return true;
		else
			return false;
	}

	public void doMove(Checker first, Checker second) {

	}

	public void actionPerformed(ActionEvent e) {
		
		Checker checkerPressed = (Checker) e.getSource();
		if (whoseTurn == BoardData.BLACK) {

			// if the turn is just starting out
			if (turnInProgress == 0) {
		
				legalMoves = theBoard.getLegalMoves(whoseTurn);
				for (int i = 0; i < legalMoves.length; i++) {
					if (checkerPressed.getRow() == legalMoves[i].fromRow && checkerPressed.getCol() == legalMoves[i].fromCol  && checkerPressed.isBlack()) {

						checkerPressed.setActivePiece();
						temp = checkerPressed;
						turnInProgress = 1;

					}

				}
				// if the the player has selected a piece to move
			} else if (turnInProgress == 1) {

				CheckersMove theMove = new CheckersMove(temp.getRow(), temp.getCol(), checkerPressed.getRow(),
						checkerPressed.getCol());

				if (checkerPressed == temp) {
					turnInProgress = 0;
					temp.unactivatePiece();

				} else
					for (int i = 0; i < legalMoves.length; i++) {
						if (isLegalMove(legalMoves[i], theMove)) {

							if (theMove.isJump()) {
								theBoard.makeMove(theMove);
								refreshBoard();
								legalJumps = theBoard.getLegalJumpsFrom(whoseTurn, theMove.toRow, theMove.toCol);

								if (legalJumps != null) {
									System.out.print("theres another jump");
									temp = checkerPressed;
									temp.setActivePiece();
									turnInProgress = 2;
								} else {
									turnInProgress = 0;
									whoseTurn = BoardData.RED;
								}

								break;
							} else {
								turnInProgress = 0;
								whoseTurn = BoardData.RED;
								theBoard.makeMove(theMove);
								refreshBoard();
								break;
							}
						}

					}
			}

		} else if (whoseTurn == BoardData.RED) {
			if (turnInProgress == 0) {
				
				legalMoves = theBoard.getLegalMoves(whoseTurn);
				for (int i = 0; i < legalMoves.length; i++) {
					temp = checkerPressed;

					if (checkerPressed.getRow() == legalMoves[i].fromRow && checkerPressed.getCol() == legalMoves[i].fromCol && checkerPressed.isRed()) {
						temp.setActivePiece();
						turnInProgress = 1;

					}

				}
			} else if (turnInProgress == 1) {

				CheckersMove theMove = new CheckersMove(temp.getRow(), temp.getCol(), checkerPressed.getRow(),
						checkerPressed.getCol());
				if (checkerPressed == temp) {
					turnInProgress = 0;
					temp.unactivatePiece();
					refreshBoard();
				} else
					for (int i = 0; i < legalMoves.length; i++) {
						if (theMove.isJump()) {
							theBoard.makeMove(theMove);
							refreshBoard();
							legalJumps = theBoard.getLegalJumpsFrom(whoseTurn, theMove.toRow, theMove.toCol);

							if (legalJumps != null) {
								System.out.print("theres another jump");
								temp = checkerPressed;
								temp.setActivePiece();
								turnInProgress = 2;
							} else {
								turnInProgress = 0;
								whoseTurn = BoardData.BLACK;
							}

							break;
						} else {
							turnInProgress = 0;
							whoseTurn = BoardData.BLACK;
							theBoard.makeMove(theMove);
							refreshBoard();
							break;
						}

					}
			}

		}
		if (turnInProgress == 2) {
			CheckersMove theMove = new CheckersMove(temp.getRow(), temp.getCol(), checkerPressed.getRow(),
					checkerPressed.getCol());

			for (int i = 0; i < legalJumps.length; i++) {

				if (isLegalMove(legalJumps[i], theMove)) {
					theBoard.makeMove(theMove);
					refreshBoard();
					turnInProgress = 0;
					if (whoseTurn == BoardData.RED)
						whoseTurn = BoardData.BLACK;
					else
						whoseTurn = BoardData.RED;
					
					legalJumps = null;

					break;
				}
			}
		}

	}

}
