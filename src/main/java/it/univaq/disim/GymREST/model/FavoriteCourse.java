package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FavoriteCourse {

	private long id;
	private long user;
	private long course;

	public FavoriteCourse() {
		this.id = 0;
		this.user = 0;
		this.course = 0;
	}

	public FavoriteCourse(long id, long user, long course) {
		this.id = id;
		this.user = user;
		this.course = course;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		if (!(o instanceof FavoriteCourse)) return false;
		FavoriteCourse that = (FavoriteCourse) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUser(), getCourse());
	}
}
