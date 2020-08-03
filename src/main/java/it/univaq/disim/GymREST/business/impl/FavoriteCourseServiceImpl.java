package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.model.FavoriteCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteCourseServiceImpl extends Service implements FavoriteCourseService {

    private static final String INSERT_FAVORITE_COURSE = "INSERT INTO favoritecourse (course_id,user_user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT * FROM course LEFT JOIN favoritecourse ON favoritecourse.course_id = course.id WHERE favoritecourse.user_user_id=?";
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
    public List<Course> getAllFavoriteCourse(long idUser) {
        System.out.println("getAllFavoriteCourse");
        loadDriver();

        List<Course> favoriteCourses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FAVORITE_BY_USER);) {
            st.setLong(1,idUser);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getLong(1));
                    course.setCode(rs.getString(2));
                    course.setName(rs.getString(4));
                    course.setDescription(rs.getString(3));
                    //restituire gym di appartenenza per far visualizzare info gym?

                    favoriteCourses.add(course);
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
