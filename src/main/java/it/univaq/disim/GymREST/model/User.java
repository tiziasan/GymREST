package it.univaq.disim.GymREST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {

	private Long id;
	private String userName;
	private String email;
	private String password;
	private String name;
	private String lastName;
	private Boolean active;

	@JsonIgnore
	private List<FeedbackCourse> feedbackCourse;
	@JsonIgnore
	private List<FavoriteGym> favoriteGym;
	@JsonIgnore
	private List<FavoriteCourse> favoriteCourse;
	@JsonIgnore
	private List<FeedbackGym> feedbackGym;
	@JsonIgnore
	private Set<Role> roles;
	@JsonIgnore
	private List<Gym> gyms;

	public User() {
		this.id = null;
		this.userName = "";
		this.email = "";
		this.password = "";
		this.name = "";
		this.lastName = "";
		this.active = null;
	}

	public User(Long id, String userName, String email, String password, String name, String lastName, Boolean active) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<FeedbackCourse> getFeedbackCourse() {
		return feedbackCourse;
	}

	public void setFeedbackCourse(List<FeedbackCourse> feedbackCourse) {
		this.feedbackCourse = feedbackCourse;
	}

	public List<FavoriteGym> getFavoriteGym() {
		return favoriteGym;
	}

	public void setFavoriteGym(List<FavoriteGym> favoriteGym) {
		this.favoriteGym = favoriteGym;
	}

	public List<FavoriteCourse> getFavoriteCourse() {
		return favoriteCourse;
	}

	public void setFavoriteCourse(List<FavoriteCourse> favoriteCourse) {
		this.favoriteCourse = favoriteCourse;
	}

	public List<FeedbackGym> getFeedbackGym() {
		return feedbackGym;
	}

	public void setFeedbackGym(List<FeedbackGym> feedbackGym) {
		this.feedbackGym = feedbackGym;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Gym> getGyms() {
		return gyms;
	}

	public void setGym(List<Gym> gyms) {
		this.gyms = gyms;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return getId().equals(user.getId()) &&
				getUserName().equals(user.getUserName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUserName());
	}
}
