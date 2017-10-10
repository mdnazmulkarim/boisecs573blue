package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class TransitionGuiItem {
	private int xPos = 0;
    private int yPos = 0;
    private int width = 20;
    private int height = 40;
    
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
