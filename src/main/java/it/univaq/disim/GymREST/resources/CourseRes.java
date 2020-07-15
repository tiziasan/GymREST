package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.CourseService;
import it.univaq.disim.GymREST.business.impl.CourseServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class CourseRes {

    private final long idGym;

    CourseRes(long idGym) {
        this.idGym = idGym;
    }

    private CourseService courseService = new CourseServiceImpl();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(@QueryParam("name") String name) throws SQLException {
        if ( name != null ){
            return Response.ok(courseService.getCoursesByName(name)).build();

        }
        return Response.ok(courseService.getCoursesByGym(idGym)).build();
    }


}
