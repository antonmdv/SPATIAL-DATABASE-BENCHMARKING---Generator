import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;
import javax.swing.text.*;

public class MidPointDisplacementGeneratorView implements PropertyChangeListener, ItemListener {
	
	private static final boolean TRACE = false;
	  
	   private static final String TAB_TITLE = "Midpoint Displacement";
	   private static final String TAB_TOOLTIP = "Midpoint Displacement generator options";
	   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
	   private static final boolean DEFAULT_GENERATE_FLAG = true;
	   private static final int DEFAULT_RW_COUNT = 20;
	   private static final int DEFAULT_STEP_LENGTH = 10;
	   private static final int DEFAULT_NS_COUNT = 10;
	   
	   //***
	   // instance variables
	   //***

	   // parent panel for all elements
	   private JPanel theTabbedPanePanel;

	   // elements for 'generate' flag
	   private JCheckBox theGenerateCheckbox;
	   private JPanel theGeneratePanel;

	   //Number of elements
	   // elements for 'Midpoint Displacement'
	   private NumberFormat theNumberOfMidpointDisplacementFormat; 		//theNumberOfSquaresFormat
	   private JPanel theNumberOfMidpointDisplacementPanel;				//theNumberOfSquaresPanel
	   private JLabel theNumberOfMidpointDisplacementLabel;				//theNumberOfSquaresLabel
	   private JFormattedTextField theNumberOfMidpointDisplacementsField;	//theNumberOfSquaresField

	   //MinimumNumber
	   // elements for 'Min # of vert'
	   private NumberFormat theMinimumNumberOfStepsFormat;
	   private JPanel theMinimumNumberOfStepsPanel;
	   private JLabel theMinimumNumberOfStepsLabel;
	   private JFormattedTextField theMinimumNumberOfStepsField;
	   
	   //MaximumNumber
	   // elements for 'max # of vert'
	   private NumberFormat theMaximumNumberOfStepsFormat;
	   private JPanel theMaximumNumberOfStepsPanel;
	   private JLabel theMaximumNumberOfStepsLabel;
	   private JFormattedTextField theMaximumNumberOfStepsField;

	   // property values
	   private boolean theGenerateFlag;
	   private int theNumberOfMidPointDisplacement;
	   private int theMinimumNumberOfSteps;
	   private int theMaximumNumberOfSteps;
	   
	   /**
	    * MidpointDisplacementView
	    *
	    * This class implements the view for the Midpoint Displacement class and
	    * handles related user interface events
	    */
	   MidPointDisplacementGeneratorView()
	   {
	      theGenerateFlag = DEFAULT_GENERATE_FLAG;
	      theNumberOfMidPointDisplacement = DEFAULT_RW_COUNT;
	      theMinimumNumberOfSteps = DEFAULT_STEP_LENGTH;
	      theMaximumNumberOfSteps = DEFAULT_NS_COUNT;
	   }

	   /**
	    * setGenerateFlag
	    *
	    * This method sets the generate datfile property
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

	   /**
	    * setNumberOfAlgorithms
	    * 
	    * This method sets the current number of algorithms produced
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

	   /**
	    * setMinimumStep Length
	    * 
	    * This method sets the current maximum side length
	    */
	   
	   public void setMinimumNumberOfSteps(int aLength)
	   {
		   theMinimumNumberOfSteps = aLength;
	      if (theMinimumNumberOfStepsField != null)
	      {
	    	  theMinimumNumberOfStepsField.setValue(theMinimumNumberOfStepsField);
	    	  theMinimumNumberOfStepsField.updateUI();
	      }
	   }
	   
	   /**
	    * setMaximumNumber of Steps 
	    * 
	    * This method sets the current number of steps
	    */
	   
	   public void setMaximumNumberOfSteps(int aLength)
	   {
		   theMaximumNumberOfSteps = aLength;
	      if (theMaximumNumberOfStepsField != null)
	      {
	    	  theMaximumNumberOfStepsField.setValue(theMaximumNumberOfStepsField);
	    	  theMaximumNumberOfStepsField.updateUI();
	      }
	   }

	  /**
	   * getGenerateFlag
	   *
	   * This method returns the generate datfile property
	   */
	   public boolean getGenerateFlag()
	   {
	      return theGenerateFlag;
	   }

