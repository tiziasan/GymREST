package it.univaq.disim.GymREST.business;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.model.FavoriteCourse;

public interface FavoriteCourseService {

    long createFavoriteCourse (FavoriteCourse favoriteCourse) throws SQLException;
    Map<Long, Course> getAllFavoriteCourse(long idUser) throws SQLException;
    void deleteFavoriteCourse(long idUser, long idCourse) throws SQLException;





}
