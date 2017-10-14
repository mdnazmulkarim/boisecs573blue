package org.boisestate.graphics;

import java.awt.Color;
import java.awt.Graphics;

public interface GuiItemInterface {
	
	
	public void setX(int x);
	
	public int getX();
	
	public void setY(int y);
	
	public int getY();
	
	public void setName(String name);
	
	public String getName();
	
	public void setBorderColor(Color color);
	
	public void setFillColor(Color color);
	
	public void draw(Graphics g);
	

}
