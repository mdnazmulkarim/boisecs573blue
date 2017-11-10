package org.boisestate.petrinet;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.boisestate.core.PetrinetBuilder;
import org.boisestate.graphics.MainPanel;

public class Petrinet {

	private String id;
	private String name;
	private String title;

	public ArrayList<Place> placeVector;
	public ArrayList<Transition> transitionVector;
	public ArrayList<Arc> arcVector;

	public static ArrayList<Transition> currentFirableTransitionList = new ArrayList<Transition>();

	public static Map<Place, Integer> petrinetMarking = new HashMap<Place, Integer>();

	private PetrinetBuilder petrinetBuilder;

	public Petrinet(PetrinetBuilder pb, String title) {
		this.title = title;
		this.petrinetBuilder = pb;
		initIDNameBuilder();
		initVectors();
	}

	public Petrinet(String title) {
		this.title = title;
		initIDNameBuilder();
		initVectors();
	}

	private void initIDNameBuilder() {
		if (this.petrinetBuilder == null) {
			this.petrinetBuilder = new PetrinetBuilder(this);
		}
		this.id = petrinetBuilder.generatePetrinetID();
		this.name = petrinetBuilder.generatePetrinetName();
	}

	private void initVectors() {
		placeVector = new ArrayList<Place>();
		// placeVector.add(new Place());
		transitionVector = new ArrayList<Transition>();
		arcVector = new ArrayList<Arc>();
	}

	public void setPetrinetBuilder(PetrinetBuilder pb) {
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

	public void addTransition(Transition transition) {
		this.transitionVector.add(transition);
		petrinetBuilder.putElementInWorkingArrayList(transition.clone()); // For Redo Undo
		petrinetBuilder.putElementInActionArrayList("A");
	}

	public void addPlace(Place place) {
		this.placeVector.add(place);
		petrinetBuilder.putElementInWorkingArrayList(place.clone()); // For Redo Undo
		petrinetBuilder.putElementInActionArrayList("A");
	}

	public void deletePlace(Place place) {
		petrinetBuilder.putElementInWorkingArrayList(place.clone()); // For Redo Undo
		petrinetBuilder.putElementInActionArrayList("D");
		this.placeVector.remove(place);
	}

	public void deleteLinkedArcWithPlace(Place place) {
		System.out.println(this.arcVector.size());
		ArrayList<Arc> av = new ArrayList<Arc>();

		for(Arc arc: this.arcVector) {

			if(arc.getPlace().equals(place)) {
				petrinetBuilder.putElementInWorkingArrayList(arc.clone()); // For Redo Undo
				petrinetBuilder.putElementInActionArrayList("D");
				for(Transition trans: this.transitionVector) {
					if (trans.getArcList().contains(arc)) {
						trans.getArcList().remove(arc);
						System.out.println(trans.getArcList().size());
						
						av.add(arc);
						break;
					}
				}
			}
		}
		
		for(Arc arc: av) {
			this.arcVector.remove(arc);
			System.out.println(this.arcVector.size());
		}
	}
	
	public void removeLastPlace() {
		petrinetBuilder.workingArrayList.add(placeVector.size() - 1); // For Redo Undo
		this.placeVector.remove(placeVector.size() - 1);
	}

	public void removeAllPlace() {
		this.placeVector.clear();
	}

	public void deleteTransition(Transition trans) {
		petrinetBuilder.putElementInWorkingArrayList(trans.clone()); // For Redo Undo
		petrinetBuilder.putElementInActionArrayList("D");
		this.transitionVector.remove(trans);
	}

	public void deleteArc(Arc arc) {
		petrinetBuilder.putElementInWorkingArrayList(arc.clone()); // For Redo Undo
		petrinetBuilder.putElementInActionArrayList("D");

		Transition trans = arc.getTransition();
		trans.removeArc(arc);

		this.arcVector.remove(arc);
		// remove arc from arcDetectionMap

		petrinetBuilder.removeFromArcDetectionMap(arc);
	}

	public void removeAllTransition() {
		this.transitionVector.clear();
	}

	public void removeAllArcs() {
		this.arcVector.clear();
	}

	// public int tokensOfPlace(int x, int y){
	// for(int i=0; i<this.placeVector.size(); i++) {
	// Place place = (Place)this.placeVector.get(i);
	// if(place.getBounds().contains(new Point(x,y))) {
	// return i;
	// }
	// }
	//
	// return -1;
	// }
	public Object selectedPlace(int x, int y) {

		// for(int i=0; i<this.placeVector.size(); i++) {
		// Place place = (Place)this.placeVector.get(i);
		// if(place.getBounds().contains(new Point(x,y))) {
		// return place;
		// }
		// }

		for (Place place : this.placeVector) {
			if (place.getBounds().contains(new Point(x, y))) {
				return place;
			}
		}
		for (Transition transition : this.transitionVector) {
			if (transition.getBounds().contains(new Point(x, y))) {
				return transition;
			}
		}

		Iterator it = petrinetBuilder.arcDetectionMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Polygon p = (Polygon) pair.getKey();
			if (p.contains(x, y)) {
				Arc arc = (Arc) pair.getValue();
				return arc;
			}

		}

		return null;
	}

	public void populatefireableTransitions() {
		// ArrayList<Transition> firableArrayList = new ArrayList<Transition>();
		currentFirableTransitionList.clear();
		for (Transition trans : this.transitionVector) {
			trans.validateFiringStatus();
			if (trans.isFireable()) {
				currentFirableTransitionList.add(trans);
			}
		}
	}

	public void setFirableComboList() {
		populatefireableTransitions();
		MainPanel.activeFiringStatesList.removeAllItems();
		for (Transition trans : currentFirableTransitionList) {

			MainPanel.activeFiringStatesList.addItem(trans.getName().toString());

		}
	}

	public void playTransition() {
		int ind = MainPanel.activeFiringStatesList.getSelectedIndex();
		if (ind >= 0) {
			Transition trans = currentFirableTransitionList.get(ind);
			trans.fireTransition();
		} else {
			JOptionPane.showConfirmDialog(null, "No firable transition is available.", "Warning", 2);
		}
	}

	public void saveCurrentMarking() {
		petrinetMarking.clear();
		for (Place place : this.placeVector) {
			petrinetMarking.put(place, place.getTokenNumbers());
		}
	}

	public void restorePreviousMarking() {
		for (Place place : this.placeVector) {
			place.setTokenNumbers(petrinetMarking.get(place));
		}
	}

}
