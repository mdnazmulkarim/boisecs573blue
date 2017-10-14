package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;


public class PlaceGuiItem {
	private int xPos = -50;
    private int yPos = -50;
    private int width = 30;
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

   
    public void paintPlace(Graphics g){
        g.setColor(Color.white);
        g.fillOval(xPos, yPos, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(xPos,yPos,width,height); 
        g.drawString("P1", xPos+1, yPos+100);

    }
}
