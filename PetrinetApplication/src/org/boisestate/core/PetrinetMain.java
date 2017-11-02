package org.boisestate.core;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.boisestate.graphics.MainPanel;


public class PetrinetMain {

	public static void main(String[] args) {
		System.out.println("This is the main program of petrinet");
		System.out.println("This is main class");
		
		test();
		
		MainPanel mainPanel = new MainPanel();
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setVisible(true);
		

		
	}
	
	public static void test() {
		 // create an empty array list
		   ArrayList<StringBuilder> arrlist1 = new ArrayList<StringBuilder>();

		   // use add for new value
		   arrlist1.add(new StringBuilder("Learning-"));

		   // using clone to affect the objects pointed to by the references.
		   ArrayList arrlist2 = (ArrayList) arrlist1.clone();

		   // appending the string
		   StringBuilder strbuilder = arrlist1.get(0);
		   strbuilder.append("list1, list2-both pointing to the same StringBuilder");

		   System.out.println("The 1st list prints: ");

		   // both lists will print the same value, printing list1
		   for (int i = 0; i < arrlist1.size(); i++) {
		   System.out.print(arrlist1.get(i) + " ");
		   }

		   arrlist1.clear();
		   System.out.println("The 2nd list prints the same i.e:");

		   // both lists will print the same value, printing list2
		   for (int i = 0; i < arrlist2.size(); i++) {
		   System.out.print(arrlist2.get(i));
		   }
	}

}