	   /**
	   * getNumberOfAlgorithms
	   *
	   * This method returns the current number of paths generated
	   */
	   public int getNumberOfMidPointDisplacement()
	   {
	      return theNumberOfMidPointDisplacement;
	   }

	   /**
	    * getMaximumStepLength
	    * 
	    * This method returns the current step length
	    */
	   public int getMinimumStepLength()
	   {
	      return theMinimumNumberOfSteps;
	   }
	   
	   /**
	    * getNumberOfSteps
	    * 
	    * This method returns the current number of steps
	    */
	   public int getMaximumStepLength()
	   {
	      return theMaximumNumberOfSteps;
	   }
	   
	   /**
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
	      // number of algorithms 
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
	      // minimum # of vert
	      //***
	      
	      //build format argument
	      theMinimumNumberOfStepsFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theMinimumNumberOfStepsLabel = new JLabel("Minimum number of vert: ");
	      theMinimumNumberOfStepsLabel.setHorizontalAlignment(JLabel.LEFT);
	      theMinimumNumberOfStepsField = new JFormattedTextField(theMinimumNumberOfStepsFormat);
	      
	      theMinimumNumberOfStepsField.setValue(new Double(theMinimumNumberOfSteps));
	      theMinimumNumberOfStepsField.setColumns(10);
	      theMinimumNumberOfStepsField.addPropertyChangeListener("value", this);

	      theMinimumNumberOfStepsPanel = new JPanel();
	      theMinimumNumberOfStepsPanel.add(theMinimumNumberOfStepsLabel);
	      theMinimumNumberOfStepsPanel.add(theMinimumNumberOfStepsField);
	      
	      
	      //***
	      // max # of vert
	      //***

	      //build format argument
	      theMaximumNumberOfStepsFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theMaximumNumberOfStepsLabel = new JLabel("Maximum number of vert: ");
	      theMaximumNumberOfStepsLabel.setHorizontalAlignment(JLabel.LEFT);
	      theMaximumNumberOfStepsField = new JFormattedTextField(theMaximumNumberOfStepsFormat);
	      
	      theMaximumNumberOfStepsField.setValue(new Double(theMaximumNumberOfSteps));
	      theMaximumNumberOfStepsField.setColumns(10);
	      theMaximumNumberOfStepsField.addPropertyChangeListener("value", this);

	      theMaximumNumberOfStepsPanel = new JPanel();
	      theMaximumNumberOfStepsPanel.add(theMaximumNumberOfStepsLabel);
	      theMaximumNumberOfStepsPanel.add(theMaximumNumberOfStepsField);
	      
	      
	      // build tab
	      theTabbedPanePanel = new JPanel();
	      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
	      theTabbedPanePanel.add(theGeneratePanel);
	      theTabbedPanePanel.add(theNumberOfMidpointDisplacementPanel);
	      theTabbedPanePanel.add(theMinimumNumberOfStepsPanel);
	      theTabbedPanePanel.add(theMaximumNumberOfStepsPanel);
	      
	      
	      // add new tab to tabbed pane
	      aTabbedPane.addTab(TAB_TITLE, null, theTabbedPanePanel, TAB_TOOLTIP);
	   }

	   /** 
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
	      else if (source == theMinimumNumberOfStepsField)
	      {
	    	  theMinimumNumberOfSteps = ((Number)theMinimumNumberOfStepsField.getValue()).intValue();
	         if (TRACE)
	            System.out.println("Random Walk: maximum step length = " + theMinimumNumberOfSteps);
	      }
	      else if (source == theMaximumNumberOfStepsField)
	      {
	    	  theMaximumNumberOfSteps = ((Number)theMaximumNumberOfStepsField.getValue()).intValue();
	    	  if (TRACE)
	    		  System.out.println("Random Walk: number of steps = " + theMaximumNumberOfSteps);
	      }
	   }
	   
	   /**
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
	        	 theMinimumNumberOfStepsField.setEnabled(true);
	        	 theMaximumNumberOfStepsField.setEnabled(true);
	         }
	         else
	         {
	        	 theNumberOfMidpointDisplacementsField.setEnabled(false);
	        	 theMinimumNumberOfStepsField.setEnabled(false);
	        	 theMaximumNumberOfStepsField.setEnabled(false);
	         }
	         if (TRACE)
	            System.out.println("Midpoint Displacement: generate = " + theGenerateFlag);
	      }
	   }
}
