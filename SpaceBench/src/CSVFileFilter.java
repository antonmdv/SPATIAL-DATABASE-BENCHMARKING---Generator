/**
 * CSVFileFilter.java
 *
 * @author :  Tim Faulkner
 * @version : 12/28/2011
 */
import java.io.*;
//aa
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