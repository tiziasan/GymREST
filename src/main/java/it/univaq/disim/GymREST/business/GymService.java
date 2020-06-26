package it.univaq.disim.GymREST.business;

import java.sql.SQLException;
import java.util.List;

import it.univaq.disim.GymREST.model.Gym;

public interface GymService {
	
	List<Gym> getAllGym() throws SQLException;
	List<Gym> getGymByRegion(String region) throws SQLException;
	List<Gym> getGymByName(String hint) throws SQLException;
	Gym getGym(long id) throws SQLException;
	void insertGym(Gym gym) throws SQLException;
	void updateGym(Gym gym) throws SQLException;
	void deleteGym(Gym gym) throws SQLException;

}
