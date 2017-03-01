/*
 * SceneOptionsView.java
 *
 * Original Authors: Hamad Altammami
 * Version Date: 01/20/2017
 * 
 * This file has to do with the mechanics of generating Quick-star polygons
 */

import javax.swing.*;
import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class QuickStarPolygonGenerator {
	
	QuickStarPolygonGenerator() {}
	
	@SuppressWarnings("resource")
	public void generate(DataGenModel aModel) throws IOException
	{
		// set up file output 
		String outFilename;
	    FileWriter f = null;
	    PrintWriter out = null;
	    
	    // Is the generate flag checked?
	    if (aModel.theGenerateQSPolygonsFlag == false)
	    	return;
	    
	    // generate output file
	    outFilename = aModel.theFilenamePrefix + "Quick-StarPolygons.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating quick-star polygons datafile [" + outFilename + "]");

		// input from user
		int numOfQSPolygons = aModel.theNumberOfQSPolygons;
		int numOfQSVertices = aModel.theNumberOfQSVertices;
		double starRadius = aModel.theStarRadius;
		
		
		int outerCount = 0;	// polygon counter
		int innerCount = 0;	// vertex counter
		double angle = 0;	// angle between two consecutive vertices 
		double randRadius;	// radius in the range 0-radius
		
		// vertex coordinates
		double x;
		double y;
		
		// center of polygon
		double centerX;
		double centerY;
		double gap;		// angle gap
		boolean verify;
		
		
		gap = (2 * Math.PI) / numOfQSVertices;
		Random rand = new Random();
		while (outerCount < numOfQSPolygons) {
			// generating random center within scene bound
			centerX = rand.nextDouble() * aModel.theSceneLength + 1;
			centerY = rand.nextDouble() * aModel.theSceneLength + 1;
			verify = withinSceneLength(aModel, starRadius, centerX, centerY);
			
			if (verify) {
				out.print("POLYGON (");
				
				angle = 0;
				innerCount = 0;
				
				// Generates Vertices
				while (innerCount < numOfQSVertices) {
					randRadius = rand.nextFloat() * starRadius;
					x = centerX + randRadius * Math.cos(angle);
					y = centerY + randRadius * Math.sin(angle);
					out.printf("%f %f, ", x, y);
					angle += gap;
					innerCount++;
				}
				out.println(")");
				outerCount++;
			}
		}
		out.close();
		System.out.println("    " + numOfQSPolygons + " quick-star polygons were generated.");

	}
	public boolean withinSceneLength (DataGenModel aModel, double maxRadius, double centerX, double centerY) {
		if (centerX + maxRadius < aModel.theSceneLength && centerX - maxRadius > 0
					&& centerY + maxRadius < aModel.theSceneLength && centerY - maxRadius > 0) {
			return true;
		}
		return false;
	}
}