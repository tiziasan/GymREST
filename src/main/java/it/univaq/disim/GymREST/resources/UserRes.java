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

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    @GET
    @Path("{user: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("user") long id) throws SQLException{
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(userService.getUser(id)).build();
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(@Context UriInfo uriInfo, User user) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        long id = userService.createUser(user);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(this.getClass(),"getUser").build(id)).build();
    }

    @DELETE
    @Path("{user: [0-9]+}")
    public Response deleteUser(@PathParam("user") long id) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("{user: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("user") long id, User user) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        user.setId(id);
        userService.updateUser(user);
        return Response.noContent().build();
    }

    @Path("{user: [0-9]+}/favorites")
    public FavoriteRes getFavorites(@PathParam("user") long user) {
        return new FavoriteRes(user);
    }

}
