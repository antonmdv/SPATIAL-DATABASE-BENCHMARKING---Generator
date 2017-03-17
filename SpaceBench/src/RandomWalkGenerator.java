/*
 * RandomWalkGenerator.java
 *
 * Authors: 
 * Version Date: 
 * 
 * This file has to do with the mechanics of generating a string via the Random Walk algorithm
 */


import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class RandomWalkGenerator {

	/*
	 * RandomWalkGenerator
	 *
	 * This class generates the requested number of line strings 
	 * via the random walk algorithm
	 */
	
	RandomWalkGenerator()
	{
	}
	
	/*
	 * generate  edit when we change algorithm
	 *
     * This method does the actual generation work, currently generates a random point
     * then for subsequent points picks a direction, directly north south east or west,
     * and generates another point.
	 */
	public void generate(DataGenModel aModel) throws IOException
	{
		//Set up output 
		String outFilename;
	    FileWriter f = null;
	    PrintWriter out = null;
	    
	    //Points
	    double x,y;
	    
	    // do we wish line strings generated?
	    if (aModel.theGenerateRandomWalksFlag == false)
	    	return;
	    
	    // Input from User (grabbing from data model)
	    int desiredNumberOfAlgorithms = aModel.theNumberOfRandomWalks;		//number of paths produced 	 
	    int desiredNumberOfSteps = aModel.theNumberOfSteps;					//Range of steps per algorithm (from 3 to n)
	    double stepLength = aModel.theMaximumStepLength;						//Range of step length(from 0 to n)
	    
	    //generate output file
	    outFilename = aModel.theFilenamePrefix + "randomWalk.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating randomWalk datafile [" + outFilename + "]");
	   
	   
	   //prepare algorithm counters
	   int amountProduced = 0;
	   
	   //Generate desired number of algorithms
	   while (amountProduced < desiredNumberOfAlgorithms){
		   
		   //***
		   //	create next random Walk
		   //***
		   
		   //prepare
		   List<Point2D> xyCoords = new ArrayList<Point2D>();
		   	xyCoords.clear();
		   	x = 0;
		   	y = 0;
		   	
		   	//Random the steps per algorithm 

		   	int usedDesiredNumberOfSteps = (int)(Math.random()*(desiredNumberOfSteps)+1);
			
			//generate the starting point
			x = (Math.random()*aModel.theSceneLength)+1;
            y = (Math.random()*aModel.theSceneLength)+1;
            
            //add point to the list
            Point2D startPt = new Point2D.Double(x,y);
            Point2D checkPt = new Point2D.Double(x,y);
            xyCoords.add(startPt);
		
			//generate path from point
		   	for(int i = 1; i<usedDesiredNumberOfSteps; i++){
		   		
		   	   
		   		//chose direction to move
		   	    double xDistance = (Math.random()*(2*stepLength))-(stepLength/2);
		   	    double yDistance = (Math.random()*(2*stepLength))-(stepLength/2);		
					   
					   //Create Candidate Point
		               Point2D candidatePt = new Point2D.Double(x + xDistance,y + yDistance);
		               
		               //Check if: 
		               //			1) the point will not come back to the previous location(redundancy)
		               //			2) the point will not be outside the scene
		               if (((checkPt.getX() != (x + xDistance)) || (checkPt.getY() != (y + yDistance))) && (((x + xDistance) <= aModel.theSceneLength )&&((x + xDistance) >= 0) && ((y + yDistance) <= aModel.theSceneLength) && ((y + yDistance)>=0))) 
		               {
		            	   
		            	  //if conditions satisfied, add point to the path
		                  xyCoords.add(candidatePt);
		                  checkPt = candidatePt;
		                  x = x + xDistance;
		                  y = y + yDistance;
		               } else {
		            	   i--;
		               }
		   	   
		   }
		   	
		   	//finish algorithmic path 
		   	amountProduced++;
		   	
		   	//output line string to text file 
		   	out.print("LINESTRING (");
			 
		            for (int i = 0; i < xyCoords.size()-1; i++)
		            {
		               x = xyCoords.get(i).getX();
		               y = xyCoords.get(i).getY();
		               
		               out.print(x+" "+y+", ");
		            }
		            
		            x = xyCoords.get(xyCoords.size()-1).getX();
		            y = xyCoords.get(xyCoords.size()-1).getY();
		            out.println(x+" "+y+")"); 
		            
        	}
        	out.close();
        	
	}
}