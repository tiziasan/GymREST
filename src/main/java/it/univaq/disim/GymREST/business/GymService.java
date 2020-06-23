package it.univaq.disim.GymREST.business;

import java.util.List;

import it.univaq.disim.GymREST.model.Gym;

public interface GymService {
	
	List<Gym> getAllGym();
	List<Gym> getGymByRegion(String region);
	List<Gym> getGymByName(String hint);
	Gym getGym(long id);
	void insertGym(Gym gym);
	void updateGym(Gym gym);
	void deleteGym(Gym gym);
	
	

	

}
