package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.impl.FavoriteCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FavoriteGymServiceImpl;
import it.univaq.disim.GymREST.model.FavoriteCourse;
import it.univaq.disim.GymREST.model.FavoriteGym;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

public class FavoriteRes {

    private final long user;

    public FavoriteRes(long user) {
        this.user = user;
    }

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    @Path("gyms")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createFavoriteGym(@Context UriInfo uriinfo, FavoriteGym favoriteGym) throws SQLException {
        System.out.println(favoriteGym);
        FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);
        long id = favoriteGymService.createFavoriteGym(favoriteGym);
    }

    @Path("courses")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createFavoriteCourse(@Context UriInfo uriinfo, FavoriteCourse favoriteCourse) throws SQLException {
        System.out.println(favoriteCourse);
        FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);
        long id = favoriteCourseService.createFavoriteCourse(favoriteCourse);
    }

    @Path("gyms")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoritesGym() throws SQLException {
        FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(favoriteGymService.getAllFavoriteGym(user)).build();
    }

    @Path("courses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoritesCourse() throws SQLException {
        FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(favoriteCourseService.getAllFavoriteCourse(user)).build();
    }

    @DELETE
    @Path("gyms/{idFavorite: [0-9]+}")
    public Response deleteFavoriteGym(@PathParam("idFavorite") long idFavorite) throws SQLException {
        FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);
        favoriteGymService.deleteFavoriteGym(idFavorite);
        return Response.noContent().build();
    }
    @DELETE
    @Path("courses/{idFavorite: [0-9]+}")
    public Response deleteFavoriteCourse(@PathParam("idFavorite") long idFavorite) throws SQLException {
        FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);
        favoriteCourseService.deleteFavoriteCourse(idFavorite);
        return Response.noContent().build();
    }


}
