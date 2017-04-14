/*
 * MidPointDisplacementGenerator.java
 *
 * Original Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file has to do with the mechanics of generating line strings with the midpoint displacement algorithm
 */

import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class MidPointDisplacementGenerator {
	
	MidPointDisplacementGenerator()
	{
				
	}
	
	public void generate(DataGenModel aModel) throws IOException{
		
		//Set up output 
		String outFilename;
		FileWriter f = null;
		PrintWriter out = null;
		//Random rnd = new Random();
		
		//generate output file
		outFilename = aModel.theFilenamePrefix + "midPointDisplacement.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating midPointDisplacement datafile [" + outFilename + "]");
		
		// Input from User (grabbing from data model)
		int desiredNumberOfAlgorithms = aModel.theNumberofMidpointDisplacements;
		int  count = 0;
		
		//Points
		double x,y;
		
		while(count < desiredNumberOfAlgorithms){
			
			//get the user input
			double displacementBound = aModel.theDisplacementBound;
			double displacementBoundReduction = aModel.theDisplacementBoundReduction;
			int it = 0;
			int numOfIt = aModel.theRecursionDepth;
			
			//produce starting points
			Point2D StartPoint = new Point2D.Double((Math.random()*aModel.theSceneLength)+1,
					Math.random()*aModel.theSceneLength);
			Point2D EndPoint = new Point2D.Double(Math.random()*aModel.theSceneLength,
					Math.random()*aModel.theSceneLength);
			
			//Create Instance of Mid Point Displacement and load with starting and ending points
			LinkedList<Point2D> midPointDisp = new LinkedList<Point2D>();
			midPointDisp.add(StartPoint);
			midPointDisp.add(EndPoint);
			
			// Generate points
			while(it<numOfIt){ //recursion depth
				
				int currentSize = midPointDisp.size();
				
				//front counter
				int bBackIndex = 0;
				int fBackIndex = 1;
				
				//back counter
				int fFrontIndex=currentSize-1;
				int bFrontIndex=currentSize-2;
				
				//insertion counters
				int putFront = currentSize;
				int putBack  = fBackIndex;
				
					
					Point2D frontLowerLimit;
					Point2D frontUpperLimit;
					
					Point2D backUpperLimit;
					Point2D backLowerLimit;
					
					//for the first iteration
					if(it == 0){
						
						frontLowerLimit = midPointDisp.get(0);
						frontUpperLimit = midPointDisp.get(1);
						double X = ((frontLowerLimit.getX() + frontUpperLimit.getX())/2);
						double Y =  ((frontLowerLimit.getY() +frontUpperLimit.getY())/2);
						Y += displacementBound;
						Point2D dispPoint = new Point2D.Double(X,Y);
						midPointDisp.add(1, dispPoint);
						
					}else{
						
						//number of insertion steps per each side
						int steps;
						
						if(it == 1){
							steps = 1;
						}
						else{ 
							steps = (int) ((Math.pow(2, it))/2);
						}
						int currentStep = 0;
						
						//Insert Following
						while(currentStep<steps){
							
							backLowerLimit = midPointDisp.get(bBackIndex);
							backUpperLimit = midPointDisp.get(fBackIndex);
						
							frontUpperLimit = midPointDisp.get(fFrontIndex);
							frontLowerLimit = midPointDisp.get(bFrontIndex);
						
							double X1 = ((backLowerLimit.getX() + backUpperLimit.getX())/2);
							double Y1 = ((backLowerLimit.getY() + backUpperLimit.getY())/2);
						
							double X2 = ((frontUpperLimit.getX() + frontLowerLimit.getX())/2);
							double Y2 = ((frontUpperLimit.getY() + frontLowerLimit.getY())/2);
							
							Y1 += displacementBound;
							Y2 += displacementBound;
						
							Point2D lowerDispPoint = new Point2D.Double(X1,Y1);
							Point2D higherDispPoint = new Point2D.Double(X2,Y2);
							
							midPointDisp.add(putBack, lowerDispPoint);
							midPointDisp.add(putFront, higherDispPoint);
							
							bBackIndex = bBackIndex+2;
							fBackIndex = fBackIndex+2;
						
							putBack = putBack+2;
							//putFront = putFront-1;
							
							currentStep++;
						}	
					}	
				//displace bounds
				displacementBound *= displacementBoundReduction;
				//increase recursion 
				it++;
			}
			
			//print to txt
			//output line string to text file 
		   	out.print("LINESTRING (");
			 
		    for (int i = 0; i < midPointDisp.size()-1; i++)
		    {
		    	x = midPointDisp.get(i).getX();
		        y = midPointDisp.get(i).getY();
		               
		        out.print(x+" "+y+", ");
		    }
		            
		     x = midPointDisp.get(midPointDisp.size()-1).getX();
		     y = midPointDisp.get(midPointDisp.size()-1).getY();
		     out.println(x+" "+y+")");
		     
		     //next algorithm
		     count++;
		}
		
		out.close();
		f.close();
		
		System.out.println("    " + desiredNumberOfAlgorithms + " line strings (MPD) were generated.");
					    
					    
	}
}
