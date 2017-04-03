package checkers;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;

import checkers.Checker.CheckerType;

public class Checker extends JButton {
	public enum CheckerType {
		RED, RED_KING, BLACK, BLACK_KING;
	}
	
	int x; 
	int y;
	
	
	
	
	public CheckerType checkerType;
	public Checker(CheckerType checkerType) {
		this.checkerType = checkerType;
		switch (checkerType) {
		case RED:
			this.setBackground(Color.RED);
			
			break;
		case BLACK:
			this.setBackground(Color.BLACK);
		
			break;
		}
		
		this.setSize(100, 100);
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
