package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.Gym;

import java.sql.*;
import java.util.*;

public class FavoriteGymServiceImpl extends Service implements FavoriteGymService {

    private static final String INSERT_FAVORITE_GYM = "INSERT INTO favoritegym (gym_id,user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT gym.id, gym.address, gym.name, gym.province, gym.region, favoritegym.id FROM gym LEFT JOIN favoritegym ON favoritegym.gym_id = gym.id WHERE favoritegym.user_id=?";
    private static final String DELETE_FAVORITE_GYM = "DELETE FROM favoritegym WHERE user_id=? AND gym_id=?";

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
    public long createFavoriteGym(FavoriteGym favoriteGym) throws ServiceException {
        System.out.println("[SERVICE] FavoriteGym - createFavoriteGym");
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
            throw new ServiceException(e.getErrorCode());
        }
        return 0;

    }

    @Override
    public List<Gym> getAllFavoriteGym(long idUser) throws ServiceException {
        System.out.println("[SERVICE] FavoriteGym - getAllFavoriteGym");
        loadDriver();

        List<Gym> favoriteGyms = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FAVORITE_BY_USER);) {
            st.setLong(1,idUser);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    Gym gym = new Gym();
                    gym.setId(rs.getLong(1));
                    gym.setName(rs.getString(3));
                    gym.setRegion(rs.getString(5));
                    gym.setProvince(rs.getString(4));
                    gym.setAddress(rs.getString(2));

                    favoriteGyms.add(gym);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return favoriteGyms;
    }

    @Override
    public void deleteFavoriteGym(long idUser, long idGym) throws ServiceException {
        System.out.println("[SERVICE] FavoriteGym - deleteFavoriteGym");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FAVORITE_GYM);) {

            st.setLong(1,idUser);
            st.setLong(2,idGym);
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

}
