package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class PlaceGuiItem {
	protected int x,y;
	
	protected int outerRadius = 50;
	protected int innerRadius = 48;
	
	PlaceGuiItem(){
		
	}
	
	public void setCoordinate(double p,double q) {
		x = (int)p;
		y = (int)q;
		System.out.println(x + " " + y);
	}
	public void paintComponent (Graphics canvas) {
		
		canvas.setColor(Color.BLACK);
		canvas.fillOval(x-outerRadius,y-outerRadius,outerRadius,outerRadius);
		
		canvas.setColor(Color.WHITE);
		canvas.fillOval(x-innerRadius,y-innerRadius,innerRadius,innerRadius);
	}

}
