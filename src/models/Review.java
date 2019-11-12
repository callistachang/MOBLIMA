package models;

public class Review {
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
}
