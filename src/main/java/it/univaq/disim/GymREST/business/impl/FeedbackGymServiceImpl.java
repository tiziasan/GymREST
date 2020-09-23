package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FeedbackGym;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackGymServiceImpl extends Service implements FeedbackGymService {

    private static final String INSERT_FEEDBACK_GYM = "INSERT INTO feedbackgym (feed,rating,gym_id,user_id) VALUES (?,?,?,?)";
    private static final String GET_FEEDBACK_BY_ID = "SELECT * FROM feedbackgym WHERE id=?";
    private static final String GET_ALL_FEEDBACK_BY_GYM = "SELECT * FROM feedbackgym WHERE gym_id=?";
    private static final String GET_ALL_FEEDBACK_BY_USER = "SELECT * FROM feedbackgym WHERE user_id=?";
    private static final String DELETE_FEEDBACK__GYM = "DELETE FROM feedbackgym WHERE id=?";
    private static final String UPDATE_FEEDBACK_GYM = "UPDATE feedbackgym SET feed=?, rating=? WHERE id=?";

    public FeedbackGymServiceImpl(String url, String user, String psw) {
        super(url, user, psw);
    }

    @Override
    public long createFeedbackGym(FeedbackGym feedbackGym) throws ServiceException {
        System.out.println("[SERVICE] FeedbackGym - createFeedbackGym");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(INSERT_FEEDBACK_GYM, Statement.RETURN_GENERATED_KEYS);) {

            st.setString(1,feedbackGym.getFeed());
            st.setInt(2,feedbackGym.getRating());
            st.setLong(3,feedbackGym.getGym());
            st.setLong(4,feedbackGym.getUser());

            st.execute();

            ResultSet result = st.getGeneratedKeys();
            if (result.next()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return 0;
    }

    @Override
    public List<FeedbackGym> getAllFeedbackByGym(long id) throws ServiceException {
        System.out.println("[SERVICE] FeedbackGym - getAllFeedbackByGym");
        loadDriver();

        List<FeedbackGym> feedbackGyms = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_ALL_FEEDBACK_BY_GYM);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    FeedbackGym feedbackGym = new FeedbackGym();
                    feedbackGym.setId(rs.getLong(1));
                    feedbackGym.setRating(rs.getInt(3));
                    feedbackGym.setFeed(rs.getString(2));
                    feedbackGym.setUser(rs.getLong(5));
                    feedbackGym.setGym(rs.getLong(4));

                    feedbackGyms.add(feedbackGym);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return feedbackGyms;
    }

    @Override
    public FeedbackGym getFeedback(long id) throws ServiceException {
        System.out.println("[SERVICE] FeedbackGym - getFeedbackGym");
        loadDriver();

        FeedbackGym feedbackGym = new FeedbackGym() ;
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FEEDBACK_BY_ID);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()){
                    feedbackGym.setId(rs.getLong(1));
                    feedbackGym.setRating(rs.getInt(3));
                    feedbackGym.setFeed(rs.getString(2));
                    feedbackGym.setUser(rs.getLong(5));
                    feedbackGym.setGym(rs.getLong(4));
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return feedbackGym;
    }

    @Override
    public List<FeedbackGym> getAllFeedbackByUser(long idUser) throws ServiceException {
        System.out.println("[SERVICE] FeedbackGym - getAllFeedbackByUser");
        loadDriver();

        List<FeedbackGym> feedbackGyms = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_ALL_FEEDBACK_BY_USER);) {
            st.setLong(1,idUser);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    FeedbackGym feedbackGym = new FeedbackGym();
                    feedbackGym.setId(rs.getLong(1));
                    feedbackGym.setRating(rs.getInt(3));
                    feedbackGym.setFeed(rs.getString(2));
                    feedbackGym.setGym(rs.getLong(4));

                    feedbackGyms.add(feedbackGym);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return feedbackGyms;
    }

    @Override
    public void deleteFeedbackGym(long id) throws ServiceException {
        System.out.println("[SERVICE] FeedbackGym - deleteFeedbackGym");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FEEDBACK__GYM);) {

            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

    @Override
    public void updateFeedbackGym(FeedbackGym feedbackGym) throws ServiceException {
        System.out.println("[SERVICE] FeedbackGym - updateFeedbackGym");
        loadDriver();

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(UPDATE_FEEDBACK_GYM);) {

            st.setString(1, feedbackGym.getFeed());
            st.setInt(2, feedbackGym.getRating());
            st.setLong(3, feedbackGym.getId());

            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }
}
