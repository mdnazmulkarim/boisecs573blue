package org.boisestate.test;

import static org.junit.Assert.*;

import org.boisestate.graphics.MainPanel;
import org.boisestate.petrinet.Arc;
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
	}
//	
//	@org.junit.Test
//	public void test4() {
//		Place aPlace = new Place();
//		petrinet.addPlace(aPlace);
//		assertTrue("", petrinet.placeVector.size()==1);
//		petrinet.deletePlace(aPlace);
//		assertTrue("", petrinet.placeVector.size()==0);
//		
//			
//	}
	
	//********************MainPanel Class*************************************

	@org.junit.Test
	public void test40() {
		MainPanel mainPanel =new MainPanel();
		assertTrue("", mainPanel!=null);
	     mainPanel.fileChoose();
		
			
	}
}
