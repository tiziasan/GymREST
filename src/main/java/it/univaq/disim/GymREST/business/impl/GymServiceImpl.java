package it.univaq.disim.GymREST.business.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.GymREST.business.BusinessException;
import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.Gym;

public class GymServiceImpl extends Service implements GymService {

	private static final String GET_ALL_GYM = "SELECT * FROM gym";
	private static final String GET_GYM_BY_REGION = "SELECT * FROM gym WHERE gym.region = ?";
	private static final String GET_GYM_BY_NAME = "SELECT * FROM `gym` WHERE gym.name LIKE '%'?'%'";
	private static final String GET_GYM = "SELECT * FROM gym WHERE gym.id = ?";
	private static final String INSERT_GYM = "INSERT INTO gym (address,name,province,region,user_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE_GYM = "UPDATE gym SET address=?, name=?, province=?, region= ? WHERE id=?";
	private static final String DELETE_GYM = "DELETE FROM gym WHERE id=?";

	@Override
	public List<Gym> getAllGym() throws BusinessException, SQLException {
		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYM);
			while (rs.next()){
				Gym gym = new Gym();
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));
				gyms.add(gym);
			}
		} catch (SQLException e) {
			throw new BusinessException("getAllGym", e);
		} finally {
			closeConnection();
		}

		return gyms;
	}

	@Override
	public List<Gym> getGymByRegion(String region) throws BusinessException {
		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_GYM_BY_REGION);
			while (rs.next()){
				Gym gym = new Gym();
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));
				gyms.add(gym);
			}
		} catch (SQLException e) {
			throw new BusinessException("getGymByRegion", e);
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymByName(String hint) throws BusinessException {
		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_GYM_BY_NAME);
			while (rs.next()){
				Gym gym = new Gym();
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));
				gyms.add(gym);
			}
		} catch (SQLException e) {
			throw new BusinessException("getGymByName", e);
		}
		return gyms;
	}

	@Override
	public Gym getGym(long id) throws BusinessException {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_GYM);
			Gym gym = new Gym();
			gym.setId(rs.getLong(1));
			gym.setName(rs.getString(3));
			gym.setRegion(rs.getString(5));
			gym.setProvince(rs.getString(4));
			gym.setAddress(rs.getString(2));

			return gym;
		} catch (SQLException e) {
			throw new BusinessException("getGym", e);
		}
	}

	@Override
	public void insertGym(Gym gym) throws BusinessException {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_GYM_BY_REGION);
			gym.setId(rs.getLong(1));
			gym.setName(rs.getString(3));
			gym.setRegion(rs.getString(5));
			gym.setProvince(rs.getString(4));
			gym.setAddress(rs.getString(2));

		} catch (SQLException e) {
			throw new BusinessException("getGymByRegion", e);
		}
	}

	@Override
	public void updateGym(Gym gym) throws BusinessException {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_GYM_BY_REGION);
			gym.setId(rs.getLong(1));
			gym.setName(rs.getString(3));
			gym.setRegion(rs.getString(5));
			gym.setProvince(rs.getString(4));
			gym.setAddress(rs.getString(2));
		} catch (SQLException e) {
			throw new BusinessException("getGymByRegion", e);
		}
	}

	@Override
	public void deleteGym(Gym gym) throws BusinessException {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_GYM_BY_REGION);
			gym.setId(rs.getLong(1));
			gym.setName(rs.getString(3));
			gym.setRegion(rs.getString(5));
			gym.setProvince(rs.getString(4));
			gym.setAddress(rs.getString(2));
		} catch (SQLException e) {
			throw new BusinessException("getGymByRegion", e);
		}
	}



}
