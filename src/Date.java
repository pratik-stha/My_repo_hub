public class Date {
	
	public static String nextDate(int month, int day, int year) 
							throws InvalidValueException, InvalidDateException {
		if (!isInRange(month,day,year)) {
			throw new InvalidValueException("Value of month, day, or year is not in the range of permitted values");
		}

		int tomorrowDay = 0, tomorrowMonth = 0, tomorrowYear = 0;
		
		switch (month) {
			case 1:		// 31-day months except December
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
				tomorrowYear = year;
				if (day < 31) {
					tomorrowDay = day + 1;
					tomorrowMonth = month;
				} else {
					tomorrowDay = 1;
					tomorrowMonth = month + 1;
				}
				break;
				
			case 4:		// 30-day months
			case 6:
			case 9:
			case 11:
				tomorrowYear = year;
				if (day < 30) {
					tomorrowDay = day + 1;
					tomorrowMonth = month;
				} else if (day == 30) {
					tomorrowDay = 1;
					tomorrowMonth = month + 1;
				} else {
					throw new InvalidDateException("Invalid Input Date");
				}
				break;
				
			case 12:		// December
				if (day < 31) {
					tomorrowDay = day + 1;
					tomorrowMonth = month;
					tomorrowYear = year;
				} else {
					tomorrowDay = 1;
					tomorrowMonth = 1;
					tomorrowYear = year + 1;					
				}
				break;
				
			case 2:		// February
				tomorrowYear = year;
				if (day < 28) {
					tomorrowDay = day + 1;
					tomorrowMonth = month;
				} else if (day == 28) {
					if (isLeapYear(year)) {
						tomorrowDay = 29;
						tomorrowMonth = month;
					} else {
						tomorrowDay = 1;
						tomorrowMonth = 3;
					}
				} else if (day == 29) {
					if (isLeapYear(year)) {
						tomorrowDay = 1;
						tomorrowMonth = 3;
					} else {
						throw new InvalidDateException("Invalid Input Date");
					}
				} else {		// day > 29
					throw new InvalidDateException("Invalid Input Date");
				}
				break;
		}
		return String.format("%02d/%02d/%04d", tomorrowMonth, tomorrowDay, tomorrowYear);
	}
	
	private static boolean isInRange(int month, int day, int year) {
		boolean c1 = 1 <= month && month <= 12;
		boolean c2 = 1 <= day && day <= 31;
		boolean c3 = 1812 <= year && year <= 2012;
		return c1 && c2 && c3;
	}
	
	private static boolean isLeapYear(int year) {
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}
}
