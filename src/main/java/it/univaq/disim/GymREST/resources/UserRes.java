package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

@Path("users")


public class UserRes {

    @GET
    @Path("{user: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("user") long id) throws SQLException{
        UserService userService = new UserServiceImpl();
        return Response.ok(userService.getUser(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@Context UriInfo uriInfo, User user) throws SQLException{
        UserService userService = new UserServiceImpl();
        long id = userService.createUser(user);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(this.getClass(),"getUser").build(id)).build();
    }

    @DELETE
    @Path("{user: [0-9]+}")
    public Response deleteUser(@PathParam("user") long id) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("{user: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("user") long id, User user) throws SQLException {
        UserService userService = new UserServiceImpl();
        user.setId(id);
        userService.updateUser(user);
        return Response.noContent().build();
    }

    @Path("{user: [0-9]+}/favorites")
    public FavoriteGymRes getFavorites(@PathParam("user") long user) {
        return new FavoriteGymRes(user);
    }





}
