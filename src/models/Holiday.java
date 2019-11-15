package models;

import java.time.LocalDate;

//configure settings, pricing manager 
public class Holiday {
	private String holidayName;
	private LocalDate holidayDate;
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public LocalDate getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}
	
}
