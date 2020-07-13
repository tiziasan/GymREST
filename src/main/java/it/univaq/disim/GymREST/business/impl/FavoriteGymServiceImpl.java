package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.FavoriteGym;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FavoriteGymServiceImpl extends Service implements FavoriteGymService {

    private static final String INSERT_FAVORITE_GYM = "INSERT INTO favoritegym (gym_id,user_user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT * FROM favoritegym WHERE user_user_id=?";
    private static final String DELETE_FAVORITE_GYM = "DELETE FROM favoritegym WHERE id=?";



    @Override
    public long createFavoriteGym(FavoriteGym favoriteGym) throws SQLException {
        System.out.println("createFavoriteGym");

        try {
            PreparedStatement st = getConnection().prepareStatement(INSERT_FAVORITE_GYM, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,favoriteGym.getGym().getId());
            st.setLong(2,favoriteGym.getUser().getId());
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
    public List<FavoriteGym> getAllFavoriteGym(long id) throws SQLException {
        System.out.println("getAllFavoriteGymBYUser");

        List<FavoriteGym> favoriteGyms = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_FAVORITE_BY_USER);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                FavoriteGym favoriteGym = new FavoriteGym( rs.getLong(1),
                        null,
                        null
                );
                favoriteGyms.add(favoriteGym);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return favoriteGyms;
    }

    @Override
    public void deleteFavoriteGym(long id) throws SQLException {
        System.out.println("deleteFavorite");

        try {
            PreparedStatement st = getConnection().prepareStatement(DELETE_FAVORITE_GYM);
            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

    }
}
