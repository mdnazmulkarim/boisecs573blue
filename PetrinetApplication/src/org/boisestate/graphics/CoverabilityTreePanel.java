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
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.boisestate.core.PetrinetBuilder;
import org.boisestate.petrinet.Marking;
import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.TreeNode;

public class CoverabilityTreePanel extends JPanel{
	
	private Petrinet petrinet;
	private JButton inputInitialMarkingButton;
	private JTextField inputInitialMarkingTextField;
	private JLabel inputInitialMarkingLabel;
	
	private String placeSequence = "";
	private String tokenSequence = "";
	private JPanel treePanel;
	
	public CoverabilityTreePanel(Petrinet  petrinet,int width,int height) {
		this.petrinet = petrinet;
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(new Color(204,204,255));
		inputInitialMarkingLabel = new JLabel("");
		placeSequence = petrinet.getPetrinetBuilder().getCurrentPlaceSequence();
		
		inputInitialMarkingLabel.setText("Input Marking"+placeSequence);
		
		inputInitialMarkingTextField = new JTextField(15);  //validation required
		inputInitialMarkingTextField.setText(petrinet.getPetrinetBuilder().getCurrentPlaceTokenSequence());
		inputInitialMarkingTextField.setToolTipText("Inside parentheses seperated by comma. ex., (1,0,0)");
		inputInitialMarkingButton = new JButton("Set");
		inputInitialMarkingButton.addActionListener(new InitialMarkingBurronListener());
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(inputInitialMarkingLabel);
		add(inputInitialMarkingTextField);
		add(inputInitialMarkingButton);
		this.setVisible(true);
		this.treePanel = this;
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
				PetrinetBuilder.markingIndex = 0;
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
					petrinet.getPetrinetBuilder().resetPlaceWithMarking(tempMarking);
					petrinet.getPetrinetBuilder().allMarking.clear();
					petrinet.getPetrinetBuilder().bfsQueue.clear();
					petrinet.getPetrinetBuilder().queueIndex =0;
					petrinet.getPetrinetBuilder().testTree = null;
					
					petrinet.getPetrinetBuilder().drawingPanel.revalidate();
					petrinet.getPetrinetBuilder().drawingPanel.repaint();
					TreeNode tree = petrinet.getPetrinetBuilder().generateCoverabilityTree();
					DefaultMutableTreeNode node =null;
					petrinet.getPetrinetBuilder().traverseTree(tree, node);
					JTree atree = new JTree(petrinet.getPetrinetBuilder().testTree);
					treePanel.add(atree);
					
					petrinet.setInitialMarking(tempMarking);
					petrinet.getPetrinetBuilder().resetPlaceWithMarking(tempMarking);
					petrinet.saveCurrentMarking();
					petrinet.getPetrinetBuilder().drawingPanel.resetTransitionColor();
					treePanel.revalidate();
					treePanel.repaint();
				}
			}
		}
		
	}
	
	private void traverseTree(TreeNode node)
	{
		
	}
	
	private void showAlert(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}