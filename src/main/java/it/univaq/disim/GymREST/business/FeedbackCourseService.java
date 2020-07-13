package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.FeedbackCourse;
import it.univaq.disim.GymREST.model.FeedbackGym;

import java.sql.SQLException;
import java.util.List;

public interface FeedbackCourseService {
    long createFeedbackGym (FeedbackCourse feedbackCourse) throws SQLException;
    List<FeedbackCourse> getAllFeedbackCourse(long id) throws SQLException;
    List<FeedbackCourse> getAllFeedbackByCourse(long id) throws SQLException;
    List<FeedbackCourse> getAllFeedbackByUser(long id) throws SQLException;
    void deleteFeedbackCourse(long id) throws SQLException;
}
