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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.boisestate.petrinet.Arc;
import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;

public class DrawingPanel extends JPanel {
	

	ArrayList<Point> pointList = new ArrayList<Point>();
		
	public Boolean arcDrawingStarted = false;
	Place tempPlace = null;
	Transition tempTransition = null;
//	ArcDirectionType arcDirection = ArcDirectionType.P_2_T;
	String arcDirection = "";
	
	
	
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
            			erasePartialArc();
            			Place place = new Place();
                		drawPlace(place, e.getX(),e.getY());                		
                	}else if (MainPanel.currentState == MainPanel.currentState.TRANSITION){
                		erasePartialArc();
                		Transition transition = new Transition();
                		drawTransition(transition, e.getX(),e.getY());
                	}else if (MainPanel.currentState == MainPanel.currentState.ARC){
                		
                		if(arcDrawingStarted){
                			pointList.add(new Point(e.getX(), e.getY()));
                			
                			// For circle points change
//                			Point pp = petrinet.getPetrinetBuilder().intersectionPoint(pointList.get(1), pointList.get(0), tempPlace.getRadius());
//                			pointList.get(0).setLocation(pp);
                			
                			repaint();
                    		Object obj = petrinet.getPetrinetBuilder().getElementUnderThisPoint(e.getX(),e.getY());
                    		
                    		if(arcDirection == "P_2_T" && obj!=null) {
                    			if(obj instanceof Transition) {
                    				arcDrawingStarted = false;
                    				// Drawing End. create new arc object and initialize temporary variables
                    				tempTransition = (Transition)obj;
                    				petrinet.getPetrinetBuilder().createArc((ArrayList<Point>)pointList.clone(), tempPlace, tempTransition, arcDirection);
                    				pointList.clear();
                    				tempPlace = null;
                    				tempTransition = null;
                    				arcDirection = "";
                    				repaint();
                    			}
                    		}
                    		else if(arcDirection == "T_2_P" && obj!=null) {
                    			if(obj instanceof Place) {
                    				arcDrawingStarted = false;
                    				// Drawing End. create new arc object and initialize temporary variables
                    				tempPlace = (Place) obj;
                    				petrinet.getPetrinetBuilder().createArc((ArrayList<Point>)pointList.clone(), tempPlace, tempTransition, arcDirection);
                    				pointList.clear();
                    				tempPlace = null;
                    				tempTransition = null;
                    				arcDirection = "";
                    				repaint();
                    			}
                    		}
                		}else {
                			Object obj = petrinet.getPetrinetBuilder().getElementUnderThisPoint(e.getX(),e.getY());
                    		if(obj != null) {
                    			if(obj instanceof Place) {
                    				tempPlace = (Place)obj;
                    				arcDrawingStarted = true;
                    				arcDirection = "P_2_T";
                    				pointList.add(new Point(e.getX(), e.getY()));
                            		repaint();
                    			}else {
                    				tempTransition = (Transition)obj;
                    				arcDrawingStarted = true;
                    				arcDirection = "T_2_P";
                    				pointList.add(new Point(e.getX(), e.getY()));
                            		repaint();
                    			}
                    			
                        		
                    		}
                		}
                		
                		
                	}
                	else if (MainPanel.currentState == MainPanel.currentState.COPY){
                		erasePartialArc();
                		Object obj = petrinet.selectedPlace(e.getX(), e.getY());
                		selectedItem = obj;
                	}else if (MainPanel.currentState == MainPanel.currentState.PASTE){
                		erasePartialArc();
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
                		erasePartialArc();
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
                			}else {
                				System.out.println("delete called");
                				
                    			petrinet.deleteArc((Arc) selectedItem);
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
            		erasePartialArc();
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
	
	public void erasePartialArc() {
		if (arcDrawingStarted) {
			pointList.clear();
			arcDrawingStarted = false;
			repaint();
		}
	}
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
        
        for(int i = 0; i< pointList.size()-1; i++){
        	Point p = pointList.get(i);
        	Point p1 = pointList.get(i+1);
            g.drawLine(p.x, p.y, p1.x, p1.y);
        }
       
        
        
        for(Arc arc: petrinet.arcVector) {
        	arc.draw(g);
        }
        
        	
    }  
}