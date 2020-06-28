package it.univaq.disim.GymREST.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.impl.GymServiceImpl;
import it.univaq.disim.GymREST.model.Gym;

import java.sql.SQLException;
import java.util.List;

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


}
