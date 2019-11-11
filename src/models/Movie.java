package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Movie implements ISerializable {
	protected ArrayList<Integer> reviewID;
	protected String title;
	protected String showingStatus;
	protected String synopsis;
	protected String director;
	protected ArrayList<String> casts;
	protected int duration;

//	public enum showingStatus {
//		COMING_SOON("Coming Soon"),
//		NOW_SHOWING("Now Showing"),
//		NO_LONGER_SHOWING("No Longer Showing");
//		
//		private String text;
//		
//		showingStatus(String text) {
//			this.text = text;
//		}
//		
//		public String getText() {
//			return text;
//		}
//		
//		public static showingStatus fromString(String text) {
//			for (showingStatus ss: values()) {
//				if (ss.text.equalsIgnoreCase(text))
//					return ss;
//			}
//			return null;
//		}
//	};
	
	public Movie(String title, String showingStatus, String director, String synopsis, ArrayList<String> casts, int duration) {
		this.title = title;
		this.showingStatus = showingStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.casts = casts;
		this.duration = duration;
	}
	
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data =  new ArrayList<Object>();
		data.add(getTitle());
		data.add(getShowingStatus());
		data.add(getSynopsis());
		data.add(getDirector());
		data.add(getCasts());
		data.add(getDuration());
		return data;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShowingStatus() {
		return showingStatus;
	}

	public void setShowingStatus(String showingStatus) {
		this.showingStatus = showingStatus;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public ArrayList<String> getCasts() {
		return casts;
	}

	public void setCasts(ArrayList<String> casts) {
		this.casts = casts;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<Integer> getReviewID() {
		return reviewID;
	}

	public void setReviewID(ArrayList<Integer> reviewID) {
		this.reviewID = reviewID;
	}
	public void printReview() {
		ArrayList<Integer> id;
		int i;
		id = getReviewID();
		for(i=0; i<id.size(); i++) {
			
		}
	}
}
