package org.boisestate.petrinet;

import org.boisestate.graphics.PlaceGuiItem;

/**
 * Represents a place in a petrinet.
 *
 * @author Nazmul
 */
public class Place {
	
	private String name;	
	private int noOfTokens;	
	private boolean isBounded;
	private PlaceGuiItem placeGuiItem;
	
	/**
	 * Constructor: creates a place with a name and .
	 * @param name is the name of this place
	 * noOfTokens is intialized to zero(0) a default value
	 * isBounded is false by default
	 */
	Place(String name){
		this.name = name;
		this.noOfTokens = 0;
		this.isBounded = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setTokenNumbers(int num) {
		this.noOfTokens = num;
	}
	
	public int getTokenNumbers() {
		return this.noOfTokens;
	}
	
	public void addToken(int num) {
		this.noOfTokens = this.noOfTokens + num;
	}
	
	public void removeToken(int num) {
		this.noOfTokens = this.noOfTokens - num;
	}
	
	public void setBounded(boolean b) {
		this.isBounded = b;
	}
	
	public boolean getBounded() {
		return this.isBounded;
	}
	
	
	
	

}
