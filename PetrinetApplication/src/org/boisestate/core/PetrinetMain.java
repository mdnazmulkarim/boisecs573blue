package org.boisestate.core;

import javax.swing.JFrame;
import org.boisestate.graphics.MainPanel;


public class PetrinetMain {

	public static void main(String[] args) {
		System.out.println("This is the main program of petrinet");
		System.out.println("This is main class");
		MainPanel mainPanel = new MainPanel();
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setVisible(true);
		

	}
	
	

}
