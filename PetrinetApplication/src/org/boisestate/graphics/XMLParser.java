package org.boisestate.graphics;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

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
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	  }
}
