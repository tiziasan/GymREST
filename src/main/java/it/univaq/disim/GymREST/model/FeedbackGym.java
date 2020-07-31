package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FeedbackGym {

	private long id;
	private String feed;
	private int rating;
	private User user;
	private Gym gym;

	public FeedbackGym() {
		this.id = 0;
		this.feed = "";
		this.rating = 0;
		this.user = null;
		this.gym = null;
	}

	public FeedbackGym(long id, String feed, int rating, User user, Gym gym) {
		this.id = id;
		this.feed = feed;
		this.rating = rating;
		this.user = user;
		this.gym = gym;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
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
