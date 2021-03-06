/*
 * SquareGenerator.java
 *
 * Original Author: Weijun Huang, Tim Faulkner
 * Editing Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file has to do with the mechanics of generating a square
 */

import java.lang.Math;
import java.io.*;


public class SquareGenerator {

   /*
    * SquareGenerator
    *
    * This class generates squares randomly within a (N by N) space.  The
    * squares are writen to a ASCII text file.
    */
	
   SquareGenerator()
   {
   }
   
   /*
    * generate
    *
    * This method places random squares, on integer bounds, within the grid,
    * writing their locations to the output file.
    */
   
   public void generate(DataGenModel aModel) throws IOException
   {
      String outFilename;

      int cnt;
      double d, s;
      double leftX, lowerY, x1, x2, y1, y2, tmp;
      FileWriter f = null;
      PrintWriter out = null;

      double[] pointsX = new double[4];
      double[] pointsY = new double[4];

      // Is the generate flag checked?
      if (aModel.theGenerateSquaresFlag == false)
         return;

      // setup file output
      outFilename = aModel.theFilenamePrefix + "squares.txt";
      f = new FileWriter(outFilename);
      out = new PrintWriter(f);

      // generate squares
      System.out.println("  creating squares datafile [" + outFilename + "]");

      cnt = 0;
      while (cnt < aModel.theNumberOfSquares)
      {
         // randomly position the max bounding square in the grid
         leftX = (Math.random() * (aModel.theSceneLength - aModel.theMaximumSquareSideLength));
         lowerY = (Math.random() * (aModel.theSceneLength - aModel.theMaximumSquareSideLength));

         // the x interval locating the square in the max bounding square
         x1 = leftX + (Math.random() * aModel.theMaximumSquareSideLength);
         x2 = leftX + (Math.random() * aModel.theMaximumSquareSideLength);
         while (x1 == x2)
            x2 = leftX + (Math.random() * aModel.theMaximumSquareSideLength);
         if (x1 > x2)
         {
            tmp = x1;
            x1 = x2;
            x2 = tmp;
         }
         d = x2 - x1;

         // the y interval locating the square in the max bounding square
         y1 = lowerY + (Math.random() * (aModel.theMaximumSquareSideLength - d));
         y2 = y1 + d;

         // the coordinates of the left bottom vertex of the square
         s = (Math.random() * d);

         pointsX[0] = x1 + s;
         pointsY[0] = y1;
         pointsX[1] = x2;
         pointsY[1] = y1 + s;
         pointsX[2] = x2 - s;
         pointsY[2] = y2;
         pointsX[3] = x1;
         pointsY[3] = y2 - s;

         //Old output: SQUARE ((926 918) (941 903) (913 954) (882 881))
         //New output: POLYGON ((926 918, 941 903, 913 954, 882 881))
         out.print("POLYGON ((");
         out.print(pointsX[0] +" " + pointsY[0] + ", ");
         out.print(pointsX[1] +" " + pointsY[1] + ", ");
         out.print(pointsX[2] +" " + pointsY[2] + ", ");
         out.print(pointsX[3] +" " + pointsY[3]);
         out.println("))");

         cnt++;
         if (cnt%10000 == 0)
            System.out.println("    generated " + cnt + " of " + aModel.theNumberOfSquares);
         }
         out.close();
         System.out.println("    " + aModel.theNumberOfSquares + " squares were generated.");
      }
   }

