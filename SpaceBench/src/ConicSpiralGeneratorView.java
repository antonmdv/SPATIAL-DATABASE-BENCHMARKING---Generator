/*
 * ConicSpiralGeneratorView
 * 
 * Authors: Hamad Altammami
 * Version Date: 02/19/2017
 * 
 * This file is for the GUI of the conic spiral generator
 */
import javax.swing.*;
//import java.io.*;
//import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;
//import javax.swing.text.*;

/*	#Input:
 * 	- number of spirals
 *	- maximum radius
 *	- angle gap
 */

public class ConicSpiralGeneratorView implements PropertyChangeListener, ItemListener{
	
	   private static final boolean TRACE = false;
	  
	   private static final String TAB_TITLE = "Conic Spiral";
	   private static final String TAB_TOOLTIP = "Conic Spiral generator options";
	   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
	   private static final boolean DEFAULT_GENERATE_FLAG = true;
	   private static final int DEFAULT_CS_COUNT = 20;
	   private static final double DEFAULT_RD_MAX = 100;
	   private static final double DEFAULT_AG = 0.25;
	   
	   //***
	   // instance variables
	   //***

	   // Parent panel for all elements
	   private JPanel theTabbedPanePanel;

	   // Elements for 'generate' flag
	   private JCheckBox theGenerateCheckbox;
	   private JPanel theGeneratePanel;

	   // Elements for 'number of Conic Spiral algorithms'
	   private NumberFormat theNumberOfConicSpiralFormat; 		//theNumberOfConicSpiralFormat
	   private JPanel theNumberOfConicSpiralPanel;				//theNumberOfConicSpiralPanel
	   private JLabel theNumberOfConicSpiralLabel;				//theNumberOfConicSpiralLabel
	   private JFormattedTextField theNumberOfConicSpiralField;	//theNumberOfConicSpiralField

	   // Elements for 'radius length'
	   private NumberFormat theMaximumRadiusLengthFormat;
	   private JPanel theMaximumRadiusLengthPanel;
	   private JLabel theMaximumRadiusLengthLabel;
	   private JFormattedTextField theMaximumRadiusLengthField;
	   
	   // Elements for 'number of vertices'
	   private NumberFormat theAngleGapFormat;
	   private JPanel theAngleGapPanel;
	   private JLabel theAngleGapLabel;
	   private JFormattedTextField theAngleGapField;

	   // Property values
	   private boolean theGenerateFlag;
	   private int theNumberOfConicSpirals;
	   private double theMaximumRadiusLength;
	   private double theAngleGap;
	   
	   /*
	    * ConicSpiralGeneratorView
	    *
	    * This class implements the view for the Conic Spiral class and
	    * handles related user interface events
	    */
	   
	   ConicSpiralGeneratorView()
	   {
	      theGenerateFlag = DEFAULT_GENERATE_FLAG;
	      theNumberOfConicSpirals = DEFAULT_CS_COUNT;
	      theMaximumRadiusLength = DEFAULT_RD_MAX;
	      theAngleGap = DEFAULT_AG;
	   }

	   /*
	    * setGenerateFlag
	    * This method sets the generate datafile property
	    */
	   
	   public void setGenerateFlag(boolean aFlag)
	   {
	      if (theGenerateCheckbox != null)
	      {
	         if (theGenerateFlag != aFlag)
	         {
	            theGenerateCheckbox.doClick();
	            theGenerateCheckbox.updateUI();
	         }
	      }
	   }
	   
	   /*
	    * setNumberOfAlgorithms
	    * This method sets the current number of spirals produced
	    */
	   
	   public void setNumberOfConicSpirals(int aCount)
	   {
		   theNumberOfConicSpirals = aCount;
	      if (theNumberOfConicSpiralField != null)
	      {
	    	  theNumberOfConicSpiralField.setValue(theNumberOfConicSpirals);
	    	  theNumberOfConicSpiralField.updateUI();
	      }
	   }
	   
