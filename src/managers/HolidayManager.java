package managers;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


import models.Holiday;
import models.Movie;

public class HolidayManager {
	private static final String DATABASE_NAME = "holidaydata";
	protected static ArrayList<Holiday> records = null;

	public int listAll() {
		return 0; //to change the code accordingly 
	}
	
	public Boolean isHoliday(LocalDate movieDate) {
		for (Holiday h: records) {
			LocalDate date = h.getHolidayDate();
			if(movieDate == date) return true;
		}
		return false;
	}

}
