package checkers;

import java.awt.*;

import javax.swing.JPanel;

import checkers.Checker.CheckerType;

public class Board extends JPanel {

	Checker[][] checkerSet = new Checker[8][8];
	GridBagConstraints gbc = new GridBagConstraints();

	public Board() {
		setLayout(new GridBagLayout());
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((row + col) % 2 == 0) {
					if (row < 3) {
						checkerSet[row][col] = new Checker(CheckerType.RED);
					} else if (row > 4) {
						checkerSet[row][col]= new Checker(CheckerType.BLACK);
					}
					else
						checkerSet[row][col] = new Checker(CheckerType.BLANK);
					
					
					checkerSet[row][col].setBackground(Color.lightGray);
					gbc.gridx = col;
					gbc.gridy = row;

				} else {
					checkerSet[row][col] = new Checker(CheckerType.BLANK);
					checkerSet[row][col].setBackground(Color.gray);
					gbc.gridx = row;
					gbc.gridy = col;
				}
				add(checkerSet[row][col], gbc);

			}
		}
	}
}
