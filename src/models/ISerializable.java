package models;

import java.util.ArrayList;
/**
 * Interface for all serializable entities.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */
public interface ISerializable {
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData();
}