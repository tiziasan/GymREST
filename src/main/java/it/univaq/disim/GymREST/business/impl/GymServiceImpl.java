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
	private static final String GET_GYMS_BY_NAME = "SELECT * FROM gym WHERE gym.name LIKE ?";
	private static final String GET_GYM = "SELECT * FROM gym WHERE gym_id = ?";
	private static final String INSERT_GYM = "INSERT INTO gym (address,name,province,region,user_user_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE_GYM = "UPDATE gym SET address=?, name=?, province=?, region= ? WHERE gym_id=?";
	private static final String DELETE_GYM = "DELETE FROM gym WHERE gym_id=?";

	@Override
	public List<Gym> getAllGyms() throws SQLException {
		System.out.println("getAllGyms");

		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYMS);
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
				Gym gym = new Gym();
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));

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
				Gym gym = new Gym();
				gym.setId(rs.getLong(1));
				gym.setName(rs.getString(3));
				gym.setRegion(rs.getString(5));
				gym.setProvince(rs.getString(4));
				gym.setAddress(rs.getString(2));

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
	public Gym getGym(long idGym) throws SQLException {
		System.out.println("getGym");

		Gym gym = new Gym();
		try {
			PreparedStatement st = getConnection().prepareStatement(GET_GYM);
			st.setLong(1,idGym);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
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
		return gym;
	}

	@Override
	public long createGym(Gym gym) throws SQLException {
		System.out.println("createGym");

		try {
			PreparedStatement st = getConnection().prepareStatement(INSERT_GYM, Statement.RETURN_GENERATED_KEYS);
			st.setString(2, gym.getName());
			st.setString(4, gym.getRegion());
			st.setString(3, gym.getProvince());
			st.setString(1, gym.getAddress());
			// da ultimare dopo utenteRes
			st.setString(5,"14");

			st.execute();

			ResultSet result = st.getGeneratedKeys();
			if (result.next()) {
				return result.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
		return 0;
	}

	@Override
	public void updateGym(Gym gym) throws SQLException {
		System.out.println("updateGym");

		try {
			PreparedStatement st = getConnection().prepareStatement(UPDATE_GYM);
			st.setString(2, gym.getName());
			st.setString(4, gym.getRegion());
			st.setString(3, gym.getProvince());
			st.setString(1, gym.getAddress());
			st.setLong(5, gym.getId());
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
	}

	@Override
	public void deleteGym(long idGym) throws SQLException {
		System.out.println("deleteGym");

		try {
			PreparedStatement st = getConnection().prepareStatement(DELETE_GYM);
			st.setLong(1,idGym);
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnetion();
		}
	}



}
