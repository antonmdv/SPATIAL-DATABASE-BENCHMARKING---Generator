/*
 * LineStringGenerator.java
 *
 * Original Authors: Weijun Huang, Tim Faulkner
 * Editing Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file has to do with the mechanics for generating line strings 
 * via a simple plane sweep algorithm
 */

import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class LineStringGenerator {

   /*
    * LineStringGenerator
    *
    * This class generates line strings randomly within a (N by N) space.  The
    * points that comprise a line string are tested for uniqueness, and the
    * resulting line strings are writen to a ASCII text file.
    */
	
   LineStringGenerator()
   {
   }

   /*
    * generate
    *
    * This method places a random set of points down, then connects them from 
    * left to right, prioritizing higher points. 
    */
   
   public void generate(DataGenModel aModel) throws IOException
   {
      String outFilename;
      FileWriter f = null;
      PrintWriter out = null;
      int desiredSegmentCount, currSegmentCount;
      double x, y;

      // do we wish points generated?
      if (aModel.theGenerateLineStringsFlag == false)
         return;

      // setup file output
      outFilename = aModel.theFilenamePrefix + "linestrings.txt";
      f = new FileWriter(outFilename);
      out = new PrintWriter(f);

      // generate linestrings
      System.out.println("  creating linestrings datafile [" + outFilename + "]");
      System.out.println("    all linestrings will contain unique points");
      List<Point2D> xyCoords = new ArrayList<Point2D>();

      int lineCount = 0;
      while (lineCount < aModel.theNumberOfLineStrings)
      {
         //***
         // create next line string
         //***

         currSegmentCount = 0;
         xyCoords.clear();
         desiredSegmentCount = (int)(Math.random()*aModel.theLineStringMaxSegmentCount)+aModel.theLineStringMinSegmentCount;
         if (desiredSegmentCount >= 1)
         {
            while (currSegmentCount <= desiredSegmentCount)
            {
               x = (Math.random()*aModel.theSceneLength)+1;
               y = (Math.random()*aModel.theSceneLength)+1;
               Point2D candidatePt = new Point2D.Double(x,y);
               if (xyCoords.contains(candidatePt) == false)
               {
                  xyCoords.add(candidatePt);
                  currSegmentCount++;
               }
            }

            //***
            // sort line string verticies (x then y ascending)
            //***
            
            boolean sortedByX = false;
            while (!sortedByX)
            {
              sortedByX = true;
              for (int i = 0; i < xyCoords.size()-1; i++)
              {
                 if (xyCoords.get(i).getX() > xyCoords.get(i+1).getX())
                 {
                    sortedByX = false;
                    double x1 = xyCoords.get(i).getX();
                    double y1 = xyCoords.get(i).getY();
                    double x2 = xyCoords.get(i+1).getX();
                    double y2 = xyCoords.get(i+1).getY();
                    Point2D p1 = new Point2D.Double(x1,y1);
                    Point2D p2 = new Point2D.Double(x2,y2);
                    xyCoords.set(i, p2);
                    xyCoords.set(i+1, p1);
                 }
              }
            }
            boolean sortedByY = false;
            while (!sortedByY)
            {
              sortedByY = true;
              for (int i = 0; i < xyCoords.size()-1; i++)
              {
                 double x1 = xyCoords.get(i).getX();
                 double y1 = xyCoords.get(i).getY();
                 double x2 = xyCoords.get(i+1).getX();
                 double y2 = xyCoords.get(i+1).getY();
                 if ((x1 == x2) && (y1 > y2))
                 {
                    sortedByY = false;
                    Point2D p1 = new Point2D.Double(x1,y1);
                    Point2D p2 = new Point2D.Double(x2,y2);
                    xyCoords.set(i, p2);
                    xyCoords.set(i+1, p1);
                 }
              }
            }

            //***
            // write line string to output file
            //***
            
            //Old format - LINESTRING ((30 10) (10 30) (40 40))
            //New format - LINESTRING (30 10, 10 30, 40 40)
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
         lineCount++;
         if (lineCount%10000 == 0)
            System.out.println("    generated " + lineCount + " of " + aModel.theNumberOfLineStrings);
      }
      out.close();
      System.out.println("    " + aModel.theNumberOfLineStrings + " line strings were generated.");
   }
}