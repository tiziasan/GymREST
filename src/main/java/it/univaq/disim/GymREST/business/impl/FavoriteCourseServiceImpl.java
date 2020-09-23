package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.model.FavoriteCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteCourseServiceImpl extends Service implements FavoriteCourseService {

    private static final String INSERT_FAVORITE_COURSE = "INSERT INTO favoritecourse (course_id,user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT * FROM course LEFT JOIN favoritecourse ON favoritecourse.course_id = course.id WHERE favoritecourse.user_id=?";
    private static final String DELETE_FAVORITE_COURSE = "DELETE FROM favoritecourse WHERE user_id=? AND course_id=?";

    @Override
    public long createFavoriteCourse(FavoriteCourse favoriteCourse) throws ServiceException {
        System.out.println("[SERVICE] FavoriteCourse - createFavoriteCourse");

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
            throw new ServiceException(e.getErrorCode());
        }
        return 0;
    }

    @Override
    public List<Course> getAllFavoriteCourse(long idUser) throws ServiceException {
        System.out.println("[SERVICE] FavoriteCourse - getAllFavoriteCourse");

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

                    favoriteCourses.add(course);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return favoriteCourses;
    }

    @Override
    public void deleteFavoriteCourse(long idUser, long idCourse) throws ServiceException {
        System.out.println("[SERVICE] FavoriteCourse - deleteFavoriteCourse");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FAVORITE_COURSE);) {

            st.setLong(1,idUser);
            st.setLong(2,idCourse);
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

}
