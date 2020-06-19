package it.univaq.disim.GymREST.model;

public class FeedbackCourse {

	private Long id;
	private String feed;
	private int rating;
	private User user;
	private Course course;

	public FeedbackCourse() {
		this.id = null;
		this.feed = "";
		this.rating = 0;
		this.user = null;
		this.course = null;
	}

	public FeedbackCourse(Long id, String feed, int rating, User user, Course course) {
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


}
