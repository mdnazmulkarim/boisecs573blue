package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class ArcGuiItem implements GuiItemInterface{

	private Color fillColor;
	private Color borderColor;
	private int xPos = -50;
    private int yPos = -50;
    private int radius = 30;
    private int height = 40;
    private int nameHeight = 10;
    private String name = "";
    //private int weight = 1;
    
    

    public ArrayList<Point> pointsVector;
    
    public ArcGuiItem()
    {

    	this.fillColor = Color.BLACK;
    	this.borderColor = Color.BLACK;
    	this.pointsVector = new ArrayList<Point>();
    	
    	
    } 
    
   
    
    public void setPointVector(ArrayList<Point> object) {
    	
    	this.pointsVector = object;
    }
    public ArrayList getPointVector() {
    	return this.pointsVector;
    }
    
    
    @Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBorderColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		//Point p1, p2; 
	     /* Draw old lines.Every 2 points construct a line.*/
		
		
		 for(int i = 0; i< this.pointsVector.size()-1; i++){
	        	Point p = this.pointsVector.get(i);
	        	Point p1 = this.pointsVector.get(i+1);
	        	g.setColor(fillColor);
	            g.drawLine(p.x, p.y, p1.x, p1.y);
	            if(i==this.pointsVector.size()-2) {
	            	g.fillOval(p1.x-5, p1.y-5, 10, 10);
	            }
	            
//	            int middleIndex = 0+this.pointsVector.size()/2; 
//	            if(this.pointsVector.size()>1)
//	            {
//		            Point pi = this.pointsVector.get(middleIndex);
//		            Point pj = this.pointsVector.get(middleIndex+1);
//		            
//		            int x = (pi.x+ pj.x)/2;
//		            int y = (pi.y+ pj.y)/2;
//		            g.drawString(""+weight, x, y);
//	            }
	      }
	}
	
}
