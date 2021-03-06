/*
 * PointGenerator.java
 *
 * Authors:  Weijun Huang, Tim Faulkner
 * Editing Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file relates to the generation mechanics for the point generator
 */

import java.io.*;

public class PointGenerator
{
   /*
    * PointGenerator
    *
    * This class generates points randomly within a (N by N) space, optionally
    * checking for duplicate points.  The points are writen to a ASCII text
    * file.
    */
	
   PointGenerator()
   {
   }
   
   /*
    * generate
    *
    * This method places random points, on integer bounds, within the grid,
    * writting their locations to the output file. It optionally checks for
    * duplicate points.
    *
    * Duplicates are checked by checking against the list of points. This is 
    * very time inefficient for large numbers of points. 
    */
   
   public void generate(DataGenModel aModel) throws IOException
   {
      double x,y;
      //,i,j;
      long cnt;
      String outFilename;
      FileWriter f = null;
      PrintWriter out = null;

      // do we wish points generated?
      if (aModel.theGeneratePointsFlag == false)
         return;

      // setup file output
      outFilename = aModel.theFilenamePrefix + "points.txt";
      f = new FileWriter(outFilename);
      out = new PrintWriter(f);

      // generate points
      System.out.println("  creating points datafile [" + outFilename + "]");
      if (aModel.theUniquePointsFlag == false)
      {
         System.out.println("    dataset may contain duplicate points");
         cnt = 0;
         while (cnt < aModel.theNumberOfPoints)
         {
               x = (Math.random()*aModel.theSceneLength)+1;
               y = (Math.random()*aModel.theSceneLength)+1;
               out.println("POINT (" + x + " " + y + ")");
               cnt++;
               if (cnt%100000 == 0)
                  System.out.println("    generated " + cnt + " of " + aModel.theNumberOfPoints);
         }
         System.out.println("    " + aModel.theNumberOfPoints + " points were generated.");
      }
      else
      {
           System.out.println("    data will contain only unique points");
           double[] xValues = new double[(aModel.theNumberOfPoints)];
           double[] yValues = new double[(aModel.theNumberOfPoints)];
           int duplicates = 0;
           cnt = 0;
           while (cnt < aModel.theNumberOfPoints)
           {
                 x = (Math.random()*aModel.theSceneLength)+1;
                 y = (Math.random()*aModel.theSceneLength)+1;
                 int prevdup = duplicates;
                 
                 for(int a = 0; a < cnt; a++) {
                	 if (xValues[a] == x) {
                		 if (yValues[a] == y){
                			 duplicates++; 
                		 }
                	 }
                 }
                 
                 if(prevdup == duplicates) {
                     xValues[(int) cnt] = x;
                     yValues[(int) cnt] = y;
                     out.println("POINT (" + x + " " + y + ")");
                     cnt++;
                     if (cnt%100000 == 0)
                        System.out.println("    generated " + cnt + " of " + aModel.theNumberOfPoints);
                   }
           }
           System.out.println("    " + aModel.theNumberOfPoints + " points were generated.");
           System.out.println("    " + duplicates + " duplicates were eliminated.");
      }
      out.close();
   }
}