package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.FeedbackGym;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackGymServiceImpl extends Service implements FeedbackGymService {

    private static final String INSERT_FEEDBACK_GYM = "INSERT INTO feedbackgym (feed,rating,gym_id,user_user_id) VALUES (?,?,?,?)";
    private static final String GET_ALL_FEEDBACK = "SELECT * FROM feedbackgym";
    private static final String GET_ALL_FEEDBACK_BY_GYM = "SELECT * FROM feedbackgym WHERE gym_id=?";
    private static final String GET_ALL_FEEDBACK_BY_USER = "SELECT * FROM feedbackgym WHERE user_user_id=?";
    private static final String DELETE_FEEDBACK__GYM = "DELETE FROM feedbackgym WHERE id=?";

    @Override
    public long createFeedbackGym(FeedbackGym feedbackGym) throws SQLException {
        System.out.println("createFeedbackGym");

        try {
            PreparedStatement st = getConnection().prepareStatement(INSERT_FEEDBACK_GYM, Statement.RETURN_GENERATED_KEYS);
            st.setString(1,feedbackGym.getFeed());
            st.setInt(2,feedbackGym.getRating());
            st.setLong(3,feedbackGym.getGym().getId());
            st.setLong(4,feedbackGym.getUser().getId());
            st.execute();

            ResultSet result = st.getGeneratedKeys();
            if (result.next()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return 0;

    }

    @Override
    public List<FeedbackGym> getAllFeedbackGym(long id) throws SQLException {
        System.out.println("getAllFeedbackGym");

        List<FeedbackGym> feedbackGyms = new ArrayList<>();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(GET_ALL_FEEDBACK);
            while (rs.next()){
                FeedbackGym feedbackGym = new FeedbackGym();
                feedbackGym.setId(rs.getLong(1));
                feedbackGym.setRating(rs.getInt(2));
                feedbackGym.setFeed(rs.getString(3));

                feedbackGyms.add(feedbackGym);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return feedbackGyms;
    }

    @Override
    public List<FeedbackGym> getAllFeedbackByGym(long id) throws SQLException {
        System.out.println("getAllFeedbackByGym");

        List<FeedbackGym> feedbackGyms = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_ALL_FEEDBACK_BY_GYM);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                FeedbackGym feedbackGym = new FeedbackGym();
                feedbackGym.setId(rs.getLong(1));
                feedbackGym.setRating(rs.getInt(2));
                feedbackGym.setFeed(rs.getString(3));

                feedbackGyms.add(feedbackGym);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return feedbackGyms;
    }

    @Override
    public List<FeedbackGym> getAllFeedbackByUser(long id) throws SQLException {
        System.out.println("getAllFeedbackByUser");

        List<FeedbackGym> feedbackGyms = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_ALL_FEEDBACK_BY_USER);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                FeedbackGym feedbackGym = new FeedbackGym();
                feedbackGym.setId(rs.getLong(1));
                feedbackGym.setRating(rs.getInt(2));
                feedbackGym.setFeed(rs.getString(3));

                feedbackGyms.add(feedbackGym);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return feedbackGyms;       }

    @Override
    public void deleteFeedbackGym(long id) throws SQLException {
        System.out.println("deleteFeedbackGym");

        try {
            PreparedStatement st = getConnection().prepareStatement(DELETE_FEEDBACK__GYM);
            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

    }
}
