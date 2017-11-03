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
	
	private void setFireable(boolean fireable) {
		this.fireable = fireable;
	}
	
	public void validateFiringStatus()
	{
		if(isTransitionFireable())
			setFireable(true);
		else
			setFireable(false);
	}
	
	private boolean isTransitionFireable() 
	{
	    boolean canBeFired = false;	
	    if(getNumberOfPlayableIncomingPlaces() >= getNoOfIncomiongPlaces())
	    {
	    	canBeFired = true;
	    }		
		return canBeFired;
		
		
	}
	
	private int getNoOfIncomiongPlaces()
	{
		int incomingPlaces = 0;
		for(Arc arc : arcVector)
		{
			if(arc.getDirectionType().equals("P2T"))
					incomingPlaces++;
		}
		return incomingPlaces;
	}
	
	private int getNumberOfPlayableIncomingPlaces()
	{
		int noOfPlayableIncomingPlaces = 0;
		for(Arc arc : arcVector)
		{
			if(arc.getDirectionType().equals("P2T") && arc.getPlace().getTokenNumbers()>=arc.getWeight())
				noOfPlayableIncomingPlaces++;
		}
		return noOfPlayableIncomingPlaces;
	}
	
	public void  fireTransition()
	{
		if(isFireable())
		{
			for(Arc arc : arcVector)
			{
				if(arc.getDirectionType().equals("P2T"))
				{	
					arc.getPlace().removeToken(arc.getWeight()); }
				else if(arc.getDirectionType().equals("T2P"))
				{	
					arc.getPlace().addToken(arc.getWeight());
				}
				else
				{
					System.out.println("This should not happen.");
				}
			}
		}
		else
		{
			System.out.println("Are you kidding!!....You can't fire me without fuel.");
		}
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
