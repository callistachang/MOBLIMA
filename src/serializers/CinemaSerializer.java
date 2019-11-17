package serializers;

import java.util.ArrayList;

import managers.ReviewManager;
import managers.ShowtimeManager;
import models.GoldCinema;
import models.ISerializable;
import models.PlatinumCinema;
import models.RegularCinema;
import models.Review;
import models.Showtime;

public class CinemaSerializer extends AbstractSerializer {

	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		ArrayList<Integer> showtimesInt = splitArrayToIntegers(d[1]);
		ArrayList<Showtime> showtimes = parseIdArrayToShowtime(showtimesInt);
//		System.out.println("CinemaSerializerShowtimes " + showtimesInt);
//		System.out.println("CinemaSerializerShowtimes " + showtimes);
		
		if (d[2].equals("Gold"))
			return new GoldCinema(d[0], showtimes);
		else if (d[2].equals("Platinum"))
			return new PlatinumCinema(d[0], showtimes);
		else 
			return new RegularCinema(d[0], showtimes);
	}
	
	public ArrayList<Showtime> parseIdArrayToShowtime(ArrayList<Integer> showtimeIDs) {
		ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
		ShowtimeManager sm = new ShowtimeManager();
		for (int id: showtimeIDs) {
			Showtime showtime = sm.getShowtimeByID(id);
			showtimes.add(showtime);
		}
		return showtimes;
	}

}
