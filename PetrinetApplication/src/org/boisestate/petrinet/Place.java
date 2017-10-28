package org.boisestate.petrinet;

import org.boisestate.graphics.PlaceGuiItem;

/**
 * Represents a place in a petrinet.
 *
 * @author Nazmul
 */
public class Place extends PlaceGuiItem{
	
	private boolean isBounded;
	private String ID;
	/**
	 * Constructor: creates a place with a name and .
	 * @param name is the name of this place
	 * noOfTokens is intialized to zero(0) a default value
	 * isBounded is false by default
	 */
	Place(String name){
		
		super(name,0);
		this.isBounded = false;
	}
	
	public void addToken(int num) {
		super.setTokenNumbers(super.getTokenNumbers()+ num);
	}
	
	public void removeToken(int num) {
		super.setTokenNumbers(super.getTokenNumbers()-num);
	}
	
	public void setBounded(boolean b) {
		this.isBounded = b;
	}
	
	public boolean getBounded() {
		return this.isBounded;
	}
	
	
	
	

}
