package org.boisestate.graphics;

import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
 

public class MainPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JMenuBar menuBar;
	protected JMenuItem newAction,openAction,saveAction,exitAction;
	protected JButton placeButton,transitionButton,arcButton;
	
    public MainPanel() {
        setTitle("Draw Petri Net");
        setSize(500, 500);
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
    		  double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
    	      double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
    		  
//    		  Place p = new Place();
//    		  p.setCoordinate(mouseX, mouseY);
//    		  p.paintComponent(getGraphics());
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
