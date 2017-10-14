package org.boisestate.graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DrawingPanel extends JPanel {
	

	PlaceGuiItem place = new PlaceGuiItem();
	
	TransitionGuiItem transiiton = new TransitionGuiItem();

	
    public DrawingPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
            	if(MainPanel.currentState == MainPanel.currentState.PLACE) {
            		drawPlace(e.getX(),e.getY());
            	}else if (MainPanel.currentState == MainPanel.currentState.TRANSITION){
            		drawTransition(e.getX(),e.getY());
            	}
                
            }
        });

        addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
            	if(MainPanel.currentState == MainPanel.currentState.PLACE) {
            		drawPlace(e.getX(),e.getY());
            	}else if (MainPanel.currentState == MainPanel.currentState.TRANSITION){
            		drawTransition(e.getX(),e.getY());
            	}
            }
        });

    }
   
    private void drawPlace(int x, int y){
    	place.setX(x);
    	place.setY(y);
        repaint(place.getX(), place.getY(), 
        		place.getWidth()+1, 
        		place.getHeight()+100);
       
        MainPanel.placeCoordinator.add(x + "," + y);
		System.out.println("Current placeCoordinator array list is:"+MainPanel.placeCoordinator);

//        // Current square state, stored as final variables 
//        // to avoid repeat invocations of the same methods.
//        final int CURR_X = place.getX();
//        final int CURR_Y = place.getY();
//        final int CURR_W = place.getWidth();
//        final int CURR_H = place.getHeight();
//        final int OFFSET = 1;
//
//        if ((CURR_X!=x) || (CURR_Y!=y)) {
//
//            // The square is moving, repaint background 
//            // over the old square location. 
////            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);
//
//            // Update coordinates.
//        	place.setX(x);
//        	place.setY(y);
//
//            repaint(place.getX(), place.getY(), 
//            		place.getWidth()+OFFSET, 
//            		place.getHeight()+OFFSET);
//        }
    }
    private void drawTransition(int x, int y){
    	transiiton.setX(x);
    	transiiton.setY(y);

        // Repaint the square at the new location.
        repaint(transiiton.getX(), transiiton.getY(), 
        		transiiton.getWidth()+1, 
        		transiiton.getHeight()+1);
        
        MainPanel.transitionCoordinator.add(x + "," + y);
		System.out.println("Current transitionCoordinator array list is:"+MainPanel.transitionCoordinator);
		
//        // Current square state, stored as final variables 
//        // to avoid repeat invocations of the same methods.
//        final int CURR_X = transiiton.getX();
//        final int CURR_Y = transiiton.getY();
//        final int CURR_W = transiiton.getWidth();
//        final int CURR_H = transiiton.getHeight();
//        final int OFFSET = 1;
//
//        if ((CURR_X!=x) || (CURR_Y!=y)) {
//
//            // The square is moving, repaint background 
//            // over the old square location. 
////            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);
//
//            // Update coordinates.
//        	transiiton.setX(x);
//        	transiiton.setY(y);
//
//            // Repaint the square at the new location.
//            repaint(transiiton.getX(), transiiton.getY(), 
//            		transiiton.getWidth()+OFFSET, 
//            		transiiton.getHeight()+OFFSET);
//        }
    }

    public Dimension getPreferredSize() {
    	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

         int height = screenSize.height * 3 / 4;
         int width = screenSize.width * 5/6;

        return new Dimension(width,height);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        place.draw(g);
        transiiton.draw(g);
    }  
}