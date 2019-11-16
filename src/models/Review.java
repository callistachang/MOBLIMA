package models;

import java.util.ArrayList;

import managers.Formatter;

public class Review implements ISerializable {
	private int id;
	private int rating;
	private Account user;
	private String content;
	
	public Review(int id, int rating, Account user, String content) {
		this.id = id;
		this.rating = rating;
		this.user = user;
		this.content = content;
	}
	
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(Formatter.getStringFromInt(id));
		data.add(Formatter.getStringFromInt(rating));
		data.add(user.getUsername());
		data.add(content);
		return data;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return String.valueOf(id);
	}
}
