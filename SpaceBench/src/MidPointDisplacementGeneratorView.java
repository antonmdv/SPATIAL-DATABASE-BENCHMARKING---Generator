/*
 * MidPointDisplacementGeneratorView.java
 *
 * Original Author: 
 * Version Date: 
 * 
 * This file has to do with building a panel for the Midpoint Displacement generator
 */

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;
import javax.swing.text.*;

public class MidPointDisplacementGeneratorView implements PropertyChangeListener, ItemListener {
	
	   //***
	   // class variables
	   //***
	
	   private static final boolean TRACE = false;
	  
	   private static final String TAB_TITLE = "Midpoint Displacement";
	   private static final String TAB_TOOLTIP = "Midpoint Displacement generator options";
	   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
	   private static final boolean DEFAULT_GENERATE_FLAG = true;

	   //Default displays
	   private static final int DEFAULT_COUNT = 20;
	   private static final int DEFAULT_REC_DEPTH = 10;
	   private static final double DEFAULT_DISP_BOUND = 5.8;
	   private static final double	DEFAULT_DISP_BOUND_REDUCTION = 0.7;
	   
	   //***
	   // instance variables
	   //***

	   // parent panel for all elements
	   private JPanel theTabbedPanePanel;

	   // elements for 'generate' flag
	   private JCheckBox theGenerateCheckbox;
	   private JPanel theGeneratePanel;

	  
	   //theNumberOfMidPointDisplacement
	   // elements for Number of Line Strings produced
	   private NumberFormat theNumberOfMidpointDisplacementFormat; 		
	   private JPanel theNumberOfMidpointDisplacementPanel;				
	   private JLabel theNumberOfMidpointDisplacementLabel;				
	   private JFormattedTextField theNumberOfMidpointDisplacementsField;	

	   
	   //theRecursionDepth
	   // elements for recursion depth
	   private NumberFormat theRecursionDepthFormat;
	   private JPanel theRecursionDepthPanel;
	   private JLabel theRecursionDepthLabel;
	   private JFormattedTextField theRecursionDepthField;
	   
	   
	   //theDisplacementBound
	   // elements for Y displacement bound
	   private NumberFormat theDisplacementBoundFormat;
	   private JPanel theDisplacementBoundPanel;
	   private JLabel theDisplacementBoundLabel;
	   private JFormattedTextField theDisplacementBoundField;

	   //theDisplacementBoundReduction
	   // elements for displacement bound reduction value
	   private NumberFormat theDisplacementBoundReductionFormat;
	   private JPanel theDisplacementBoundReductionPanel;
	   private JLabel theDisplacementBoundReductionLabel;
	   private JFormattedTextField theDisplacementBoundReductionField;
	   
	   // property values
	   private boolean theGenerateFlag;
	   private int theNumberOfMidPointDisplacement;
	   private int theRecursionDepth;
	   private double theDisplacementBound;
	   private double theDisplacementBoundReduction;
	
	   
	   /*
	    * MidpointDisplacementView
	    *
	    * This class implements the view for the Midpoint Displacement class and
	    * handles related user interface events
	    */
	   
	   MidPointDisplacementGeneratorView()
	   {
	      theGenerateFlag = DEFAULT_GENERATE_FLAG;
	      theNumberOfMidPointDisplacement = DEFAULT_COUNT;
	      theRecursionDepth = DEFAULT_REC_DEPTH;
	      theDisplacementBound = DEFAULT_DISP_BOUND;
	      theDisplacementBoundReduction = DEFAULT_DISP_BOUND_REDUCTION;
	   }

	   /*
	    * setGenerateFlag
	    *
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
	    * setNumberOfMidPointDisplacements
	    * 
	    * This method sets the current number of line strings produced
	    */
	   
	   public void setNumberOfMidPointDisplacements(int aCount)
	   {
		   theNumberOfMidPointDisplacement = aCount;
	      if (theNumberOfMidpointDisplacementsField != null)
	      {
	    	  theNumberOfMidpointDisplacementsField.setValue(theNumberOfMidpointDisplacementsField);
	    	  theNumberOfMidpointDisplacementsField.updateUI();
	      }
	   }

	   /*
	    * setRecursionDepth 
	    * 
	    * This method sets how many times each line string will be broken down
	    */
	   
	   public void setRecursionDepth(int aLength)
	   {
		   theRecursionDepth = aLength;
	      if (theRecursionDepthField != null)
	      {
	    	  theRecursionDepthField.setValue(theRecursionDepthField);
	    	  theRecursionDepthField.updateUI();
	      }
	   }
	   
