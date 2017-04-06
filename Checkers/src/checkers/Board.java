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

		if (whoseTurn == 0) {
			if (turnInProgress == false) {

				if (checkerPressed.isBlack()) {

					checkerPressed.setBackground(Color.yellow);
					temp = checkerPressed;
					turnInProgress = true;
				} else {
					if (checkerPressed.isRed() || checkerPressed.isBlank()) {
						System.out.print("It's Black's turn, please select a Black piece to move.");
					}

				}
			} else {
				if (checkerPressed.isBlack() || checkerPressed.isRed()) {
					System.out.print("That's not a legal move, silly!");
				} else {
					if (checkerPressed.getYPosition() == temp.getYPosition() - 1) {
						if ((checkerPressed.getXPosition() == temp.getXPosition() - 1)
								|| (checkerPressed.getXPosition() == temp.getXPosition() + 1)) {
							
							Checker movedTo = checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()];
							Checker movedFrom = checkerSet[temp.getYPosition()][temp.getXPosition()];
							theBlank.setBackground(Color.lightGray);
							
							GridBagLayout layout = (GridBagLayout)getLayout();
							GridBagConstraints gbc = layout.getConstraints(movedTo);
							remove(movedTo);
							add(new Checker(CheckerType.BLACK), gbc);
							revalidate();
							repaint();
						    gbc = layout.getConstraints(movedFrom);
						    remove(movedFrom);
						    add(theBlank, gbc);
						 	revalidate();
							repaint();
						    
							
							
							
						//	checkerSet[checkerPressed.getYPosition()][checkerPressed.getXPosition()] = temp; 
							
							
						
							
							System.out.print("Legal move");
							turnInProgress = false;
							whoseTurn = 1;
						} else
							System.out.print("Not a legal move!");
					} else {
						System.out.print("Not a legal move!");
					}

				}

			}
			/*
			 * if(temp.isRed()) {
			 * System.out.print("You clicked a red at X = "+temp.getXPosition()
			 * +", Y = "+ temp.getYPosition()); } else if(temp.isBlack()) {
			 * System.out.print("You clicked a black"); } else if
			 * (temp.isBlank()){ System.out.print("You clicked a blank"); }
			 */

		}

	}
	
}
