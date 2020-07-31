package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FeedbackCourse {

	private Long id;
	private String feed;
	private int rating;
	private long user;
	private long course;

	public FeedbackCourse() {
		this.id = null;
		this.feed = "";
		this.rating = 0;
		this.user = 0;
		this.course = 0;
	}

	public FeedbackCourse(Long id, String feed, int rating, long user, long course) {
		this.id = id;
		this.feed = feed;
		this.rating = rating;
		this.user = user;
		this.course = course;
	}

	public Long getId() {
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

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getCourse() {
		return course;
	}

	public void setCourse(long course) {
		this.course = course;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FeedbackCourse)) return false;
		FeedbackCourse that = (FeedbackCourse) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
