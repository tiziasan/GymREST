package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.CourseService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.model.Gym;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl extends Service implements CourseService {

    private static final String GET_ALL_COURSES_BY_GYMS = "SELECT * FROM course WHERE gym_gym_id=?";
    private static final String GET_COURSES_BY_NAME = "SELECT * FROM course WHERE course.name LIKE ?";
    private static final String GET_COURSE = "SELECT * FROM course WHERE course.id = ?";
    private static final String INSERT_COURSE = "INSERT INTO course (code,description,name,gym_gym_id) VALUES (?,?,?,?)";
    private static final String UPDATE_COURSE = "UPDATE course SET address=?, name=?, province=?, region= ? WHERE gym_id=?";
    private static final String DELETE_COURSE = "DELETE FROM course WHERE gym_id=?";

    @Override
    public List<Course> getCoursesByGym(long courseId) throws SQLException {
        System.out.println("getCoursesByGym");

        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_ALL_COURSES_BY_GYMS);
            st.setLong(1,courseId);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getLong(1));
                course.setCode(rs.getString(2));
                course.setName(rs.getString(4));
                course.setDescription(rs.getString(3));

                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return courses;

    }

    @Override
    public List<Course> getCoursesByName(String hint) throws SQLException {
        System.out.println("getGymsByRegion");

        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_COURSES_BY_NAME);
            st.setString(1, "%" + hint + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getLong(1));
                course.setCode(rs.getString(2));
                course.setName(rs.getString(4));
                course.setDescription(rs.getString(3));

                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return courses;
    }

    @Override
    public Course getCourse(long idCourse) throws SQLException {
        System.out.println("getCourse");

        Course course = new Course();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_COURSE);
            st.setLong(1,idCourse);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                course.setId(rs.getLong(1));
                course.setCode(rs.getString(2));
                course.setName(rs.getString(4));
                course.setDescription(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return course;
    }

    @Override
    public long createCourse(Course course) throws SQLException {
        System.out.println("createCourse");

        try {
            PreparedStatement st = getConnection().prepareStatement(INSERT_COURSE, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, course.getCode());
            st.setString(3, course.getName());
            st.setString(2, course.getDescription());
            // da ultimare
            st.setString(4,"8");

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
    public void updateCourse(Course course) throws SQLException {

    }

    @Override
    public void deleteCourse(Course course) throws SQLException {

    }
}
