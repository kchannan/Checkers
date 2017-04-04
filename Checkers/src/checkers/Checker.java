package checkers;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Checker extends JButton {

	ImageIcon RedImg = new ImageIcon(this.getClass().getResource("RedChecker.png"));
	ImageIcon BlackImg = new ImageIcon(this.getClass().getResource("BlackChecker.png"));

	public enum CheckerType {
		RED, RED_KING, BLACK, BLACK_KING, BLANK;
	}

	int x;
	int y;

	public CheckerType checkerType;

	public Checker(CheckerType checkerType) {
		this.checkerType = checkerType;
		switch (checkerType) {
		case RED:
			this.setIcon(RedImg);

			break;
		case BLACK:
			this.setIcon(BlackImg);

			break;
		case BLANK:

			break;
		}

		setPreferredSize(new Dimension(50, 50));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
