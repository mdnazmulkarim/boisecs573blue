package org.boisestate.util;

import java.util.Random;
import java.util.Vector;

public class PetrinetUtility {
	
	public static enum ITEM {PLACE,TRANSITION,ARC, PETRINET};
	
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
			case PETRINET :
				returnName = returnName.concat("PN");
				returnName = returnName.concat(System.getProperty("user.name"));
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
	
	 public static int RND_NUM_BASE = 999999;
	 public String generateRandomSequence(String tag) {
	        String rndSequence=tag;
	        Random rnd = new Random(System.currentTimeMillis());

	        int nextint = rnd.nextInt(RND_NUM_BASE++);

	        if (nextint < 1000000) {
	            nextint += 1000000;
	        }

	        rndSequence = rndSequence + nextint;

	        return rndSequence;
	    }
	
	public String generateAPetrinetID()
	{
		return generateRandomSequence("PETRI");
	}
	
	public String generateAPlaceID()
	{
		return generateRandomSequence("PLACE");
	}
	public String generateATransitionID()
	{
		return generateRandomSequence("TRANS");
	}
	
	public String generateAPetrineName(String name)
	{
		if(name==null || name.isEmpty())
		{
			return "";
		}
		else
		{
			return generateRandomSequence("PETRI");
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
