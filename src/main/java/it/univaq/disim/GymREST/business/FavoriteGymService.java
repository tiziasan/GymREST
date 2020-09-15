package it.univaq.disim.GymREST.business;

import java.util.Map;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.Gym;

public interface FavoriteGymService {

    long createFavoriteGym (FavoriteGym favoriteGym) throws ServiceException;
    Map<Long, Gym> getAllFavoriteGym(long idUser) throws ServiceException;
    void deleteFavoriteGym(long idUser, long idGym) throws ServiceException;

}
