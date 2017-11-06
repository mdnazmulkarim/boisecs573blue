package org.boisestate.core;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.boisestate.graphics.MainPanel;


public class PetrinetMain {
	
	private static PetrinetMain  mainApplication;
	private MainPanel mainPanel;

	public static void main(String[] args) {
		System.out.println("This is the main program of petrinet");		
		mainApplication = new PetrinetMain();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mainApplication.createMainPanel();                        
            }
        });
		
	}
	
	private void createMainPanel() {
		mainPanel = new MainPanel();
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setVisible(true);		
		
	}
	
}
