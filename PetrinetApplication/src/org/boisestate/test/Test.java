package org.boisestate.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import org.boisestate.core.PetrinetBuilder;
import org.boisestate.core.PetrinetMain;
import org.boisestate.graphics.Constants;
import org.boisestate.graphics.CoverabilityTreePanel;
import org.boisestate.graphics.DrawingPanel;
import org.boisestate.graphics.MainPanel;
import org.boisestate.graphics.PetriNetSaver;
import org.boisestate.graphics.XMLParser;
import org.boisestate.petrinet.Arc;
import org.boisestate.petrinet.Marking;
import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;

import junit.framework.*;


public class Test extends TestCase{

	
	private Petrinet petrinet = new Petrinet("");

	
	//********************Petrinet Class*************************************
	@org.junit.Test
	public void test1() {
				
		assertTrue("", petrinet.placeVector.size()==0);
		assertTrue("", petrinet.arcVector.size()==0);
		assertTrue("", petrinet.transitionVector.size()==0);
		
	}
	
	@org.junit.Test
	public void test2() {
		Transition aTransition = new Transition();
		petrinet.addTransition(aTransition);
		assertTrue("", petrinet.transitionVector.size()==1);
		petrinet.deleteTransition(aTransition);
		assertTrue("", petrinet.transitionVector.size()==0);
		petrinet.removeAllTransition();
		petrinet.saveCurrentMarking();
		petrinet.populatefireableTransitions(); 
		petrinet.setFirableComboList();	
		petrinet.playTransition();
			
	}
	
	@org.junit.Test
	public void test3() {
		Place aPlace = new Place();
		petrinet.addPlace(aPlace);
		assertTrue("", petrinet.placeVector.size()==1);
		petrinet.deletePlace(aPlace);
		assertTrue("", petrinet.placeVector.size()==0);
		petrinet.addPlace(aPlace);
		assertTrue("", petrinet.placeVector.size()==1);
		petrinet.removeLastPlace();
		petrinet.removeAllPlace();
		petrinet.restorePreviousMarking();
		aPlace.addToken(2);
		aPlace.removeToken(1);
	}

	//********************Arc Class*************************************
	@org.junit.Test
	public void test4() {
		petrinet.getPetrinetBuilder().printLists();
		Place aPlace = new Place();
		petrinet.addPlace(aPlace);
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		assertTrue("", petrinet.getPetrinetBuilder().existingPlaceNameCheck("P1",aPlace)==true);

		petrinet.getPetrinetBuilder().placeInputDialog(aPlace);
		
	}
	
	
	@org.junit.Test
	public void test5() {
		petrinet.getPetrinetBuilder().printLists();
		Place aPlace = new Place();
		petrinet.addPlace(aPlace);
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");		
	}
	@org.junit.Test
	public void test6() {
		petrinet.getPetrinetBuilder().printLists();
		Transition aTrans = new Transition();
		petrinet.addTransition(aTrans);
		aTrans.setName("T1");
		assertTrue("", petrinet.getPetrinetBuilder().existingTransitionNameCheck("T1",aTrans)==true);
	}
	@org.junit.Test
	public void test7() {
		petrinet.getPetrinetBuilder().printLists();
		Transition aTrans = new Transition();
		petrinet.addTransition(aTrans);
		aTrans.setName("T1");
		assertFalse("", petrinet.getPetrinetBuilder().existingTransitionNameCheck("T2",aTrans)==false);
	}
	@org.junit.Test
	public void test8() {
		petrinet.getPetrinetBuilder().printLists();
		Place aPlace = new Place();
		petrinet.addPlace(aPlace);
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		assertFalse("", petrinet.getPetrinetBuilder().existingPlaceNameCheck("P2",aPlace)==false);

	}
	@org.junit.Test
	public void test9() {
		petrinet.getPetrinetBuilder().getElementFromRedoArrayList();

		petrinet.getPetrinetBuilder().printLists();
		Place aPlace = new Place();
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		Transition aTrans = new Transition();
		petrinet.addTransition(aTrans);
		aTrans.setName("T1");
		petrinet.getPetrinetBuilder().getElementFromActionArrayList();
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().putElementInRedoArrayList(aPlace);
		petrinet.getPetrinetBuilder().putElementInRedoArrayList(aTrans);
		petrinet.getPetrinetBuilder().getElementFromRedoArrayList();
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
//		petrinet.getPetrinetBuilder().removeElementFromRedoArrayList();
		petrinet.getPetrinetBuilder().getElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().putElementInRedoActionArrayList(aPlace);
		petrinet.getPetrinetBuilder().getElementFromRedoActionArrayList();
//		petrinet.getPetrinetBuilder().removeElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().putElementInActionArrayList(aPlace);
//		petrinet.getPetrinetBuilder().removeElementFromActionArrayList();
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aPlace);
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
//		petrinet.getPetrinetBuilder().removeElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().getElementFromActionArrayList();
		
