package it.univaq.disim.GymREST.business.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.Gym;

public class GymServiceImpl extends Service implements GymService {

	private static final String GET_ALL_GYMS = "SELECT * FROM gym";
	private static final String GET_GYMS_BY_REGION = "SELECT * FROM gym WHERE gym.region = ?";
	private static final String GET_GYMS_BY_NAME = "SELECT * FROM gym WHERE gym.name LIKE ?";
	private static final String GET_GYM = "SELECT * FROM gym WHERE gym_id = ?";
	private static final String INSERT_GYM = "INSERT INTO gym (address,name,province,region,user_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE_GYM = "UPDATE gym SET address=?, name=?, province=?, region= ? WHERE id=?";
	private static final String DELETE_GYM = "DELETE FROM gym WHERE gym_id=?";

	@Override
	public List<Gym> getAllGyms() throws ServiceException {
		System.out.println("[SERVICE] Gym - getAllGyms");

		List<Gym> gyms = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
			 Statement st = connection.createStatement();
			 ResultSet rs = st.executeQuery(GET_ALL_GYMS);) {

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
			throw new ServiceException(e.getErrorCode());
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymsByRegion(String region) throws ServiceException {
		System.out.println("[SERVICE] Gym - getGymsByRegion");

		List<Gym> gyms = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
			 PreparedStatement st = connection.prepareStatement(GET_GYMS_BY_REGION);) {
			st.setString(1,region);

			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()){
					Gym gym = new Gym();
					gym.setId(rs.getLong(1));
					gym.setName(rs.getString(3));
					gym.setRegion(rs.getString(5));
					gym.setProvince(rs.getString(4));
					gym.setAddress(rs.getString(2));

					gyms.add(gym);
				}
			}
		} catch (SQLException e) {
			throw new ServiceException(e.getErrorCode());
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymsByName(String name) throws ServiceException {
		System.out.println("[SERVICE] Gym - getGymsByName");

		List<Gym> gyms = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
			 PreparedStatement st = connection.prepareStatement(GET_GYMS_BY_NAME);) {

			st.setString(1, "%" + name + "%");
			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()){
					Gym gym = new Gym();
					gym.setId(rs.getLong(1));
					gym.setName(rs.getString(3));
					gym.setRegion(rs.getString(5));
					gym.setProvince(rs.getString(4));
					gym.setAddress(rs.getString(2));

					gyms.add(gym);
				}
			}
		} catch (SQLException e) {
			throw new ServiceException(e.getErrorCode());
		}
		return gyms;
	}

	@Override
	public Gym getGym(long id) throws ServiceException {
		System.out.println("[SERVICE] Gym - getGym");

		Gym gym = new Gym();
		try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
			 PreparedStatement st = connection.prepareStatement(GET_GYM);) {
			st.setLong(1,id);

			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					gym.setId(rs.getLong(1));
					gym.setName(rs.getString(3));
					gym.setRegion(rs.getString(5));
					gym.setProvince(rs.getString(4));
					gym.setAddress(rs.getString(2));
				}
			}

		} catch (SQLException e) {
			throw new ServiceException(e.getErrorCode());
		}
		return gym;
	}

	@Override
	public long createGym(Gym gym) throws ServiceException {
		System.out.println("[SERVICE] Gym - createGym");

		try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
			 PreparedStatement st = connection.prepareStatement(INSERT_GYM, Statement.RETURN_GENERATED_KEYS);) {

			st.setString(2, gym.getName());
			st.setString(4, gym.getRegion());
			st.setString(3, gym.getProvince());
			st.setString(1, gym.getAddress());
			st.setLong(5, gym.getUser());

			st.execute();

			try (ResultSet result = st.getGeneratedKeys();) {
				if (result.next()) {
					return result.getLong(1);
				}
			}
		} catch (SQLException e) {
			throw new ServiceException(e.getErrorCode());
		}
		return 0;
	}

	@Override
	public void updateGym(Gym gym) throws ServiceException {
		System.out.println("[SERVICE] Gym - updateGym");

		try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
			 PreparedStatement st = connection.prepareStatement(UPDATE_GYM);) {

			st.setString(2, gym.getName());
			st.setString(4, gym.getRegion());
			st.setString(3, gym.getProvince());
			st.setString(1, gym.getAddress());
			st.setLong(5, gym.getId());

			st.execute();

		} catch (SQLException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}

	@Override
	public void deleteGym(long id) throws ServiceException {
		System.out.println("[SERVICE] Gym - deleteGym");

		try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
			 PreparedStatement st = connection.prepareStatement(DELETE_GYM);) {

			st.setLong(1,id);
			st.execute();

		} catch (SQLException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}

}
