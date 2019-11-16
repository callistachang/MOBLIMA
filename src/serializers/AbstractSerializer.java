package serializers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import models.ISerializable;

public abstract class AbstractSerializer {
		
	public ArrayList<String> serialize(ArrayList objectArray) {
		ArrayList<String> dataArray = new ArrayList<String>();
		
		for (int i = 0; i < objectArray.size(); i++) {
			ISerializable object = (ISerializable) objectArray.get(i);
			dataArray.add(serialize(object));
		}
		
		return dataArray;
	}
	
	public ArrayList deserialize(ArrayList<String> dataArray) {
		ArrayList<ISerializable> objectArray = new ArrayList<ISerializable>();
		
		for (String data: dataArray) {
			objectArray.add(deserialize(data));
		}
		return objectArray;
	}
	
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
//		System.out.println(serializedData);
		return serializedData;
	}
		
	protected abstract ISerializable deserialize(String data);
	
	protected String[] splitByAttribute(String data) {
		return data.split(",");
	}
	
	protected ArrayList<String> parseArrayToStrings(String[] data, int index) {
		String[] splitData = data[index].split(";");
		for (String str: splitData) {
			System.out.println(str);
		}
		return new ArrayList<String>(Arrays.asList(splitData));
	}
	
	protected int[] parseArrayToIntegers(String[] data, int index) {
		String[] splitDataStr = data[index].split(";");
		for (int i = 0; i < splitDataStr.length; i++) {
			if (splitDataStr[i].equals("null"))
				splitDataStr[i] = "0";
		}
	    return Stream.of(splitDataStr).mapToInt(Integer::parseInt).toArray();
	}
	
	protected int parseInteger(String[] data, int index) {
		return Integer.parseInt(data[index]);
	}
}
