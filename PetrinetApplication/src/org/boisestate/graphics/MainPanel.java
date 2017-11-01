package org.boisestate.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
 
enum State { PLACE, TRANSITION, ARC, NOTHING, COPY, PASTE, DELETE, UNDO, REDO }

public class MainPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JMenuBar menuBar;
	protected JMenuItem newAction,openAction,saveAction,exitAction,deleteAction,copyAction,pasteAction,undoAction,redoAction;
	protected JButton placeButton,transitionButton,arcButton,arrowButton;
	protected DrawingPanel drawingPanel;
	public static State currentState;
	public Petrinet petrinet;
	public PetriNetSaver savePetrinet;
	public static ArrayList<String> placeCoordinator = new ArrayList<String>();
	public static ArrayList<String> transitionCoordinator = new ArrayList<String>();
	int width, height;
    public MainPanel() {
        setTitle("Draw Petri Net");
        petrinet = new Petrinet("Vending Machine");
        savePetrinet = new PetriNetSaver(petrinet);
     // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

         height = screenSize.height * 3 / 4;
         width = screenSize.width * 5/6;

        setSize(width, height);
        this.getContentPane().setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
  
        placeButton = new JButton("Place");
  	  	placeButton.setVisible(true);
  	  	add(placeButton);
	        
	    transitionButton = new JButton("Transition");
	    transitionButton.setVisible(true);
	    add(transitionButton);
			
	    arcButton = new JButton("Arc");
	    arcButton.setVisible(true);
	    add(arcButton);
        
	    arrowButton = new JButton("Arrow");
	    arrowButton.setVisible(true);
	    add(arrowButton);
	    
        createFileMenu();
        createEditMenu();
        createActions();
        
        
        drawingPanel = new DrawingPanel(petrinet); 
        drawingPanel.setPreferredSize(new Dimension(width, height));
        drawingPanel.setBackground(Color.white);
		this.getContentPane().add(drawingPanel, BorderLayout.NORTH);
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
    }
    public int alertDialog() {
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to close current petrinet?","Warning",2); 
    	if(dialogResult == JOptionPane.YES_OPTION){ 
    		// Saving code here }
    		 
    	}
    	return dialogResult;
    }
    public void fileChoose() {
    	JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			petrinet.removeAllPlace();
			XMLParser parser = new XMLParser(selectedFile,petrinet);
			drawingPanel.paintAgain();
		}
    }
    public void undoAction() {
    	Object obj = petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
    	Object actionObj = petrinet.getPetrinetBuilder().getElementFromActionArrayList();
    	
		if(obj!=null && obj instanceof Place) {
			if(actionObj.equals("A")) {
				System.out.println("Count "+ petrinet.placeVector.size());
				petrinet.placeVector.remove(obj);
				System.out.println("count next ="+petrinet.placeVector.size());
				drawingPanel.paintAgain();
				petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("D");
			}else {
				System.out.println("Count "+ petrinet.placeVector.size());
				petrinet.placeVector.add((Place) obj);
				System.out.println("count next ="+petrinet.placeVector.size());
				drawingPanel.paintAgain();
				petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("A");
			}
			
			petrinet.getPetrinetBuilder().putElementInRedoArrayList(obj);
			petrinet.getPetrinetBuilder().removeElementFromWorkingArrayList();
			petrinet.getPetrinetBuilder().removeElementFromActionArrayList();	
		}
		
    }
    public void redoAction() {
    	Object obj = petrinet.getPetrinetBuilder().getElementFromRedoArrayList();
    	Object actionObj = petrinet.getPetrinetBuilder().getElementFromRedoActionArrayList();
    	
		if(obj!=null && obj instanceof Place) {
			if(actionObj.equals("A")) {
				petrinet.placeVector.remove(obj);
				drawingPanel.paintAgain();
				petrinet.getPetrinetBuilder().putElementInActionArrayList("D");
			}else {
				petrinet.placeVector.add((Place) obj);
				drawingPanel.paintAgain();
				petrinet.getPetrinetBuilder().putElementInActionArrayList("A");
			}
			
			petrinet.getPetrinetBuilder().putElementInWorkingArrayList(obj);
			petrinet.getPetrinetBuilder().removeElementFromRedoActionArrayList();
			petrinet.getPetrinetBuilder().removeElementFromRedoArrayList();	
		}
		
    }
    private void createActions() {
      newAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		if(alertDialog() == JOptionPane.YES_OPTION) {
    			petrinet.removeAllPlace();
     			drawingPanel.paintAgain();
    		}
    	  }
      });
      openAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  if(alertDialog() == JOptionPane.YES_OPTION) {
        		  fileChoose();
      		}
    	  }
      });
      saveAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  savePetrinet.xmlFileName();
    	  }
      });
      deleteAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  currentState = currentState.DELETE;
    	  }
      });
      copyAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  currentState = currentState.COPY;
    	  }
      });
      pasteAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  currentState = currentState.PASTE;
    	  }
      });
      undoAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
//    		  currentState = currentState.UNDO;
    		  if(petrinet.getPetrinetBuilder().workingArrayList.size()>0) {
        		  undoAction();
    		  }
    	  }
      });
      redoAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
//    		  currentState = currentState.REDO;
    		  if(petrinet.getPetrinetBuilder().redoArrayList.size()>0) {
        		  redoAction();
    		  }
    	  }
      });
      exitAction.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		  dispose();
    	  }
      });
      placeButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    	      currentState = currentState.PLACE;
    		 
    	  }
      });
      transitionButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    	      currentState = currentState.TRANSITION;
    		 
    	  }
      });
      arcButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    	      currentState = currentState.ARC;
    		 
    	  }
      });
      arrowButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    	      currentState = currentState.NOTHING;
    		 
    	  }
      });
    }
    
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
         
        newAction = new JMenuItem("New");
        openAction = new JMenuItem("Open");
        saveAction = new JMenuItem("Save As");
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
      
         deleteAction = new JMenuItem("Delete");
         copyAction = new JMenuItem("Copy");
         pasteAction = new JMenuItem("Paste");
         undoAction = new JMenuItem("Undo");
         redoAction = new JMenuItem("Redo");
          
         editMenu.add(deleteAction);
         editMenu.add(copyAction);
         editMenu.add(pasteAction);
         editMenu.add(undoAction);
         editMenu.add(redoAction);
        return editMenu;
    }
    
}
