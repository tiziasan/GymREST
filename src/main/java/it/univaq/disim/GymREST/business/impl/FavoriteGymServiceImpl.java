package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.Gym;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class FavoriteGymServiceImpl extends Service implements FavoriteGymService {

    private static final String INSERT_FAVORITE_GYM = "INSERT INTO favoritegym (gym_gym_id,user_user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT gym.gym_id, gym.address, gym.name, gym.province, gym.region, favoritegym.id FROM gym LEFT JOIN favoritegym ON favoritegym.gym_gym_id = gym.gym_id WHERE favoritegym.user_user_id=?";
    private static final String DELETE_FAVORITE_GYM = "DELETE FROM favoritegym WHERE user_user_id=? AND gym_gym_id=?";

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
    public long createFavoriteGym(FavoriteGym favoriteGym){
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
    public Map<Long, Gym> getAllFavoriteGym(long idUser){
        System.out.println("getAllFavoriteGym");
        loadDriver();

        Map<Long,Gym> favoriteGyms = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FAVORITE_BY_USER);) {
            st.setLong(1,idUser);

            try (ResultSet rs = st.executeQuery();) {
                Gym gym;
                long key;
                while (rs.next()){
                    gym = new Gym();
                    gym.setId(rs.getLong(1));
                    gym.setName(rs.getString(3));
                    gym.setRegion(rs.getString(5));
                    gym.setProvince(rs.getString(4));
                    gym.setAddress(rs.getString(2));

                    key = rs.getLong(6);
                    favoriteGyms.put(key, gym);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteGyms;
    }

    @Override
    public void deleteFavoriteGym(long idUser, long idGym){
        System.out.println("deleteFavoriteGym");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FAVORITE_GYM);) {

            st.setLong(1,idUser);
            st.setLong(2,idGym);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
