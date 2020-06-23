package it.univaq.disim.GymREST.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.univaq.disim.GymREST.model.Gym;

import java.util.ArrayList;
import java.util.List;

@Path("gyms")
public class GymRes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllGyms(){
//        List<Gym> gymList = new ArrayList<>();
//        User u = new User(1L,"a","a","a","a","a",true);
//        gymList.add(new Gym(1L,"prova","a","s","d",u));
//        gymList.add(new Gym(1L,"prova","a","s","d",null));
//        return Response.ok(gymList).build();
        Gym g = new Gym(1L,"prova","a","s","d",null);
        return Response.ok(g).build();

    }

}
