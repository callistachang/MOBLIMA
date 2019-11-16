package models;

import java.time.LocalDate;
import java.util.ArrayList;

import managers.Formatter;

//configure settings, pricing manager 
public class Holiday implements ISerializable {
	private int id;
	private String name;
	private LocalDate date;
	
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(Formatter.getStringFromInt(id));
		data.add(name);
		data.add(Formatter.getStringFromLocalDate(date));
		return data;
	}
	
	public String getHolidayName() {
		return name;
	}
	public void setHolidayName(String holidayName) {
		this.name = holidayName;
	}
	public LocalDate getHolidayDate() {
		return date;
	}
	public void setHolidayDate(LocalDate holidayDate) {
		this.date = holidayDate;
	}
}