	   /*
	    * setDisplacementBound 
	    * 
	    * This method sets the displacement bound value
	    */
	   
	   public void setDisplacementBound(double aLength)
	   {
		   theDisplacementBound = aLength;
	      if (theDisplacementBoundField != null)
	      {
	    	  theDisplacementBoundField.setValue(theDisplacementBoundField);
	    	  theDisplacementBoundField.updateUI();
	      }
	   }
	   
	   /*
	    * theDisplacementBoundReduction 
	    * 
	    * This method sets the displacement bound reduction value
	    */
	   
	   public void setDisplacementBoundReduction(double aLength)
	   {
		   theDisplacementBoundReduction = aLength;
	      if (theDisplacementBoundReductionField != null)
	      {
	    	  theDisplacementBoundReductionField.setValue(theDisplacementBoundReductionField);
	    	  theDisplacementBoundReductionField.updateUI();
	      }
	   }
	   

	  /*
	   * getGenerateFlag
	   *
	   * This method returns the generate datafile property
	   */
	   
	   public boolean getGenerateFlag()
	   {
	      return theGenerateFlag;
	   }

	  /*
	   * getNumberOfMidPointDisplacement
	   *
	   * This method returns the current number of line strings generated
	   */
	   
	   public int getNumberOfMidPointDisplacement()
	   {
	      return theNumberOfMidPointDisplacement;
	   }

	   /*
	    * getRecursionDepth
	    * 
	    * This method returns depth of the recursion
	    */
	   
	   public int getRecursionDepth()
	   {
	      return theRecursionDepth;
	   }
	   
	   /*
	    * getDisplacementBound
	    * 
	    * This method returns the current displacement bound value
	    */
	   
	   public double getDisplacementBound()
	   {
	      return theDisplacementBound;
	   }
	   
	   /*
	    * getDisplacementBoundReduction
	    * 
	    * This method returns the current displacement bound reduction value
	    */
	   
	   public double getDisplacementBoundReduction()
	   {
	      return theDisplacementBoundReduction;
	   }
	   
	   /*
	    * build
	    * 
	    * This method builds the user interface and ties in any
	    * event listeners
	    */
	   
	   public void build(JTabbedPane aTabbedPane)
	   {
	      //***
	      // generate flag
	      //***

	      // create generate flag [checkbox]
	      theGenerateCheckbox = new JCheckBox(GENERATE_CHECKBOX_TITLE);
	      theGenerateCheckbox.setSelected(theGenerateFlag);
	      theGenerateCheckbox.addItemListener(this);

	      // add to containing panel
	      theGeneratePanel = new JPanel();
	      theGeneratePanel.add(theGenerateCheckbox);

	      //***
	      // number of Midpoint Displacements 
	      //***
	   
	      //build format argument
	      theNumberOfMidpointDisplacementFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theNumberOfMidpointDisplacementLabel = new JLabel("Number of Line Strings: ");
	      theNumberOfMidpointDisplacementLabel.setHorizontalAlignment(JLabel.LEFT);
	      theNumberOfMidpointDisplacementsField = new JFormattedTextField(theNumberOfMidpointDisplacementFormat);
	      
	      theNumberOfMidpointDisplacementsField.setValue(new Double(theNumberOfMidPointDisplacement));
	      theNumberOfMidpointDisplacementsField.setColumns(10);
	      theNumberOfMidpointDisplacementsField.addPropertyChangeListener("value", this);

	      theNumberOfMidpointDisplacementPanel = new JPanel();
	      theNumberOfMidpointDisplacementPanel.add(theNumberOfMidpointDisplacementLabel);
	      theNumberOfMidpointDisplacementPanel.add(theNumberOfMidpointDisplacementsField);
	      
	      //***
	      // Recursion Depth
	      //***
	      
	      //build format argument
	      theRecursionDepthFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theRecursionDepthLabel = new JLabel("Recursion Depth: ");
	      theRecursionDepthLabel.setHorizontalAlignment(JLabel.LEFT);
	      theRecursionDepthField = new JFormattedTextField(theRecursionDepthFormat);
	      
	      theRecursionDepthField.setValue(new Double(theRecursionDepth));
	      theRecursionDepthField.setColumns(10);
	      theRecursionDepthField.addPropertyChangeListener("value", this);

	      theRecursionDepthPanel = new JPanel();
	      theRecursionDepthPanel.add(theRecursionDepthLabel);
	      theRecursionDepthPanel.add(theRecursionDepthField);
	      
	      
	      //***
	      // Displacement Bound
	      //***

	      //build format argument
	      theDisplacementBoundFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theDisplacementBoundLabel = new JLabel("Displacement Bound: ");
	      theDisplacementBoundLabel.setHorizontalAlignment(JLabel.LEFT);
	      theDisplacementBoundField = new JFormattedTextField(theDisplacementBoundFormat);
	      
	      theDisplacementBoundField.setValue(new Double(theDisplacementBound));
	      theDisplacementBoundField.setColumns(10);
	      theDisplacementBoundField.addPropertyChangeListener("value", this);

	      theDisplacementBoundPanel = new JPanel();
	      theDisplacementBoundPanel.add(theDisplacementBoundLabel);
	      theDisplacementBoundPanel.add(theDisplacementBoundField);
	      
	      //***
	      // Displacement Bound Reduction
	      //***
	      
	      //build format argument
	      theDisplacementBoundReductionFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theDisplacementBoundReductionLabel = new JLabel("Displacement Bound Reduction: ");
	      theDisplacementBoundReductionLabel.setHorizontalAlignment(JLabel.LEFT);
	      theDisplacementBoundReductionField = new JFormattedTextField(theDisplacementBoundReductionFormat);
	      
	      theDisplacementBoundReductionField.setValue(new Double(theDisplacementBoundReduction));
	      theDisplacementBoundReductionField.setColumns(10);
	      theDisplacementBoundReductionField.addPropertyChangeListener("value", this);

	      theDisplacementBoundReductionPanel = new JPanel();
	      theDisplacementBoundReductionPanel.add(theDisplacementBoundReductionLabel);
	      theDisplacementBoundReductionPanel.add(theDisplacementBoundReductionField);
	      
	      
	      // build tab
	      theTabbedPanePanel = new JPanel();
	      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
	      theTabbedPanePanel.add(theGeneratePanel);
	      theTabbedPanePanel.add(theNumberOfMidpointDisplacementPanel);
	      theTabbedPanePanel.add(theRecursionDepthPanel);
	      theTabbedPanePanel.add(theDisplacementBoundPanel);
	      theTabbedPanePanel.add(theDisplacementBoundReductionPanel);
	      
	      
	      
	      // add new tab to tabbed pane
	      aTabbedPane.addTab(TAB_TITLE, null, theTabbedPanePanel, TAB_TOOLTIP);
	   }

