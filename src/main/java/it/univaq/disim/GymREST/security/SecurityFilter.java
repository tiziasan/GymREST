package it.univaq.disim.GymREST.security;

import it.univaq.disim.GymREST.model.User;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    public String usernameL = new User().getUserName();
    public String passwordL = new User().getPassword();

    private static final String AUTHORIZATION_HEADER_KEY = "Autorizzazione";
    private static final String SECURIRY_URL = "security";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        if (containerRequestContext.getUriInfo().getPath().contains(SECURIRY_URL)) {
            List<String> authHeader = containerRequestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_KEY, "");
                String encodedString = Base64.getEncoder().encodeToString(authToken.getBytes());
                byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
                String decodedString = new String(decodedBytes);
                StringTokenizer token = new StringTokenizer(decodedString, ":");
                String username = token.nextToken();
                String password = token.nextToken();

                if (usernameL.equals(username) && passwordL.equals(password)) {
                    return;
                }
            }
            Response unathorized = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Accesso Negato, riprovare")
                    .build();

            containerRequestContext.abortWith(unathorized);
        }
    }




}
