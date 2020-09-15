package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FeedbackCourse;

import java.util.List;

public interface FeedbackCourseService {

    long createFeedbackCourse (FeedbackCourse feedbackCourse) throws ServiceException;
    FeedbackCourse getFeedback(long id) throws ServiceException;
    List<FeedbackCourse> getAllFeedbackByCourse(long id) throws ServiceException;
    List<FeedbackCourse> getAllFeedbackByUser(long id) throws ServiceException;
    void deleteFeedbackCourse(long id) throws ServiceException;
    void updateFeedbackCourse(FeedbackCourse feedbackCourse) throws ServiceException;

}
