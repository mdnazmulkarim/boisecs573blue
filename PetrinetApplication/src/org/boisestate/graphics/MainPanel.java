package org.boisestate.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import org.boisestate.petrinet.Arc;
import org.boisestate.petrinet.Petrinet;
import org.boisestate.petrinet.Place;
import org.boisestate.petrinet.Transition;
 
enum State { PLACE, TRANSITION, ARC, NOTHING, COPY, PASTE, DELETE, UNDO, REDO, SIMULATING,PLAY,DRAWING }

public class MainPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JMenuBar menuBar;
	protected JMenuItem newAction, openAction, saveAction, exitAction, deleteAction, copyAction, pasteAction,
			undoAction, redoAction;
	protected JButton placeButton, transitionButton, arcButton, arrowButton, simulateButton, playButton,
			activeFiringStates;
	protected DrawingPanel drawingPanel;
	public static State currentState;
	public Petrinet petrinet;
	public PetriNetSaver savePetrinet;
	public static ArrayList<String> placeCoordinator = new ArrayList<String>();
	public static ArrayList<String> transitionCoordinator = new ArrayList<String>();
	public static JComboBox activeFiringStatesList = new JComboBox();
	public static String petriNetName = "Untitled document";
	int width, height;
	JFrame thisFrame = null;

	public MainPanel() {
		this.setTitle(petriNetName);
		petrinet = new Petrinet("Vending Machine");
		savePetrinet = new PetriNetSaver(petrinet);
		// get the screen size as a java dimension
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		thisFrame = this;
		height = screenSize.height * 3 / 4;
		width = screenSize.width * 5 / 6;

		setSize(width, height);
		this.getContentPane().setLayout(new FlowLayout());

		JPanel panel = new JPanel();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		placeButton = new JButton("Place");
		placeButton.setVisible(true);
		add(placeButton);

		transitionButton = new JButton("Transition");
		transitionButton.setVisible(true);
		add(transitionButton);

		arcButton = new JButton("Arc");
		arcButton.setVisible(true);
		add(arcButton);

		arrowButton = new JButton("Arrow");
		arrowButton.setVisible(true);
		add(arrowButton);

		simulateButton = new JButton("Simulation Mode");
		simulateButton.setVisible(true);
		add(simulateButton);

		playButton = new JButton("Play");
		playButton.setVisible(true);
		add(playButton);

		add(activeFiringStatesList);

		createFileMenu();
		createEditMenu();
		createActions();

		drawingPanel = new DrawingPanel(petrinet);
		drawingPanel.setPreferredSize(new Dimension(width, height));
		drawingPanel.setBackground(Color.white);
		this.getContentPane().add(drawingPanel, BorderLayout.NORTH);
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		petrinet.getPetrinetBuilder().setDrawingPanel(drawingPanel);
	}

	public int alertDialog() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to close current petrinet?", "Warning", 2);
		if (dialogResult == JOptionPane.YES_OPTION) {
			// Saving code here }

		}
		return dialogResult;
	}

	public void fileChoose() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			petrinet.removeAllPlace();
			petrinet.removeAllTransition();
			petrinet.removeAllArcs();
			XMLParser parser = new XMLParser(selectedFile, petrinet);
			drawingPanel.paintAgain();

			thisFrame.setTitle(selectedFile.getName());
			thisFrame.validate();
		}
	}

	public void redoActionForPlace(Object obj, Object actionObj) {
		Place objP = (Place) obj;

		if (actionObj.equals("A")) {
			for (Place place : petrinet.placeVector) {
				if (place.getX() == objP.getX() && place.getY() == objP.getY()) {
					petrinet.placeVector.remove(place);

					drawingPanel.paintAgain();
					petrinet.getPetrinetBuilder().putElementInActionArrayList("D");
					petrinet.getPetrinetBuilder().putElementInWorkingArrayList(objP.clone());
					break;

				}
			}

		} else if (actionObj.equals("D")) {
			petrinet.placeVector.add(objP);

			drawingPanel.paintAgain();
			petrinet.getPetrinetBuilder().putElementInActionArrayList("A");
			petrinet.getPetrinetBuilder().putElementInWorkingArrayList(objP.clone());
		} else {

			for (Place place : petrinet.placeVector) {
				if (place.getX() == objP.getX() && place.getY() == objP.getY()) {
					petrinet.getPetrinetBuilder().putElementInWorkingArrayList(place.clone());

					place.setName(objP.getName());
					place.setTokenNumbers(objP.getTokenNumbers());

					drawingPanel.paintAgain();

					break;
				}
			}

			petrinet.getPetrinetBuilder().putElementInActionArrayList("M");

		}

		petrinet.getPetrinetBuilder().removeElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().removeElementFromRedoArrayList();
	}

	public void redoActionForTransition(Object obj, Object actionObj) {
		Transition objP = (Transition) obj;

		if (actionObj.equals("A")) {
			for (Transition trans : petrinet.transitionVector) {
				if (trans.getX() == objP.getX() && trans.getY() == objP.getY()) {
					petrinet.transitionVector.remove(trans);
					drawingPanel.paintAgain();
					petrinet.getPetrinetBuilder().putElementInActionArrayList("D");
					petrinet.getPetrinetBuilder().putElementInWorkingArrayList(objP.clone());
					break;
				}
			}

		} else if (actionObj.equals("D")) {
			petrinet.transitionVector.add(objP);

			drawingPanel.paintAgain();
			petrinet.getPetrinetBuilder().putElementInActionArrayList("A");
			petrinet.getPetrinetBuilder().putElementInWorkingArrayList(objP.clone());
		} else {

			for (Transition trans : petrinet.transitionVector) {
				if (trans.getX() == objP.getX() && trans.getY() == objP.getY()) {
					petrinet.getPetrinetBuilder().putElementInWorkingArrayList(trans.clone());

					trans.setName(objP.getName());

					drawingPanel.paintAgain();

					break;
				}
			}

			petrinet.getPetrinetBuilder().putElementInActionArrayList("M");

		}

		petrinet.getPetrinetBuilder().removeElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().removeElementFromRedoArrayList();
	}

	public void redoActionForArc(Object obj, Object actionObj) {
		Arc objP = (Arc) obj;

		if (actionObj.equals("A")) {
			for (Arc arc : petrinet.arcVector) {
				if (arc.getDirectionType().equals(objP.getDirectionType()) && arc.getPlace().equals(objP.getPlace())
						&& arc.getTransition().equals(objP.getTransition())) {
					petrinet.arcVector.remove(arc);
					// remove arc from arcDetectionMap
					Transition trans = arc.getTransition();
					trans.getArcList().remove(arc);
					petrinet.getPetrinetBuilder().removeFromArcDetectionMap(arc);

					drawingPanel.paintAgain();
					petrinet.getPetrinetBuilder().putElementInActionArrayList("D");
					petrinet.getPetrinetBuilder().putElementInWorkingArrayList(objP.clone());
					break;
				}
			}
		} else if (actionObj.equals("D")) {
			petrinet.arcVector.add(objP);
			Transition trans = objP.getTransition();
			trans.getArcList().add(objP);
			//// add arc from arcDetectionMap
			petrinet.getPetrinetBuilder().createPolygonMapForArc(objP);

			drawingPanel.paintAgain();
			petrinet.getPetrinetBuilder().putElementInActionArrayList("A");
			petrinet.getPetrinetBuilder().putElementInWorkingArrayList(objP.clone());
		}
		petrinet.getPetrinetBuilder().removeElementFromRedoActionArrayList();
		petrinet.getPetrinetBuilder().removeElementFromRedoArrayList();
	}

	public void undoActionsForPlace(Object obj, Object actionObj) {
		Place objP = (Place) obj;

		if (actionObj.equals("A")) {
			for (Place place : petrinet.placeVector) {
				if (place.getX() == objP.getX() && place.getY() == objP.getY()) {
					petrinet.placeVector.remove(place);
					drawingPanel.paintAgain();
					petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("D");
					petrinet.getPetrinetBuilder().putElementInRedoArrayList(place.clone());
					break;
				}
			}
		} else if (actionObj.equals("D")) {
			petrinet.placeVector.add(objP);
			drawingPanel.paintAgain();
			petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("A");
			petrinet.getPetrinetBuilder().putElementInRedoArrayList(objP.clone());
		} else if (actionObj.equals("M")) {
			for (Place place : petrinet.placeVector) {
				if (place.getX() == objP.getX() && place.getY() == objP.getY()) {
					petrinet.getPetrinetBuilder().putElementInRedoArrayList(place.clone());
					place.setName(objP.getName());
					place.setTokenNumbers(objP.getTokenNumbers());
					drawingPanel.paintAgain();
					break;
				}
			}
			petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("M");
			drawingPanel.paintAgain();
		}

		petrinet.getPetrinetBuilder().removeElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().removeElementFromActionArrayList();
	}

	public void undoActionsForTransition(Object obj, Object actionObj) {
		Transition objP = (Transition) obj;

		if (actionObj.equals("A")) {
			for (Transition trans : petrinet.transitionVector) {
				if (trans.getX() == objP.getX() && trans.getY() == objP.getY()) {
					petrinet.transitionVector.remove(trans);
					drawingPanel.paintAgain();
					petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("D");
					petrinet.getPetrinetBuilder().putElementInRedoArrayList(trans.clone());
					break;
				}
			}
		} else if (actionObj.equals("D")) {
			petrinet.transitionVector.add(objP);
			drawingPanel.paintAgain();
			petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("A");
			petrinet.getPetrinetBuilder().putElementInRedoArrayList(objP.clone());
		} else {
			for (Transition trans : petrinet.transitionVector) {
				if (trans.getX() == objP.getX() && trans.getY() == objP.getY()) {
					petrinet.getPetrinetBuilder().putElementInRedoArrayList(trans.clone());
					trans.setName(objP.getName());
					drawingPanel.paintAgain();
					break;
				}
			}
			petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("M");
			drawingPanel.paintAgain();
		}

		petrinet.getPetrinetBuilder().removeElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().removeElementFromActionArrayList();
	}

	public void undoActionsForArc(Object obj, Object actionObj) {
		Arc objP = (Arc) obj;

		if (actionObj.equals("A")) {
			for (Arc arc : petrinet.arcVector) {
				if (arc.getDirectionType().equals(objP.getDirectionType()) && arc.getPlace().equals(objP.getPlace())
						&& arc.getTransition().equals(objP.getTransition())) {
					petrinet.arcVector.remove(arc);
					// remove arc from arcDetectionMap
					Transition trans = arc.getTransition();
					trans.getArcList().remove(arc);

					petrinet.getPetrinetBuilder().removeFromArcDetectionMap(arc);

					drawingPanel.paintAgain();
					petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("D");
					petrinet.getPetrinetBuilder().putElementInRedoArrayList(arc.clone());
					break;
				}
			}
		} else if (actionObj.equals("D")) {
			petrinet.arcVector.add(objP);
			//// add arc from arcDetectionMap
			Transition trans = objP.getTransition();
			trans.getArcList().add(objP);

			petrinet.getPetrinetBuilder().createPolygonMapForArc(objP);

			drawingPanel.paintAgain();
			petrinet.getPetrinetBuilder().putElementInRedoActionArrayList("A");
			petrinet.getPetrinetBuilder().putElementInRedoArrayList(objP.clone());
		}

		petrinet.getPetrinetBuilder().removeElementFromWorkingArrayList();
		petrinet.getPetrinetBuilder().removeElementFromActionArrayList();
	}

	public void undoAction() {
		Object obj = petrinet.getPetrinetBuilder().getElementFromWorkingArrayList();
		Object actionObj = petrinet.getPetrinetBuilder().getElementFromActionArrayList();

		if (obj != null) {
			if (obj instanceof Place) {
				undoActionsForPlace(obj, actionObj);
			} else if (obj instanceof Transition) {
				undoActionsForTransition(obj, actionObj);
			} else {
				undoActionsForArc(obj, actionObj);
			}
		}
		System.out.println("PRINTING AFTER UNDO");
		petrinet.getPetrinetBuilder().printLists();

	}

	public void redoAction() {
		Object obj = petrinet.getPetrinetBuilder().getElementFromRedoArrayList();
		Object actionObj = petrinet.getPetrinetBuilder().getElementFromRedoActionArrayList();

		if (obj != null) {
			if (obj instanceof Place) {
				redoActionForPlace(obj, actionObj);
			} else if (obj instanceof Transition) {
				redoActionForTransition(obj, actionObj);
			} else {
				redoActionForArc(obj, actionObj);
			}
		}

	}

	private void createActions() {
		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					if (alertDialog() == JOptionPane.YES_OPTION) {
						petrinet.removeAllPlace();
						petrinet.removeAllTransition();
						petrinet.removeAllArcs();
						drawingPanel.paintAgain();
					}
				}

			}
		});
		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					if (alertDialog() == JOptionPane.YES_OPTION) {
						fileChoose();
					}
				}
			}
		});
		saveAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {

					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.showSaveDialog(null);

					String path = chooser.getSelectedFile().getAbsolutePath();
					String filename = chooser.getSelectedFile().getName();

					if (filename != null && !(filename.isEmpty())) {
						savePetrinet.xmlFileName(path);
						thisFrame.setTitle(filename);
						thisFrame.validate();
					}

				}
			}
		});
		deleteAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					currentState = currentState.DELETE;
				}
			}
		});
		copyAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					currentState = currentState.COPY;
				}
			}
		});
		pasteAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					currentState = currentState.PASTE;
				}
			}
		});
		undoAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					if (petrinet.getPetrinetBuilder().workingArrayList.size() > 0) {
						undoAction();
					}
				}

			}
		});
		redoAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					if (petrinet.getPetrinetBuilder().redoArrayList.size() > 0) {
						redoAction();
					}
				}

			}
		});
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		placeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					currentState = currentState.PLACE;
				}
			}
		});
		transitionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					currentState = currentState.TRANSITION;
				}
			}
		});
		arcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					currentState = currentState.ARC;
				}
			}
		});
		arrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isSimulationModeOn()) {
					currentState = currentState.NOTHING;
				}
			}
		});
		simulateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (simulateButton.getText().equals("Simulation Mode")) {
					currentState = currentState.SIMULATING;
					petrinet.setFirableComboList();
					toggleSimulateButton("Drawing Mode");

					petrinet.saveCurrentMarking();

					drawingPanel.paintAgain();

				} else {
					petrinet.restorePreviousMarking();
					currentState = currentState.DRAWING;
					toggleSimulateButton("Simulation Mode");
					petrinet.currentFirableTransitionList.clear();

					for (Transition trans : petrinet.transitionVector) {
						trans.setFillColor(Color.GRAY);
					}
					drawingPanel.paintAgain();
				}

			}
		});
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// currentState = currentState.PLAY;
				petrinet.playTransition();
				petrinet.setFirableComboList();
				drawingPanel.paintAgain();

			}
		});

	}

	public Boolean isSimulationModeOn() {
		Boolean isSimulationModeOn = false;
		if (currentState == currentState.SIMULATING) {
			isSimulationModeOn = true;
			JOptionPane.showConfirmDialog(null, "Disable the simulation mode at first.", "Warning", 2);
		}
		return isSimulationModeOn;
	}

	public void toggleSimulateButton(String str) {
		simulateButton.setText(str);
	}

	protected JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		newAction = new JMenuItem("New");
		openAction = new JMenuItem("Open");
		saveAction = new JMenuItem("Save As");
		exitAction = new JMenuItem("Exit");

		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
		return fileMenu;
	}

	protected JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);

		deleteAction = new JMenuItem("Delete");
		copyAction = new JMenuItem("Copy");
		pasteAction = new JMenuItem("Paste");
		undoAction = new JMenuItem("Undo");
		redoAction = new JMenuItem("Redo");

		editMenu.add(deleteAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		editMenu.add(undoAction);
		editMenu.add(redoAction);
		return editMenu;
	}

}
