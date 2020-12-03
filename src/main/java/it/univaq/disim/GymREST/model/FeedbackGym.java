package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FeedbackGym {

	private long id;
	private String feed;
	private int rating;
	private long user;
	private String userName;
	private String userLastname;
	private long gym;
	private String gymName;

	public FeedbackGym() {
		this.id = 0;
		this.feed = "";
		this.rating = 0;
		this.user = 0;
		this.gym = 0;
	}

	public FeedbackGym(long id, String feed, int rating, long user, long gym) {
		this.id = id;
		this.feed = feed;
		this.rating = rating;
		this.user = user;
		this.gym = gym;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getGym() {
		return gym;
	}

	public void setGym(long gym) {
		this.gym = gym;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FeedbackGym)) return false;
		FeedbackGym that = (FeedbackGym) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUser(), getGym());
	}
}
