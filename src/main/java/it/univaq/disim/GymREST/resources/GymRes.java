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

    private GymService gymService = new GymServiceImpl();

    //un altro metodo per fare la distinzione delle 3 url
    //Passing Collection in Query Parameters -- https://memorynotfound.com/jaxrs-queryparam-example/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGyms(@QueryParam("name") String name, @QueryParam("region") String region) throws SQLException {
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
        return Response.ok(gymService.getGym(idGym)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGym(@Context UriInfo uriinfo, Gym gym) throws SQLException {
        long idGym = gymService.createGym(gym);
        return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getGym").build(idGym)).build();
    }

    @PUT
    @Path("{idGym: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGym(@PathParam("idGym") long idGym, Gym gym) throws SQLException {
        gym.setId(idGym);
        gymService.updateGym(gym);
        return Response.noContent().build();
    }

}
