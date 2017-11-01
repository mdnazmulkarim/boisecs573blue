package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class PlaceGuiItem implements GuiItemInterface{
	private Color fillColor;
	private Color borderColor;
	private int xPos = -50;
    private int yPos = -50;
    private int radius = 30;
    private int height = 40;
    private int nameHeight = 10;
    private String name = "";
    private int noOfTokens = 0;
    
    public PlaceGuiItem()
    {

    	this.fillColor = Color.GRAY;
    	this.borderColor = Color.BLACK;
    } 
    
    @Override
    public void setX(int xPos){ 
        this.xPos = xPos;
    }

    @Override
    public int getX(){
        return xPos;
    }

    @Override
    public void setY(int yPos){
        this.yPos = yPos;
    }

    @Override
    public int getY(){
        return yPos;
    }

   
    
    @Override
	public void setName(String name) {
		this.name = name;
		
	}
	@Override
	public String getName() {
		return this.name;
	}
	
	public void setTokenNumbers(int num) {
		this.noOfTokens = num;
	}
	
	public int getTokenNumbers() {
		return this.noOfTokens;
	}
	
	@Override
	public void setBorderColor(Color color) {
		this.borderColor = color;
		
	}

	@Override
	public void setFillColor(Color color) {
		this.fillColor = color;
		
	}
	
	public void setRadius(int w){
        this.radius =w;
    } 
	
	public int getRadius(){
        return radius;
    } 

	public void setHight(int h){
        this.height = h;
    }
	
	public int getHeight(){
        return height;
    }
	    

   
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(xPos, yPos, radius, height-nameHeight);
        g.setColor(Color.BLACK);
        g.drawOval(xPos,yPos,radius,height-nameHeight); 
        
//        if(noOfTokens > 0) {
//        	if (noOfTokens == 1) {
//        		g.fillOval(xPos+(radius/2), yPos+(radius/2), 4, 4);
//        	}
//        	else if (noOfTokens == 2) {
//        		g.fillOval(xPos+(radius/2), yPos+(radius/2), 4, 4);
//        		g.fillOval(xPos+(radius/4), yPos+(radius/2), 4, 4);
//        	}
//        	else if (noOfTokens == 3) {
//        		g.fillOval(xPos+(radius/2), yPos+(radius/2), 4, 4);
//        		g.fillOval(xPos+(radius/4), yPos+(radius/2), 4, 4);
//        		g.fillOval(xPos+(radius/4), yPos+(radius/4), 4, 4);
//        	}
//        	else if (noOfTokens == 4) {
//        		g.fillOval(xPos+(radius/2), yPos+(radius/2), 4, 4);
//        		g.fillOval(xPos+(radius/4), yPos+(radius/2), 4, 4);
//        		g.fillOval(xPos+(radius/4), yPos+(radius/4), 4, 4);
//        		g.fillOval(xPos+(radius/2), yPos+(radius/5), 4, 4);
//        	}else {
//        	}
//        }
        if (noOfTokens > 0) {
    		g.drawString("{"+noOfTokens+"}", xPos+10+2, yPos+height+11);

        }

        g.drawString(name, xPos+10+2, yPos+height);
        
    }

	public Rectangle getBounds() {
		return new Rectangle(xPos,yPos,radius,height);
	}
}
