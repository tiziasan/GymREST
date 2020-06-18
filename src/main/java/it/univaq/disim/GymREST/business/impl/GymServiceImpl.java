package it.univaq.disim.GymREST.business.impl;

import java.util.List;

import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.model.Gym;

public class GymServiceImpl implements GymService {
	
	private static final String GET_ALL_GYM = "SELECT * FROM gym";
	private static final String GET_GYM_BY_REGION = "SELECT * FROM gym WHERE gym.region = ?";
	private static final String GET_GYM_BY_NAME = "SELECT * FROM `gym` WHERE gym.name LIKE '%?%'";
	private static final String GET_GYM = "SELECT * FROM gym WHERE gym.id = ?";
	private static final String INSERT_GYM = "INSERT INTO gym (address,name,province,region,user_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE_GYM = "UPDATE gym SET address=?, name=?, province=?, region= ? WHERE id=?";
	private static final String DELETE_GYM = "DELETE FROM gym WHERE id=?";


	@Override
	public List<Gym> getAllGym() {
		
		return null;
	}

	@Override
	public List<Gym> getGymByRegion(String region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gym> getGymByName(String himt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gym getGym(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertGym(Gym gym) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGym(Gym gym) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGym(Gym gym) {
		// TODO Auto-generated method stub
		
	}

}
