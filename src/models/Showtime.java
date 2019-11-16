package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import managers.Formatter;
import managers.MovieManager;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;

public class Showtime implements ISerializable {
	private int id;
	private LocalDate date;
	private LocalTime time;
	private int movieID;
	private ArrayList <Integer> seatsTaken; // HOW should i serialize this.

	//	public Cinema cinema;
	// NOT SURE if we should do the conversion of date string to LocalDate in the constructor OR in the ShowtimeManager
	// Since this is technically like the 'weak entity' between Movie and Cinema, should we include transactional details
	// E.g. Whether the movie is 3D or something, lol

	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(Formatter.getStringFromInt(id));
		data.add(Formatter.getStringFromLocalDate(date));
		data.add(Formatter.getStringFromLocalTime(time));
		data.add(Formatter.getStringFromInt(movieID));
		data.add(Formatter.getStringArrayFromIntegerArray(seatsTaken));
		return data;
	}

	public Showtime(int id, LocalDate date, LocalTime time, int movieID, ArrayList<Integer> seatsTaken) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.movieID = movieID;
		this.seatsTaken = seatsTaken;
	}
	
	

	public int getId() {
		return id;
	}

	public ArrayList<Integer> getSeatsTaken() {
		return seatsTaken;
	}
	
	public ArrayList<String> getSeatsTakenAsString() {
		ArrayList<String> seatsTakenStrings = new ArrayList<String>();
		
		for (int i: this.seatsTaken) {
			seatsTakenStrings.add(String.valueOf(i));
		}
		return seatsTakenStrings;
	}
	

	public void setId(int id) {
		this.id = id;
	}


	public void setSeatsTaken(ArrayList<Integer> seatsTaken) {
		this.seatsTaken = seatsTaken;
	}


	public Showtime(int movieID, Cinema cinema, LocalDate date, LocalTime time) {
		this.movieID = movieID;
//		this.cinema = cinema;
		this.date = date;
		this.time = time;
	}

	public String getDateAsString() {
		return this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getDateAsTime() {
		return this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}


	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public int getNoSeatsTaken() {
		return seatsTaken.size();
	}
	
	public void bookSeat(int seatNo) {
		seatsTaken.add(seatNo);
	}
	
	public void setAttr(int attrNum, String attrVal) {
		switch (attrNum) {
			case 1:
				movieID = Integer.parseInt(attrVal);
				break;
			case 2:
				date = Formatter.getLocalDateFromString(attrVal);
				break;
			case 3:
				time = Formatter.getLocalTimeFromString(attrVal);
				break;
			
		}
	}	
}
