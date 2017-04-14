/*
 * ConicSpiralGeneratorView
 * 
 * Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file is for the GUI of the conic spiral generator
 */
import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;

/*	#Input:
 * 	- number of spirals
 *	- maximum radius
 *	- base radius
 *	- angle gap range
 */

public class ConicSpiralGeneratorView implements PropertyChangeListener, ItemListener{
	
	   private static final boolean TRACE = false;
	  
	   private static final String TAB_TITLE = "Conic Spiral";
	   private static final String TAB_TOOLTIP = "Conic Spiral generator options";
	   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
	   private static final boolean DEFAULT_GENERATE_FLAG = true;
	   private static final int DEFAULT_CS_COUNT = 20;
	   private static final double DEFAULT_RD_MAX = 300;
	   private static final double DEFAULT_RD_INC = 15;
	   private static final double DEFAULT_BASE_RD = 40;
	   private static final double DEFAULT_AGL = 0.8;
	   private static final double DEFAULT_AGU = 1;
	   
	   //***
	   // instance variables
	   //***

	   // Parent panel for all elements
	   private JPanel theTabbedPanePanel;

	   // Elements for 'generate' flag
	   private JCheckBox theGenerateCheckbox;
	   private JPanel theGeneratePanel;

	   // Elements for 'number of Conic Spirals'
	   private NumberFormat theNumberOfConicSpiralFormat; 		//theNumberOfConicSpiralFormat
	   private JPanel theNumberOfConicSpiralPanel;				//theNumberOfConicSpiralPanel
	   private JLabel theNumberOfConicSpiralLabel;				//theNumberOfConicSpiralLabel
	   private JFormattedTextField theNumberOfConicSpiralField;	//theNumberOfConicSpiralField

	   // Elements for 'radius length'
	   private NumberFormat theMaximumRadiusLengthFormat;
	   private JPanel theMaximumRadiusLengthPanel;
	   private JLabel theMaximumRadiusLengthLabel;
	   private JFormattedTextField theMaximumRadiusLengthField;
	   
	   // Elements for 'segment increments'
	   private NumberFormat theSegmentIncFormat;
	   private JPanel theSegmentIncPanel;
	   private JLabel theSegmentIncLabel;
	   private JFormattedTextField theSegmentIncField;
	   
	   // Elements for 'base radius'
	   private NumberFormat theRadiusFormat;
	   private JPanel theRadiusPanel;
	   private JLabel theRadiusLabel;
	   private JFormattedTextField theRadiusField;
	   
	   // Elements for 'Lower angle gap range'
	   private NumberFormat theAngleGapLowerFormat;
	   private JPanel theAngleGapLowerPanel;
	   private JLabel theAngleGapLowerLabel;
	   private JFormattedTextField theAngleGapLowerField;
	   
	   // Elements for 'Upper angle gap range'
	   private NumberFormat theAngleGapUpperFormat;
	   private JPanel theAngleGapUpperPanel;
	   private JLabel theAngleGapUpperLabel;
	   private JFormattedTextField theAngleGapUpperField;
	   
	   // Property values
	   private boolean theGenerateFlag;
	   private int theNumberOfConicSpirals;
	   private double theMaximumRadiusLength;
	   private double theSegmentInc;
	   private double theRadius;
	   private double theAngleGapLower;
	   private double theAngleGapUpper;
	   
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
	      theSegmentInc = DEFAULT_RD_INC;
	      theRadius = DEFAULT_BASE_RD;
	      theAngleGapLower = DEFAULT_AGL;
	      theAngleGapUpper = DEFAULT_AGU;
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
	    * setRadius increments
	    * This method sets the radius increments
	    */
	   
	   public void setSegmentInc(double aLength)
	   {
		   theSegmentInc = aLength;
	      if (theSegmentIncField != null)
	      {
	    	  theSegmentIncField.setValue(theSegmentIncField);
	    	  theSegmentIncField.updateUI();
	      }
	   }
	   
	   /*
	    * setRadius 
	    * This method sets the base radius 
	    */
	   
