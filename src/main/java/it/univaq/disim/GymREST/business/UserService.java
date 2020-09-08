package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.User;

import java.sql.SQLException;

public interface UserService {

    boolean checkUser(String username, String password) throws SQLException;
    boolean checkRole(String username, String role);
    User getUserById(long id) throws SQLException;
    User getUserByUsername(String username);
    long createUser(User user) throws SQLException, ServiceException;
    void addRoleToUser(long idUser) throws SQLException;
    void deleteUser(long id) throws SQLException;
    void updateUser(User user) throws SQLException;
}
