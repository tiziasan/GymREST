package it.univaq.disim.GymREST.model;


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
	
	private List<FeedbackCourse> feedbackCourse;
	
	private List<FavoriteGym> favoriteGym;
	
	private List<FavoriteCourse> favoriteCourse;
	
	private List<FeedbackGym> feedbackGym;
	

	private Set<Role> roles;
	
	private List<Gym> gym;

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

	public List<Gym> getGym() {
		return gym;
	}

	public void setGym(List<Gym> gym) {
		this.gym = gym;
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
