package models;

import java.util.ArrayList;

public abstract class Cinema implements ISerializable {
	private String id; // must be cinema code
	private ArrayList<Showtime> showtimes;
	
	public Cinema(String id, ArrayList<Showtime> showtimes) {
		this.id = id;
		this.showtimes = showtimes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Showtime> getShowtimes() {
		return showtimes;
	}
	public void setShowtimes(ArrayList<Showtime> showtimes) {
		this.showtimes = showtimes;
	}
	public abstract String getCinemaClass();
	
	public abstract int getRows();
	
	public abstract int getCols();
	
	public int getTotalNoSeats() {
		return getRows()*getCols();
	}
	
	public int getNoSeatsAvailable(Showtime showtime) {
		return getTotalNoSeats() - showtime.getNoSeatsTaken();
	}
	
	public void printSeatingPlan(Showtime showtime) {
		int i, j;
		System.out.print(" ");
		
		for(i=0; i<getCols(); i++) {
			if(i+1<10) {
				System.out.print(" 0" + (i+1) + " ");
			}
			else {
				System.out.print(" " + (i+1) + " ");
			}
		}
		System.out.println();
		
		for(i=0; i<getRows(); i++) {
			System.out.print((char)('A' + i));
			for(j=0; j<getCols(); j++) {
				if(showtime.getSeatsTaken().contains(i*getCols() + j + 1)) {
					System.out.print("(xx)");
				}
				else {
					System.out.print("(__)");
				}
				
			}
			System.out.println();
		}
	}
	
	public void addShowtime(Showtime showtime) {
		showtimes.add(showtime);
	}
}
