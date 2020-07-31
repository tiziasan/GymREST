package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FeedbackCourse {

	private long id;
	private String feed;
	private int rating;
	private User user;
	private Course course;

	public FeedbackCourse() {
		this.id = 0;
		this.feed = "";
		this.rating = 0;
		this.user = null;
		this.course = null;
	}

	public FeedbackCourse(long id, String feed, int rating, User user, Course course) {
		this.id = id;
		this.feed = feed;
		this.rating = rating;
		this.user = user;
		this.course = course;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FeedbackCourse)) return false;
		FeedbackCourse that = (FeedbackCourse) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