	   public void setRadius(double aLength)
	   {
		   theRadius = aLength;
	      if (theRadiusField != null)
	      {
	    	  theRadiusField.setValue(theRadiusField);
	    	  theRadiusField.updateUI();
	      }
	   }
	   /*
	    * setAngleGapLower
	    * This method sets the lower angle gap
	    */
	   
	   public void setAngleGapLower(double aGap)
	   {
		   theAngleGapLower = aGap;
	      if (theAngleGapLowerField != null)
	      {
	    	  theAngleGapLowerField.setValue(theAngleGapLowerField);
	    	  theAngleGapLowerField.updateUI();
	      }
	   }
	   /*
	    * setAngleGapUpper
	    * This method sets the upper angle gap
	    */
	   
	   public void setAngleGapUpper(double aGap)
	   {
		   theAngleGapUpper = aGap;
	      if (theAngleGapUpperField != null)
	      {
	    	  theAngleGapUpperField.setValue(theAngleGapUpperField);
	    	  theAngleGapUpperField.updateUI();
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
	    * getSegmentInc
	    * This method returns the segment increment
	    */
	   
	   public double getSegmentInc()
	   {
	      return theSegmentInc;
	   }
	   /*
	    * getRadius
	    * This method returns the base radius
	    */
	   
	   public double getRadius()
	   {
	      return theRadius;
	   }
	   
	   /*
	    * getAngleGapLower
	    * This method returns the lower range of angle gap
	    */
	   
	   public double getAngleGapLower()
	   {
		   return theAngleGapLower;
	   }
	   /*
	    * getAngleGapUpper
	    * This method returns the upper range of angle gap
	    */
	   
	   public double getAngleGapUpper()
	   {
		   return theAngleGapUpper;
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
	      // Max radius length
	      //***
	      
	      // Build format arguments
	      theMaximumRadiusLengthFormat = NumberFormat.getNumberInstance();

	      // Create number of point elements [label, field]
	      theMaximumRadiusLengthLabel = new JLabel("Max Radius Length:");
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
	      // Radius increments
	      //***
	      
	      // Build format arguments
	      theSegmentIncFormat = NumberFormat.getNumberInstance();

	      // Create number of point elements [label, field]
	      theSegmentIncLabel = new JLabel("Max Segment Increment:");
	      theSegmentIncLabel.setHorizontalAlignment(JLabel.LEFT);
	      theSegmentIncField = new JFormattedTextField(theSegmentIncFormat);
	      
	      theSegmentIncField.setValue(new Double(theSegmentInc));
	      theSegmentIncField.setColumns(10);
	      theSegmentIncField.addPropertyChangeListener("value", this);
	      

	      // Add to containing panel
	      theSegmentIncPanel = new JPanel();
	      theSegmentIncPanel.add(theSegmentIncLabel);
	      theSegmentIncPanel.add(theSegmentIncField);
	      
	      //***
	      // base radius 
	      //***
	      
	      // Build format arguments
	      theRadiusFormat = NumberFormat.getNumberInstance();

	      // Create number of point elements [label, field]
	      theRadiusLabel = new JLabel("Enter base radius:");
	      theRadiusLabel.setHorizontalAlignment(JLabel.LEFT);
	      theRadiusField = new JFormattedTextField(theRadiusFormat);
	      
	      theRadiusField.setValue(new Double(theRadius));
	      theRadiusField.setColumns(10);
	      theRadiusField.addPropertyChangeListener("value", this);
	      

	      // Add to containing panel
	      theRadiusPanel = new JPanel();
	      theRadiusPanel.add(theRadiusLabel);
	      theRadiusPanel.add(theRadiusField);
	      
	      //***
	      // Angle gap Lower range
	      //***
	      
	      
	      // Build format arguments
	      theAngleGapLowerFormat = NumberFormat.getNumberInstance();

	      // Create number of point elements [label, field]
	      theAngleGapLowerLabel = new JLabel("Angle Gap (Lower):");
	      theAngleGapLowerLabel.setHorizontalAlignment(JLabel.LEFT);
	      theAngleGapLowerField = new JFormattedTextField(theAngleGapLowerFormat);
	      
	      theAngleGapLowerField.setValue(new Double(theAngleGapLower));
	      theAngleGapLowerField.setColumns(10);
	      theAngleGapLowerField.addPropertyChangeListener("value", this);
	      
	      // Add to containing panel
	      theAngleGapLowerPanel = new JPanel();
	      theAngleGapLowerPanel.add(theAngleGapLowerLabel);
	      theAngleGapLowerPanel.add(theAngleGapLowerField);
	      
	      
	      //***
	      // Angle gap Upper range
	      //***
	      
	      // Build format arguments
	      theAngleGapUpperFormat = NumberFormat.getNumberInstance();

	      // Create number of point elements [label, field]
	      theAngleGapUpperLabel = new JLabel("Angle Gap (Upper):");
	      theAngleGapUpperLabel.setHorizontalAlignment(JLabel.LEFT);
	      theAngleGapUpperField = new JFormattedTextField(theAngleGapUpperFormat);
	      
	      theAngleGapUpperField.setValue(new Double(theAngleGapUpper));
	      theAngleGapUpperField.setColumns(10);
	      theAngleGapUpperField.addPropertyChangeListener("value", this);
	      
	      // Add to containing panel
	      theAngleGapUpperPanel = new JPanel();
	      theAngleGapUpperPanel.add(theAngleGapUpperLabel);
	      theAngleGapUpperPanel.add(theAngleGapUpperField);
	      
	      // Build tab
	      theTabbedPanePanel = new JPanel();
	      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
	      theTabbedPanePanel.add(theGeneratePanel);
	      theTabbedPanePanel.add(theNumberOfConicSpiralPanel);
	      theTabbedPanePanel.add(theMaximumRadiusLengthPanel);
	      theTabbedPanePanel.add(theSegmentIncPanel);
	      theTabbedPanePanel.add(theRadiusPanel);
	      theTabbedPanePanel.add(theAngleGapLowerPanel);
	      theTabbedPanePanel.add(theAngleGapUpperPanel);
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
		            System.out.println("Conic Spiral: max radius = " + theMaximumRadiusLength);
		      }
		      else if (source == theSegmentIncField)
		      {
		    	  theSegmentInc = ((Number)theSegmentIncField.getValue()).doubleValue();
		         if (TRACE)
		            System.out.println("Conic Spiral: segment increment = " + theSegmentInc);
		      }
		      else if (source == theRadiusField)
		      {
		    	  theRadius = ((Number)theRadiusField.getValue()).doubleValue();
		         if (TRACE)
		            System.out.println("Conic Spiral: base radius = " + theRadius);
		      }
		      else if (source == theAngleGapLowerField)
		      {
		    	  theAngleGapLower = ((Number)theAngleGapLowerField.getValue()).intValue();
		    	  if (TRACE)
		    		  System.out.println("Conic Spiral: lower angle gap = " + theAngleGapLower);
		      }
		      else if (source == theAngleGapUpperField)
		      {
		    	  theAngleGapUpper = ((Number)theAngleGapUpperField.getValue()).intValue();
		    	  if (TRACE)
		    		  System.out.println("Conic Spiral: upper angle gap = " + theAngleGapUpper);
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
		        	 theSegmentIncField.setEnabled(true);
		        	 theRadiusField.setEnabled(true);
		        	 theAngleGapLowerField.setEnabled(true);
		        	 theAngleGapUpperField.setEnabled(true);
		         }
		         else
		         {
		        	 theNumberOfConicSpiralField.setEnabled(false);
		        	 theMaximumRadiusLengthField.setEnabled(false);
		        	 theAngleGapLowerField.setEnabled(false);
		        	 theRadiusField.setEnabled(false);
		        	 theSegmentIncField.setEnabled(false);
		        	 theAngleGapUpperField.setEnabled(false);
		         }
		         if (TRACE)
		            System.out.println("Conic Sprial: generate = " + theGenerateFlag);
		      }
		   }
	   
}
