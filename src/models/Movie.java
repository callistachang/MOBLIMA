package models;

import java.util.ArrayList;

public class Movie {
	protected String title;
	protected showingStatus showingStatus;
	protected String synopsis;
	protected String director;
	protected ArrayList<String> casts;
	protected int duration;
	
//	public static ArrayList<String> getAttributeNames() {
//		ArrayList<String> attributeNames = new ArrayList<String>();
//		attributeNames.add("Title");
//		attributeNames.add("Showing Status");
//		attributeNames.add("Synopsis");
//		attributeNames.add("Director");
//		attributeNames.add("Casts");
//		attributeNames.add("Duration");
//	}
	
	@Override
	public String toString() {
		return "Movie [title=" + title + ", showingStatus=" + showingStatus + ", synopsis=" + synopsis + ", director="
				+ director + ", casts=" + casts + ", duration=" + duration + "]";
	}

	public enum showingStatus {
		COMING_SOON,
		NOW_SHOWING,
		NO_LONGER_SHOWING
	};
	
	public Movie(String title, String showingStatus, String director, String synopsis, ArrayList<String> casts, int duration) {
		this.title = title;
		this.showingStatus = showingStatusToEnum(showingStatus);
		this.synopsis = synopsis;
		this.director = director;
		this.casts = casts;
		this.duration = duration;
	}
	
	public String showingStatusToString(showingStatus statusEnum) {
		return statusEnum == showingStatus.COMING_SOON ? "Coming Soon" :
			   statusEnum == showingStatus.NOW_SHOWING ? "Now Showing" : "No Longer Showing";
	}
	
	public showingStatus showingStatusToEnum(String statusString) {
		return statusString.equals("Coming Soon") ? showingStatus.COMING_SOON :
			   statusString.equals("Now Showing") ? showingStatus.NOW_SHOWING : showingStatus.NO_LONGER_SHOWING;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShowingStatus() {
		return showingStatusToString(showingStatus);
	}

	public void setShowingStatus(String showingStatus) {
		this.showingStatus = showingStatusToEnum(showingStatus);
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
}