	   /*
	    * setRadius Length
	    * This method sets the current maximum radius length
	    */
	   
	   public void setMaximumRadiusLength(double aLength)
	   {
		   theMaximumRadiusLength = aLength;
	      if (theMaximumRadiusLengthField != null)
	      {
	    	  theMaximumRadiusLengthField.setValue(theMaximumRadiusLengthField);
	    	  theMaximumRadiusLengthField.updateUI();
	      }
	   }
	   
	   /*
	    * setNumber of vertices 
	    * This method sets the current number of vertices to generate
	    */
	   
	   public void setAngleGap(double aGap)
	   {
		   theAngleGap = aGap;
	      if (theAngleGapField != null)
	      {
	    	  theAngleGapField.setValue(theAngleGapField);
	    	  theAngleGapField.updateUI();
	      }
	   }
	   
	   /*
	    * getGenerateFlag
		* This method returns the generate datfile property
		*/
	   
		public boolean getGenerateFlag()
		{
			return theGenerateFlag;
		}
		
		/*
		 * getNumberOfAlgorithms
		 * This method returns the current number of spirals to be generated
		 */
		
		   public int getNumberOfConicSpirals()
		   {
		      return theNumberOfConicSpirals;
		   }
		   
		   /*
		    * getMaximumRadiusLength
		    * This method returns the current Radius length
		    */
		   
		   public double getMaximumRadiusLength()
		   {
		      return theMaximumRadiusLength;
		   }
		   
		   /*
		    * getNumberOfVertices
		    * This method returns the current number of steps
		    */
		   
		   public double getAngleGap()
		   {
			   return theAngleGap;
		   }
		   /*
		    * build
		    * This method builds the user interface and ties in any
		    * event listeners
		    */
		   
		   public void build(JTabbedPane aTabbedPane)
		   {
			
			 //***
			 // Generate flag
			 //***
			   
			// Create generate flag [checkbox]
			theGenerateCheckbox = new JCheckBox(GENERATE_CHECKBOX_TITLE);
			theGenerateCheckbox.setSelected(theGenerateFlag);
			theGenerateCheckbox.addItemListener(this);

			// Add to containing panel
			theGeneratePanel = new JPanel();
			theGeneratePanel.add(theGenerateCheckbox);
			
			  //***
		      // Number of conic spirals
		      //***

		      // Build format arguments
			  theNumberOfConicSpiralFormat = NumberFormat.getIntegerInstance();

		      // Create number of point elements [label, field]
			  theNumberOfConicSpiralLabel = new JLabel("Number of Conic Spirals:");
			  theNumberOfConicSpiralLabel.setHorizontalAlignment(JLabel.LEFT);
		      theNumberOfConicSpiralField = new JFormattedTextField(theNumberOfConicSpiralFormat);
		      
		      theNumberOfConicSpiralField.setValue(new Double(theNumberOfConicSpirals));
		      theNumberOfConicSpiralField.setColumns(10);
		      theNumberOfConicSpiralField.addPropertyChangeListener("value", this);
		      

		      // Add to containing panel
		      theNumberOfConicSpiralPanel = new JPanel();
		      theNumberOfConicSpiralPanel.add(theNumberOfConicSpiralLabel);
		      theNumberOfConicSpiralPanel.add(theNumberOfConicSpiralField);
			  
		      
		      //***
		      // Radius length
		      //***
		      
		      // Build format arguments
		      theMaximumRadiusLengthFormat = NumberFormat.getNumberInstance();

		      // Create number of point elements [label, field]
		      theMaximumRadiusLengthLabel = new JLabel("Radius Length:");
		      theMaximumRadiusLengthLabel.setHorizontalAlignment(JLabel.LEFT);
		      theMaximumRadiusLengthField = new JFormattedTextField(theMaximumRadiusLengthFormat);
		      
		      theMaximumRadiusLengthField.setValue(new Double(theMaximumRadiusLength));
		      theMaximumRadiusLengthField.setColumns(10);
		      theMaximumRadiusLengthField.addPropertyChangeListener("value", this);
		      

		      // Add to containing panel
		      theMaximumRadiusLengthPanel = new JPanel();
		      theMaximumRadiusLengthPanel.add(theMaximumRadiusLengthLabel);
		      theMaximumRadiusLengthPanel.add(theMaximumRadiusLengthField);
		      
		      //***
		      // Number of vertices
		      //***
		      
		      // Build format arguments
		      theAngleGapFormat = NumberFormat.getIntegerInstance();

		      // Create number of point elements [label, field]
		      theAngleGapLabel = new JLabel("Angle Gap (from 0.1 to 1.99)");
		      theAngleGapLabel.setHorizontalAlignment(JLabel.LEFT);
		      theAngleGapField = new JFormattedTextField(theAngleGapFormat);
		      
		      theAngleGapField.setValue(new Double(theAngleGap));
		      theAngleGapField.setColumns(10);
		      theAngleGapField.addPropertyChangeListener("value", this);
		      
		      // Add to containing panel
		      theAngleGapPanel = new JPanel();
		      theAngleGapPanel.add(theAngleGapLabel);
		      theAngleGapPanel.add(theAngleGapField);
		      
		      // Build tab
		      theTabbedPanePanel = new JPanel();
		      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
		      theTabbedPanePanel.add(theGeneratePanel);
		      theTabbedPanePanel.add(theNumberOfConicSpiralPanel);
		      theTabbedPanePanel.add(theMaximumRadiusLengthPanel);
		      theTabbedPanePanel.add(theAngleGapPanel);
		      
		      
		      // Add new tab to tabbed pane
		      aTabbedPane.addTab(TAB_TITLE, null, theTabbedPanePanel, TAB_TOOLTIP);
			   
		   }
		   