		MainPanel mainPanel = new MainPanel();
		mainPanel.alertDialog();
		mainPanel.undoAction();
		mainPanel.redoAction();
	}

	
	@org.junit.Test
	public void test10() {
		Place aPlace = new Place();
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		aPlace.setX(50);
		aPlace.setY(50);
		petrinet.addPlace(aPlace);
		petrinet.getPetrinetBuilder().getElementUnderThisPoint(aPlace.getX(),aPlace.getY());

	}
	@org.junit.Test
	public void test12() {
		petrinet.getPetrinetBuilder().getElementUnderThisPoint(10,10);
	}
	@org.junit.Test
	public void test11() {
		Transition aTrans = new Transition();
		petrinet.addTransition(aTrans);
		aTrans.setName("T1");
		aTrans.setX(50);
		aTrans.setY(50);
		petrinet.addTransition(aTrans);
		petrinet.getPetrinetBuilder().getElementUnderThisPoint(aTrans.getX(),aTrans.getY());

	}
	@org.junit.Test
	public void test13() {
		Place aPlace = new Place();
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		aPlace.setX(50);
		aPlace.setY(50);
		petrinet.addPlace(aPlace);
		Transition aTrans = new Transition();
		aTrans.setName("T1");
		aTrans.setX(100);
		aTrans.setY(190);
		petrinet.addTransition(aTrans);
		
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new Point(aPlace.getX(),aPlace.getY()));
		pointList.add(new Point(aTrans.getX(),aTrans.getY()));

		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aPlace);
		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aTrans);
		petrinet.getPetrinetBuilder().createArc(pointList,aPlace,aTrans,"P_2_T");
	}
	@org.junit.Test
	public void test14() {
		Place aPlace = new Place();
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		aPlace.setX(50);
		aPlace.setY(50);
		petrinet.addPlace(aPlace);
		Transition aTrans = new Transition();
		aTrans.setName("T1");
		aTrans.setX(100);
		aTrans.setY(190);
		petrinet.addTransition(aTrans);
		
		Arc arc = new Arc(aPlace, aTrans, "P_2_T");
		petrinet.addArc(arc);
//		Arc arc1 = new Arc(aPlace, aTrans, "P_2_T");
//		petrinet.addArc(arc1);
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new Point(aPlace.getX(),aPlace.getY()));
		pointList.add(new Point(aTrans.getX(),aTrans.getY()));
		arc.setPointVector(pointList);
		arc.getPointVector();
		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aPlace);
		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aTrans);
		petrinet.getPetrinetBuilder().createArc(pointList,aPlace,aTrans,"P_2_T");
//		petrinet.getPetrinetBuilder().createArc(pointList,aPlace,aTrans,"P_2_T");

	}
	@org.junit.Test
	public void test15() {
		Place aPlace = new Place();
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		aPlace.setX(50);
		aPlace.setY(50);
		petrinet.addPlace(aPlace);
		Transition aTrans = new Transition();
		aTrans.setName("T1");
		aTrans.setX(100);
		aTrans.setY(190);
		petrinet.addTransition(aTrans);
		
		Arc arc = new Arc(aPlace, aTrans, "P_2_T");
		petrinet.addArc(arc);

		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new Point(aPlace.getX(),aPlace.getY()));
		pointList.add(new Point(aTrans.getX(),aTrans.getY()));
		arc.setPointVector(pointList);
		arc.getPointVector();
		
		arc.setX(50);
		arc.getX();
		arc.setY(50);
		arc.getY();
		arc.setName("A1");
		arc.getName();
		arc.setFillColor(Color.BLACK);
		arc.setBorderColor(Color.BLACK);

		aPlace.setRadius(30);
		aPlace.getRadius();
		aPlace.setHight(30);
		aPlace.getHeight();
		aPlace.setFillColor(Color.BLACK);
		aPlace.setBorderColor(Color.BLACK);
		
		aTrans.setWidth(30);
		aTrans.getWidth();
		aTrans.setHeight(30);
		aTrans.getHeight();
		aTrans.setFillColor(Color.BLACK);
		aTrans.setBorderColor(Color.BLACK);
		
		
