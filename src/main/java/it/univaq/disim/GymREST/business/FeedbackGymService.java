package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.model.FeedbackGym;

import java.sql.SQLException;
import java.util.List;

public interface FeedbackGymService {

        long createFeedbackGym (FeedbackGym feedbackGym) throws SQLException;
        FeedbackGym getFeedback(long id) throws SQLException;
        List<FeedbackGym> getAllFeedbackByGym(long id) throws SQLException;
        List<FeedbackGym> getAllFeedbackByUser(long id) throws SQLException;
        void deleteFeedbackGym(long id) throws SQLException;
        void updateFeedbackGym(FeedbackGym feedbackGym) throws SQLException;
}
