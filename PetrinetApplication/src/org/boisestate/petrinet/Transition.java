package org.boisestate.petrinet;

import java.util.Vector;

import org.boisestate.graphics.TransitionGuiItem;

public class Transition extends TransitionGuiItem implements Cloneable{

	private Vector<Arc> arcVector;
	private boolean fireable;
	
	public Transition() 
	{
		super();
		this.arcVector = new Vector<Arc>();
		fireable=false;
	}
	
	public boolean isFireable() {
		return fireable;
	}
	
	public void setFireable(boolean fireable) {
		this.fireable = fireable;
	}
	
	public boolean isTransitionFireable() 
	{
		return false;
	}
	
	public void  fireTransition()
	{
		
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
