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
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DrawingPanel extends JPanel {
	

	PlaceGuiItem place = new PlaceGuiItem("P1",1);	
	TransitionGuiItem transiiton = new TransitionGuiItem("T1");

	
    public DrawingPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
            	if(e.getButton() == MouseEvent.BUTTON1)
            		System.out.println("button 1");
            	if(e.getButton() == MouseEvent.NOBUTTON)
            		System.out.println("button 0");
            	if(e.getButton() == MouseEvent.BUTTON2)
            		System.out.println("button 2");
            	if(e.getButton() == MouseEvent.BUTTON3)
            		System.out.println("button 3");
            	if(MainPanel.currentState == MainPanel.currentState.PLACE) {
            		drawPlace(e.getX(),e.getY());
            	}else if (MainPanel.currentState == MainPanel.currentState.TRANSITION){
            		drawTransition(e.getX(),e.getY());
            	}
                
            }
        });

//        addMouseMotionListener(new MouseAdapter(){
//            public void mouseDragged(MouseEvent e){
//            	if(MainPanel.currentState == MainPanel.currentState.PLACE) {
//            		drawPlace(e.getX(),e.getY());
//            	}else if (MainPanel.currentState == MainPanel.currentState.TRANSITION){
//            		drawTransition(e.getX(),e.getY());
//            	}
//            }
//        });

    }
   
    private void drawPlace(int x, int y){
    	place.setX(x);
    	place.setY(y);
        repaint(place.getX(), place.getY(), 
        		place.getWidth()+1, 
        		place.getHeight()+10+2);
       
        MainPanel.placeCoordinator.add(x + "," + y);
		System.out.println("Current placeCoordinator array list is:"+MainPanel.placeCoordinator);


    }
    private void drawTransition(int x, int y){
    	transiiton.setX(x);
    	transiiton.setY(y);

        // Repaint the square at the new location.
        repaint(transiiton.getX(), transiiton.getY(), 
        		transiiton.getWidth()+1, 
        		transiiton.getHeight()+10+2);
        
        MainPanel.transitionCoordinator.add(x + "," + y);
		System.out.println("Current transitionCoordinator array list is:"+MainPanel.transitionCoordinator);
	
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