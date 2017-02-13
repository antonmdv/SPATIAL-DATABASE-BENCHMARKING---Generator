/*
 * DataGenApp.java
 *
 * Original Author: Tim Faulkner
 * Editing Authors:
 * Version Date: 12/28/2011
 * 
 * This file creates the data generator 
 * and is the main entry point for the program
 */
import java.io.*;

public class DataGenApp {

   //***
   // instance variables
   //***
   
   DataGenerator theDataGenerator;

   //***
   // main entry point
   //***
   
   public static void main (final String[] args) throws IOException {
   
      DataGenerator dataGen = new DataGenerator();
      dataGen.run();
   }

}
