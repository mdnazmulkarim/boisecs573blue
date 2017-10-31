package org.boisestate.petrinet;

import java.util.Vector;

import org.boisestate.core.PetrinetBuilder;

public class Petrinet {
	
	private String id;
	private String name;
	private String title;
	
	private Vector<Place> placeVector;
	private Vector<Transition> transitionVector;
	private Vector<Arc> arcVector;

	private PetrinetBuilder petrinetBuilder;
	
	public Petrinet(PetrinetBuilder pb, String title) 
	{
		this.title = title;
		this.petrinetBuilder = pb;
		initIDNameBuilder();
	
	}
	
	public Petrinet(String title) 
	{
		this.title = title;
		initIDNameBuilder();	
	}
	
	private void initIDNameBuilder()
	{
		if(this.petrinetBuilder == null)
		{	
			this.petrinetBuilder = new PetrinetBuilder();
		}
		this.id = petrinetBuilder.generatePetrinetID();
		this.name = petrinetBuilder.generatePetrinetName();
	}
	
	private void initVectors()
	{
		placeVector = new Vector<Place>();
		placeVector.add(new Place(""));
		transitionVector = new Vector<Transition>();
		arcVector = new Vector<Arc>();
	}
	
	public void setPetrinetBuilder(PetrinetBuilder pb)
	{
		this.petrinetBuilder = pb;
	}

	public PetrinetBuilder getPetrinetBuilder() {
		return petrinetBuilder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
