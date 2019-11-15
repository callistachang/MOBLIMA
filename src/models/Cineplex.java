package models;

import java.util.ArrayList;

import models.Movie.ShowingStatus;

public class Cineplex {
	private int id;
	private String name;
	private ArrayList<Cinema> cinemas;
//	private ArrayList<Integer> movieIDs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
//	public ArrayList<Movie> getMovies() {
//		return movies;
//	}
//	public void setMovies(ArrayList<Movie> movies) {
//		this.movies = movies;
//	}
//	
//	public ArrayList<Movie> getShowingMovies() {
//		ArrayList<Movie> showingMovies = new ArrayList<Movie>();
//		for (Movie m: movies) {
//			if (m.getStatus() == ShowingStatus.NOW_SHOWING) {
//				showingMovies.add(m);
//			}
//		}
//		return showingMovies;
//	}
}





