package serializers;

import java.util.ArrayList;
import java.util.Arrays;

import models.ISerializable;

public abstract class AbstractSerializer {
		
	public ArrayList<String> serialize(ArrayList objectArray) {
		ArrayList<String> dataArray = new ArrayList<String>();
		
		for (int i = 0; i < objectArray.size(); i++) {
			dataArray.add(serialize((ISerializable) objectArray.get(i)));
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
	protected String serialize(ISerializable object) {
		ArrayList<Object> objectData = object.getSerializableData();
		
		String serializedData = "";
		
		for (int i = 0; i < objectData.size(); i++) {
			Object data = objectData.get(i);
			
			// if ArrayList, separate by semicolons
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
		
	protected abstract ISerializable deserialize(String data);
	
	protected String[] splitByAttribute(String data) {
		return data.split(",");
	}
	
	protected ArrayList<String> parseArray(String[] data, int index) {
		String[] splitData = data[index].split(";");
		return new ArrayList<String>(Arrays.asList(splitData));
	}
	
	protected int parseInteger(String[] data, int index) {
		return Integer.parseInt(data[index]);
	}
}
