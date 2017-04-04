package checkers;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import checkers.Checker.CheckerType;

public class BoardGUI extends JFrame {

	
	Board theBoard = new Board();

	public BoardGUI() {
		this.setTitle("Checkers");

		setSize(400, 400);
		add(theBoard);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}

	public static void main(String[] args) {
		new BoardGUI();

	}

}
