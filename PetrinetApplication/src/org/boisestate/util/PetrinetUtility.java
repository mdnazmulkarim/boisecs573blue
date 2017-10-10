package org.boisestate.util;

import java.util.Vector;

public class PetrinetUtility {
	
	public static enum ITEM {PLACE,TRANSITION,ARC};
	private String namePrefixPlace = "P"; 
	private String namePrefixTransition = "T"; 
	private String namePrefixArc = "A";
	
	private int currentIndexForPlace = 1;
	private int currentIndexForTransition = 1;
	private int currentIndexForArc = 1;
	
	private Vector<String> unusedNamesPlace = null;
	private Vector<String> unusedNamesTransition = null;
	private Vector<String> unusedNamesArc = null;
	
	public PetrinetUtility()
	{
		unusedNamesPlace = new Vector<String>();
		unusedNamesTransition = new Vector<String>();
		unusedNamesArc = new Vector<String>();
	}
	
	public String getAName(ITEM item ) 
	{
		String returnName = "";
		switch(item) 
		{
			case PLACE :
				returnName =  this.namePrefixPlace + currentIndexForPlace;
				currentIndexForPlace ++;
			    break;
			case TRANSITION :
				returnName =  this.namePrefixTransition + currentIndexForTransition;
				currentIndexForTransition ++;
			    break;
			case ARC :
				returnName =  this.namePrefixArc + currentIndexForArc;
				currentIndexForArc ++;
			    break;     
		}
		
		return returnName;
	}
	
		
	public void saveNameunused(ITEM item , String name)
	{
		switch(item) 
		{
			case PLACE :
				unusedNamesPlace.addElement(name);
				break;
			case TRANSITION :
				unusedNamesTransition.addElement(name);
				break;
			case ARC :
				unusedNamesArc.addElement(name);
				break;
		}
	}
	
//	public String getAnUnusedName(ITEM item)
//	{
//		String name  = "";
//		
//		switch(item) 
//		{
//			case PLACE :
//				
//			break;
//		
//		
//	}
	
}
