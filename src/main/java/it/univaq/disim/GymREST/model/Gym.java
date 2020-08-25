package it.univaq.disim.GymREST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

public class Gym {

	private long id;
	private String name;
	private String address;
	private String province;
	private String region;
	private long user;

	@JsonIgnore
	private List<FeedbackGym> feedbackGym;
	@JsonIgnore
	private List<FavoriteGym> favoriteGym;
	@JsonIgnore
	private List<Course> course;


	public Gym() {
		this.id = 0;
		this.name = "";
		this.address = "";
		this.province = "";
		this.region = "";
		this.user = 0;
	}

	public Gym(long id, String name, String address, String province, String region, long user) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.province = province;
		this.region = region;
		this.user = user;
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

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Gym)) return false;
		Gym gym = (Gym) o;
		return getId() == gym.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Gym{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", province='" + province + '\'' +
				", region='" + region + '\'' +
				'}';
	}
}
