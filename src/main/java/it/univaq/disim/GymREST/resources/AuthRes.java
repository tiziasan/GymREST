package it.univaq.disim.GymREST.resources;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import it.univaq.disim.GymREST.JWTHelpers;
import it.univaq.disim.GymREST.business.UserService;
import it.univaq.disim.GymREST.business.impl.UserServiceImpl;
import it.univaq.disim.GymREST.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
public class AuthRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(@Context UriInfo uriInfo,@FormParam("email") String email,
                                 @FormParam("username") String username, @FormParam("password") String password,
                                 @FormParam("lastname") String lastname, @FormParam("name") String name) throws SQLException {
        UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
        User user = new User(username, email, password, name, lastname);
        long idUser = userService.createUser(user);
        userService.addRoleToUser(idUser);

        return Response.created(uriInfo.getBaseUriBuilder().path("users").path(UserRes.class,"getUser").build(idUser)).build();
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@Context UriInfo uriinfo,
                                     @FormParam("username") String username, @FormParam("password") String password) {
        try {
            UserService userService = new UserServiceImpl(urlDB, userDB, pswDB);
            if (userService.checkUser(username, password)){
                System.out.println("Login OK");
                String authToken = issueToken(uriinfo, username);

                return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
            }
            return Response.status(UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(UriInfo context, String username) {
        Key key = JWTHelpers.getInstance().getJwtKey();
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuer(context.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
        return token;
    }

    @GET
    @Path("/refresh")
    public Response refreshJWTToken(@Context HttpHeaders headers, @Context UriInfo uriinfo) {
        try {
            String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer".length()).trim();
            Key key = JWTHelpers.getInstance().getJwtKey();
            Claims jwsc = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            String newtoken = issueToken(uriinfo, jwsc.getSubject());

            return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + newtoken).build();

        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

}