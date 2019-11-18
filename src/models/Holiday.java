package models;

import java.time.LocalDate;
import java.util.ArrayList;

import managers.Formatter;

//configure settings, pricing manager 
/**
 * The list of recognized holidays by MOBLIMA.
 * Holidays will have higher ticket prices.
 * @author balad
 *
 */
public class Holiday implements ISerializable {
	/**
	 * Unique identification number of the holiday.
	 */
	private int id;
	/**
	 * Name of the holiday.
	 */
	private String name;
	/**
	 * Date of the holiday.
	 */
	private LocalDate date;
	public Holiday(int id, String name, LocalDate date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(Formatter.getStringFromInt(id));
		data.add(name);
		data.add(Formatter.getStringFromLocalDate(date));
		return data;
	}
	/**
	 * Gets name of the holiday.
	 * @return This holiday's name.
	 */
	public String getHolidayName() {
		return name;
	}
	/**
	 * Name of holiday to set.
	 * @param holidayName This holiday's name.
	 */
	public void setHolidayName(String holidayName) {
		this.name = holidayName;
	}
	/**
	 * Gets date of the holiday.
	 * @return This holiday's date.
	 */
	public LocalDate getHolidayDate() {
		return date;
	}
	/**
	 * Date of holiday to set.
	 * @param holidayDate Date of the holiday to be inputted. 
	 */
	public void setHolidayDate(LocalDate holidayDate) {
		this.date = holidayDate;
	}
}
