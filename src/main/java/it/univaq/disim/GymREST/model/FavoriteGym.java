package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FavoriteGym {

	private long id;
	private User user;
	private Gym gym;

	public FavoriteGym() {
		this.id = 0;
		this.user = null;
		this.gym = null;
	}

	public FavoriteGym(long id, User user, Gym gym) {
		this.id = id;
		this.user = user;
		this.gym = gym;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FavoriteGym)) return false;
		FavoriteGym that = (FavoriteGym) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUser(), getGym());
	}
}
