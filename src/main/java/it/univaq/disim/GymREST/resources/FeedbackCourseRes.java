package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FeedbackCourseService;
import it.univaq.disim.GymREST.business.FeedbackGymService;
import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FeedbackCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FeedbackGymServiceImpl;
import it.univaq.disim.GymREST.business.impl.GymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.model.FeedbackCourse;
import it.univaq.disim.GymREST.model.Gym;
import it.univaq.disim.GymREST.model.User;
import it.univaq.disim.GymREST.security.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

public class FeedbackCourseRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    private final long idGym;
    private final long idCourse;

    FeedbackCourseRes(long idGym, long idCourse){
        this.idGym = idGym;
        this.idCourse = idCourse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksCourse() throws SQLException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl(urlDB, userDB, pswDB);

        return Response.ok(feedbackCourseService.getAllFeedbackByCourse(idCourse)).build();
    }

    @GET
    @Path("{idFeedback: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbackCourse(@PathParam("idFeedback") long idFeedback) throws SQLException {
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl(urlDB, userDB, pswDB);

        return Response.ok(feedbackCourseService.getFeedback(idFeedback)).build();
    }

    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFeedbackCourse(@Context SecurityContext securityContext, @Context UriInfo uriinfo, FeedbackCourse feedbackCourse) throws SQLException {
        String username = securityContext.getUserPrincipal().getName();
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl(urlDB, userDB, pswDB);

            User user = userService.getUserByUsername(username);
            feedbackCourse.setUser(user.getId());
            feedbackCourse.setCourse(idCourse);

            long idFeedback = feedbackCourseService.createFeedbackCourse(feedbackCourse);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getFeedbackCourse").build(idFeedback)).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Auth
    @Path("{idFeedback: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFeedbackGym(@Context SecurityContext securityContext,@PathParam("idFeedback") long idFeedback, FeedbackCourse feedbackCourse) throws SQLException {
        if (securityContext.isUserInRole("utente")) {
            FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl(urlDB, userDB, pswDB);

            if (isUserManagerOfGym(securityContext)){
                feedbackCourse.setId(idFeedback);
                feedbackCourseService.updateFeedbackCourse(feedbackCourse);

                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Auth
    @Path("{idFeedback: [0-9]+}")
    public Response deleteFeedbackGym(@Context SecurityContext securityContext, @PathParam("idFeedback") long idFeedback) throws SQLException {
        if (securityContext.isUserInRole("utente")) {
            FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl(urlDB, userDB, pswDB);

            if (isUserManagerOfGym(securityContext)){
                feedbackCourseService.deleteFeedbackCourse(idFeedback);

                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    public boolean isUserManagerOfGym(SecurityContext securityContext) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);
        Gym gym = gymService.getGym(idGym);

        return gym.getUser() == user.getId();
    }

}
