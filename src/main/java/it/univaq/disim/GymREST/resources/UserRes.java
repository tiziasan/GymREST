package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackCourseService;
import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FeedbackCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.User;
import it.univaq.disim.GymREST.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Auth
@Path("users")
public class UserRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    @GET
    @Path("{idUser: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException{
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);

        if (idUser == user.getId()){
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("{idUser: [0-9]+}")
    public Response deleteUser(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);

        if (idUser == user.getId()){
            userService.deleteUser(idUser);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Path("{idUser: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@Context SecurityContext securityContext, @PathParam("idUser") long idUser, User user) throws ServiceException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);

        String username = securityContext.getUserPrincipal().getName();

        if (idUser == userService.getUserByUsername(username).getId()){
            user.setId(idUser);
            userService.updateUser(user);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("{idUser: [0-9]+}/feedbacks/gyms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksGym(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);

        if (idUser == user.getId()){
            return Response.ok(feedbackGymService.getAllFeedbackByUser(idUser)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("{idUser: [0-9]+}/feedbacks/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksCourse(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl(urlDB, userDB, pswDB);

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);

        if (idUser == user.getId()){
            return Response.ok(feedbackCourseService.getAllFeedbackByUser(idUser)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }


    @Path("{idUser: [0-9]+}/favorites")
    public FavoriteRes getFavorites(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) {
        System.out.println("From UserRes to FavoriteRes");
        return new FavoriteRes(idUser);
    }

}
