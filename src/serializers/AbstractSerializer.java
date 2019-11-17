package serializers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import managers.Formatter;
import models.ISerializable;

/**
 * Handles the conversion of objects to string and vice-versa.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */
public abstract class AbstractSerializer {
	
	/**
	 * Converts Object array to String array to store in external CSV.
	 * @param objectArray Array of objects to be converted.
	 * @return Array of data type String converted from object.
	 */
	public ArrayList<String> serialize(ArrayList objectArray) {
		ArrayList<String> dataArray = new ArrayList<String>();
		
		for (int i = 0; i < objectArray.size(); i++) {
			ISerializable object = (ISerializable) objectArray.get(i);
			dataArray.add(serialize(object));
		}
		
		return dataArray;
	}
	/**
	 * Converts String array to Object array to store in external CSV.
	 * @param dataArray Array of data type String to be converted.
	 * @return Array of objects converted from the data type String.
	 */
	public ArrayList deserialize(ArrayList<String> dataArray) {
		ArrayList<ISerializable> objectArray = new ArrayList<ISerializable>();
		
		for (String data: dataArray) {
			objectArray.add(deserialize(data));
		}
		return objectArray;
	}
	/**
	 * Converts a single object to its string representation.
	 * Called by the previous function serialize.
	 * @param object Object that needs to be converted.
	 * @return String that is converted from object.
	 */
	@SuppressWarnings("unchecked")
	public String serialize(ISerializable object) {
		ArrayList<Object> objectData = object.getSerializableData();
		
		String serializedData = "";
		for (int i = 0; i < objectData.size(); i++) {
			Object data = objectData.get(i);
			
			if (data instanceof ArrayList) {
				String arraySerialized = "";
				for (String el: (ArrayList<String>) data)
				{
					arraySerialized += el + ";";
				}
				objectData.set(i, arraySerialized);
			}
			
			serializedData += objectData.get(i) + ",";
		}		
		return serializedData;
	}
		
	/**
	 * Deserializes CSV data to its data type.
	 * Extended in sub-classes.
	 * @param data Data whose data type needs to be converted.
	 * @return 
	 */
	protected abstract ISerializable deserialize(String data);
	
	/**
	 * A utility function to split CSV elements by commas.
	 * @param data String data which needs to be separated by commas. 
	 * @return An array of strings, that has been separated by commas. 
	 */
	protected String[] splitByAttribute(String data) {
		return data.split(",");
	}
	
	/**
	 * A utility function to split a CSV element by semicolons.
	 * @param arr String data which needs to be separated by semicolons.
	 * @return An ArrayList of strings that has been separated by semicolons.
	 */
	protected ArrayList<String> splitArrayToStrings(String arr) {
		String[] splitArr = arr.split(";");
		return new ArrayList<String>(Arrays.asList(splitArr));
	}
	
	/**
	 * A utility function to split a CSV element by semicolons.
	 * @param arr String data which needs to be separated by semicolons.
	 * @return An ArrayList of integers that has been separated by semicolons.
	 */
	protected ArrayList<Integer> splitArrayToIntegers(String arr) {
		String[] splitArr = arr.split(";");	// "2", "3", "4"
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for (String str: splitArr) {
				intArray.add(Formatter.getIntFromString(str));
		}
		return intArray;
	}
	
	

}
