package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.FavoriteGym;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteGymServiceImpl extends Service implements FavoriteGymService {

    private static final String INSERT_FAVORITE_GYM = "INSERT INTO favoritegym (gym_id,user_user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT * FROM favoritegym WHERE user_user_id=?";
    private static final String DELETE_FAVORITE_GYM = "DELETE FROM favoritegym WHERE id=?";

    private String urlDB;
    private String userDB;
    private String pswDB;

    public FavoriteGymServiceImpl(String url, String user, String psw) {
        super();
        this.urlDB = url;
        this.userDB = user;
        this.pswDB = psw;
    }

    @Override
    public long createFavoriteGym(FavoriteGym favoriteGym) throws SQLException {
        System.out.println("createFavoriteGym");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(INSERT_FAVORITE_GYM, Statement.RETURN_GENERATED_KEYS);) {
            st.setLong(1,favoriteGym.getGym());
            st.setLong(2,favoriteGym.getUser());
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
    public List<FavoriteGym> getAllFavoriteGym(long id) throws SQLException {
        System.out.println("getAllFavoriteGym");
        loadDriver();

        List<FavoriteGym> favoriteGyms = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FAVORITE_BY_USER);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    FavoriteGym favoriteGym = new FavoriteGym();
                    favoriteGym.setId(rs.getLong(1));
                    favoriteGym.setGym(rs.getLong(2));
                    favoriteGym.setUser(rs.getLong(3));

                    favoriteGyms.add(favoriteGym);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteGyms;
    }

    @Override
    public void deleteFavoriteGym(long id) throws SQLException {
        System.out.println("deleteFavoriteGym");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FAVORITE_GYM);) {
            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
