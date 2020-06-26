package it.univaq.disim.GymREST.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllGyms() throws SQLException {
        List<Gym> gymList = gymService.getAllGym();
        return Response.ok(gymList).build();
    }


}
