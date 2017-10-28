package org.boisestate.petrinet;

public class Arc {
	
	private int weight;
	private Place connectedPlace;
	private Transition connectedTransition;
	private ArcDirectionType directionType;
	
	public enum ArcDirectionType  {
	      P_2_T, T_2_P
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
	
	

}
