package it.univaq.disim.GymREST.business.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.model.Gym;

public class GymServiceImpl implements GymService {

	private static final String GET_ALL_GYM = "SELECT * FROM gym";
	private static final String GET_GYM_BY_REGION = "SELECT * FROM gym WHERE gym.region = ?";
	private static final String GET_GYM_BY_NAME = "SELECT * FROM `gym` WHERE gym.name LIKE '%'?'%'";
	private static final String GET_GYM = "SELECT * FROM gym WHERE gym.id = ?";
	private static final String INSERT_GYM = "INSERT INTO gym (address,name,province,region,user_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE_GYM = "UPDATE gym SET address=?, name=?, province=?, region= ? WHERE id=?";
	private static final String DELETE_GYM = "DELETE FROM gym WHERE id=?";

	private Connection connection;

	public GymServiceImpl(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/gymportal", "gymportal", "gymportal");
			if (connection != null) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public List<Gym> getAllGym() throws SQLException {
		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = connection.createStatement();
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
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection Closed");
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymByRegion(String region) throws SQLException {
		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = connection.createStatement();
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
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymByName(String hint) throws SQLException {
		List<Gym> gyms = new ArrayList<>();
		try {
			Statement st = connection.createStatement();
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
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return gyms;
	}

	@Override
	public Gym getGym(long id) throws SQLException {
		Gym gym = null;
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(GET_ALL_GYM);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return gym;
	}

	@Override
	public void insertGym(Gym gym) throws SQLException {
		try {
			Statement st = connection.createStatement();
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
			connection.close();
		}
	}

	@Override
	public void updateGym(Gym gym) throws SQLException {
		try {
			Statement st = connection.createStatement();
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
			connection.close();
		}
	}

	@Override
	public void deleteGym(Gym gym) throws SQLException {
		try {
			Statement st = connection.createStatement();
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
			connection.close();
		}
	}



}
