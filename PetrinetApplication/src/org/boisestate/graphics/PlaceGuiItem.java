package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class PlaceGuiItem implements GuiItemInterface{
	private Color fillColor;
	private Color borderColor;
	private int xPos = -50;
    private int yPos = -50;
    private int width = 30;
    private int height = 40;
    private int nameHeight = 10;
    private String name = "";
    private int noOfTokens = 0;
    
    public PlaceGuiItem(String name, int tokens)
    {
    	this.name = name;
    	this.noOfTokens = tokens;
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
	
	public void setWidth(int w){
        this.width =w;
    } 
	
	public int getWidth(){
        return width;
    } 

	public void setHight(int h){
        this.height =h;
    }
	
	public int getHeight(){
        return height;
    }
	    

   
    public void draw(Graphics g){
    	System.out.println("call to place draw");
        g.setColor(Color.white);
        g.fillOval(xPos, yPos, width, height-nameHeight);
        g.setColor(Color.BLACK);
        g.drawOval(xPos,yPos,width,height-nameHeight); 
        g.drawString(name, xPos+10+2, yPos+height);
    }

	
}
