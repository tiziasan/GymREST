package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.impl.FavoriteGymServiceImpl;
import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.Gym;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

@Path("prova")
public class FavoriteCourseRes {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createFavoriteGym(@Context UriInfo uriinfo, FavoriteGym favoriteGym) throws SQLException {
        System.out.println(favoriteGym);
        FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl();
        long id = favoriteGymService.createFavoriteGym(favoriteGym);
    }

}
