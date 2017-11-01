package org.boisestate.petrinet;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import org.boisestate.core.PetrinetBuilder;
import org.boisestate.graphics.PlaceGuiItem;

public class Petrinet {
	
	private String id;
	private String name;
	private String title;
	
	public ArrayList<Place> placeVector;
	private ArrayList<Transition> transitionVector;
	private ArrayList<Arc> arcVector;

	private PetrinetBuilder petrinetBuilder;
	
	public Petrinet(PetrinetBuilder pb, String title) 
	{
		this.title = title;
		this.petrinetBuilder = pb;
		initIDNameBuilder();
		initVectors();
	}
	
	public Petrinet(String title) 
	{
		this.title = title;
		initIDNameBuilder();
		initVectors();
	}
	
	private void initIDNameBuilder()
	{
		if(this.petrinetBuilder == null)
		{	
			this.petrinetBuilder = new PetrinetBuilder(this);
		}
		this.id = petrinetBuilder.generatePetrinetID();
		this.name = petrinetBuilder.generatePetrinetName();
	}
	
	private void initVectors()
	{
		placeVector = new ArrayList<Place>();
		placeVector.add(new Place());
		transitionVector = new ArrayList<Transition>();
		arcVector = new ArrayList<Arc>();
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
	
	public void addPlace(Place place) {
		this.placeVector.add(place);
		System.out.println("add place called");
		petrinetBuilder.putElementInWorkingArrayList(place); //For Redo Undo
		petrinetBuilder.putElementInActionArrayList("A");
	}
	public void deletePlace(Place place) {
		petrinetBuilder.putElementInWorkingArrayList(place); //For Redo Undo
		petrinetBuilder.putElementInActionArrayList("D");
		this.placeVector.remove(place);
	}
	public void removeLastPlace() {		
		petrinetBuilder.workingArrayList.add(placeVector.size()-1); //For Redo Undo
		this.placeVector.remove(placeVector.size()-1);
	}
	public void removeAllPlace() {
		this.placeVector.clear();
	}
//	public int tokensOfPlace(int x, int y){
//		for(int i=0; i<this.placeVector.size(); i++) {
//			Place place = (Place)this.placeVector.get(i);
//			if(place.getBounds().contains(new Point(x,y))) {
//				return i;
//			}
//		}
//		
//		return -1;
//	}
	public Object selectedPlace(int x, int y){
		
		for(int i=0; i<this.placeVector.size(); i++) {
			Place place = (Place)this.placeVector.get(i);
			if(place.getBounds().contains(new Point(x,y))) {
				return place;
			}
		}
		
		return null;
	}
	
	
}
