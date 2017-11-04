package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
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
//	            	g.fillOval(p1.x-5, p1.y-5, 10, 10);
	            	drawArrow(g,p.x,p.y,p1.x,p1.y);
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
	
	
	private final int ARR_SIZE = 10;

	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {

		Graphics2D g = (Graphics2D)g1.create();
		
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        
        
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}
