package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;
import it.univaq.disim.GymREST.model.FeedbackGym;
import it.univaq.disim.GymREST.security.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
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
    public Response getFeedbacksGym() throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);

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
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFeedbackGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, FeedbackGym feedbackGym) throws SQLException {
        if (securityContext.isUserInRole("utente")) {

            FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
            feedbackGym.setGym(idGym);

            //recupera id da utente connesso
            feedbackGym.setUser(1);

            long idFeedback = feedbackGymService.createFeedbackGym(feedbackGym);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getFeedbackGym").build(idFeedback)).build();
        } else {
            return Response.serverError().entity("Non hai i permessi per fare questa operazione").build();

        }
    }

    @PUT
    @Auth
    @Path("{idFeedback: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeedbackGym(@Context SecurityContext securityContext, @PathParam("idFeedback") long idFeedback, FeedbackGym feedbackGym) throws SQLException {
        if (securityContext.isUserInRole("utente")) {

            FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
            feedbackGym.setId(idFeedback);
            feedbackGymService.updateFeedbackGym(feedbackGym);

            return Response.noContent().build();
        }else {
            return Response.serverError().entity("Non hai i permessi per fare questa operazione").build();

        }
    }

    @DELETE
    @Auth
    @Path("{idFeedback: [0-9]+}")
    public Response deleteFeedbackGym(@Context SecurityContext securityContext,@PathParam("idFeedback") long idFeedback) throws SQLException {
        if (securityContext.isUserInRole("utente")) {

            FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
            feedbackGymService.deleteFeedbackGym(idFeedback);

            return Response.noContent().build();
        }else {
            return Response.serverError().entity("Non hai i permessi per fare questa operazione").build();

        }

    }

}
