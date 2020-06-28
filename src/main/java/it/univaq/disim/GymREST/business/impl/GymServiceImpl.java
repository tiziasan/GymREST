package it.univaq.disim.GymREST.business.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.Gym;

public class GymServiceImpl extends Service implements GymService {

	private static final String GET_ALL_GYMS = "SELECT * FROM gym";
	private static final String GET_GYMS_BY_REGION = "SELECT * FROM gym WHERE gym.region = ?";
	private static final String GET_GYMS_BY_NAME = "SELECT * FROM `gym` WHERE gym.name LIKE '%'?'%'";
	private static final String GET_GYM = "SELECT * FROM gym WHERE gym.id = ?";
	private static final String INSERT_GYM = "INSERT INTO gym (address,name,province,region,user_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE_GYM = "UPDATE gym SET address=?, name=?, province=?, region= ? WHERE id=?";
	private static final String DELETE_GYM = "DELETE FROM gym WHERE id=?";

	@Override
	public List<Gym> getAllGyms() throws SQLException {
		System.out.println("getAllGyms");

		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYMS);
			while (rs.next()){
				Gym gym = new Gym( rs.getLong(1),
						rs.getString(3),
						rs.getString(2),
						rs.getString(4),
						rs.getString(5),
						null
				);
				gyms.add(gym);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymsByRegion(String region) throws SQLException {
		System.out.println("getGymsByRegion");

		List<Gym> gyms = new ArrayList<>();
		try {
			PreparedStatement st = getConnection().prepareStatement(GET_GYMS_BY_REGION);
			st.setString(1,region);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				Gym gym = new Gym( rs.getLong(1),
						rs.getString(3),
						rs.getString(2),
						rs.getString(4),
						rs.getString(5),
						null
				);
				gyms.add(gym);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymsByName(String name) throws SQLException {
		System.out.println("getGymsByName");

		List<Gym> gyms = new ArrayList<>();
		try {
			PreparedStatement st = getConnection().prepareStatement(GET_GYMS_BY_NAME);
			st.setString(1, "%" + name + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				Gym gym = new Gym( rs.getLong(1),
						rs.getString(3),
						rs.getString(2),
						rs.getString(4),
						rs.getString(5),
						null
				);
				gyms.add(gym);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
		return gyms;
	}

	@Override
	public Gym getGym(long id) throws SQLException {
		Gym gym = null;
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYM);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
		return gym;
	}

	@Override
	public void createGym(Gym gym) throws SQLException {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYM);
			while (rs.next()){
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
	}

	@Override
	public void updateGym(Gym gym) throws SQLException {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYM);
			while (rs.next()){
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
	}

	@Override
	public void deleteGym(Gym gym) throws SQLException {
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYM);
			while (rs.next()){
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
	}



}
