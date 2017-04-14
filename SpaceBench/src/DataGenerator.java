/*
 * DataGenerator.java
 *
 * Original Author: Tim Faulkner
 * Editing Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file has to do with creation of the main program window
 */

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DataGenerator implements ActionListener {

   //***
   // instance variables
   //***

   private static final boolean TRACE = false;

   //***
   // instance variables
   //***
   
   // application window elements
   private JFrame theAppFrame;
   private JTabbedPane theOptionsPane;
   private JButton loadOptionsButton;
   private JButton saveOptionsButton;
   private JButton generateButton;

   // data model
   DataGenModel theDataGenModel;
   
   // data generators
   PointGenerator thePointGenerator;
   SquareGenerator theSquareGenerator;
   TriangleGenerator theTriangleGenerator;
   PolygonGenerator thePolygonGenerator;
   LineStringGenerator theLineStringGenerator;
   
   //New
   RandomWalkGenerator theRandomWalkGenerator;
   ConicSpiralGenerator theConicSpiralGenerator;
   MidPointDisplacementGenerator theMidPointDisplacementGenerator;
   QuickStarPolygonGenerator theQuickStarPolygonGenerator;

   // data generator views
   SceneOptionsView theSceneOptionsView;
   PointGeneratorView thePointGeneratorView;
   SquareGeneratorView theSquareGeneratorView;
   TriangleGeneratorView theTriangleGeneratorView;
   PolygonGeneratorView thePolygonGeneratorView;
   LineStringGeneratorView theLineStringGeneratorView;
   
   //New
   RandomWalkGeneratorView theRandomWalkGeneratorView;
   ConicSpiralGeneratorView theConicSpiralGeneratorView;
   MidPointDisplacementGeneratorView theMidPointDisplacementGeneratorView;
   QuickStarPolygonGeneratorView theQuickStarPolygonGeneratorView;

   /*
    * DataGenerator()
    * 
    * This method create the main application window
    */
   
   DataGenerator()
   {
      theAppFrame = new JFrame("SpaceBench Data Generation");
      theOptionsPane = new JTabbedPane();

       theAppFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
   }

   /*
    * run()
    *
    * This method sets up the various generators and builds datafiles
    * based upon current parameters
    */
   
   public void run()
   {
      if (TRACE)
         System.out.println("DataGenerator: run");

      buildGeneratorsAndViews();
      buildOptionsDialog();
      theDataGenModel = new DataGenModel();
   }

   /*
    * buildGenerators
    *
    * This method builds the generators which create the various
    * data files
    */
   
   private void buildGeneratorsAndViews()
   {
      if (TRACE)
         System.out.println("DataGenerator: buildGeneratorsAndViews");

      // build generators
      thePointGenerator = new PointGenerator();
      theSquareGenerator = new SquareGenerator();
      theTriangleGenerator = new TriangleGenerator();
      thePolygonGenerator = new PolygonGenerator();
      theLineStringGenerator = new LineStringGenerator();
      
      //New
      theRandomWalkGenerator = new RandomWalkGenerator();
      theConicSpiralGenerator = new ConicSpiralGenerator();
      theMidPointDisplacementGenerator = new MidPointDisplacementGenerator();
      theQuickStarPolygonGenerator = new QuickStarPolygonGenerator();
      

      // build their views
      theSceneOptionsView = new SceneOptionsView();
      thePointGeneratorView = new PointGeneratorView();
      theSquareGeneratorView = new SquareGeneratorView();
      theTriangleGeneratorView = new TriangleGeneratorView();
      thePolygonGeneratorView = new PolygonGeneratorView();
      theLineStringGeneratorView = new LineStringGeneratorView();
      
      //New
      theRandomWalkGeneratorView = new RandomWalkGeneratorView();
      theConicSpiralGeneratorView = new ConicSpiralGeneratorView();
      theMidPointDisplacementGeneratorView = new MidPointDisplacementGeneratorView();
      theQuickStarPolygonGeneratorView = new QuickStarPolygonGeneratorView();
  }

   /*
    * buildOptionsDialog
    *
    * This method buids the user interface, each generator is asked
    * for its corresponding UI which is placed within a tab
    */
   
   private void buildOptionsDialog()
   {
      //***
      // build inner panes
      //***

      theSceneOptionsView.build(theOptionsPane);
      thePointGeneratorView.build(theOptionsPane);
      theSquareGeneratorView.build(theOptionsPane);
      theTriangleGeneratorView.build(theOptionsPane);
      thePolygonGeneratorView.build(theOptionsPane);
      theLineStringGeneratorView.build(theOptionsPane);
      
      //New
      theRandomWalkGeneratorView.build(theOptionsPane);
      theConicSpiralGeneratorView.build(theOptionsPane);
      theMidPointDisplacementGeneratorView.build(theOptionsPane);
      theQuickStarPolygonGeneratorView.build(theOptionsPane);

  
      //***
      // add management buttons to bottom of options pane
      //***

      // build buttons
      loadOptionsButton = new JButton("Load options");
      saveOptionsButton = new JButton("Save options");
      generateButton = new JButton("Generate data");

      loadOptionsButton.setActionCommand("loadModel");
      loadOptionsButton.addActionListener(this);
      saveOptionsButton.setActionCommand("saveModel");
      saveOptionsButton.addActionListener(this);
      generateButton.setActionCommand("generateData");
      generateButton.addActionListener(this);

      // place in panel
      JPanel innerPanel = new JPanel();
      innerPanel.add(loadOptionsButton);
      innerPanel.add(saveOptionsButton);
      innerPanel.add(generateButton);

      // add to frames panel
      theAppFrame.getContentPane().add(innerPanel, BorderLayout.PAGE_END);
      
      //***
      // show application window
      //***

      theAppFrame.getContentPane().add(theOptionsPane, BorderLayout.CENTER);
      theAppFrame.setSize(500, 350);
      theAppFrame.setVisible(true);
   }

   /*
    * actionPerformed
    * 
    * Called when a text field's "value" changes
    */
   
   public void actionPerformed(ActionEvent ae)
   {
      if (TRACE)
         System.out.println("DataGenerator: button pressed = " + ae.getActionCommand());

      if ("loadModel".equals(ae.getActionCommand()))
      {
         theDataGenModel.load(theAppFrame);
         updateView();
      }
      else if ("saveModel".equals(ae.getActionCommand()))
      {
         updateModel();
         theDataGenModel.save(theAppFrame);
      }
      else if ("generateData".equals(ae.getActionCommand()))
      {
         updateModel();
         generateData();
      }
   }

   /*
    * updateModel
    *
    * This method updates the model with current view settings
    */
   
   private void updateModel()
   {
      if (TRACE)
         System.out.println("DataGenerator: updateModel");

      //***
      // update view to capture current JTextField values
      // JAVA Note: they only update if user presses 
      //            'return' key while in the field
      //***

      theSceneOptionsView.captureFilenamePrefix();

      //***
      // update model
      //***

      // scene options
      theDataGenModel.theSceneLength = theSceneOptionsView.getSceneLength();
      theDataGenModel.theFilenamePrefix = theSceneOptionsView.getFilenamePrefix();
      
      // point options
      theDataGenModel.theGeneratePointsFlag = thePointGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfPoints = thePointGeneratorView.getNumberOfPoints();
      theDataGenModel.theUniquePointsFlag = thePointGeneratorView.getUniquePointsFlag();
      
      // square options
      theDataGenModel.theGenerateSquaresFlag = theSquareGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfSquares = theSquareGeneratorView.getNumberOfSquares();
      theDataGenModel.theMaximumSquareSideLength = theSquareGeneratorView.getMaximumSideLength();

      // triangle options
      theDataGenModel.theGenerateTrianglesFlag = theTriangleGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfTriangles = theTriangleGeneratorView.getNumberOfTriangles();
      theDataGenModel.theTriangleBBoxHeight = theTriangleGeneratorView.getBBoxHeight();
      theDataGenModel.theTriangleBBoxWidth = theTriangleGeneratorView.getBBoxWidth();

      // polygon options
      theDataGenModel.theGeneratePolygonsFlag = thePolygonGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfPolygons = thePolygonGeneratorView.getNumberOfPolygons();
      theDataGenModel.thePolygonMaxVertexCount = thePolygonGeneratorView.getMaximumVertexCount();
      theDataGenModel.thePolygonBBoxLength = thePolygonGeneratorView.getBBoxLength();
      theDataGenModel.thePolygonMinVertexCount = thePolygonGeneratorView.getMinimumVertexCount();

      // line string options
      theDataGenModel.theGenerateLineStringsFlag = theLineStringGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfLineStrings = theLineStringGeneratorView.getNumberOfLineStrings();
      theDataGenModel.theLineStringMaxSegmentCount = theLineStringGeneratorView.getMaximumSegmentCount();
      theDataGenModel.theLineStringMinSegmentCount = theLineStringGeneratorView.getMinimumSegmentCount();
      
      // the Random walk options
      theDataGenModel.theGenerateRandomWalksFlag = theRandomWalkGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfRandomWalks = theRandomWalkGeneratorView.getNumberOfRandomWalks();
      theDataGenModel.theMaximumStepLength = theRandomWalkGeneratorView.getMaximumStepLength();
      theDataGenModel.theNumberOfSteps = theRandomWalkGeneratorView.getNumberOfSteps();
      
      // Conic spiral options
      theDataGenModel.theGenerateConicSpiralsFlag = theConicSpiralGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfConicSpirals = theConicSpiralGeneratorView.getNumberOfConicSpirals();
      theDataGenModel.theMaximumRadiusLength = theConicSpiralGeneratorView.getMaximumRadiusLength();
      theDataGenModel.theSegmentInc = theConicSpiralGeneratorView.getSegmentInc();
      theDataGenModel.theRadius = theConicSpiralGeneratorView.getRadius();
      theDataGenModel.theAngleGapLower = theConicSpiralGeneratorView.getAngleGapLower();
      theDataGenModel.theAngleGapUpper = theConicSpiralGeneratorView.getAngleGapUpper();
      
      // MidPoint Displacement options 
      theDataGenModel.theGenerateMidpointDisplacementsFlag = theMidPointDisplacementGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberofMidpointDisplacements = theMidPointDisplacementGeneratorView.getNumberOfMidPointDisplacement();
      theDataGenModel.theRecursionDepth = theMidPointDisplacementGeneratorView.getRecursionDepth();
      theDataGenModel.theDisplacementBound = theMidPointDisplacementGeneratorView.getDisplacementBound();
      theDataGenModel.theDisplacementBoundReduction = theMidPointDisplacementGeneratorView.getDisplacementBoundReduction();
      
      // Quick-Star Polygon options
      theDataGenModel.theGenerateQSPolygonsFlag = theQuickStarPolygonGeneratorView.getGenerateFlag();
      theDataGenModel.theNumberOfQSPolygons = theQuickStarPolygonGeneratorView.getNumberOfQSPolygons();
      theDataGenModel.theNumberOfQSVertices = theQuickStarPolygonGeneratorView.getVertexCount();
      theDataGenModel.theStarRadius = theQuickStarPolygonGeneratorView.getStarRadius();
   }

   /*
    * updateView
    *
    * This method updates the view with current model settings
    */
   
   private void updateView()
   {
      // scene options
      theSceneOptionsView.setSceneLength(theDataGenModel.theSceneLength);
      theSceneOptionsView.setFilenamePrefix(theDataGenModel.theFilenamePrefix);

      // point options
       thePointGeneratorView.setGenerateFlag(theDataGenModel.theGeneratePointsFlag);
       thePointGeneratorView.setNumberOfPoints(theDataGenModel.theNumberOfPoints);
       thePointGeneratorView.setUniquePointsFlag(theDataGenModel.theUniquePointsFlag);

       // square options
       theSquareGeneratorView.setGenerateFlag(theDataGenModel.theGenerateSquaresFlag);
       theSquareGeneratorView.setNumberOfSquares(theDataGenModel.theNumberOfSquares);
       theSquareGeneratorView.setMaximumSideLength(theDataGenModel.theMaximumSquareSideLength);

       // triangle options
       theTriangleGeneratorView.setGenerateFlag(theDataGenModel.theGenerateTrianglesFlag);
       theTriangleGeneratorView.setNumberOfTriangles(theDataGenModel.theNumberOfTriangles);
       theTriangleGeneratorView.setBBoxHeight(theDataGenModel.theTriangleBBoxHeight);
       theTriangleGeneratorView.setBBoxWidth(theDataGenModel.theTriangleBBoxWidth);

       // polygon options
       thePolygonGeneratorView.setGenerateFlag(theDataGenModel.theGeneratePolygonsFlag);
       thePolygonGeneratorView.setNumberOfPolygons(theDataGenModel.theNumberOfPolygons);
       thePolygonGeneratorView.setMaximumVertexCount(theDataGenModel.thePolygonMaxVertexCount);
       thePolygonGeneratorView.setBBoxLength(theDataGenModel.thePolygonBBoxLength);
       thePolygonGeneratorView.setMinimumVertexCount(theDataGenModel.thePolygonMinVertexCount);

       // line string options
       theLineStringGeneratorView.setGenerateFlag(theDataGenModel.theGenerateLineStringsFlag);
       theLineStringGeneratorView.setNumberOfLineStrings(theDataGenModel.theNumberOfLineStrings);
       theLineStringGeneratorView.setMaximumSegmentCount(theDataGenModel.theLineStringMaxSegmentCount);
       
       //**************************************************************************************
       //Random Walk options
       theRandomWalkGeneratorView.setGenerateFlag(theDataGenModel.theGenerateRandomWalksFlag);
       theRandomWalkGeneratorView.setNumberOfRandomWalks(theDataGenModel.theNumberOfRandomWalks);
       theRandomWalkGeneratorView.setMaximumStepLength(theDataGenModel.theMaximumStepLength);
       theRandomWalkGeneratorView.setNumberOfSteps(theDataGenModel.theNumberOfSteps);
       
       //Spiral Conic options
       theConicSpiralGeneratorView.setGenerateFlag(theDataGenModel.theGenerateConicSpiralsFlag);
       theConicSpiralGeneratorView.setNumberOfConicSpirals(theDataGenModel.theNumberOfConicSpirals);
       theConicSpiralGeneratorView.setMaximumRadiusLength(theDataGenModel.theMaximumRadiusLength);
       theConicSpiralGeneratorView.setSegmentInc(theDataGenModel.theSegmentInc);
       theConicSpiralGeneratorView.setRadius(theDataGenModel.theRadius);
       theConicSpiralGeneratorView.setAngleGapLower(theDataGenModel.theAngleGapLower);
       theConicSpiralGeneratorView.setAngleGapUpper(theDataGenModel.theAngleGapUpper);
       
       //Midpoint Displacement options 
       theMidPointDisplacementGeneratorView.setGenerateFlag(true);
       theMidPointDisplacementGeneratorView.setNumberOfMidPointDisplacements(theDataGenModel.theNumberofMidpointDisplacements);
       theMidPointDisplacementGeneratorView.setRecursionDepth(theDataGenModel.theRecursionDepth);
       theMidPointDisplacementGeneratorView.setDisplacementBound(theDataGenModel.theDisplacementBound);
       theMidPointDisplacementGeneratorView.setDisplacementBoundReduction(theDataGenModel.theDisplacementBoundReduction);
       
       //Quick-Star Polygon options
       theQuickStarPolygonGeneratorView.setGenerateFlag(theDataGenModel.theGenerateConicSpiralsFlag);
       theQuickStarPolygonGeneratorView.setNumberOfQSPolygons(theDataGenModel.theNumberOfQSPolygons);
       theQuickStarPolygonGeneratorView.setVertexCount(theDataGenModel.theNumberOfQSVertices);
       theQuickStarPolygonGeneratorView.setStarRadius(theDataGenModel.theStarRadius);
       
   }

   /*
    * generateData
    *
    * This method runs each data generator
    */
   
   private void generateData()
   {
      if (TRACE)
         System.out.println("DataGenerator: generateData");

      //***
      // generate spatial content
      //***

      // validate model
      if (theDataGenModel.validate(theAppFrame) == false)
      {
         return;
      }

      // generate data
      try
      {
         System.out.println("generating data files");
         thePointGenerator.generate(theDataGenModel);
         theSquareGenerator.generate(theDataGenModel);
         theTriangleGenerator.generate(theDataGenModel);
         thePolygonGenerator.generate(theDataGenModel);
         theLineStringGenerator.generate(theDataGenModel);
         
         //new
         theRandomWalkGenerator.generate(theDataGenModel);
         theConicSpiralGenerator.generate(theDataGenModel);
         theMidPointDisplacementGenerator.generate(theDataGenModel);
         theQuickStarPolygonGenerator.generate(theDataGenModel);
         
         System.out.println("  finished data generation");
      }
      
      catch (IOException e)
      {
      }

   }

}
