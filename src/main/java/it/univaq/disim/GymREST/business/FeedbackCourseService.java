package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.model.FeedbackCourse;

import java.sql.SQLException;
import java.util.List;

public interface FeedbackCourseService {

    long createFeedbackCourse (FeedbackCourse feedbackCourse) throws SQLException;
    FeedbackCourse getFeedback(long id) throws SQLException;
    List<FeedbackCourse> getAllFeedbackByCourse(long id) throws SQLException;
    List<FeedbackCourse> getAllFeedbackByUser(long id) throws SQLException;
    void deleteFeedbackCourse(long id) throws SQLException;
    void updateFeedbackCourse(FeedbackCourse feedbackCourse) throws SQLException;

}
