package org.boisestate.petrinet;

import java.awt.Color;
import java.util.ArrayList;

import org.boisestate.graphics.TransitionGuiItem;

public class Transition extends TransitionGuiItem implements Cloneable{

	public ArrayList<Arc> arcVector;
	private boolean fireable;
	
	public Transition() {
		super();
		this.arcVector = new ArrayList<Arc>();
		fireable = false;
	}

	public boolean isFireable() {
		return fireable;
	}

	private void setFireable(boolean fireable) {
		this.fireable = fireable;
	}

	public void validateFiringStatus() {
		if (isTransitionFireable()) {
			super.setFillColor(Color.RED);
			setFireable(true);
		} else {
			super.setFillColor(Color.GRAY);
			setFireable(false);
		}
	}

	private boolean isTransitionFireable() {
		boolean canBeFired = false;
		//System.out.println(
		//		"isTransitionFireable---" + getNumberOfPlayableIncomingPlaces() + " " + getNoOfIncomiongPlaces());

		if (getNumberOfPlayableIncomingPlaces() != 0
				&& getNumberOfPlayableIncomingPlaces() >= getNoOfIncomiongPlaces()) {
			canBeFired = true;
		}
		return canBeFired;

	}

	public void setArc(Arc arc) {
		this.arcVector.add(arc);
	}

	public ArrayList getArcList() {
		return this.arcVector;
	}

	public void removeArc(Arc arc) {
		this.arcVector.remove(arc);
	}

	private int getNoOfIncomiongPlaces() {
		int incomingPlaces = 0;
		for (Arc arc : arcVector) {
			if (arc.getDirectionType().equals("P_2_T"))
				incomingPlaces++;
		}
		return incomingPlaces;
	}
	
	private int getNumberOfPlayableIncomingPlaces() {
		int noOfPlayableIncomingPlaces = 0;
		for (Arc arc : arcVector) {
			//System.out.println(
			//		"getNumberOfPlayableIncomingPlaces---" + arc.getPlace().getTokenNumbers() + " " + arc.getWeight());
			if (arc.getDirectionType().equals("P_2_T") && arc.getPlace().getTokenNumbers() >= arc.getWeight())
				noOfPlayableIncomingPlaces++;
		}
		return noOfPlayableIncomingPlaces;
	}

	public void fireTransition() {
		if (isFireable()) {
			for (Arc arc : arcVector) {
				if (arc.getDirectionType().equals("P_2_T")) {
					arc.getPlace().removeToken(arc.getWeight());
				} else if (arc.getDirectionType().equals("T_2_P")) {
					arc.getPlace().addToken(arc.getWeight());
				} else {
					System.out.println("This should not happen.");
				}
			}
		} else {
			System.out.println("Are you kidding!!....You can't fire me without fuel.");
		}
	}
	
	
	public Object clone() {
		Object o = null;
		try {
			o=super.clone();

		} catch (CloneNotSupportedException e) {
			System.out.println("Clone Not Supported"+e.toString());
		}
		return o;
	}
	
	
	

}
