package it.univaq.disim.GymREST.business.impl;

import java.sql.*;
import java.util.ArrayList;
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

	Connection con = null;

	public GymServiceImpl(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/gymportal", "gymportal", "gymportal");
			if (con != null) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public List<Gym> getAllGym() {
		List<Gym> gyms = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(GET_ALL_GYM);
			System.out.println(rs);
			while (rs.next()){
				Gym g = new Gym( rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5), null );
				gyms.add(g);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return gyms;
	}

	@Override
	public List<Gym> getGymByRegion(String region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gym> getGymByName(String hint) {
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