	   /*
	    * propertyChange
	    * 
	    * Called when a field's "value" property changes
	    */
	   
	   public void propertyChange(PropertyChangeEvent e)
	   {
	      Object source = e.getSource();
	      
	      if (source == theNumberOfMidpointDisplacementsField)
	      {
	    	  theNumberOfMidPointDisplacement = ((Number)theNumberOfMidpointDisplacementsField.getValue()).intValue();
	         if (TRACE)
	            System.out.println("MidPoint Displacement: number of algorithms = " + theNumberOfMidPointDisplacement);
	      }
	      
	      else if (source == theRecursionDepthField)
	      {
	    	  theRecursionDepth = ((Number)theRecursionDepthField.getValue()).intValue();
	         if (TRACE)
	            System.out.println("MidPoint Displacement: the recursion depth = " + theRecursionDepth);
	      }
	      
	      else if (source == theDisplacementBoundField)
	      {
	    	  theDisplacementBound = ((Number)theDisplacementBoundField.getValue()).intValue();
	    	  if (TRACE)
	    		  System.out.println("Random Walk: the displacement bound = " + theDisplacementBound);
	      }
	      
	      else if(source == theDisplacementBoundReductionField)
	      {
	    	  theDisplacementBoundReduction = ((Number)theDisplacementBoundReductionField.getValue()).intValue();
	    	  if (TRACE)
	    		  System.out.println("Random Walk: the displacement bound reduction = " + theDisplacementBoundReduction);  
	      }
	   }
	   
	   /*
	    * itemStateChanged
	    *
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
	        	 theNumberOfMidpointDisplacementsField.setEnabled(true);
	        	 theRecursionDepthField.setEnabled(true);
	        	 theDisplacementBoundField.setEnabled(true);
	        	 theDisplacementBoundReductionField.setEnabled(true);
	         }
	         else
	         {
	        	 theNumberOfMidpointDisplacementsField.setEnabled(false);
	        	 theRecursionDepthField.setEnabled(false);
	        	 theDisplacementBoundField.setEnabled(false);
	        	 theDisplacementBoundReductionField.setEnabled(false);
	         }
	         if (TRACE)
	            System.out.println("Midpoint Displacement: generate = " + theGenerateFlag);
	      }
	   }
}
