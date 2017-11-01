package org.boisestate.core;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;
import org.boisestate.util.PetrinetUtility;
import org.boisestate.graphics.DrawingPanel;


public class PetrinetBuilder {
	public ArrayList<Object> workingArrayList;
	public ArrayList<Object> redoArrayList;
	public ArrayList<Object> actionArrayList;
	public ArrayList<Object> redoActionArrayList;
	//Random rand;
	public PetrinetUtility petrinetUtility;
	public Petrinet petrinet;
	public DrawingPanel drawingPanel;
	public PetrinetBuilder(Petrinet petrinet)
	{
		this.petrinet = petrinet;
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
	private Boolean existingPlaceNameCheck(String placeName,Place pl) {
		for(Place place: petrinet.placeVector) {
			if(place != pl && placeName.equals(place.getName())){
				return false;
			}

		}
		return true;
	}
	private Boolean existingTransitionNameCheck(String transName,Transition pl) {
		for(Transition trans: petrinet.transitionVector) {
			if(trans != pl && transName.equals(trans.getName())){
				return false;
			}

		}
		return true;
	}
	public void setDrawingPanel(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	public Place clonePlace(Place place) {
		Place p = new Place();
		p.setName(place.getName());
		p.setTokenNumbers(place.getTokenNumbers());
		p.setX(place.getX());
		p.setY(place.getY());
		p.setRadius(30);
		p.setHight(40);
		
		
		
		if(p==place){
			System.out.println("Clone failed.....");
		}
		return p;
	}
	public Transition cloneTransition(Transition transition) {
		Transition p = new Transition();
		p.setName(transition.getName());
		p.setX(transition.getX());
		p.setY(transition.getY());
	
		
		
		
		if(p==transition){
			System.out.println("Clone failed.....");
		}
		return p;
	}
	public void transitionInputDialog(Object selectedItem) {
		Transition transition = (Transition) selectedItem;
		
		JTextField name = new JTextField();
		name.setText(transition.getName());
		Object[] message = {
		    "Name:", name,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(name.getText()!=null && (!name.getText().equals(transition.getName()))){
	    		boolean b = existingTransitionNameCheck(name.getText(),transition);

	    		if(!b){
	    			final JPanel panel = new JPanel();
	    		    JOptionPane.showMessageDialog(panel, "Duplicate name error.", "Error", JOptionPane.ERROR_MESSAGE);
	    		}else{
			    	transition.setName(name.getText());
   			    }
			 
			    drawingPanel.partialPaint(new Rectangle(transition.getX(), transition.getY(), 
                   		transition.getWidth()+1, 
                   		transition.getHeight()+10+2));
			}
	    
		} else {
		    System.out.println("Not Changed.");
		}
		
	
	}
	public void placeInputDialog(Object selectedItem) {
		   Place place = (Place) selectedItem;
			
			JTextField name = new JTextField();
			JTextField numberOfTokens = new JTextField();
			name.setText(place.getName());
			numberOfTokens.setText(Integer.toString(place.getTokenNumbers()));
			Object[] message = {
			    "Name:", name,
			    "NumberOfTokens:", numberOfTokens
			};

			int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if(name.getText()!=null && (!name.getText().equals(place.getName()) || Integer.parseInt(numberOfTokens.getText())!=place.getTokenNumbers())){
		    		boolean b = existingPlaceNameCheck(name.getText(),place);

		    		if(!b){
		    			final JPanel panel = new JPanel();
		    		    JOptionPane.showMessageDialog(panel, "Duplicate name error.", "Error", JOptionPane.ERROR_MESSAGE);
		    		}else{

		    		this.putElementInWorkingArrayList(place.clone());
	   			    this.putElementInActionArrayList("M");
	   			    	
	   			    

	   			    
	   			    Place pp = (Place)petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
	   				System.out.println("oldName: "+ pp.getName()+" "+petrinet.getPetrinetBuilder().workingArrayList.size());

				    	place.setName(name.getText());
				    	if(numberOfTokens.getText()!=null){
				    		place.setTokenNumbers(Integer.parseInt(numberOfTokens.getText()));
				    	}
				 
				    drawingPanel.partialPaint(new Rectangle(place.getX(), place.getY(), 
	                   		place.getRadius()+1, 
	                   		place.getHeight()+10+2));
				    	
//				    	drawingPanel.paintAgain();
	   			   
	   			    Place pp1 = (Place)petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
	   				System.out.println("oldName next: "+ pp1.getName()+" "+petrinet.getPetrinetBuilder().workingArrayList.size());
		    		}
		    
			    }
			    
			} else {
			    System.out.println("Not Changed.");
			}
	   }
	
	public void printLists()
	{
		System.out.println("##Printing Places:");
    	for(Place place: petrinet.placeVector) {
    		System.out.println(place.getName()+"|"+place.getX()+"|"+place.getY());
    	}
    	System.out.println("##Printing Working List:");
    	Place placeObj;
    	Transition transitionObj;
    	for(Object objW: petrinet.getPetrinetBuilder().workingArrayList) {
    		if( objW instanceof Place)
    		{
    			placeObj = (Place)objW;
    			System.out.println(placeObj.getName()+"|"+placeObj.getX()+"|"+placeObj.getY());
    		}
    		else if( objW instanceof Transition) 
    		{
    			transitionObj = (Transition)objW;
    			System.out.println(transitionObj.getName()+"|"+transitionObj.getX()+"|"+transitionObj.getY());
    		}
    		else
    		{
    			System.out.println("Undetected object");
    		}
    		
    	}
	}

}
