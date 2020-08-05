package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.model.Gym;
import it.univaq.disim.GymREST.model.User;

import java.sql.*;

public class UserServiceImpl extends Service implements UserService {

    private static final String CHECK_USER = "SELECT COUNT(1) FROM user WHERE user_name=? AND password=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String UPDATE_USER = "UPDATE user SET email=?, last_name=?, name=?, password=?, user_name=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String CREATE_USER = "INSERT INTO user (email,last_name,name,password,user_name) VALUES (?,?,?,?,?)";

    private String urlDB;
    private String userDB;
    private String pswDB;

    public UserServiceImpl(String url, String user, String psw) {
        super();
        this.urlDB = url;
        this.userDB = user;
        this.pswDB = psw;
    }

    @Override
    public boolean checkUser(String username, String password) {
        System.out.println("checkUser");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(CHECK_USER);) {
            st.setString(1,username);
            st.setString(2,password);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    if (rs.getInt(1) == 1){
                        System.out.println("user exists");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("user doesn't exist");
        return false;
    }

    @Override
    public long createUser(User user) {
        System.out.println("createUser");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);) {

            st.setString(1, user.getEmail());
            st.setString(2, user.getLastName());
            st.setString(3, user.getName());
            st.setString(4, user.getPassword());
            st.setString(5, user.getUserName());

            st.execute();

            try (ResultSet result = st.getGeneratedKeys();) {
                if (result.next()) {
                    return result.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void deleteUser(long id) {
        System.out.println("deleteUser");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_USER);) {

            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        System.out.println("updateUser");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(UPDATE_USER);) {

            st.setString(1, user.getEmail());
            st.setString(2, user.getLastName());
            st.setString(3, user.getName());
            st.setString(4, user.getPassword());
            st.setString(5, user.getUserName());

            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(long id) {
        System.out.println("getUser");
        loadDriver();

        User user = new User();
        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(GET_USER_BY_ID);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                    user.setEmail(rs.getString(3));
                    user.setLastName(rs.getString(4));
                    user.setName(rs.getString(5));
                    user.setPassword(rs.getString(6));
                    user.setUserName(rs.getString(7));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
