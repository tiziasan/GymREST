package it.univaq.disim.GymREST.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.impl.GymServiceImpl;
import it.univaq.disim.GymREST.model.Gym;

import java.sql.SQLException;

@Path("gyms")
public class GymRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    //un altro metodo per fare la distinzione delle 3 url
    //Passing Collection in Query Parameters -- https://memorynotfound.com/jaxrs-queryparam-example/
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
        return Response.serverError().entity("Chiamata errata").build();
    }

    @GET
    @Path("{idGym: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGym(@PathParam("idGym") long idGym) throws SQLException {
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(gymService.getGym(idGym)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGym(@Context UriInfo uriinfo, Gym gym) throws SQLException {
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);
        long idGym = gymService.createGym(gym);
        return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getGym").build(idGym)).build();
    }

    @PUT
    @Path("{idGym: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGym(@PathParam("idGym") long idGym, Gym gym) throws SQLException {
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);
        gym.setId(idGym);
        gymService.updateGym(gym);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{idGym: [0-9]+}")
    public Response deleteGym(@PathParam("idGym") long idGym) throws SQLException {
        GymService gymService = new GymServiceImpl(urlDB, userDB, pswDB);
        gymService.deleteGym(idGym);
        return Response.noContent().build();
    }

    @Path("{idGym: [0-9]+}/courses")
    public CourseRes getCourses(@PathParam("idGym") long idGym) {
        return new CourseRes(idGym);
    }

    @Path("{idGym: [0-9]+}/feedbacks")
    public FeedbackCourseRes getFeedbacksGym(@PathParam("idGym") long idGym) { return new FeedbackCourseRes(idGym); }
}

























