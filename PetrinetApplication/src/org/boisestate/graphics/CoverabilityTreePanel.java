package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.boisestate.petrinet.Petrinet;

public class CoverabilityTreePanel extends JPanel{
	
	private Petrinet petrinet;
	private JButton inputInitialMarkingButton;
	private JTextField inputInitialMarkingTextField;
	private JLabel inputInitialMarkingLabel;
	
	public CoverabilityTreePanel(Petrinet  petrinet,int width,int height) {
		this.petrinet = petrinet;
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(new Color(204,204,255));
		inputInitialMarkingLabel = new JLabel("");
		inputInitialMarkingLabel.setText("Input Marking"+petrinet.getPetrinetBuilder().getCurrentPlaceSequence());
		
		inputInitialMarkingTextField = new JTextField(15);
		inputInitialMarkingButton = new JButton("Set");
		inputInitialMarkingButton.addActionListener(new InitialMarkingBurronListener());
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(inputInitialMarkingLabel);
		add(inputInitialMarkingTextField);
		add(inputInitialMarkingButton);
		this.setVisible(true);
	}
	
	private class InitialMarkingBurronListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}