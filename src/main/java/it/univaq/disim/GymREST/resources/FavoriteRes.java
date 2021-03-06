package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.FavoriteCourseService;
import it.univaq.disim.GymREST.business.FavoriteGymService;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.FavoriteCourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.FavoriteGymServiceImpl;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.exceptions.ServiceException;
import it.univaq.disim.GymREST.model.FavoriteCourse;
import it.univaq.disim.GymREST.model.FavoriteGym;
import it.univaq.disim.GymREST.model.User;
import it.univaq.disim.GymREST.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Auth
public class FavoriteRes {

    private final long idUser;

    public FavoriteRes(long idUser) {
        this.idUser = idUser;
    }

    @POST
    @Path("gyms")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, long idGym) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                FavoriteGym favoriteGym = new FavoriteGym();
                favoriteGym.setGym(idGym);
                favoriteGym.setUser(user.getId());
                favoriteGymService.createFavoriteGym(favoriteGym);

                return Response.created(uriinfo.getAbsolutePathBuilder().build()).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("courses")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteCourse(@Context SecurityContext securityContext, @Context UriInfo uriinfo, long idCourse) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl();

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
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("gyms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFavoritesGym(@Context SecurityContext securityContext) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                return Response.ok(favoriteGymService.getAllFavoriteGym(idUser)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFavoritesCourse(@Context SecurityContext securityContext) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                return Response.ok(favoriteCourseService.getAllFavoriteCourse(idUser)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("gyms/{idGym: [0-9]+}")
    public Response deleteFavoriteGym(@Context SecurityContext securityContext, @PathParam("idGym") long idGym) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                favoriteGymService.deleteFavoriteGym(user.getId(), idGym);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("courses/{idCourse: [0-9]+}")
    public Response deleteFavoriteCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (idUser == user.getId()){
                favoriteCourseService.deleteFavoriteCourse(idUser, idCourse);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
