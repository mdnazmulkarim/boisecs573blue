package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.boisestate.petrinet.Marking;
import org.boisestate.petrinet.Petrinet;

public class CoverabilityTreePanel extends JPanel{
	
	private Petrinet petrinet;
	private JButton inputInitialMarkingButton;
	private JTextField inputInitialMarkingTextField;
	private JLabel inputInitialMarkingLabel;
	
	private String placeSequence = "";
	private String tokenSequence = "";
	
	public CoverabilityTreePanel(Petrinet  petrinet,int width,int height) {
		this.petrinet = petrinet;
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(new Color(204,204,255));
		inputInitialMarkingLabel = new JLabel("");
		placeSequence = petrinet.getPetrinetBuilder().getCurrentPlaceSequence();
		
		inputInitialMarkingLabel.setText("Input Marking"+placeSequence);
		
		inputInitialMarkingTextField = new JTextField(15);  //validation required
		inputInitialMarkingTextField.setText("()");
		inputInitialMarkingTextField.setToolTipText("Inside parentheses seperated by comma. ex., (1,0,0)");
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
			
			if(inputInitialMarkingTextField.getText()==null || inputInitialMarkingTextField.getText().isEmpty())
			{
				showAlert("Initial marking cannot be empty");
			}
			else
			{
				Marking tempMarking = new Marking("M0");
				tempMarking.setPlaceSequence(placeSequence);
	            tokenSequence = inputInitialMarkingTextField.getText();
				tempMarking.setTokenSequence(tokenSequence);
				System.out.println(tempMarking);
				System.out.println("INPUT : "+tokenSequence);
				if(!petrinet.getPetrinetBuilder().checkMarkingValidity(tempMarking))
				{	
					showAlert("Invalid initial marking. Please provide valid data.");
					}
				else
				{
					
					petrinet.setInitialMarking(tempMarking);
					petrinet.getPetrinetBuilder().resetPlaceWithinitialMarking();
					petrinet.getPetrinetBuilder().drawingPanel.revalidate();
					petrinet.getPetrinetBuilder().drawingPanel.repaint();
				}
			}
		}
		
	}
	
	private void showAlert(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}