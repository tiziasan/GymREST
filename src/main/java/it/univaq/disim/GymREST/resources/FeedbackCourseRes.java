package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackCourseService;
import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.impl.FeedbackCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class FeedbackCourseRes {

    private final long id;

    FeedbackCourseRes(long id) {
        this.id = id;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksGym() throws SQLException{
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();
        return Response.ok(feedbackGymService.getAllFeedbackByGym(id)).build();

    }




}
