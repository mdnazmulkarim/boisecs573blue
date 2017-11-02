/*package org.boisestate.graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 


import java.awt.Color;
import java.awt.Graphics;

class RedSquare{

    private int xPos = 50;
    private int yPos = 50;
    private int width = 20;
    private int height = 20;
    
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
//        g.fillRect(xPos,yPos,width,height);
        g.fillOval(xPos, yPos, width, height);
        g.setColor(Color.BLACK);
//        g.drawRect(xPos,yPos,width,height);  
        g.drawOval(xPos,yPos,width,height);  
    }
    
}
*/