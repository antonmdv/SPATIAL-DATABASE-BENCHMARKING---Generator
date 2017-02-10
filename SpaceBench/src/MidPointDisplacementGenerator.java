import javax.swing.*;
import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class MidPointDisplacementGenerator {
	
	MidPointDisplacementGenerator(){
				
	}
	
	public void generate(DataGenModel aModel) throws IOException{
		
		//Set up output 
		String outFilename;
		FileWriter f = null;
		PrintWriter out = null;
		Random rnd = new Random();
				
		//Points
		double x,y;
			    
		// Input from User (grabbing from data model)
		int desiredNumberOfAlgorithms = 5;
		double displacementBound = 2.4;
		double displacementBoundReduction = 0.7;
		int numOfIt = 10;
			    
		//generate output file
		outFilename = aModel.theFilenamePrefix + "midPointDisplacement.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating randomWalk datafile [" + outFilename + "]");
		
		int amountProduced = 0;
		
		while(amountProduced<desiredNumberOfAlgorithms){
			
			//prepare
			LinkedList<Point2D> midPointDisp = new LinkedList<Point2D>();
			midPointDisp.clear();
			int it = 0;
			x=0;
			y=0;
			
			//produce starting points
			Point2D StartPoint = new Point2D.Double((Math.random()*aModel.theSceneLength)+1,
					Math.random()*aModel.theSceneLength);
			Point2D EndPoint = new Point2D.Double(Math.random()*aModel.theSceneLength,
					Math.random()*aModel.theSceneLength);
			
			//add to list
			midPointDisp.add(StartPoint);
			midPointDisp.add(EndPoint);
			
			//produce linestring 
			while(it<numOfIt){
				
				int currentSize = midPointDisp.size()-1;
				
				for(int i = 0; i < currentSize; i++){
					
	
					double X = ((midPointDisp.get(i).getX() + midPointDisp.get(i+1).getX())/2);
					double Y = ((midPointDisp.get(i).getY() + midPointDisp.get(i+1).getY())/2);
					
					Y *= displacementBound;
					
					Point2D dispPoint = new Point2D.Double(X,Y);
					midPointDisp.add(i+1, dispPoint);
					
				}
				
				displacementBound *= displacementBoundReduction;
				it++;
			}
			
			//print to txt
			//output algorithm to text file 
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
			
		}
		out.close();
		
		System.out.println("    " + desiredNumberOfAlgorithms + " line strings (MPD) were generated.");
					    
					    
	}
}
