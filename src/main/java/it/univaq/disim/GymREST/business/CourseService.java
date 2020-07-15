package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.model.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseService {
	
	List<Course> getCoursesByGym(long gymId) throws SQLException;
	List<Course> getCoursesByName(String hint) throws SQLException;
	Course getCourse(long id) throws SQLException;
	long createCourse(Course course) throws SQLException;
	void updateCourse(Course course) throws SQLException;
	void deleteCourse(long id) throws SQLException;

}
