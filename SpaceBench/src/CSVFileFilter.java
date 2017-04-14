/*
 * CSVFileFilter.java
 *
 * Original Author:  Tim Faulkner
 * Editing Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 12/28/2011
 * 
 * This file does as it says, filters out CSV files
 */
import java.io.*;

class CSVFileFilter extends javax.swing.filechooser.FileFilter
{
   public boolean accept(File f)
   {
      return f.isDirectory() || f.getName().toLowerCase().endsWith(".csv");
   }

   public String getDescription()
   {
      return ".csv files";
   }
}