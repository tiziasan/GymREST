package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.model.User;

import java.sql.SQLException;

public interface UserService {

    long createUser(User user) throws SQLException;
    void deleteUser(long id) throws SQLException;
    void updateUser(User user) throws SQLException;
    User getUser(long id) throws SQLException;
}
