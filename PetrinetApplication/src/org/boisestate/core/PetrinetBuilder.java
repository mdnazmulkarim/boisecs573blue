package org.boisestate.core;

import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import org.boisestate.petrinet.Arc;
import org.boisestate.petrinet.Marking;
import org.boisestate.petrinet.TreeNode;
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
	
	
	public boolean checkMarkingValidity(Marking inputMarking) {
		int[] tokensSerialBy;
		
		String tempDetails = inputMarking.getTokenSequence();
		System.out.println("Seq:"+tempDetails);
		if(tempDetails.isEmpty())
			return false;
		tempDetails = tempDetails.replace("(", "");
		tempDetails = tempDetails.replace(")", "");
		String[] tokens = tempDetails.split("[,]");
		tokensSerialBy = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
		{
			try {
			tokensSerialBy[i] = Integer.parseInt(tokens[i]);
			if(tokensSerialBy[i]<0)
				return false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println(nfe.getMessage());
				return false;
			}
		}
		
		if(tokensSerialBy.length != petrinet.placeVector.size())
			return false;
		
		return true;
	}
	
	public void resetPlaceWithMarking(Marking marking)
	{
		int[] tokensSerialBy;
		String tempDetails = marking.getTokenSequence();
		tempDetails = tempDetails.replace("(", "");
		tempDetails = tempDetails.replace(")", "");
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
	
	
	
	public static int markingIndex = 0;
	/**
	 * 
	 */
	public Marking generateMarkingFromCurrentPlaces()
	{
		markingIndex++;
		Marking initialMarking = new Marking("M"+markingIndex);
		String details ="(";
		String detailsPlace ="(";
		for(Place place : petrinet.placeVector)
		{
			details +=  place.getTokenNumbers();
			details += ",";
			
			detailsPlace +=  place.getName();
			detailsPlace += ",";
		}
		details  = details.substring(0, details.length()-1);
		details += ")";
		
		detailsPlace  = detailsPlace.substring(0, detailsPlace.length()-1);
		detailsPlace += ")";
		
		initialMarking.setTokenSequence(details);
		initialMarking.setPlaceSequence(detailsPlace);
		
		System.out.println("Marking generated:");
		System.out.println(initialMarking.getName()+"|"+initialMarking.getPlaceSequence()+"|"+initialMarking.getTokenSequence());
		
		return initialMarking;
	}
	
	/**
	 * This method just prints current Place tokens
	 */
	public void printCurrentPlaceMark() {
		System.out.println("Printing Current Places mark:");
		for(Place place : petrinet.placeVector)
		{
			System.out.println(place.getName() + ":"+place.getTokenNumbers());
		}
	}
	
	/**
	 * This method return current Place sequence
	 */
	public String getCurrentPlaceSequence() {
		String returnString = "(";
		for(Place place : petrinet.placeVector)
		{
			returnString +=place.getName()+",";
			//System.out.println(place.getName() + ":"+place.getTokenNumbers());
		}
		returnString  = returnString.substring(0, returnString.length()-1);
		returnString += ")";
		return returnString;
	}
	
	public static ArrayList tokenPlaceHistory = new ArrayList();
	
	public String getCurrentPlaceTokenSequence() {
		tokenPlaceHistory.clear();
		
		String returnString = "(";
		for(Place place : petrinet.placeVector)
		{
			tokenPlaceHistory.add(new Integer(place.getTokenNumbers()));
			returnString +=place.getTokenNumbers()+",";
			//System.out.println(place.getName() + ":"+place.getTokenNumbers());
		}
		returnString  = returnString.substring(0, returnString.length()-1);
		returnString += ")";
		return returnString;
	}
	
	private String getPresentableTreeNode(Marking marking)
	{
		return marking.getPrecedenceTransitionName()+"->"+marking.getName()+":"+marking.getTokenSequence().replace("999", "W");
	}
	
	
	
	public Map<String,Marking> allMarking = new HashMap<String,Marking>();
	public ArrayList<TreeNode> bfsQueue = new ArrayList<TreeNode>();
	public int queueIndex = 0;
	
	public ArrayList<Marking> tokensHistory = new ArrayList<Marking>();  //used for omega
	//public static int markingCount = 1;
	
	public TreeNode generateCoverabilityTree() {
		TreeNode<Marking> root = new TreeNode<>(petrinet.getInitialMarking());

		tokensHistory.clear();
		tokensHistory.add(root.getData());

		allMarking.put(root.getData().getTokenSequence(), root.getData());
		bfsQueue.add(root);

		TreeNode node = getElementFromQueue();
		while (node != null) {
			traverseNode(node);
			node = getElementFromQueue();
		}
		return root;
	}
	
	
	public static boolean hasDeadTransition = false;
	private void traverseNode(TreeNode node)
	{
		Marking marking = (Marking) node.getData();		
		if(node.getStatus() != TreeNode.MARK_NEW)
			return;		
		
		System.out.println("Traverse Node:"+marking);
		
		resetPlaceWithMarking(marking);
		petrinet.populatefireableTransitions();
		if(petrinet.currentFirableTransitionList.size()==0)
		{
			node.setStatus(TreeNode.MARK_DEAD);
			System.out.println("NO fireable transition");
			hasDeadTransition = true;
		}
		else
		{
			System.out.println("Found fireable transitions:");
			for(Transition transition : petrinet.currentFirableTransitionList )
			{
				resetPlaceWithMarking(marking);
				transition.fireTransition();
				Marking newMarking = generateMarkingFromCurrentPlaces();	
				
				
				//newMarking = transformMarkingWithOmegaFactor(marking,newMarking,transition);
				//newMarking = transformMarkingWithOmegaFactorUpdated(marking,newMarking);
				
				System.out.println("string my----"+newMarking.getTokenSequence());
				tokenHistoryChecking(newMarking);
				

				//updateTokenplaceHistory(newMarking);
				newMarking.setPrecedenceTransitionName(transition.getName());
				TreeNode<Marking> newNode = new TreeNode<>(newMarking);	
				
				if(isOldMarking(newMarking))
				{
					System.out.println("OLD:"+newMarking);
					newNode.setStatus(TreeNode.MARK_OLD);
				}
				else
				{
					System.out.println("New:"+newMarking);
					allMarking.put(newNode.getData().getTokenSequence(), newNode.getData());
					bfsQueue.add(newNode);					
				}
								
				node.addChild(newNode);
								
			}
		}
		
		if(node.getStatus() == TreeNode.MARK_NEW)
			node.setStatus(TreeNode.MARK_VISITED);
	}
	
	public static boolean hasOmega = false;
	
	private void tokenHistoryChecking(Marking mark) {
		
		String tempDetailsCurrent = mark.getTokenSequence();
		tempDetailsCurrent = tempDetailsCurrent.replace("(", "");
		tempDetailsCurrent = tempDetailsCurrent.replace(")", "");
		String[] tokensCurrent = tempDetailsCurrent.split("[,]");
		
		int index = -1;
		for(Marking mm: tokensHistory) {
			String tempDetailsPre = mm.getTokenSequence();
			tempDetailsPre = tempDetailsPre.replace("(", "");
			tempDetailsPre = tempDetailsPre.replace(")", "");
			String[] tokensPre = tempDetailsPre.split("[,]");
			
			
			
		
			for(int i=0; i<tokensPre.length; i++){
				if(tokensPre[i].equals("1")) {
					if(!tokensPre[i].equals(tokensCurrent[i])) {
						break;
					}
				}
				else {
					if(i==tokensPre.length-1) {
						for(int j = 0; j<tokensPre.length; j++) {
							if(tokensPre[j].equals("0")) {
								if(!tokensCurrent[j].equals("0")) {
									index = j;
									tokensCurrent[j]="999";
									hasOmega = true;
									break;
								}
							}
						}
					}
				}
				
			}
		}
		String str="(";
		for(int i=0; i<tokensCurrent.length;i++) {
			str += tokensCurrent[i]+",";
		}
		str  = str.substring(0, str.length()-1);
		str += ")";
		mark.setTokenSequence(str);
		tokensHistory.add(mark);

	}
	
	
	public boolean isOldMarking(Marking marking)
	{
		Marking probableMarking = allMarking.get(marking.getTokenSequence());
		if( probableMarking != null)
			return true;
		//else
		//	allMarking.put(marking.getTokenSequence(),marking);
		return false;
	}
	
	public DefaultMutableTreeNode testTree;
	
	public void traverseTree(TreeNode tree,DefaultMutableTreeNode node) {

	    // print, increment counter, whatever
	   // System.out.println(tree.toString());
		//DefaultMutableTreeNode root;
		if(node ==null)
		{     
			String str = (String)getPresentableTreeNode((Marking)tree.getData())+tree.getStatusName(tree.getStatus());
			//System.out.println("traverseTree----!@@@@STATUS"+tree.getStatus());
			//System.out.println("traverseTree1----!@@@@---"+getStatusName(tree.getStatus()));

			node = new DefaultMutableTreeNode(str);
	       //System.out.println(getPresentableTreeNode((Marking)tree.getData()));
	        testTree = node;
		}// traverse children
	    int childCount = tree.getChildren().size();
	    if (childCount == 0) {
	        // leaf node, we're done
	    } else {
	        for (int i = 0; i < childCount; i++) {
	            TreeNode child = (TreeNode) tree.getChildren().get(i);
	            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode((String)getPresentableTreeNode((Marking)child.getData())+child.getStatusName(child.getStatus()));
				//System.out.println("traverseTree2----!@@@@---"+child.getStatus());
	            //System.out.println(getPresentableTreeNode((Marking)child.getData()));
	            traverseTree(child,childNode);
	            node.add(childNode);
	        }
	    }
	}
	
	private TreeNode getElementFromQueue() {
		if(queueIndex >=bfsQueue.size())
			return null;
		TreeNode node = bfsQueue.get(queueIndex);
		queueIndex++;
		return node;
	}
	
	
	
	
	
	
//	private void updateTokenplaceHistory(Marking marking)
//	{
//		String tempDetailsPre = marking.getTokenSequence();
//		tempDetailsPre = tempDetailsPre.replace("(", "");
//		tempDetailsPre = tempDetailsPre.replace(")", "");
//		String[] tokensPre = tempDetailsPre.split("[,]");
//		
//		for(int i=0;i<tokensPre.length;i++)
//		{
//			if(Integer.parseInt(tokensPre[i])>=1 && (int)tokenPlaceHistory.get(i) <= new Integer(0))
//			{
//				
//				tokenPlaceHistory.remove(i);
//				tokenPlaceHistory.add(i, Integer.parseInt(tokensPre[i]));			
//			}
//		}
//	}
	
	
//	private Marking transformMarkingWithOmegaFactorUpdated(Marking priorMarking, Marking postMarking)
//	{
//		int[] tokensSerialByPre;
//		int[] tokensSerialByPost;
//		
//		String tempDetailsPre = priorMarking.getTokenSequence();
//		tempDetailsPre = tempDetailsPre.replace("(", "");
//		tempDetailsPre = tempDetailsPre.replace(")", "");
//		String[] tokensPre = tempDetailsPre.split("[,]");
//		tokensSerialByPre = new int[tokensPre.length];
//		
//		for(int i=0;i<tokensSerialByPre.length;i++)
//		{
//			tokensSerialByPre[i] = Integer.parseInt(tokensPre[i]);
//		}
//		
//		
//		String tempDetailsPost = postMarking.getTokenSequence();
//		tempDetailsPost = tempDetailsPost.replace("(", "");
//		tempDetailsPost = tempDetailsPost.replace(")", "");
//		String[] tokensPost = tempDetailsPost.split("[,]");
//		tokensSerialByPost = new int[tokensPost.length];
//		
//		for(int i=0;i<tokensSerialByPost.length;i++)
//		{
//			tokensSerialByPost[i] = Integer.parseInt(tokensPost[i]);
//		}
//		
//		boolean hasOmega = false;
//		for(int i=0;i<tokensSerialByPre.length;i++)
//		{
//			
//		}
//		
//		return postMarking;
//	}
//	
	
	
	/**
	 * @param priorMarking
	 * @param postMarking
	 * @param transition
	 * @return
	 */
//	private Marking transformMarkingWithOmegaFactor(Marking priorMarking, Marking postMarking,Transition transition )
//	{
//		int[] tokensSerialByPre;
//		int[] tokensSerialByPost;
//		
//		String tempDetailsPlaces = postMarking.getPlaceSequence();
//		tempDetailsPlaces = tempDetailsPlaces.replace("(", "");
//		tempDetailsPlaces = tempDetailsPlaces.replace(")", "");
//		String[] tokensPlaces = tempDetailsPlaces.split("[,]");
//		
//		int[] placeOmegaStatus = new int[tokensPlaces.length];
//		for(int i=0;i<placeOmegaStatus.length;i++)
//		{
//			placeOmegaStatus[i] = 0;
//		}
//		
//		String tempDetailsPre = priorMarking.getTokenSequence();
//		tempDetailsPre = tempDetailsPre.replace("(", "");
//		tempDetailsPre = tempDetailsPre.replace(")", "");
//		String[] tokensPre = tempDetailsPre.split("[,]");
//		tokensSerialByPre = new int[tokensPre.length];
//		
//		String tempDetailsPost = postMarking.getTokenSequence();
//		tempDetailsPost = tempDetailsPost.replace("(", "");
//		tempDetailsPost = tempDetailsPost.replace(")", "");
//		String[] tokensPost = tempDetailsPost.split("[,]");
//		tokensSerialByPost = new int[tokensPost.length];
//		
//		ArrayList<String> incmingPlaces = new ArrayList<String>();
//		ArrayList<String> outgoingPlaces = new ArrayList<String>();
//		
//		for (Arc arc : transition.arcVector) {
//			if(arc.getDirectionType().equals("P_2_T"))
//			{
//				incmingPlaces.add(arc.getPlace().getName());
//			}
//			else if(arc.getDirectionType().equals("T_2_P"))
//			{
//				outgoingPlaces.add(arc.getPlace().getName());
//			}
//		}
//		
//		boolean hasOmegaOccurance = false;
//		for(int i=0;i<outgoingPlaces.size();i++)
//		{
//			for(int j=0;j<incmingPlaces.size();j++)
//			{
//				if(outgoingPlaces.get(i).equalsIgnoreCase(incmingPlaces.get(j)))
//				{
//					hasOmegaOccurance = true;
//					for(int k=0;k<tokensPlaces.length;k++)
//					{
//						if(outgoingPlaces.get(i).equalsIgnoreCase(tokensPlaces[k]))
//						{
//							placeOmegaStatus[k] =1;
//						}
//					}
//				}
//			}
//		}
//		
//		if(hasOmegaOccurance)
//		{
//			for(int i=0;i<placeOmegaStatus.length;i++)
//			{
//				if(placeOmegaStatus[i] ==0)
//				{
//					tokensPost[i] = "999";
//				}
//			}
//		}
//		
//		String details ="(";		
//		for(int i=0;i<tokensPost.length;i++)
//		{
//			details += tokensPost[i];
//			details += ",";
//		}
//		details  = details.substring(0, details.length()-1);
//		details += ")";
//		
////		for(int i=0;i<tokensPre.length;i++)
////		{
////			tokensSerialBy[i] = Integer.parseInt(tokens[i]);
////		}
//		postMarking.setTokenSequence(details);
//		return postMarking;
//	}
	

	
	

}
