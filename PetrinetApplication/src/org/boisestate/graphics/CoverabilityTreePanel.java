package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.boisestate.petrinet.Petrinet;

public class CoverabilityTreePanel extends JPanel{
	
	private Petrinet petrinet;
	
	public CoverabilityTreePanel(Petrinet  petrinet,int width,int height) {
		this.petrinet = petrinet;
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.gray);
		this.setVisible(true);
	}
	
}