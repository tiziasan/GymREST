package it.univaq.disim.GymREST.model;

import java.util.Objects;

public class Role {

    private long id;
    private String role;

    public Role() {
        this.id = 0;
        this.role = "";
    }

    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return getId() == role1.getId() &&
                getRole().equals(role1.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole());
    }
}
