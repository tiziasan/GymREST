package it.univaq.disim.GymREST.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.Response;

import it.univaq.disim.mwt.gymportalREST.model.Gym;

import java.util.ArrayList;
import java.util.List;

@Path("gyms")

public class GymRes {
    @GET
    @Produces("application/json")
    public Response GetAllGyms(){
        List<Gym> gymList = new ArrayList<>();
        return null;

    }
}
