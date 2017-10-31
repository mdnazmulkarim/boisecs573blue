package org.boisestate.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;


public class TransitionGuiItem implements GuiItemInterface
{
	
	private Color fillColor;
	private Color borderColor;
	private int xPos = -50;
    private int yPos = -50;
    private int width = 20;
    private int height = 45;
    private int nameHeight = 15;
    private String name = "";
    
    public TransitionGuiItem(String name)
    {
    	this.name = name;
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
	    
	@Override
    public void draw(Graphics g){
        g.setColor(this.fillColor);     
        g.fillRect(xPos, yPos, width, height-nameHeight);
        g.setColor(this.borderColor);
        g.drawRect(xPos,yPos,width,height-nameHeight);  
        g.drawString(name, xPos+2, yPos+height);
       
    }

		
}
