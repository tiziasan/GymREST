package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackCourseService;
import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FeedbackCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("users")
public class UserRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    @GET
    @Path("{idUser: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("idUser") long idUser) throws SQLException{
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(userService.getUser(idUser)).build();
    }

    @DELETE
    @Path("{idUser: [0-9]+}")
    public Response deleteUser(@PathParam("idUser") long idUser) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        userService.deleteUser(idUser);
        return Response.noContent().build();
    }

    @PUT
    @Path("{idUser: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("idUser") long idUser, User user) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        user.setId(idUser);
        userService.updateUser(user);
        return Response.noContent().build();
    }

    @GET
    @Path("{idUser: [0-9]+}/feedbacks/gyms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksGym(@PathParam("idUser") long idUser) throws SQLException {
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(feedbackGymService.getAllFeedbackByUser(idUser)).build();
    }

    @GET
    @Path("{idUser: [0-9]+}/feedbacks/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksCourse(@PathParam("idUser") long idUser) throws SQLException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(feedbackCourseService.getAllFeedbackByUser(idUser)).build();
    }


    @Path("{idUser: [0-9]+}/favorites")
    public FavoriteRes getFavorites(@PathParam("idUser") long idUser) {
        System.out.println("From UserRes to FavoriteRes");
        return new FavoriteRes(idUser);
    }


}
