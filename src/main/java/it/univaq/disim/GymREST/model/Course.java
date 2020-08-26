package it.univaq.disim.GymREST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

public class Course {

	private long id;
	private String name;
	private String description;
	private String code;

	@JsonIgnore
	private List<FeedbackCourse> feedbackCourse;
	@JsonIgnore
	private List<FavoriteCourse> favoriteCourse;
	@JsonIgnore
	private long gym;

	public Course() {
		this.id = 0;
		this.name = "";
		this.description = "";
		this.code = "";
		this.gym = 0;
	}

	public Course(long id, String name, String description, String code, long gym) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.code = code;
		this.gym = gym;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<FeedbackCourse> getFeedbackCourse() {
		return feedbackCourse;
	}

	public void setFeedbackCourse(List<FeedbackCourse> feedbackCourse) {
		this.feedbackCourse = feedbackCourse;
	}

	public List<FavoriteCourse> getFavoriteCourse() {
		return favoriteCourse;
	}

	public void setFavoriteCourse(List<FavoriteCourse> favoriteCourse) {
		this.favoriteCourse = favoriteCourse;
	}

	public long getGym() {
		return gym;
	}

	public void setGym(long gym) {
		this.gym = gym;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Course)) return false;
		Course course = (Course) o;
		return getId() == course.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getCode());
	}
}
