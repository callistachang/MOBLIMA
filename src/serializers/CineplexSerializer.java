package serializers;

import models.Cineplex;
import models.ISerializable;

import java.util.ArrayList;

import managers.CinemaManager;
import models.Cinema;

public class CineplexSerializer extends AbstractSerializer {

	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		ArrayList<String> cinemaIDs = parseArrayToStrings(d, 2);
		ArrayList<Cinema> cinemas = parseIdArrayToCinemas(cinemaIDs);
		
		// TODO Auto-generated method stub
		return new Cineplex(d[0], d[1], cinemas);
	}
	
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
