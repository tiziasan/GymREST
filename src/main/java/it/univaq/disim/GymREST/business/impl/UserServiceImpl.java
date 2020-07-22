package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServiceImpl extends Service implements UserService {

    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String UPDATE_USER = "UPDATE users SET email=?, last_name=?, name=?, password=?, user_name=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id=?";
    private static final String CREATE_USER = "INSERT INTO users (email,last_name,name,password,user_name) VALUES (?,?,?,?,?)";


    @Override
    public long createUser(User user) throws SQLException {
        System.out.println("createUser");

        try {
            PreparedStatement st = getConnection().prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getEmail());
            st.setString(2, user.getLastName());
            st.setString(3, user.getName());
            st.setString(4, user.getPassword());
            st.setString(5, user.getUserName());
            st.execute();

            ResultSet result = st.getGeneratedKeys();
            if (result.next()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return 0;
    }

    @Override
    public void deleteUser(long id) throws SQLException {
        System.out.println("deleteUser");

        try {
            PreparedStatement st = getConnection().prepareStatement(DELETE_USER);
            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

    }

    @Override
    public void updateUser(User user) throws SQLException {
        System.out.println("updateUser");

        try {
            PreparedStatement st = getConnection().prepareStatement(UPDATE_USER);
            st.setString(1, user.getEmail());
            st.setString(2, user.getLastName());
            st.setString(3, user.getName());
            st.setString(4, user.getPassword());
            st.setString(5, user.getUserName());
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

    }

    @Override
    public User getUser(long id) throws SQLException {
        System.out.println("getUser");

        User user = new User();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_USER_BY_ID);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setEmail(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setName(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setUserName(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return user;    }
}
