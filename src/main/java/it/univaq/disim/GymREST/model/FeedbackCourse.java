package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FeedbackCourse {

	private long id;
	private String feed;
	private int rating;
	private long user;
	private String userName;
	private String userLastname;
	private long course;
	private String courseName;

	public FeedbackCourse() {
		this.id = 0;
		this.feed = "";
		this.rating = 0;
		this.user = 0;
		this.course = 0;
	}

	public FeedbackCourse(long id, String feed, int rating, long user, long course) {

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
		if (!(o instanceof FeedbackCourse)) return false;
		FeedbackCourse that = (FeedbackCourse) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
