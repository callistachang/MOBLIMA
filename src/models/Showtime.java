package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Showtime {
	public Movie movie;
	public Cinema cinema;
	// NOT SURE if we should do the conversion of date string to LocalDate in the constructor OR in the ShowtimeManager
	public LocalDate date;
	public LocalTime time;
	// Since this is technically like the 'weak entity' between Movie and Cinema, should we include transactional details
	// E.g. Whether the movie is 3D or something, lol
	
	public Showtime(Movie movie, Cinema cinema, LocalDate date, LocalTime time) {
		this.movie = movie;
		this.cinema = cinema;
		this.date = date;
		this.time = time;
	}
	
	public String formatDateToString() {
		return this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String formatTimeToString() {
		return this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
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
}
