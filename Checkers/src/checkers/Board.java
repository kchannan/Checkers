package checkers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

import checkers.Checker.CheckerType;

public class Board extends JPanel implements ActionListener {

	Checker[][] checkerSet = new Checker[8][8];
	boolean turnInProgress = false;
	GridBagConstraints gbc = new GridBagConstraints();
	Checker temp = new Checker();
	Checker theBlank = new Checker(CheckerType.BLANK);
	Checker theRed = new Checker(CheckerType.RED);
	Checker theBlack = new Checker(CheckerType.BLACK);
	/*
	 * For whoseTurn, 0 is Black 1 is Red
	 */
	private int whoseTurn = 0;

	public Board() {
		setLayout(new GridBagLayout());
		theBlank.setBackground(Color.lightGray);
		theRed.setBackground(Color.lightGray);
		theBlack.setBackground(Color.lightGray);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((row + col) % 2 == 0) {
					if (row < 3) {
						checkerSet[row][col] = new Checker(CheckerType.RED);
					} else if (row > 4) {
						checkerSet[row][col] = new Checker(CheckerType.BLACK);
					} else
						checkerSet[row][col] = new Checker(CheckerType.BLANK);

					checkerSet[row][col].setBackground(Color.lightGray);

					gbc.gridx = col;
					gbc.gridy = row;

				} else {
					checkerSet[row][col] = new Checker(CheckerType.BLANK);
					checkerSet[row][col].setBackground(Color.gray);
					gbc.gridx = col;
					gbc.gridy = row;
				}

				checkerSet[row][col].setXY(col, row);
				add(checkerSet[row][col], gbc);
				checkerSet[row][col].addActionListener(this);

			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		Checker checkerPressed = (Checker) e.getSource();
		Checker theBlank = new Checker(CheckerType.BLANK);
		Checker theRed = new Checker(CheckerType.RED);
		Checker theBlack = new Checker(CheckerType.BLACK);
		theBlank.addActionListener(this);
		theRed.addActionListener(this);
		theBlack.addActionListener(this);
		if (whoseTurn == 0) {
			if (turnInProgress == false) {

				if (checkerPressed.isBlack()) {

					checkerPressed.setActivePiece();
					temp = checkerPressed;
					turnInProgress = true;
				} else {
					if (checkerPressed.isRed() || checkerPressed.isBlank()) {
						System.out.println("It's Black's turn, please select a Black piece to move.");

					}

				}
			} else {
				if (checkerPressed.isBlack() || checkerPressed.isRed()) {
					System.out.println("Pressed other piece");
					temp.unactivatePiece();
					turnInProgress = false;
				} else {
					if (checkerPressed.getYPosition() == temp.getYPosition() - 1) {
						if ((checkerPressed.getXPosition() == temp.getXPosition() - 1)
								|| (checkerPressed.getXPosition() == temp.getXPosition() + 1)) {

							Checker movedTo = checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()];
							Checker movedFrom = checkerSet[temp.getYPosition()][temp.getXPosition()];
							// checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()]
							// = new Checker(CheckerType.BLACK);
							// checkerSet[temp.getYPosition()][temp.getXPosition()]
							// = new Checker(CheckerType.BLANK);

							theBlank.setBackground(Color.lightGray);

							GridBagLayout layout = (GridBagLayout) getLayout();
							GridBagConstraints gbc = layout.getConstraints(movedTo);
							
							remove(movedTo);
							
							add(theBlack, gbc);

							gbc = layout.getConstraints(movedFrom);
							remove(movedFrom);
							add(theBlank, gbc);
							revalidate();
							repaint();
							checkerSet[temp.getYPosition()][temp.getXPosition()].addActionListener(this);
							checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()].addActionListener(this);

							System.out.println("Legal move");
							turnInProgress = false;
							whoseTurn = 1;
						} else
							System.out.println("Not a legal move! Wrong X");
						temp.unactivatePiece();
						turnInProgress = false;

					} else {
						System.out.println("Not a legal move! Wrong Y");
						temp.unactivatePiece();
						turnInProgress = false;
					}

				}

			}

		} else if (whoseTurn == 1) {
			if (turnInProgress == false) {

				if (checkerPressed.isRed()) {

					checkerPressed.setActivePiece();
					temp = checkerPressed;
					turnInProgress = true;
				} else {
					if (checkerPressed.isBlack() || checkerPressed.isBlank()) {
						System.out.println("It's Black's turn, please select a Black piece to move.");

					}

				}
			} else {
				if (checkerPressed.isBlack() || checkerPressed.isRed()) {
					System.out.println("Not a valid Move!");
					temp.unactivatePiece();
					turnInProgress = false;
				} else {
					if (checkerPressed.getYPosition() == temp.getYPosition() + 1) {
						if ((checkerPressed.getXPosition() == temp.getXPosition() - 1)
								|| (checkerPressed.getXPosition() == temp.getXPosition() + 1)) {

							Checker movedTo = checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()];
							Checker movedFrom = checkerSet[temp.getYPosition()][temp.getXPosition()];
							// checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()]
							// = new Checker(CheckerType.RED);
							// checkerSet[temp.getYPosition()][temp.getXPosition()]
							// = new Checker(CheckerType.BLANK);
							
							System.out.println(checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()].getXPosition());
							System.out.println(checkerSet[temp.getYPosition()][temp.getXPosition()].getXPosition());
							theBlank.setBackground(Color.lightGray);

							GridBagLayout layout = (GridBagLayout) getLayout();
							GridBagConstraints gbc = layout.getConstraints(movedTo);
							remove(movedTo);
							add(theRed, gbc);
							revalidate();
							repaint();

							gbc = layout.getConstraints(movedFrom);
							remove(movedFrom);
							add(theBlank, gbc);
							revalidate();
							repaint();
							checkerSet[temp.getYPosition()][temp.getXPosition()].addActionListener(this);
							checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()].addActionListener(this);
							System.out.println("Legal move");
							turnInProgress = false;
							whoseTurn = 0;
						} else
							System.out.println("Not a legal move! Try again.");
						temp.unactivatePiece();
						turnInProgress = false;

					} else {
						System.out.println("Not a legal move! Try again.");
						temp.unactivatePiece();
						turnInProgress = false;
					}

				}

			}
		}

	}

}
