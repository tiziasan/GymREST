package it.univaq.disim.GymREST.business;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.Gym;

public interface FavoriteGymService {

    long createFavoriteGym (FavoriteGym favoriteGym) throws SQLException;
    Map<Long, Gym> getAllFavoriteGym(long idUser) throws SQLException;
    void deleteFavoriteGym(long idUser, long idGym) throws SQLException;

}
