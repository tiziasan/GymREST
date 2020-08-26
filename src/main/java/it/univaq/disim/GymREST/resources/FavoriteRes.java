package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FavoriteCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FavoriteGymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.model.FavoriteCourse;
import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.User;
import it.univaq.disim.GymREST.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Auth
public class FavoriteRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    private final long idUser;

    public FavoriteRes(long idUser) {
        this.idUser = idUser;
    }

    @POST
    @Path("gyms")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, long idGym) throws SQLException {
        try {
            if (securityContext.isUserInRole("utente")) {
                UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);

                String username = securityContext.getUserPrincipal().getName();
                User user = userService.getUserByUsername(username);

                if (idUser == user.getId()){
                    FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);

                    FavoriteGym favoriteGym = new FavoriteGym();
                    favoriteGym.setGym(idGym);
                    favoriteGym.setUser(user.getId());
                    favoriteGymService.createFavoriteGym(favoriteGym);

                    return Response.created(uriinfo.getAbsolutePathBuilder().build()).build();
                } else {
                    return Response.status(Response.Status.FORBIDDEN).build();
                }
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("courses")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteCourse(@Context SecurityContext securityContext, @Context UriInfo uriinfo, long idCourse) throws SQLException {
        try {

        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                FavoriteCourse favoriteCourse = new FavoriteCourse();
                favoriteCourse.setCourse(idCourse);
                favoriteCourse.setUser(user.getId());
                favoriteCourseService.createFavoriteCourse(favoriteCourse);

                return Response.created(uriinfo.getAbsolutePathBuilder().build()).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("gyms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFavoritesGym(@Context SecurityContext securityContext) throws SQLException {
        try {

        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                return Response.ok(favoriteGymService.getAllFavoriteGym(idUser)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFavoritesCourse(@Context SecurityContext securityContext) throws SQLException {
        try {

        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                return Response.ok(favoriteCourseService.getAllFavoriteCourse(idUser)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("gyms/{idGym: [0-9]+}")
    public Response deleteFavoriteGym(@Context SecurityContext securityContext, @PathParam("idGym") long idGym) throws SQLException {
        try {

        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl(urlDB, userDB, pswDB);

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                favoriteGymService.deleteFavoriteGym(user.getId(), idGym);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("courses/{idCourse: [0-9]+}")
    public Response deleteFavoriteCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse) throws SQLException {
        try {

        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl(urlDB, userDB, pswDB);

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                favoriteCourseService.deleteFavoriteCourse(idUser, idCourse);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
