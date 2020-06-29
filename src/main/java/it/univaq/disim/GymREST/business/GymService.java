package it.univaq.disim.GymREST.business;

import java.sql.SQLException;
import java.util.List;

import it.univaq.disim.GymREST.model.Gym;

public interface GymService {
	
	List<Gym> getAllGyms() throws SQLException;
	List<Gym> getGymsByRegion(String region) throws SQLException;
	List<Gym> getGymsByName(String hint) throws SQLException;
	Gym getGym(long id) throws SQLException;
	long createGym(Gym gym) throws SQLException;
	void updateGym(Gym gym) throws SQLException;
	void deleteGym(long idGym) throws SQLException;

}
