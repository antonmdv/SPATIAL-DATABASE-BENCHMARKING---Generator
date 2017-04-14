/*
 * ConicSpiralGenerator
 * 
 * Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file is used for generating line strings using the conic spiral generator
 */
import java.lang.Math;
import java.io.*;
import java.util.*;

public class ConicSpiralGenerator {

	ConicSpiralGenerator()
	{
	}
	
	// Class used for generation
	public void generate(DataGenModel aModel) throws IOException
	{
	
		// File output setup
		String outFilename;
	    FileWriter f = null;
	    PrintWriter out = null;
	    
	    
	    // Checks if the checkbox for spirals is checked
	    if (aModel.theGenerateConicSpiralsFlag == false)
	    	return;
	    
	    // Generates output file
	    outFilename = aModel.theFilenamePrefix + "conicSpiral.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating conicSprial datafile [" + outFilename + "]");

		// User input
		int numOfConicSpirals = aModel.theNumberOfConicSpirals;
		double radiusLength = aModel.theMaximumRadiusLength;
		double segmentInc = aModel.theSegmentInc;
		double baseRadius = aModel.theRadius;
		double angleGapLower = aModel.theAngleGapLower;
		double angleGapUpper = aModel.theAngleGapUpper;
		
		
		int count = 0;	// loop counter
		
		
		Random r = new Random();
		// Variables needed for generating spirals
		double x;
		double y;
		double theta;
		double centerX;
		double centerY;
		double nextX;
		double nextY;
		double randAngleGap;
		boolean verify;
		double dist;
		
		while (count < numOfConicSpirals)
        {
			// Generates random center within scene bounds
			centerX = r.nextDouble() * aModel.theSceneLength + 1;
			centerY = r.nextDouble() * aModel.theSceneLength + 1;
			
			verify = false;
			verify = withinSceneLength (aModel, radiusLength, centerX, centerY);
			if (verify) {
				out.print("LINESTRING (");
				x = centerX;
				y = centerY;

				theta = 0;
				baseRadius = aModel.theRadius;
				// Loops generate new points for line strings
				do {
					// Generates new vertex
					randAngleGap = angleGapLower + (angleGapUpper - angleGapLower) * r.nextDouble();
					baseRadius += r.nextDouble() * segmentInc;
					theta += randAngleGap;
					
					x = x + baseRadius*Math.cos(theta);
					y = y + baseRadius*Math.sin(theta);
					
					out.printf("%f %f, ", x, y);
					
					// projected vertex after the last one generated
					nextX = x + baseRadius*Math.cos(Math.PI*theta);
					nextY = y + baseRadius*Math.sin(Math.PI*theta);

					// distance formula between center and last vertex generated
					dist = Math.sqrt(Math.pow(centerX - nextX, 2) + Math.pow(centerY - nextY, 2));
					
				} while (radiusLength > dist);
				out.println(")");
				count++;
	        }
		}
		out.close();
		System.out.println("    " + numOfConicSpirals + " conic spirals were generated.");
	}
	public boolean withinSceneLength (DataGenModel aModel, double maxRadius, double centerX, double centerY) {
		if (centerX + maxRadius < aModel.theSceneLength && centerX - maxRadius > 0
					&& centerY + maxRadius < aModel.theSceneLength && centerY - maxRadius > 0) {
			return true;
		}
		return false;
	}
}