package org.boisestate.petrinet;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import org.boisestate.graphics.ArcGuiItem;
import org.boisestate.graphics.DrawingPanel;
import org.boisestate.util.PetrinetUtility;

public class Arc extends ArcGuiItem implements Cloneable{
	public enum ArcDirectionType  {
	      P_2_T, T_2_P
   }
	public static ArcDirectionType arcDirectionType;
	private int weight;
	private Place connectedPlace;
	private Transition connectedTransition;
	private ArcDirectionType directionType;
	Point sPoint = new Point(-1, -1); 
    Point ePoint = new Point(-1, -1); 
   

	   
	public Arc() {
	}
	public Arc(Place place, Transition transition, ArcDirectionType type )
	{
		this.connectedPlace = place;
		this.connectedTransition = transition;
		this.directionType = type;
		this.weight = 1;
	}
	
	public Arc(Place place, Transition transition, int w, ArcDirectionType type ){
		this.connectedPlace = place;
		this.connectedTransition = transition;
		this.directionType = type;
		this.weight = w;
	}
	
	public void setPlace(Place place){
		this.connectedPlace = place;
	}
	
	public Place getPlace(){
		return this.connectedPlace;
	}
	
	public void setTransition(Transition transition){
		this.connectedTransition = transition;
	}
	
	public Transition getTransition(){
	     return this.connectedTransition;
	}
	public Object clone() {
		Object o = null;
		try {
			o=super.clone();

		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
		}
		return o;
	}

	

}
