package org.boisestate.core;

import java.util.ArrayList;
import java.util.Random;

import org.boisestate.petrinet.Transition;
import org.boisestate.util.PetrinetUtility;

public class PetrinetBuilder {
	public ArrayList<Object> workingArrayList;
	public ArrayList<Object> redoArrayList;
	public ArrayList<Object> actionArrayList;
	public ArrayList<Object> redoActionArrayList;
	//Random rand;
	public PetrinetUtility petrinetUtility;
	
	public PetrinetBuilder()
	{
		//this.rand = new Random();
		petrinetUtility = new PetrinetUtility();
		workingArrayList = new ArrayList<Object>();
		redoArrayList = new ArrayList<Object>();
		actionArrayList = new ArrayList<Object>();
		redoActionArrayList = new ArrayList<Object>();
	}
	public Object getElementFromWorkingArrayList() {
		if(workingArrayList.size()>0)
			return workingArrayList.get(workingArrayList.size()-1);
		else return null;
	}
	public void putElementInWorkingArrayList(Object obj) {
		workingArrayList.add(obj);
	}
	public void removeElementFromWorkingArrayList() {
		workingArrayList.remove(workingArrayList.size()-1);
	}
	public Object getElementFromActionArrayList() {
		if(actionArrayList.size()>0)
			return actionArrayList.get(actionArrayList.size()-1);
		else return null;
	}
	public void putElementInActionArrayList(Object obj) {
		actionArrayList.add(obj);
	}
	public void removeElementFromActionArrayList() {
		actionArrayList.remove(actionArrayList.size()-1);
	}
	public Object getElementFromRedoActionArrayList() {
		if(redoActionArrayList.size()>0)
			return redoActionArrayList.get(redoActionArrayList.size()-1);
		else return null;
	}
	public void putElementInRedoActionArrayList(Object obj) {
		redoActionArrayList.add(obj);
	}
	public void removeElementFromRedoActionArrayList() {
		redoActionArrayList.remove(redoActionArrayList.size()-1);
	}
	public Object getElementFromRedoArrayList() {
		if(redoArrayList.size()>0)
			return redoArrayList.get(redoArrayList.size()-1);
		else return null;
	}
	public void putElementInRedoArrayList(Object obj) {
		redoArrayList.add(obj);
	}
	public void removeElementFromRedoArrayList() {
		redoArrayList.remove(redoArrayList.size()-1);
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
