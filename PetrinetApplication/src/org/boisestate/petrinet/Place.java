package org.boisestate.petrinet;

import org.boisestate.graphics.PlaceGuiItem;

/**
 * Represents a place in a petrinet.
 *
 * @author Nazmul
 */
public class Place extends PlaceGuiItem implements Cloneable{
	
	/**
	 * Constructor: creates a place with a name and .
	 * @param name is the name of this place
	 * noOfTokens is intialized to zero(0) a default value
	 */
	public Place() {

		super();
	}

	public void addToken(int num) {
		if(super.getTokenNumbers()!=999)
		{ 
			super.setTokenNumbers(super.getTokenNumbers() + num);
		}
	}

	public void removeToken(int num) {
		if(super.getTokenNumbers()!=999)
		{     
			super.setTokenNumbers(super.getTokenNumbers() - num);
		}
	}

	
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();

		} catch (CloneNotSupportedException e) {
			System.out.println("Clone Not Supported" + e.toString());
		}
		return o;
	}

}
