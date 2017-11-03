package org.boisestate.graphics;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.boisestate.petrinet.Arc;
import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.Point;
import java.io.File;


public class XMLParser {
	Petrinet petrinet;
	  public XMLParser(File fXmlFile, Petrinet petrinet) {
		  this.petrinet = petrinet;
	    try {
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		    doc.getDocumentElement().normalize();

		    
		    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    
		   
		    NodeList nList = doc.getElementsByTagName(Constants.PLACE);
		    System.out.println("----------------------------");

		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            
		            Place place = new Place();
		            place.setName(eElement.getElementsByTagName(Constants.PLACE_NAME)
                            .item(0).getTextContent());
		            place.setTokenNumbers(Integer.parseInt(eElement.getElementsByTagName(Constants.NUMBER_OF_TOKENS_OF_PLACE)
		                                 .item(0).getTextContent()));
		            
		            place.setX(Integer.parseInt(eElement.getElementsByTagName(Constants.ORIGIN_X_OF_PLACE)
		                                 .item(0).getTextContent()));
		            place.setY(Integer.parseInt(eElement.getElementsByTagName(Constants.ORIGIN_Y_OF_PLACE)
                            .item(0).getTextContent()));		            
		            petrinet.placeVector.add(place);
		        }
		    }
		    
		    NodeList nList1 = doc.getElementsByTagName(Constants.TRANSITION);
		    System.out.println("----------------------------");

		    for (int temp = 0; temp < nList1.getLength(); temp++) {
		        Node nNode = nList1.item(temp);
		        System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            
		            Transition transition = new Transition();
		            transition.setName(eElement.getElementsByTagName(Constants.TRANSITION_NAME)
                            .item(0).getTextContent());		            
		            transition.setX(Integer.parseInt(eElement.getElementsByTagName(Constants.ORIGIN_X_OF_TRANSITION)
		                                 .item(0).getTextContent()));
		            transition.setY(Integer.parseInt(eElement.getElementsByTagName(Constants.ORIGIN_Y_OF_TRANSITION)
                            .item(0).getTextContent()));		            
		            petrinet.transitionVector.add(transition);  
		        }
		    }
		    
		    NodeList nList2 = doc.getElementsByTagName(Constants.ARC);
		    System.out.println("----------------------------");

		    for (int temp = 0; temp < nList2.getLength(); temp++) {
		        Node nNode = nList2.item(temp);
		        System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            
		            Arc arc = new Arc();
		            arc.setDirectionType(eElement.getElementsByTagName(Constants.ARC_DIRECTION_TYPE)
                            .item(0).getTextContent());	
		            
		            String placeName = eElement.getElementsByTagName(Constants.ARC_PLACE_NAME)
                            .item(0).getTextContent();
		            
		            for(Place place: petrinet.placeVector) {
		            	if(place.getName().equals(placeName)) {
		            		arc.setPlace(place);
		            		break;
		            	}
		            }
		            
		            String transitionName = eElement.getElementsByTagName(Constants.ARC_TRANSITION_NAME)
                            .item(0).getTextContent();
		            
		            for(Transition trans: petrinet.transitionVector) {
		            	if(trans.getName().equals(transitionName)) {
		            		arc.setTransition(trans);
		            		break;
		            	}
		            }
		            
		            String pointsString = eElement.getElementsByTagName(Constants.ARC_POINTS)
                            .item(0).getTextContent();
		            
		            String[] parts = pointsString.split(";");
		            for(int i=0; i<parts.length; i++) {
		            	String s = parts[i];
		            	String[] spS = s.split(",");
		            	Point p = new Point(Integer.parseInt(spS[0]),Integer.parseInt(spS[1]));
		            	arc.pointsVector.add(p);
		            }
		            
		            
		            petrinet.arcVector.add(arc);  
		        }
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	  }
}
