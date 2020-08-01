package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.FavoriteCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteCourseServiceImpl extends Service implements FavoriteCourseService {

    private static final String INSERT_FAVORITE_COURSE = "INSERT INTO favoritecourse (course_id,user_user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT * FROM favoritecourse WHERE user_user_id=?";
    private static final String DELETE_FAVORITE_COURSE = "DELETE FROM favoritecourse WHERE id=?";

    private String urlDB;
    private String userDB;
    private String pswDB;

    public FavoriteCourseServiceImpl(String url, String user, String psw) {
        super();
        this.urlDB = url;
        this.userDB = user;
        this.pswDB = psw;
    }

    @Override
    public long createFavoriteCourse(FavoriteCourse favoriteCourse) {
        System.out.println("createFavoriteCourse");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(INSERT_FAVORITE_COURSE, Statement.RETURN_GENERATED_KEYS);) {
            st.setLong(1,favoriteCourse.getCourse());
            st.setLong(2,favoriteCourse.getUser());
            st.execute();

            try (ResultSet result = st.getGeneratedKeys();) {
                if (result.next()) {
                    return result.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<FavoriteCourse> getAllFavoriteCourse(long id) {
        System.out.println("getAllFavoriteCourse");
        loadDriver();

        List<FavoriteCourse> favoriteCourses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FAVORITE_BY_USER);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    FavoriteCourse favoriteCourse = new FavoriteCourse();
                    favoriteCourse.setId(rs.getLong(1));
                    favoriteCourse.setCourse(rs.getLong(2));
                    favoriteCourse.setUser(rs.getLong(3));

                    favoriteCourses.add(favoriteCourse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteCourses;
    }

    @Override
    public void deleteFavoriteCourse(long id) {
        System.out.println("deleteFavoriteCourse");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FAVORITE_COURSE);) {

            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
