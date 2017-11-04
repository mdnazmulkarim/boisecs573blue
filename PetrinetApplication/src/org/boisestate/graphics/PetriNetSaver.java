package org.boisestate.graphics;

//import Constants;
//import XmlParse;

import java.awt.Point;
import java.io.File;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.boisestate.petrinet.Arc;
import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PetriNetSaver {
	Petrinet petrinet;
	public PetriNetSaver(Petrinet petrinet) {
		this.petrinet = petrinet;
	}
	 public void xmlFileName(String petriName) {
		 try {
				
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement(Constants.ROOT_OF_XML);
				doc.appendChild(rootElement);

				Element title = doc.createElement(Constants.PETRI_NET_TITLE);
				title.appendChild(doc.createTextNode("PetriNet"));
				rootElement.appendChild(title);
				
				Element ID = doc.createElement(Constants.PETRI_NET_ID);
				ID.appendChild(doc.createTextNode("100"));
				rootElement.appendChild(ID);
				
				Element name = doc.createElement(Constants.PETRI_NET_NAME);
				name.appendChild(doc.createTextNode(petriName));
				rootElement.appendChild(name);
				
				Element numberOfPlaces = doc.createElement(Constants.PETRI_NET_NUMBER_OF_PALCES);
				numberOfPlaces.appendChild(doc.createTextNode("10"));
				rootElement.appendChild(numberOfPlaces);
				
				Element numberOfTransitions = doc.createElement(Constants.PETRI_NET_NUMBER_OF_TRANSITIONS);
				numberOfTransitions.appendChild(doc.createTextNode("10"));
				rootElement.appendChild(numberOfTransitions);
				
				Element numberOfArcs = doc.createElement(Constants.PETRI_NET_NUMBER_OF_ARCS);
				numberOfArcs.appendChild(doc.createTextNode("10"));
				rootElement.appendChild(numberOfArcs);
				
				
				// Places elements
				Element places = doc.createElement(Constants.PLACES);
				rootElement.appendChild(places);
				createPlaces(doc,places);
				
				
				// Transitions elements
				Element transitions = doc.createElement(Constants.TRANSITIONS);
				rootElement.appendChild(transitions);
				createTransitions(doc,transitions);

				// Arc elements
				Element arcs = doc.createElement(Constants.ARCS);
				rootElement.appendChild(arcs);
				createArcs(doc,arcs);
				
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(petriName + ".xml"));

				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);

				transformer.transform(source, result);

				System.out.println("File saved!");
				
//				XmlParse.xmlParse();

			  } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
		
		
	 }
	  
	
	public void createPlaces(Document doc, Element places) {
		Vector<Object> placesVector = new Vector<Object>();
		
		System.out.println("place vector size : "+petrinet.placeVector.size());
		
		for(Place place : petrinet.placeVector) {
			if(place.getX()!=-50){
				Vector<String>placeVector1 = new Vector<String>();
				placeVector1.addElement(place.getName());
				placeVector1.addElement(Integer.toString(place.getTokenNumbers()));
				placeVector1.addElement(Integer.toString(place.getX()));
				placeVector1.addElement(Integer.toString(place.getY()));
				placeVector1.addElement(Integer.toString(place.getRadius()));
				
				
				placesVector.addElement(placeVector1);
			}
			

			
		}
			
		
		if(placesVector.size() > 0) {
			int count = placesVector.size();
			
			for (int i=0; i<count; i++) {
				Element place = doc.createElement(Constants.PLACE);
				places.appendChild(place);
				place.setAttribute("id", Integer.toString(i+1));						
				
				Vector<Object> vector = new Vector<Object>();
				vector = (Vector<Object>) placesVector.elementAt(i);
//				System.out.println(vector);
				
				Element placeName = doc.createElement(Constants.PLACE_NAME);
				placeName.appendChild(doc.createTextNode(vector.elementAt(0).toString()));
				place.appendChild(placeName);
				
				Element placeNumberOfTokens = doc.createElement(Constants.NUMBER_OF_TOKENS_OF_PLACE);
				placeNumberOfTokens.appendChild(doc.createTextNode(vector.elementAt(1).toString()));
				place.appendChild(placeNumberOfTokens);
				
				Element placeOriginX = doc.createElement(Constants.ORIGIN_X_OF_PLACE);
				placeOriginX.appendChild(doc.createTextNode(vector.elementAt(2).toString()));
				place.appendChild(placeOriginX);
				
				Element placeOriginY = doc.createElement(Constants.ORIGIN_Y_OF_PLACE);
				placeOriginY.appendChild(doc.createTextNode(vector.elementAt(3).toString()));
				place.appendChild(placeOriginY);
				
				Element placeRadius = doc.createElement(Constants.RADIUS_OF_PLACE);
				placeRadius.appendChild(doc.createTextNode(vector.elementAt(4).toString()));
				place.appendChild(placeRadius);
			}
		}
		
	}
	
	public void createTransitions(Document doc, Element transitions) {
		Vector<Object> transitionVector = new Vector<Object>();
		
		System.out.println("transition vector size : "+petrinet.transitionVector.size());
		
		for(TransitionGuiItem transition : petrinet.transitionVector) {
			if(transition.getX()!=-50){
				Vector<String>transitionVector1 = new Vector<String>();
				transitionVector1.addElement(transition.getName());
				transitionVector1.addElement(Integer.toString(transition.getX()));
				transitionVector1.addElement(Integer.toString(transition.getY()));
				
				
				transitionVector.addElement(transitionVector1);
			}
			

			
		}
			
		
		if(transitionVector.size() > 0) {
			int count = transitionVector.size();
			
			for (int i=0; i<count; i++) {
				Element place = doc.createElement(Constants.TRANSITION);
				transitions.appendChild(place);
				place.setAttribute("id", Integer.toString(i+1));						
				
				Vector<Object> vector = new Vector<Object>();
				vector = (Vector<Object>) transitionVector.elementAt(i);
//				System.out.println(vector);
				
				Element transitionName = doc.createElement(Constants.TRANSITION_NAME);
				transitionName.appendChild(doc.createTextNode(vector.elementAt(0).toString()));
				place.appendChild(transitionName);
				
				
				
				Element placeOriginX = doc.createElement(Constants.ORIGIN_X_OF_TRANSITION);
				placeOriginX.appendChild(doc.createTextNode(vector.elementAt(1).toString()));
				place.appendChild(placeOriginX);
				
				Element placeOriginY = doc.createElement(Constants.ORIGIN_Y_OF_TRANSITION);
				placeOriginY.appendChild(doc.createTextNode(vector.elementAt(2).toString()));
				place.appendChild(placeOriginY);
				
			
			}
		}
		
	}
	public void createArcs(Document doc, Element arcs) {
		Vector<Object> arcVector = new Vector<Object>();
		for(Arc arc : petrinet.arcVector) {
			Vector<String>arcVector1 = new Vector<String>();
			arcVector1.addElement(arc.getDirectionType().toString());
			
			Place place = arc.getPlace();
			arcVector1.addElement(place.getName());
			Transition trans = arc.getTransition();
			arcVector1.addElement(trans.getName());

			String s = new String("");
			for(int i=0; i<arc.getPointVector().size(); i++) {
				Point p = (Point)arc.getPointVector().get(i);
				int x = p.x;
				int y = p.y;
				s = (s.concat(Integer.toString(x) + "," + Integer.toString(y)).concat(";"));
			}
			System.out.println("Place name : "+place.getName() + " " + arc.getDirectionType().toString() + " " + trans.getName() + " " + s);

			arcVector1.addElement(s);

			arcVector.addElement(arcVector1);
		}
		if(arcVector.size() > 0) {
			int count = arcVector.size();
			
			for (int i=0; i<count; i++) {
				Element arc = doc.createElement(Constants.ARC);
				arcs.appendChild(arc);
				arc.setAttribute("id", Integer.toString(i+1));						
				
				Vector<Object> vector = new Vector<Object>();
				vector = (Vector<Object>) arcVector.elementAt(i);				
				
				Element arcDirectionType = doc.createElement(Constants.ARC_DIRECTION_TYPE);
				arcDirectionType.appendChild(doc.createTextNode(vector.elementAt(0).toString()));
				arc.appendChild(arcDirectionType);
								
				Element arcPlaceName = doc.createElement(Constants.ARC_PLACE_NAME);
				arcPlaceName.appendChild(doc.createTextNode(vector.elementAt(1).toString()));
				arc.appendChild(arcPlaceName);
				
				Element arcTransitionName = doc.createElement(Constants.ARC_TRANSITION_NAME);
				arcTransitionName.appendChild(doc.createTextNode(vector.elementAt(2).toString()));
				arc.appendChild(arcTransitionName);
				
				Element arcPointsString = doc.createElement(Constants.ARC_POINTS);
				arcPointsString.appendChild(doc.createTextNode(vector.elementAt(3).toString()));
				arc.appendChild(arcPointsString);
				
			}
		}
	}
}