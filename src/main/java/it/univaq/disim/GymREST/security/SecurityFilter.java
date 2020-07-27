package it.univaq.disim.GymREST.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

@Provider
@Authorization
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = null;
        //come esempio, proviamo a cercare il token in vari punti, in ordine di priorità
        //in un'applicazione reale, potremmo scegliere una sola modalità
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring("Bearer".length()).trim();
        }

        if (token != null && !token.isEmpty()) {
            try {
                //validiamo il token JWT
                Key key = AppGlobal.getInstance().getJwtKey();
                Jws<Claims> jwsc = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}