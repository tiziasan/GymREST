package it.univaq.disim.GymREST.business;

import java.util.List;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.model.FavoriteCourse;

public interface FavoriteCourseService {

    long createFavoriteCourse (FavoriteCourse favoriteCourse) throws ServiceException;
    List<Course> getAllFavoriteCourse(long idUser) throws ServiceException;
    void deleteFavoriteCourse(long idUser, long idCourse) throws ServiceException;

}
