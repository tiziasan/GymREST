package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.impl.FavoriteCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FavoriteGymServiceImpl;
import it.univaq.disim.GymREST.model.FavoriteCourse;
import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.FeedbackCourse;
import it.univaq.disim.GymREST.security.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

public class FavoriteRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    private final long idUser;

    public FavoriteRes(long idUser) {
        this.idUser = idUser;
    }

    @Path("gyms")
    @Auth
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, long idGym) throws SQLException {
        if (securityContext.isUserInRole("utente")) {

            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);

            FavoriteGym favoriteGym = new FavoriteGym();
            favoriteGym.setGym(idGym);
            favoriteGym.setUser(idUser);
            favoriteGymService.createFavoriteGym(favoriteGym);

            return Response.created(uriinfo.getAbsolutePathBuilder().build()).build();
        } else {
            return Response.serverError().entity("Non hai i permessi per fare questa operazione").build();

        }
    }

    @Path("courses")
    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteCourse(@Context SecurityContext securityContext,@Context UriInfo uriinfo, long idCourse) throws SQLException {
        if (securityContext.isUserInRole("utente")) {

            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);

            FavoriteCourse favoriteCourse = new FavoriteCourse();
            favoriteCourse.setCourse(idCourse);
            favoriteCourse.setUser(idUser);
            favoriteCourseService.createFavoriteCourse(favoriteCourse);

            return Response.created(uriinfo.getAbsolutePathBuilder().build()).build();
        } else {
            return Response.serverError().entity("Non hai i permessi per fare questa operazione").build();

        }
    }


    @Path("gyms")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoritesGym() throws SQLException {
        FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(favoriteGymService.getAllFavoriteGym(idUser)).build();
    }

    @Path("courses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoritesCourse() throws SQLException {
        FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(favoriteCourseService.getAllFavoriteCourse(idUser)).build();
    }

    @DELETE
    @Auth
    @Path("gyms/{idGym: [0-9]+}")
    public Response deleteFavoriteGym(@Context SecurityContext securityContext,@PathParam("idGym") long idGym) throws SQLException {
        if (securityContext.isUserInRole("utente")) {

            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);
            favoriteGymService.deleteFavoriteGym(idUser, idGym);
            return Response.noContent().build();
        } else {
            return Response.serverError().entity("Non hai i permessi per fare questa operazione").build();

        }
    }

    @DELETE
    @Auth
    @Path("courses/{idCourse: [0-9]+}")
    public Response deleteFavoriteCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse) throws SQLException {
        if (securityContext.isUserInRole("utente")) {

            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);
            favoriteCourseService.deleteFavoriteCourse(idUser, idCourse);
            return Response.noContent().build();
        } else {
            return Response.serverError().entity("Non hai i permessi per fare questa operazione").build();

        }
    }


}
