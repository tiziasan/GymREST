package it.univaq.disim.GymREST.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.GymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.model.Gym;
import it.univaq.disim.GymREST.model.User;
import it.univaq.disim.GymREST.security.Auth;

import java.sql.SQLException;

@Path("gyms")
public class GymRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGyms(@QueryParam("name") String name, @QueryParam("region") String region) throws SQLException {
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);
        if ( region==null && name==null ) {
            return Response.ok(gymService.getAllGyms()).build();

        } if ( region!=null && name==null ){
            return Response.ok(gymService.getGymsByRegion(region)).build();

        } if ( region==null && name!=null ){
            return Response.ok(gymService.getGymsByName(name)).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("{idGym: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGym(@PathParam("idGym") long idGym) throws SQLException {
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(gymService.getGym(idGym)).build();
    }

    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, Gym gym) throws SQLException {
        if (securityContext.isUserInRole("gestore")){
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);
            gym.setUser(user.getId());

            long idGym = gymService.createGym(gym);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getGym").build(idGym)).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("{idGym: [0-9]+}")
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGym(@Context SecurityContext securityContext, @PathParam("idGym") long idGym, Gym gym) throws SQLException {
        if (securityContext.isUserInRole("gestore")) {
            GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);

            if (isUserManagerOfGym(securityContext, idGym)){
                gym.setId(idGym);
                gymService.updateGym(gym);
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
    @Path("{idGym: [0-9]+}")
    public Response deleteGym(@Context SecurityContext securityContext, @PathParam("idGym") long idGym) throws SQLException {
        if (securityContext.isUserInRole("gestore")) {
            GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);

            if (isUserManagerOfGym(securityContext, idGym)){
                gymService.deleteGym(idGym);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    @Path("{idGym: [0-9]+}/courses")
    public CourseRes getCourses(@PathParam("idGym") long idGym) {
        System.out.println("From GymRes to CourseRes");
        return new CourseRes(idGym);
    }

    @Path("{idGym: [0-9]+}/feedbacks")
    public FeedbackGymRes getFeedbacksGym(@PathParam("idGym") long idGym) {
        System.out.println("From GymRes to FeedbackGymRes");
        return new FeedbackGymRes(idGym);
    }


    public boolean isUserManagerOfGym(SecurityContext securityContext, long idGym) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);
        Gym gym = gymService.getGym(idGym);

        return gym.getUser() == user.getId();
    }

}

























