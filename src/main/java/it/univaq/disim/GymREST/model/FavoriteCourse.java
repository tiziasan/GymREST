package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FavoriteCourse {

	private Long id;
	private User user;
	private Course course;

	public FavoriteCourse() {
		this.id = null;
		this.user = null;
		this.course = null;
	}

	public FavoriteCourse(Long id, User user, Course course) {
		this.id = id;
		this.user = user;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		if (!(o instanceof FavoriteCourse)) return false;
		FavoriteCourse that = (FavoriteCourse) o;
		return getId().equals(that.getId()) &&
				getUser().equals(that.getUser()) &&
				getCourse().equals(that.getCourse());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUser(), getCourse());
	}
}