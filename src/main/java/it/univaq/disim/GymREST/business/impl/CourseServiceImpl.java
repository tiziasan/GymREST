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
    private static final String INSERT_COURSE = "INSERT INTO course (address,name,province,region,user_user_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE_COURSE = "UPDATE course SET address=?, name=?, province=?, region= ? WHERE gym_id=?";
    private static final String DELETE_COURSE = "DELETE FROM course WHERE gym_id=?";

    @Override
    public List<Course> getCoursesByGym(long courseId) throws SQLException {
        return null;
    }

    @Override
    public List<Course> getCoursesByName(String hint) throws SQLException {
        return null;
    }

    @Override
    public Course getCourse(long id) throws SQLException {
        return null;
    }

    @Override
    public long createCourse(Course course) throws SQLException {
        return 0;
    }

    @Override
    public void updateCourse(Course course) throws SQLException {

    }

    @Override
    public void deleteCourse(Course course) throws SQLException {

    }
}
