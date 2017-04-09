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

	private String color;
	private int x;
	private int y;

	public boolean isRed() {
		if (this.color == "Red")
			return true;
		else
			return false;

	}

	public boolean isBlack() {
		if (this.color == "Black")
			return true;
		else
			return false;
	}

	public boolean isBlank() {
		if (this.color == "Blank")
			return true;
		else
			return false;
	}

	public int getXPosition() {
		return this.x;
	}

	public int getYPosition() {
		return this.y;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setActivePiece() {
		this.setBackground(Color.yellow);
	}

	public void unactivatePiece() {
		this.setBackground(Color.lightGray);
	}

	public CheckerType checkerType;

	public Checker() {

	}

	public Checker(CheckerType checkerType) {
		this.checkerType = checkerType;
		switch (checkerType) {
		case RED:
			this.setIcon(RedImg);
			this.color = "Red";
			this.setBackground(Color.lightGray);
			break;
		case BLACK:
			this.setIcon(BlackImg);
			this.color = "Black";
			this.setBackground(Color.lightGray);
			break;
		case BLANK:
			this.color = "Blank";
			this.setBackground(Color.lightGray);
			break;
		}

		setPreferredSize(new Dimension(50, 50));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
