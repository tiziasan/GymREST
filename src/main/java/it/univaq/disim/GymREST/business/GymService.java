package it.univaq.disim.GymREST.business;

import java.util.List;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.Gym;

public interface GymService {
	
	List<Gym> getAllGyms() throws ServiceException;
	List<Gym> getGymsByRegion(String region) throws ServiceException;
	List<Gym> getGymsByName(String hint) throws ServiceException;
	Gym getGym(long id) throws ServiceException;
	long createGym(Gym gym) throws ServiceException;
	void updateGym(Gym gym) throws ServiceException;
	void deleteGym(long id) throws ServiceException;

}
