import java.io.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.junit.jupiter.params.provider.Arguments;

public class file_try {


	   public static void main(String args[])throws IOException {
	      
		   int[] months = {0, 2, 6, 7, 12, 13};
			int[] days = {0, 14, 28, 29, 30, 31, 32};
			int[] years = {1811, 1996, 2000, 2002, 2013};
			//String expectedOutput = "";
		   File file = new File("src/Hello1.txt");
	      
	      // creates the file
	      file.createNewFile();
	      
	      // creates a FileWriter Object
	      FileWriter writer = new FileWriter(file); 
	      int count = 0;
	      // Writes the content to the file
	      for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					for (int k = 0; k < 5; k++) {
							writer.write(Integer.toString(months[i])+","+Integer.toString(days[j])+","+ Integer.toString(years[k])+","+expectedOutput(months[i],days[j],years[k])+"\n");
							writer.flush();
							count++;
					 }
				}
			}
	      System.out.println(count);
	     
	      writer.close();


	   }
	   
		private static String expectedOutput(int month, int day, int year) {
			if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1812 || year > 2012) {
				return "InvalidValueException";
			}
			try {
				GregorianCalendar g = new GregorianCalendar();
				g.setLenient(false);

				g.set(year, month - 1, day); //decrement of month by 1 as it starts from 0
				g.add(GregorianCalendar.DATE, 1);
				return (new SimpleDateFormat("MM/dd/yyyy").format(g.getTime()));  // returns the string of date
				// TO DO: Same as in Project 1
				
				
				
			} catch (IllegalArgumentException ex) {
				return "InvalidDateException";
			}
		}
	
}
