package it.univaq.disim.GymREST;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "*,Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Custom-header");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "*");
        responseContext.getHeaders().add(
                "Access-Control-Expose-Headers",
                "Location,Authorization, *");

    }


}
