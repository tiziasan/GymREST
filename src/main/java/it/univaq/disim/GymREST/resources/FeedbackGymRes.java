package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FeedbackGym;
import it.univaq.disim.GymREST.model.Role;
import it.univaq.disim.GymREST.model.User;
import it.univaq.disim.GymREST.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

public class FeedbackGymRes {

    private final long idGym;

    FeedbackGymRes(long idGym){
        this.idGym = idGym;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksGym() throws ServiceException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();

        return Response.ok(feedbackGymService.getAllFeedbackByGym(idGym)).build();
    }

    @GET
    @Path("{idFeedback: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbackGym(@PathParam("idFeedback") long idFeedback) throws ServiceException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();

        return Response.ok(feedbackGymService.getFeedback(idFeedback)).build();
    }

    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFeedbackGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, FeedbackGym feedbackGym) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            feedbackGym.setUser(user.getId());
            feedbackGym.setGym(idGym);

            long idFeedback = feedbackGymService.createFeedbackGym(feedbackGym);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getFeedbackGym").build(idFeedback)).build();
        } 
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Auth
    @Path("{idFeedback: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeedbackGym(@Context SecurityContext securityContext, @PathParam("idFeedback") long idFeedback, FeedbackGym feedbackGym) throws ServiceException {
        if (securityContext.isUserInRole(Role.Values.CUSTOMER)) {
            UserService userService = new UserServiceImpl();
            FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);
            if (feedbackGymService.getFeedback(idFeedback).getUser() == user.getId()){
                feedbackGym.setId(idFeedback);
                feedbackGymService.updateFeedbackGym(feedbackGym);

                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Auth
    @Path("{idFeedback: [0-9]+}")
    public Response deleteFeedbackGym(@Context SecurityContext securityContext,@PathParam("idFeedback") long idFeedback) throws ServiceException {
        if (securityContext.isUserInRole(Role.Values.CUSTOMER)) {
            UserService userService = new UserServiceImpl();
            FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);
            if (feedbackGymService.getFeedback(idFeedback).getUser() == user.getId()){
                feedbackGymService.deleteFeedbackGym(idFeedback);

                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
