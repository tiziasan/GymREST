package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.model.FavoriteCourse;
import it.univaq.disim.GymREST.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FavoriteCourseServiceImpl extends Service implements FavoriteCourseService {

    private static final String INSERT_FAVORITE_COURSE = "INSERT INTO favoritecourse (course_id,user_user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT * FROM favoritecourse WHERE user_user_id=?";
    private static final String DELETE_FAVORITE_COURSE = "DELETE FROM favoritecourse WHERE id=?";

    @Override
    public long createFavoriteCourse(FavoriteCourse favoriteCourse) throws SQLException {
        System.out.println("createFavoriteCourse");

        try {
            PreparedStatement st = getConnection().prepareStatement(INSERT_FAVORITE_COURSE, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,favoriteCourse.getCourse().getId());
            st.setLong(2,favoriteCourse.getUser().getId());
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
    public List<FavoriteCourse> getAllFavoriteCourse(long id) throws SQLException {
        System.out.println("getAllFavoriteCourse");

        List<FavoriteCourse> favoriteCourses = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_FAVORITE_BY_USER);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                FavoriteCourse favoriteCourse = new FavoriteCourse();
                favoriteCourse.setId(rs.getLong(1));
                favoriteCourses.add(favoriteCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return favoriteCourses;
    }

    @Override
    public void deleteFavoriteCourse(long id) throws SQLException {
        System.out.println("deleteFavoriteCourse");

        try {
            PreparedStatement st = getConnection().prepareStatement(DELETE_FAVORITE_COURSE);
            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

    }
}
