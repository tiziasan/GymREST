package it.univaq.disim.GymREST.business;

import java.sql.SQLException;
import java.util.List;

import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.Gym;

public interface FavoriteGymService {

    long createFavoriteGym (FavoriteGym favoriteGym) throws SQLException;
    List<Gym> getAllFavoriteGym(long id) throws SQLException;
    void deleteFavoriteGym(long id) throws SQLException;





}
