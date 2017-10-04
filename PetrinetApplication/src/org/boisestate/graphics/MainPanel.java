package org.boisestate.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
 
enum State { PLACE, TRANSITION, ARC }

public class MainPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JMenuBar menuBar;
	protected JMenuItem newAction,openAction,saveAction,exitAction;
	protected JButton placeButton,transitionButton,arcButton;
	protected JPanel drawingPanel;
	public State currentState;
	
    public MainPanel() {
        setTitle("Draw Petri Net");
        
     // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // get 2/3 of the height, and 2/3 of the width
        int height = screenSize.height * 3 / 4;
        int width = screenSize.width * 5/6;

        // set the jframe height and width
//        jframe.setPreferredSize(new Dimension(width, height));
        
        setSize(width, height);
        this.getContentPane().setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
  
        placeButton = new JButton("Place");
  	  	placeButton.setVisible(false);
  	  	add(placeButton);
	        
	    transitionButton = new JButton("Transition");
	    transitionButton.setVisible(false);
	    add(transitionButton);
			
	    arcButton = new JButton("Arc");
	    arcButton.setVisible(false);
	    add(arcButton);
        
        createFileMenu();
        createEditMenu();
        createActions();
        
        
        drawingPanel = new JPanel(new BorderLayout()); 
        drawingPanel.setPreferredSize(new Dimension(width, height));
        drawingPanel.setBackground(Color.white);
		this.getContentPane().add(drawingPanel, BorderLayout.NORTH);
    }
    private void createActions() {
      newAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  System.out.println("You have clicked on the new action");
    		  placeButton.setVisible(true);
    		  transitionButton.setVisible(true);
    		  arcButton.setVisible(true);
    		  
    	  }
      });
      openAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {

    	  }
      });
      saveAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {

    	  }
      });
      exitAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  dispose();
    	  }
      });
      placeButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
//    		  double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
//    	      double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
//    		  
//    		  Place p = new Place();
//    		  p.setCoordinate(mouseX, mouseY);
//    		  p.paintComponent(getGraphics());
    		  
    		  currentState = currentState.PLACE;
    		 
    	  }
      });
      
      
    }
    
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
         
        newAction = new JMenuItem("New");
        openAction = new JMenuItem("Open");
        saveAction = new JMenuItem("Save");
        exitAction = new JMenuItem("Exit");
        
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        return fileMenu;
    }
    protected JMenu createEditMenu() {
         JMenu editMenu = new JMenu("Edit");
         menuBar.add(editMenu);
      
         JMenuItem cutAction = new JMenuItem("Cut");
         JMenuItem copyAction = new JMenuItem("Copy");
         JMenuItem pasteAction = new JMenuItem("Paste");
         JMenuItem undoAction = new JMenuItem("Undo");
         JMenuItem redoAction = new JMenuItem("Redo");
          
         editMenu.add(cutAction);
         editMenu.add(copyAction);
         editMenu.add(pasteAction);
         editMenu.add(undoAction);
         editMenu.add(redoAction);
        return editMenu;
    }
    
}
