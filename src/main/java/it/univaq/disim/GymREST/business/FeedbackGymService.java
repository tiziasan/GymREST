package it.univaq.disim.GymREST.business;

import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FeedbackGym;

import java.util.List;

public interface FeedbackGymService {

    long createFeedbackGym (FeedbackGym feedbackGym) throws ServiceException;
    FeedbackGym getFeedback(long id) throws ServiceException;
    List<FeedbackGym> getAllFeedbackByGym(long id) throws ServiceException;
    List<FeedbackGym> getAllFeedbackByUser(long id) throws ServiceException;
    void deleteFeedbackGym(long id) throws ServiceException;
    void updateFeedbackGym(FeedbackGym feedbackGym) throws ServiceException;
}