//		mainPanel.redoActionForPlace(aPlace,PetrinetBuilder.ADD);
//		mainPanel.redoActionForPlace(aPlace,PetrinetBuilder.DELETE);
	}
	
	@org.junit.Test
	public void test16() {
		Constants.ROOT_OF_XML = "PetriNet";
	}
	@org.junit.Test
	public void test17() {
		PetrinetMain petrinet = new PetrinetMain();
		petrinet.createMainPanel();
		assertTrue("", petrinet!=null);
	}
//	@org.junit.Test
//	public void test17() {
//		MainPanel mainPanel = new MainPanel();
//		mainPanel.alertDialog();
//	}
//	
	@org.junit.Test
	public void test18() {
		Place aPlace = new Place();
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		aPlace.setX(50);
		aPlace.setY(50);
		petrinet.addPlace(aPlace);
		Transition aTrans = new Transition();
		aTrans.setName("T1");
		aTrans.setX(100);
		aTrans.setY(190);
		petrinet.addTransition(aTrans);
		
		Arc arc = new Arc(aPlace, aTrans, "P_2_T");
		petrinet.addArc(arc);
//		Arc arc1 = new Arc(aPlace, aTrans, "P_2_T");
//		petrinet.addArc(arc1);
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new Point(aPlace.getX(),aPlace.getY()));
		pointList.add(new Point(aTrans.getX(),aTrans.getY()));
		arc.setPointVector(pointList);
		arc.getPointVector();
		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aPlace);
		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aTrans);
		petrinet.getPetrinetBuilder().createArc(pointList,aPlace,aTrans,"P_2_T");
//		petrinet.getPetrinetBuilder().createArc(pointList,aPlace,aTrans,"P_2_T");

		MainPanel mainPanel =new MainPanel();
		assertTrue("", mainPanel!=null);
	     mainPanel.fileChoose();
		
		DrawingPanel draw = new DrawingPanel(petrinet);
		draw.arcDrawingStarted = true;
		draw.erasePartialArc();
		draw.placeNameSet(aPlace);
		draw.transitionNameSet(aTrans);
		draw.drawPlace(aPlace, 100, 100);
		draw.drawTransition(aTrans, 200, 200);
		draw.getPreferredSize();
		draw.resetTransitionColor();
		
		PetriNetSaver saver = new PetriNetSaver(petrinet);
		saver.xmlFileName("testing");
		XMLParser xmlParse = new XMLParser(null, petrinet);
		
	}
	@org.junit.Test
	public void test19() {
		petrinet.getPetrinetBuilder().getElementFromRedoArrayList();

		petrinet.getPetrinetBuilder().printLists();
		Place aPlace = new Place();
		aPlace.setTokenNumbers(2);
		aPlace.setName("P1");
		Transition aTrans = new Transition();
		petrinet.addTransition(aTrans);
		aTrans.setName("T1");
		petrinet.getPetrinetBuilder().getElementFromActionArrayList();
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().putElementInRedoArrayList(aPlace);
		petrinet.getPetrinetBuilder().putElementInRedoArrayList(aTrans);
		petrinet.getPetrinetBuilder().getElementFromRedoArrayList();
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().removeElementFromRedoArrayList();
		petrinet.getPetrinetBuilder().getElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().putElementInRedoActionArrayList(aPlace);
		petrinet.getPetrinetBuilder().getElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().removeElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().putElementInActionArrayList(aPlace);
		petrinet.getPetrinetBuilder().removeElementFromActionArrayList();
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().putElementInWorkingArrayList(aPlace);
		petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().removeElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().getElementFromActionArrayList();
	}
	@org.junit.Test
	public void test20() {
		petrinet.getPetrinetBuilder().printLists();
		Transition aTrans = new Transition();
		petrinet.addTransition(aTrans);
		aTrans.setName("T1");
		petrinet.getPetrinetBuilder().transitionInputDialog(aTrans);
		
	}
	//********************MainPanel Class*************************************

	
}
