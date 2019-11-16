package models;

import java.util.ArrayList;

import models.Movie.ShowingStatus;

public class Cineplex implements ISerializable {
	private String id;
	private String name;
	private ArrayList<Cinema> cinemas;
//	private ArrayList<Integer> movieIDs;
	
	public Cineplex(String id, String name, ArrayList<Cinema> cinemas) {
		this.id = id;
		this.name = name;
		this.cinemas = cinemas;
	}
	
	@Override
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(id);
		data.add(name);
		data.add(getCinemaIDs());
		return data;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	public ArrayList<String> getCinemaIDs() {
		ArrayList<String> cinemasStr = new ArrayList<String>();
		for (Cinema c: cinemas) {
			cinemasStr.add(c.getId());
		}
		return cinemasStr;
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





