package it.univaq.disim.GymREST.business.impl;

import it.univaq.disim.GymREST.business.FeedbackCourseService;
import it.univaq.disim.GymREST.business.Service;
import it.univaq.disim.GymREST.model.FeedbackCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackCourseServiceImpl extends Service implements FeedbackCourseService {

    private static final String INSERT_FEEDBACK_COURSE = "INSERT INTO feedbackcourse (feed,rating,course_id,user_user_id) VALUES (?,?,?,?)";
    private static final String GET_ALL_FEEDBACK_BY_COURSE = "SELECT * FROM feedbackcourse WHERE course_id=?";
    private static final String GET_ALL_FEEDBACK_BY_USER = "SELECT * FROM feedbackcourse WHERE user_user_id=?";
    private static final String DELETE_FEEDBACK__COURSE = "DELETE FROM feedbackcourse WHERE id=?";
    private static final String UPDATE_FEEDBACK_COURSE = "UPDATE feedbackcourse SET feed=?, rating=?";


    @Override
    public long createFeedbackCourse(FeedbackCourse feedbackCourse) throws SQLException {
        System.out.println("createFeedbackCourse");

        try {
            PreparedStatement st = getConnection().prepareStatement(INSERT_FEEDBACK_COURSE, Statement.RETURN_GENERATED_KEYS);

            st.setString(1,feedbackCourse.getFeed());
            st.setInt(2,feedbackCourse.getRating());
            st.setLong(3,feedbackCourse.getCourse());
            st.setLong(4,feedbackCourse.getUser());
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
    public List<FeedbackCourse> getAllFeedbackByCourse(long id) throws SQLException {
        System.out.println("getAllFeedbackByCourse");

        List<FeedbackCourse> feedbackCourses = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_ALL_FEEDBACK_BY_COURSE);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                FeedbackCourse feedbackCourse = new FeedbackCourse();
                feedbackCourse.setId(rs.getLong(1));
                feedbackCourse.setRating(rs.getInt(2));
                feedbackCourse.setFeed(rs.getString(3));
                feedbackCourses.add(feedbackCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return feedbackCourses;
    }

    @Override
    public List<FeedbackCourse> getAllFeedbackByUser(long id) throws SQLException {
        System.out.println("getAllFeedbackByUser");

        List<FeedbackCourse> feedbackCourses = new ArrayList<>();
        try {
            PreparedStatement st = getConnection().prepareStatement(GET_ALL_FEEDBACK_BY_USER);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                FeedbackCourse feedbackCourse = new FeedbackCourse();
                feedbackCourse.setId(rs.getLong(1));
                feedbackCourse.setRating(rs.getInt(2));
                feedbackCourse.setFeed(rs.getString(3));
                feedbackCourses.add(feedbackCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }
        return feedbackCourses;    }

    @Override
    public void deleteFeedbackCourse(long id) throws SQLException {
        System.out.println("deleteFeedbackCourse");

        try {
            PreparedStatement st = getConnection().prepareStatement(DELETE_FEEDBACK__COURSE);
            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

    }

    @Override
    public void updateFeedbackCourse(FeedbackCourse feedbackCourse) throws SQLException {
        System.out.println("updateFeedbackCourse");

        try {
            PreparedStatement st = getConnection().prepareStatement(UPDATE_FEEDBACK_COURSE);
            st.setString(1, feedbackCourse.getFeed());
            st.setInt(2, feedbackCourse.getRating());
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnetion();
        }

    }
}
