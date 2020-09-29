package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.Role;
import it.univaq.disim.GymREST.model.User;

import java.sql.*;

public class UserServiceImpl extends Service implements UserService {

    private static final String CHECK_USER = "SELECT COUNT(1) FROM user WHERE username=? AND password=?";
    private static final String CHECK_ROLE = "SELECT COUNT(1) FROM user WHERE username=? AND role=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String GET_USER_BY_USERNAME = "SELECT * FROM user WHERE username=?";
    private static final String UPDATE_USER = "UPDATE user SET email=?, lastname=?, name=?, password=?, username=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String CREATE_USER = "INSERT INTO user (email,lastname,name,password,username,role) VALUES (?,?,?,?,?,?)";
    private static final String ADD_ROLE_TO_USER = "UPDATE user SET role=? WHERE id=?)";


    @Override
    public boolean checkUser(String username, String password) throws ServiceException {
        System.out.println("[SERVICE] User - checkUser");

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(CHECK_USER);) {
            st.setString(1,username);
            st.setString(2,password);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    if (rs.getInt(1) == 1){
                        System.out.println("User exists");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        System.out.println("User doesn't exist");
        return false;
    }

    @Override
    public boolean checkRole(String username, String role) throws ServiceException {
        System.out.println("[SERVICE] User - checkRole");

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(CHECK_ROLE);) {
            st.setString(1,username);
            st.setString(2,role);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    if (rs.getInt(1) == 1){
                        System.out.println("User has role " + role);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        System.out.println("User hasn't role " + role);
        return false;
    }

    @Override
    public long createUser(User user) throws ServiceException {
        System.out.println("[SERVICE] User - createUser");

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);) {

            st.setString(1, user.getEmail());
            st.setString(2, user.getLastname());
            st.setString(3, user.getName());
            st.setString(4, user.getPassword());
            st.setString(5, user.getUsername());
            st.setString(6, Role.CUSTOMER.getValue());

            st.execute();

            try (ResultSet result = st.getGeneratedKeys();) {
                if (result.next()) {

                    return result.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return 0;
    }

    @Override
    public void addRoleToUser(long idUser, Role role) throws ServiceException {
        System.out.println("[SERVICE] User - addRoleToUser");

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(ADD_ROLE_TO_USER);) {
            st.setString(1, role.getValue());
            st.setLong(2, idUser);

            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

    @Override
    public User getUserById(long id) throws ServiceException {
        System.out.println("[SERVICE] User - getUser");

        User user = new User();
        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(GET_USER_BY_ID);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                    user.setEmail(rs.getString(2));
                    user.setLastname(rs.getString(3));
                    user.setName(rs.getString(4));
//                    user.setPassword(rs.getString(5));
                    user.setRole(Role.valueOf(rs.getString(6)));
                    user.setUsername(rs.getString(7));
                }
            }

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws ServiceException {
        System.out.println("[SERVICE] User - getUser");

        User user = new User();
        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(GET_USER_BY_USERNAME);) {
            st.setString(1,username);

            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                    user.setEmail(rs.getString(2));
                    user.setLastname(rs.getString(3));
                    user.setName(rs.getString(4));
//                    user.setPassword(rs.getString(5));
                    user.setRole(Role.valueOf(rs.getString(6)));
                    user.setUsername(rs.getString(7));
                }
            }

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        System.out.println("[SERVICE] User - updateUser");

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(UPDATE_USER);) {

            st.setString(1, user.getEmail());
            st.setString(2, user.getLastname());
            st.setString(3, user.getName());
            st.setString(4, user.getPassword());
            st.setString(5, user.getUsername());

            st.setLong(6, user.getId());

            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

    @Override
    public void deleteUser(long id) throws ServiceException {
        System.out.println("[SERVICE] User - deleteUser");

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_USER);) {

            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }


}
