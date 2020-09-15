package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.Course;
import java.util.List;

public interface CourseService {
	
	List<Course> getCoursesByGym(long gymId) throws ServiceException;
	List<Course> getCoursesByName(String hint) throws ServiceException;
	Course getCourse(long id) throws ServiceException;
	long createCourse(Course course) throws ServiceException;
	void updateCourse(Course course) throws ServiceException;
	void deleteCourse(long id) throws ServiceException;

}
