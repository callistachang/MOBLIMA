package models;

import java.util.ArrayList;

import models.Movie.ShowingStatus;
/**
 * A group of cinemas housed in the same building. 
 * Contains a mix of cinema classes.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */
public class Cineplex implements ISerializable {
	/**
	 * Unique identification number of the cineplex.
	 * Written in XX format, where XX are the same repeated capital letter.
	 */
	private String id;
	/**
	 * The name of the cineplex.
	 */
	private String name;
	/**
	 * The list of cinemas housed in this cineplex.
	 */
	private ArrayList<Cinema> cinemas;
//	private ArrayList<Integer> movieIDs;
	/**
	 * Creates a new cineplex with a list of cinemas.
	 * @param id This cineplex's ID.
	 * @param name This cineplex's name.
	 * @param cinemas This cineplex's list of cinemas.
	 */
	public Cineplex(String id, String name, ArrayList<Cinema> cinemas) {
		this.id = id;
		this.name = name;
		this.cinemas = cinemas;
	}
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	@Override
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(id);
		data.add(name);
		data.add(getCinemaIDs());
		return data;
	}
	/**
	 * Gets the ID of the cineplex.
	 * @return This cineplex's ID.
	 */
	public String getId() {
		return id;
	}
	/**
	 * ID to set.
	 * @param id This cineplex's ID.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Gets name of the cineplex.
	 * @return This cineplex's name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Cineplex's name to set.
	 * @param name This cineplex's name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets list of cinemas in the Cineplex.
	 * @return This cineplex's cinemas.
	 */
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	/**
	 * List of cinemas to set.
	 * @param cinemas This cineplex's cinemas.
	 */
	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	/**
	 * Gets list of cinemas by their IDs in the cineplex.
	 * @return This cineplex's list of cinemas' IDs.
	 */
	public ArrayList<String> getCinemaIDs() {
		ArrayList<String> cinemasStr = new ArrayList<String>();
		for (Cinema c: cinemas) {
			cinemasStr.add(c.getId());
		}
		return cinemasStr;
	}



