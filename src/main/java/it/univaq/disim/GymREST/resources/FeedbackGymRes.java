package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.model.FeedbackGym;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

public class FeedbackGymRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    private final long idGym;

    FeedbackGymRes(long idGym){
        this.idGym=idGym;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksGym(@QueryParam("user") long user) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
        if ( user > 0 ){
            return Response.ok(feedbackGymService.getAllFeedbackByUser(user)).build();
        }

        return Response.ok(feedbackGymService.getAllFeedbackByGym(idGym)).build();
    }

    @GET
    @Path("{idFeedback: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbackGym(@PathParam("idFeedback") long idFeedback) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);

        return Response.ok(feedbackGymService.getFeedback(idFeedback)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFeedbackGym(@Context UriInfo uriinfo, FeedbackGym feedbackGym) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
        feedbackGym.setGym(idGym);

        //recupera id da utente connesso
        feedbackGym.setUser(1);

        long idFeedback = feedbackGymService.createFeedbackGym(feedbackGym);

        return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getFeedbackGym").build(idFeedback)).build();
    }

    @PUT
    @Path("{idFeedback: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeedbackGym(@PathParam("idFeedback") long idFeedback, FeedbackGym feedbackGym) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
        feedbackGym.setId(idFeedback);
        feedbackGymService.updateFeedbackGym(feedbackGym);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{idFeedback: [0-9]+}")
    public Response deleteFeedbackGym(@PathParam("idFeedback") long idFeedback) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
        feedbackGymService.deleteFeedbackGym(idFeedback);

        return Response.noContent().build();
    }

}
