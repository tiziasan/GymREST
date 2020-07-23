package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackCourseService;
import it.univaq.disim.GymREST.business.impl.FeedbackCourseServiceImpl;
import it.univaq.disim.GymREST.model.FeedbackCourse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

public class FeedbackCourseRes {


    private final long idCourse;

    FeedbackCourseRes(long idCourse){
        this.idCourse=idCourse;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksCourse(@QueryParam("user") long user) throws SQLException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();
        if ( user > 0 ){
            return Response.ok(feedbackCourseService.getAllFeedbackByUser(user)).build();

        }
        return Response.ok(feedbackCourseService.getAllFeedbackByCourse(idCourse)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFeedbackCourse(@Context UriInfo uriinfo, FeedbackCourse feedbackCourse) throws SQLException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();
        long idFeedback = feedbackCourseService.createFeedbackCourse(feedbackCourse);

        return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getFeedback").build(idFeedback)).build();
    }

    @PUT
    @Path("{idFeedback: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeedbackGym(@PathParam("idFeedback") long idFeedback, FeedbackCourse feedbackCourse) throws SQLException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();
        feedbackCourse.setId(idFeedback);
        feedbackCourseService.updateFeedbackCourse(feedbackCourse);
        return Response.noContent().build();
    }




    @DELETE
    @Path("{idFeedback: [0-9]+}")
    public Response deleteFeedbackGym(@PathParam("idFeedback") long idFeedback) throws SQLException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();
        feedbackCourseService.deleteFeedbackCourse(idFeedback);
        return Response.noContent().build();
    }
}
