package serializers;

import models.Cineplex;

import models.ISerializable;

import java.util.ArrayList;

import managers.CinemaManager;
import models.Cinema;

/**
 * Handles the deserialization for Cineplex class.
 * @version 1.0
 * @since 2019-11-17
 * @author balad
 *
 */
public class CineplexSerializer extends AbstractSerializer {

	/**
	 * Converts data of string type to Cineplex type.
	 */
	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		ArrayList<String> cinemaIDs = splitArrayToStrings(d[2]);
		ArrayList<Cinema> cinemas = parseIdArrayToCinemas(cinemaIDs);
		
		return new Cineplex(d[0], d[1], cinemas);
	}
	/**
	 * Array of cinemaID converted into array of cinema objects.
	 * @param cinemaIDs Array of unique cinema identification number to be converted.
	 * @return Array of cinema objects.
	 */
	public ArrayList<Cinema> parseIdArrayToCinemas(ArrayList<String> cinemaIDs) {
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		CinemaManager cm = new CinemaManager();
		for (String id: cinemaIDs) {
			Cinema cinema = cm.getCinemaByID(id);
			cinemas.add(cinema);
		}
		return cinemas;
	}
}
