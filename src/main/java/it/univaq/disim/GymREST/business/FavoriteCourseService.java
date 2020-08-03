package it.univaq.disim.GymREST.business;

import java.sql.SQLException;
import java.util.List;

import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.model.FavoriteCourse;

public interface FavoriteCourseService {

    long createFavoriteCourse (FavoriteCourse favoriteCourse) throws SQLException;
    List<Course> getAllFavoriteCourse(long id) throws SQLException;
    void deleteFavoriteCourse(long id) throws SQLException;





}
