package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.User;


public interface UserService {

    boolean checkUser(String username, String password) throws ServiceException;
    boolean checkRole(String username, String role) throws ServiceException;
    User getUserById(long id) throws ServiceException;
    User getUserByIdWithoutPassword(long id) throws ServiceException;
    User getUserByUsername(String username) throws ServiceException;
    long createUser(User user) throws ServiceException;
    void addRoleToUser(long idUser) throws ServiceException;
    void deleteUser(long id) throws ServiceException;
    void updateUser(User user) throws ServiceException;
}
