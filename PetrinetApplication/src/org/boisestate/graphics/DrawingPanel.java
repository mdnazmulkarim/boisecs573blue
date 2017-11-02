package org.boisestate.graphics;

import java.awt.Button;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;

public class DrawingPanel extends JPanel {
	

		

	public int placeCount = 0;
	int transitionCount = 0;
	Object selectedItem;
	
   Petrinet petrinet;

   
	public DrawingPanel(final Petrinet petrinet) {
		this.petrinet = petrinet;
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
            	if(e.getButton() == MouseEvent.BUTTON1) {
            		if(MainPanel.currentState == MainPanel.currentState.PLACE) {
            			Place place = new Place();
                		drawPlace(place, e.getX(),e.getY());                		
                	}else if (MainPanel.currentState == MainPanel.currentState.TRANSITION){
                		Transition transition = new Transition();
                		drawTransition(transition, e.getX(),e.getY());
                	}
                	else if (MainPanel.currentState == MainPanel.currentState.COPY){
                		Object obj = petrinet.selectedPlace(e.getX(), e.getY());
                		selectedItem = obj;
                	}else if (MainPanel.currentState == MainPanel.currentState.PASTE){
                		if(selectedItem!=null) {
                			if(selectedItem instanceof Place) {
                				Place place = (Place) selectedItem;
                				Place newPlace = (Place)place.clone();
                    			drawPlace(newPlace, e.getX(), e.getY());
                			}else if (selectedItem instanceof Transition) {
                				Transition trans = (Transition) selectedItem;
                				Transition newTrans = (Transition)trans.clone();
                    			drawTransition(newTrans, e.getX(), e.getY());
                			}
                			
                		}
                	}else if (MainPanel.currentState == MainPanel.currentState.DELETE){
                		Object obj = petrinet.selectedPlace(e.getX(), e.getY());
                		selectedItem = obj;
                		if(selectedItem!=null) {
                			if(selectedItem instanceof Place){
                				System.out.println("delete called");
                				petrinet.deletePlace((Place)selectedItem);
                    			repaint();
                			}else if (selectedItem instanceof Transition) {
                				System.out.println("delete called");
                    			petrinet.deleteTransition((Transition) selectedItem);
                    			repaint();
                			}
                			
                		}
                	}
            	}
            	if(e.getButton() == MouseEvent.NOBUTTON)
            		System.out.println("button 0");
            	if(e.getButton() == MouseEvent.BUTTON2)
            		System.out.println("button 2");
            	if(e.getButton() == MouseEvent.BUTTON3) {
            		Object obj = petrinet.selectedPlace(e.getX(), e.getY());
            		selectedItem = obj;
            		if(selectedItem!=null) {
            			if(selectedItem instanceof Place){
            				petrinet.getPetrinetBuilder().placeInputDialog(selectedItem);
            			}
            			else if (selectedItem instanceof Transition) {
            				petrinet.getPetrinetBuilder().transitionInputDialog(selectedItem);
            			}
            				
            		}
            		
            	}
            		
            	
                
            }
            
            
        });
        
    }
//   private void placeInputDialog1() {
//	   Place place = (Place) selectedItem;
//		
//		JTextField name = new JTextField();
//		JTextField numberOfTokens = new JTextField();
//		name.setText(place.getName());
//		numberOfTokens.setText(Integer.toString(place.getTokenNumbers()));
//		Object[] message = {
//		    "Name:", name,
//		    "NumberOfTokens:", numberOfTokens
//		};
//
//		int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
//		if (option == JOptionPane.OK_OPTION) {
//			if(name.getText()!=null && (!name.getText().equals(place.getName()) || Integer.parseInt(numberOfTokens.getText())!=place.getTokenNumbers())){
//	    		boolean b = existingPlaceNameCheck(name.getText(),place);
//
//	    		if(!b){
//	    			final JPanel panel = new JPanel();
//	    		    JOptionPane.showMessageDialog(panel, "Duplicate name error.", "Error", JOptionPane.ERROR_MESSAGE);
//	    		}else{
//
//	    			petrinet.getPetrinetBuilder().putElementInWorkingArrayList(petrinet.clonePlace(place));
//   			    petrinet.getPetrinetBuilder().putElementInActionArrayList("M");
//   			    
//   			    Place pp = (Place)petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
//   				System.out.println("oldName: "+ pp.getName()+" "+petrinet.getPetrinetBuilder().workingArrayList.size());
//
//			    	place.setName(name.getText());
//			    	if(numberOfTokens.getText()!=null){
//   			    	place.setTokenNumbers(Integer.parseInt(numberOfTokens.getText()));
//   			    }
//   			    repaint(place.getX(), place.getY(), 
//                   		place.getRadius()+1, 
//                   		place.getHeight()+10+2);
//   			    Place pp1 = (Place)petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
//   				System.out.println("oldName next: "+ pp1.getName()+" "+petrinet.getPetrinetBuilder().workingArrayList.size());
//	    		}
//	    
//		    }
//		    
//		} else {
//		    System.out.println("Not Changed.");
//		}
//   }
   public void partialPaint(Rectangle rec){
	   repaint(rec.x, rec.y, 
          		rec.width, 
          		rec.height);
   }
	
	private void placeNameSet() {
		placeCount++;
		String name = "P" + placeCount;
		for(Place place: petrinet.placeVector) {
			if(place.getName().equals(name)) {
				placeNameSet();
			}
		}
		
	}
	private void transitionNameSet() {
		transitionCount++;
		String name = "T" + transitionCount;
		for(Transition transition: petrinet.transitionVector) {
			if(transition.getName().equals(name)) {
				transitionNameSet();
			}
		}
		
	}
    private void drawPlace(Place place, int x, int y){
    	placeNameSet();
    	place.setX(x);
    	place.setY(y);
    	place.setName("P"+placeCount);
        repaint(place.getX(), place.getY(), 
        		place.getRadius()+1, 
        		place.getHeight()+10+2);
       
        MainPanel.placeCoordinator.add(x + "," + y);
//		System.out.println("Current placeCoordinator array list is:"+MainPanel.placeCoordinator);
		
		petrinet.addPlace(place);
		

    }
    private void drawTransition(Transition transition, int x, int y){
    	transitionNameSet();
    	transition.setX(x);
    	transition.setY(y);
    	transition.setName("T" + transitionCount);
        repaint(transition.getX(), transition.getY(), 
        		transition.getWidth()+1, 
        		transition.getHeight()+10+2);
        
        MainPanel.transitionCoordinator.add(x + "," + y);
//		System.out.println("Current transitionCoordinator array list is:"+MainPanel.transitionCoordinator);
	
        petrinet.addTransition(transition);
        
        petrinet.getPetrinetBuilder().printLists();
    }

    public Dimension getPreferredSize() {
    	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

         int height = screenSize.height * 3 / 4;
         int width = screenSize.width * 5/6;

        return new Dimension(width,height);
    }
    public void paintAgain(){
    	repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        for(Place place : petrinet.placeVector){
        	place.draw(g);
        }
        for(TransitionGuiItem transition : petrinet.transitionVector){
            transition.draw(g);
        }
        	
    }  
}