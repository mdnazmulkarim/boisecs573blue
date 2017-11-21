package org.boisestate.core;

import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.boisestate.petrinet.Arc;
import org.boisestate.petrinet.Marking;
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

	// Random rand;
	public PetrinetUtility petrinetUtility;
	public Petrinet petrinet;
	public DrawingPanel drawingPanel;

	public PetrinetBuilder(Petrinet petrinet) {
		this.petrinet = petrinet;
		// this.rand = new Random();
		petrinetUtility = new PetrinetUtility();
		workingArrayList = new ArrayList<Object>();
		redoArrayList = new ArrayList<Object>();
		actionArrayList = new ArrayList<Object>();
		redoActionArrayList = new ArrayList<Object>();
	}

	public Object getElementFromWorkingArrayList() {
		if (workingArrayList.size() > 0)
			return workingArrayList.get(workingArrayList.size() - 1);
		else
			return null;
	}

	public void putElementInWorkingArrayList(Object obj) {
		workingArrayList.add(obj);
	}

	public void removeElementFromWorkingArrayList() {
		workingArrayList.remove(workingArrayList.size() - 1);
	}

	public Object getElementFromActionArrayList() {
		if (actionArrayList.size() > 0)
			return actionArrayList.get(actionArrayList.size() - 1);
		else
			return null;
	}

	public void putElementInActionArrayList(Object obj) {
		actionArrayList.add(obj);
	}

	public void removeElementFromActionArrayList() {
		actionArrayList.remove(actionArrayList.size() - 1);
	}

	public Object getElementFromRedoActionArrayList() {
		if (redoActionArrayList.size() > 0)
			return redoActionArrayList.get(redoActionArrayList.size() - 1);
		else
			return null;
	}

	public void putElementInRedoActionArrayList(Object obj) {
		redoActionArrayList.add(obj);
	}

	public void removeElementFromRedoActionArrayList() {
		redoActionArrayList.remove(redoActionArrayList.size() - 1);
	}

	public Object getElementFromRedoArrayList() {
		if (redoArrayList.size() > 0)
			return redoArrayList.get(redoArrayList.size() - 1);
		else
			return null;
	}

	public void putElementInRedoArrayList(Object obj) {
		redoArrayList.add(obj);
	}

	public void removeElementFromRedoArrayList() {
		redoArrayList.remove(redoArrayList.size() - 1);
	}

	public String generatePetrinetName() {
		return petrinetUtility.generateAPetrineName("");
	}

	public String generatePetrinetID() {
		return petrinetUtility.generateAPetrinetID();
	}

	private Boolean existingPlaceNameCheck(String placeName, Place pl) {
		for (Place place : petrinet.placeVector) {
			if (place != pl && placeName.equals(place.getName())) {
				return false;
			}

		}
		return true;
	}

	private Boolean existingTransitionNameCheck(String transName, Transition pl) {
		for (Transition trans : petrinet.transitionVector) {
			if (trans != pl && transName.equals(trans.getName())) {
				return false;
			}

		}
		return true;
	}

	public void setDrawingPanel(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	
	public void transitionInputDialog(Object selectedItem) {
		Transition trans = (Transition) selectedItem;

		JTextField name = new JTextField();
		name.setText(trans.getName());
		Object[] message = { "Name:", name, };

		int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (name.getText() != null && (!name.getText().equals(trans.getName()))) {
				boolean b = existingTransitionNameCheck(name.getText(), trans);

				if (!b) {
					final JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "Duplicate name error.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {

					this.putElementInWorkingArrayList(trans.clone());
					this.putElementInActionArrayList("M");

					trans.setName(name.getText());

					drawingPanel.partialPaint(new Rectangle(trans.getX(), trans.getY(), trans.getWidth() + 1,
							trans.getHeight() + 10 + 2));

					System.out.println("name changed....");
					petrinet.getPetrinetBuilder().printLists();

				}

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
		Object[] message = { "Name:", name, "NumberOfTokens:", numberOfTokens };

		int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (name.getText() != null && (!name.getText().equals(place.getName())
					|| Integer.parseInt(numberOfTokens.getText()) != place.getTokenNumbers())) {
				boolean b = existingPlaceNameCheck(name.getText(), place);

				if (!b) {
					final JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "Duplicate name error.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {

					this.putElementInWorkingArrayList(place.clone());
					this.putElementInActionArrayList("M");

					place.setName(name.getText());
					if (numberOfTokens.getText() != null) {
						place.setTokenNumbers(Integer.parseInt(numberOfTokens.getText()));
					}

					drawingPanel.partialPaint(new Rectangle(place.getX(), place.getY(), place.getRadius() + 1,
							place.getHeight() + 10 + 2));

					System.out.println("name changed....");
					petrinet.getPetrinetBuilder().printLists();

				}

			}

		} else {
			System.out.println("Not Changed.");
		}
	}

	public void arcInputDialog(Object selectedItem) {
		Place place = (Place) selectedItem;

		JTextField name = new JTextField();
		JTextField numberOfTokens = new JTextField();
		name.setText(place.getName());
		numberOfTokens.setText(Integer.toString(place.getTokenNumbers()));
		Object[] message = { "Name:", name, "NumberOfTokens:", numberOfTokens };

		int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (name.getText() != null && (!name.getText().equals(place.getName())
					|| Integer.parseInt(numberOfTokens.getText()) != place.getTokenNumbers())) {
				boolean b = existingPlaceNameCheck(name.getText(), place);

				if (!b) {
					final JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "Duplicate name error.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {

					this.putElementInWorkingArrayList(place.clone());
					this.putElementInActionArrayList("M");

					place.setName(name.getText());
					if (numberOfTokens.getText() != null) {
						place.setTokenNumbers(Integer.parseInt(numberOfTokens.getText()));
					}

					drawingPanel.partialPaint(new Rectangle(place.getX(), place.getY(), place.getRadius() + 1,
							place.getHeight() + 10 + 2));

					System.out.println("name changed....");
					petrinet.getPetrinetBuilder().printLists();

				}

			}

		} else {
			System.out.println("Not Changed.");
		}
	}

	public Object getElementUnderThisPoint(int x, int y) {
		Object obj = null;
		for (Place place : petrinet.placeVector) {
			if (place.getBounds().contains(new Point(x, y))) {
				obj = place;
				return obj;
			}
		}
		for (Transition trans : petrinet.transitionVector) {
			if (trans.getBounds().contains(new Point(x, y))) {
				obj = trans;
				return obj;
			}
		}
		return obj;
	}

	public void printLists() {
		System.out.println("##Printing Places:");
		for (Place place : petrinet.placeVector) {
			System.out.println(place.getName() + "|" + place.getX() + "|" + place.getY());
		}
		System.out.println("##Printing Transitions:");
		for (Transition trans : petrinet.transitionVector) {
			System.out.println(trans.getName() + "|" + trans.getX() + "|" + trans.getY());
		}
		System.out.println("##Printing Working List:");
		Place placeObj;
		Transition transitionObj;
		Arc arcObj;
		for (Object objW : petrinet.getPetrinetBuilder().workingArrayList) {
			if (objW instanceof Place) {
				placeObj = (Place) objW;
				System.out.println(placeObj.getName() + "|" + placeObj.getX() + "|" + placeObj.getY());
			} else if (objW instanceof Transition) {
				transitionObj = (Transition) objW;
				System.out.println(transitionObj.getName() + "|" + transitionObj.getX() + "|" + transitionObj.getY());
			} else if (objW instanceof Arc) {
				arcObj = (Arc) objW;
				System.out.println(arcObj.getDirectionType());
			} else {
				System.out.println("Undetected object");
			}

		}
		System.out.println("##Printing Redo List:");

		for (Object objW : petrinet.getPetrinetBuilder().redoArrayList) {
			if (objW instanceof Place) {
				placeObj = (Place) objW;
				System.out.println(placeObj.getName() + "|" + placeObj.getX() + "|" + placeObj.getY());
			} else if (objW instanceof Transition) {
				transitionObj = (Transition) objW;
				System.out.println(transitionObj.getName() + "|" + transitionObj.getX() + "|" + transitionObj.getY());
			} else if (objW instanceof Arc) {
				arcObj = (Arc) objW;
				System.out.println(arcObj.getDirectionType());
			} else {
				System.out.println("Undetected object");
			}

		}
	}

	public void createArc(ArrayList<Point> pointArray, Place place, Transition trans, String arcType) {
		Boolean isExistingTheSameArc = false;
		for (Arc arc : petrinet.arcVector) {
			if (arc.getDirectionType().equals(arcType)) {
				if (arc.getPlace().getName().equals(place.getName())
						&& arc.getTransition().getName().equals(trans.getName())) {
					isExistingTheSameArc = true;
					JOptionPane.showConfirmDialog(null, "You have the same Arc.", "Warning", 2);
					break;
				}
			}
		}
		if (!isExistingTheSameArc) {
			Arc arc = new Arc();
			arc.setPointVector((ArrayList<Point>) pointArray);
			arc.setPlace(place);
			arc.setTransition(trans);
			arc.setDirectionType(arcType);
			this.petrinet.arcVector.add(arc);

			trans.setArc(arc);

			System.out.println("its transition arc list..." + trans.getArcList().size());

			createPolygonMapForArc(arc);

			petrinet.getPetrinetBuilder().workingArrayList.add(arc.clone());
			petrinet.getPetrinetBuilder().actionArrayList.add("A");
			petrinet.getPetrinetBuilder().printLists();
			// drawingPanel.paintAgain();
		}

	}

	public Point intersectionPoint(Point p, Point center, int r) {
		int off_x = p.x - center.x;
		int off_y = p.y - center.y;
		int ls = off_x * off_x + off_y * off_y;
		if (ls > r * r) {
			double scale = r / Math.sqrt((double) ls);
			double res_x = off_x * scale + center.x;
			double res_y = center.y - off_y * scale;
			return new Point(off_x, off_y);
		}
		return null;
	}

	public Map<Polygon, Arc> arcDetectionMap = new HashMap<Polygon, Arc>();

	public void createPolygonMapForArc(Arc arc) {

		int xPoly[] = { 0, 0, 0, 0 };
		int yPoly[] = { 0, 0, 0, 0 };
		for (int i = 0; i < arc.pointsVector.size() - 1; i++) {
			Point p1 = arc.pointsVector.get(i);
			Point p2 = arc.pointsVector.get(i + 1);

			xPoly[0] = p1.x;
			xPoly[1] = p2.x;
			xPoly[2] = p2.x;
			xPoly[3] = p1.x;

			yPoly[0] = p1.y - 5;
			yPoly[1] = p2.y - 5;
			yPoly[2] = p2.y + 5;
			yPoly[3] = p1.y + 5;

			Polygon polygon = new Polygon(xPoly, yPoly, xPoly.length);

			arcDetectionMap.put(polygon, arc);
		}

		System.out.println("arc map size ---" + arcDetectionMap.size());
	}

	public void removeFromArcDetectionMap(Arc arc) {
		// arcDetectionMap.values().remove(arc);
		if (arcDetectionMap.size() > 0) {
			arcDetectionMap.values().removeAll(Collections.singleton(arc));
		}

		System.out.println("arc map size after removing...." + arcDetectionMap.size());
	}
	
	public void printInitialMarking()
	{
		if(petrinet.getInitialMarking() != null)
		{
			
		}
	}
	
	public boolean checkPlaceMarkingValidity(Marking inputMarking) {
		int[] tokensSerialBy;
		
		String tempDetails = inputMarking.getDetails();
		tempDetails = tempDetails.replaceAll("(", "");
		tempDetails = tempDetails.replaceAll(")", "");
		String[] tokens = tempDetails.split("[,]");
		tokensSerialBy = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
		{
			tokensSerialBy[i] = Integer.parseInt(tokens[i]);
		}
		int i=0;
		for(Place place : petrinet.placeVector)
		{
			if(place.getTokenNumbers() != tokensSerialBy[i]) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	public void resetPlaceWithinitialMarking()
	{
		int[] tokensSerialBy;
		String tempDetails = petrinet.getInitialMarking().getDetails();
		tempDetails = tempDetails.replaceAll("(", "");
		tempDetails = tempDetails.replaceAll(")", "");
		String[] tokens = tempDetails.split("[,]");
		tokensSerialBy = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
		{
			tokensSerialBy[i] = Integer.parseInt(tokens[i]);
		}
		int i=0;
		for(Place place : petrinet.placeVector)
		{
			place.setTokenNumbers(tokensSerialBy[i]);
			i++;
		}
	}
	
	public void generateInitialmarkingFromCurrentPlaces()
	{
		Marking initialMarking = new Marking("M0");
		String details ="(";
		for(Place place : petrinet.placeVector)
		{
			details +=  place.getTokenNumbers();
			details += ",";
		}
		details  = details.substring(0, details.length()-1);
		details += ")";
		
		initialMarking.setDetails(details);
	}
	
	public void printCurrentPlaceMark()
	{
		System.out.println("Printing Current Places mark:");
		for(Place place : petrinet.placeVector)
		{
			System.out.println(place.getName() + ":"+place.getTokenNumbers());
		}
	}

}
