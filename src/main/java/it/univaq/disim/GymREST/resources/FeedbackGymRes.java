package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;
import it.univaq.disim.GymREST.model.FeedbackGym;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

public class FeedbackGymRes {

    private final long idGym;

    FeedbackGymRes(long idGym){
        this.idGym=idGym;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksGym(@QueryParam("user") long user) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();
        if ( user > 0 ){
            return Response.ok(feedbackGymService.getAllFeedbackByUser(user)).build();

        }
        return Response.ok(feedbackGymService.getAllFeedbackByGym(idGym)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFeedbackGym(@Context UriInfo uriinfo, FeedbackGym feedbackGym) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();
        long idFeedback = feedbackGymService.createFeedbackGym(feedbackGym);

        return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getFeedback").build(idFeedback)).build();
    }

    @PUT
    @Path("{idFeedback: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeedbackGym(@PathParam("idFeedback") long idFeedback, FeedbackGym feedbackGym) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();
        feedbackGym.setId(idFeedback);
        feedbackGymService.updateFeedbackGym(feedbackGym);
        return Response.noContent().build();
    }




    @DELETE
    @Path("{idFeedback: [0-9]+}")
    public Response deleteFeedbackGym(@PathParam("idFeedback") long idFeedback) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();
        feedbackGymService.deleteFeedbackGym(idFeedback);
        return Response.noContent().build();
    }

}
