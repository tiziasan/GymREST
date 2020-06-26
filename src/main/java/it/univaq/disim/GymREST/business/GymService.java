package it.univaq.disim.GymREST.business;

import java.util.List;

import it.univaq.disim.GymREST.model.Gym;

public interface GymService {
	
	List<Gym> getAllGym() throws BusinessException;
	List<Gym> getGymByRegion(String region) throws BusinessException;
	List<Gym> getGymByName(String hint) throws BusinessException;
	Gym getGym(long id) throws BusinessException;
	void insertGym(Gym gym) throws BusinessException;
	void updateGym(Gym gym) throws BusinessException;
	void deleteGym(Gym gym) throws BusinessException;

}
