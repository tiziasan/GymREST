package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackCourseService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FeedbackCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FeedbackCourse;
import it.univaq.disim.GymREST.model.Role;
import it.univaq.disim.GymREST.model.User;
import it.univaq.disim.GymREST.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

public class FeedbackCourseRes {

    private final long idCourse;

    FeedbackCourseRes(long idCourse){
        this.idCourse = idCourse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksCourse() throws ServiceException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();

        return Response.ok(feedbackCourseService.getAllFeedbackByCourse(idCourse)).build();
    }

    @GET
    @Path("{idFeedback: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbackCourse(@PathParam("idFeedback") long idFeedback) throws ServiceException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();

        return Response.ok(feedbackCourseService.getFeedback(idFeedback)).build();
    }

    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFeedbackCourse(@Context SecurityContext securityContext, @Context UriInfo uriinfo, FeedbackCourse feedbackCourse) throws ServiceException {
        if (securityContext.isUserInRole(Role.Values.CUSTOMER)) {
            UserService userService = new UserServiceImpl();
            FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);
            feedbackCourse.setUser(user.getId());
            feedbackCourse.setCourse(idCourse);

            long idFeedback = feedbackCourseService.createFeedbackCourse(feedbackCourse);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getFeedbackCourse").build(idFeedback)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }


    @PUT
    @Auth
    @Path("{idFeedback: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeedbackGym(@Context SecurityContext securityContext,@PathParam("idFeedback") long idFeedback, FeedbackCourse feedbackCourse) throws ServiceException {
        if (securityContext.isUserInRole(Role.Values.CUSTOMER)) {
            UserService userService = new UserServiceImpl();
            FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (feedbackCourseService.getFeedback(idFeedback).getUser() == user.getId()){
                feedbackCourse.setId(idFeedback);
                feedbackCourseService.updateFeedbackCourse(feedbackCourse);

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
    public Response deleteFeedbackGym(@Context SecurityContext securityContext, @PathParam("idFeedback") long idFeedback) throws ServiceException {
        if (securityContext.isUserInRole(Role.Values.CUSTOMER)) {
            UserService userService = new UserServiceImpl();
            FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (feedbackCourseService.getFeedback(idFeedback).getUser() == user.getId()){
                feedbackCourseService.deleteFeedbackCourse(idFeedback);

                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
