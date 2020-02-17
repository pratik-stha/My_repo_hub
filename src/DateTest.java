import java.io.*;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.*;

class DateTest {

	@BeforeAll
	static void prepareTestData() {
		int[] months = { 0, 2, 6, 7, 12, 13 };
		int[] days = { 0, 14, 28, 29, 30, 31, 32 };
		int[] years = { 1811, 1996, 2000, 2002, 2013 };
		try {
			File file = new File("src/ECT-Data.csv");
			if (!file.exists()) {
				// create the file
				file.createNewFile();

				// create a FileWriter Object
				FileWriter writer = new FileWriter(file);
				// Writes the content to the file
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 7; j++) {
						for (int k = 0; k < 5; k++) {
							writer.write(Integer.toString(months[i]) + "," + Integer.toString(days[j]) + ","
									+ Integer.toString(years[k]) + "," + expectedOutput(months[i], days[j], years[k])
									+ "\n");
							writer.flush();
						}
					}
				}
			writer.close();
			}

		}

		catch (Exception ex) {

		}
	}

	@ParameterizedTest
	@CsvFileSource(resources = "ECT-Data.csv")
	void testAll(int month, int day, int year, String expectedResult)
			throws InvalidValueException, InvalidDateException {

		try {
			assertEquals(expectedResult, Date.nextDate(month, day, year));
		} catch (InvalidValueException e) {
			assertEquals(expectedResult, e.getClass().getName());
		} catch (InvalidDateException ex) {
			assertEquals(expectedResult, ex.getClass().getName());
		} catch (Exception ex2) {
			fail("Did not meet this exception");

		}
}

	private static String expectedOutput(int month, int day, int year) {
		if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1812 || year > 2012) {
			return "InvalidValueException";
		}
		try {
			GregorianCalendar g = new GregorianCalendar();
			g.setLenient(false);

			g.set(year, month - 1, day); // decrement of month by 1 as it starts from 0
			g.add(GregorianCalendar.DATE, 1);
			return (new SimpleDateFormat("MM/dd/yyyy").format(g.getTime())); // returns the string of date
		
		} catch (IllegalArgumentException ex) {
			return "InvalidDateException";
		}
	}

}
