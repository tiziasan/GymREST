package it.univaq.disim.GymREST.resources;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import it.univaq.disim.GymREST.JWTHelpers;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 *
 * @author didattica
 */
@Path("auth")
public class SecurityRes {

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@Context UriInfo uriinfo,
                                     @FormParam("username") String username, @FormParam("password") String password) {
        try {
            //if (authenticate(username, password))

            String authToken = issueToken(uriinfo, username);

            //return Response.ok(authToken).build();
            //return Response.ok().cookie(new NewCookie("token", authToken)).build();
            //return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
            //Restituiamolo in tutte le modalità, giusto per fare un esempio..
            return Response.ok(authToken)
                    .cookie(new NewCookie("token", authToken))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(UriInfo context, String username) {
        String token = username + "skfjsdkj";

//        JWT
//        Key key = JWTHelpers.getInstance().getJwtKey();
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuer(context.getAbsolutePath().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
//                .signWith(key)
//                .compact();
        return token;
    }

    //Metodo per fare "refresh" del token JWT senza ritrasmettere le credenziali
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