		   /*
		    * propertyChange
		    * Called when a field's "value" property changes
		    */
		   
		   public void propertyChange(PropertyChangeEvent e)
		   {
		      Object source = e.getSource();
		      if (source == theNumberOfConicSpiralField)
		      {
		    	  theNumberOfConicSpirals = ((Number)theNumberOfConicSpiralField.getValue()).intValue();
		         if (TRACE)
		            System.out.println("Conic Spiral: number of algorithms = " + theNumberOfConicSpirals);
		      }
		      else if (source == theMaximumRadiusLengthField)
		      {
		    	  theMaximumRadiusLength = ((Number)theMaximumRadiusLengthField.getValue()).doubleValue();
		         if (TRACE)
		            System.out.println("Conic Spiral: radius increments = " + theMaximumRadiusLength);
		      }
		      else if (source == theAngleGapField)
		      {
		    	  theAngleGap = ((Number)theAngleGapField.getValue()).intValue();
		    	  if (TRACE)
		    		  System.out.println("Conic Spiral: angle gap = " + theAngleGap);
		      }
		   }
		   
		   /*
		    * itemStateChanged
		    * Called when a checkbox's state changes
		    */
		   
		   public void itemStateChanged(ItemEvent e)
		   {
		      Object source = e.getItemSelectable();
		      if (source == theGenerateCheckbox)
		      {
		         theGenerateFlag = !theGenerateFlag;
		         if (theGenerateFlag)
		         {
		        	 theNumberOfConicSpiralField.setEnabled(true);
		        	 theMaximumRadiusLengthField.setEnabled(true);
		        	 theAngleGapField.setEnabled(true);
		         }
		         else
		         {
		        	 theNumberOfConicSpiralField.setEnabled(false);
		        	 theMaximumRadiusLengthField.setEnabled(false);
		        	 theAngleGapField.setEnabled(false);
		         }
		         if (TRACE)
		            System.out.println("Random Walk: generate = " + theGenerateFlag);
		      }
		   }
	   
}
