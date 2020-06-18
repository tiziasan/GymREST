package it.univaq.disim.GymREST.model;


import java.util.List;
import java.util.Objects;

public class Gym {
	

	private Long id;
	

	private String name;
	

	private String address;
	

	private String province;
	

	private String region;
	
	private List<FeedbackGym> feedbackGym;
	
	private List<FavoriteGym> favoriteGym;
	
	private List<Course> course;

	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<FeedbackGym> getFeedbackGym() {
		return feedbackGym;
	}

	public void setFeedbackGym(List<FeedbackGym> feedbackGym) {
		this.feedbackGym = feedbackGym;
	}

	public List<FavoriteGym> getFavoriteGym() {
		return favoriteGym;
	}

	public void setFavoriteGym(List<FavoriteGym> favoriteGym) {
		this.favoriteGym = favoriteGym;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Gym)) return false;
		Gym gym = (Gym) o;
		return getId().equals(gym.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
