package org.boisestate.core;

import java.util.Random;

import org.boisestate.util.PetrinetUtility;

public class PetrinetBuilder {
	
	//Random rand;
	public PetrinetUtility petrinetUtility;
	
	public PetrinetBuilder()
	{
		//this.rand = new Random();
		petrinetUtility = new PetrinetUtility();
	}
	
	public String generatePetrinetName()
	{
		return petrinetUtility.generateAPetrineName("");
	}
	
	public String generatePetrinetID()
	{
		return petrinetUtility.generateAPetrinetID();
	}

}
