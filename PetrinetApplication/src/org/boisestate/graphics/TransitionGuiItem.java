package org.boisestate.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class TransitionGuiItem {
	private int xPos = -50;
    private int yPos = -50;
    private int width = 10;
    private int height = 30;
    
    public void setX(int xPos){ 
        this.xPos = xPos;
    }

    public int getX(){
        return xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }

    public void paintTransition(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(xPos, yPos, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(xPos,yPos,width,height);  
    }
}
