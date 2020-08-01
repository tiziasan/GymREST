package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class FavoriteGym {

	private long id;
	private long user;
	private long gym;

	public FavoriteGym() {
		this.id = 0;
		this.user = 0;
		this.gym = 0;
	}

	public FavoriteGym(long id, long user, long gym) {
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

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
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
		if (!(o instanceof FavoriteGym)) return false;
		FavoriteGym that = (FavoriteGym) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUser(), getGym());
	}
}
