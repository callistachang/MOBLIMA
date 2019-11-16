package serializers;

import java.util.ArrayList;

import models.GoldCinema;
import models.ISerializable;
import models.PlatinumCinema;
import models.RegularCinema;
import models.Showtime;

public class CinemaSerializer extends AbstractSerializer {

	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		ArrayList<Showtime> showtimes = null;
		
		if (d[2].equals("Gold"))
			return new GoldCinema(d[0], showtimes);
		else if (d[2].equals("Platinum"))
			return new PlatinumCinema(d[0], showtimes);
		else 
			return new RegularCinema(d[0], showtimes);
	}

}
