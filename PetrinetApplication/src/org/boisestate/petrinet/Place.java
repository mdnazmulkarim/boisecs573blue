package org.boisestate.petrinet;

import org.boisestate.graphics.PlaceGuiItem;

/**
 * Represents a place in a petrinet.
 *
 * @author Nazmul
 */
public class Place extends PlaceGuiItem implements Cloneable{
	
	private boolean isBounded;
	/**
	 * Constructor: creates a place with a name and .
	 * @param name is the name of this place
	 * noOfTokens is intialized to zero(0) a default value
	 * isBounded is false by default
	 */
	public Place(){
		
		super();
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
