package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.CourseService;
import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.impl.CourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.GymServiceImpl;
import it.univaq.disim.GymREST.model.Course;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

public class CourseRes {

    private final long idGym;

    CourseRes(long idGym) {
        this.idGym = idGym;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(@QueryParam("name") String name) throws SQLException {
        CourseService courseService = new CourseServiceImpl();
        if ( name != null ){
            return Response.ok(courseService.getCoursesByName(name)).build();

        }
        return Response.ok(courseService.getCoursesByGym(idGym)).build();
    }

    @GET
    @Path("{idCourse: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(@PathParam("idCourse") long idCourse) throws SQLException {
        CourseService courseService = new CourseServiceImpl();
        return Response.ok(courseService.getCourse(idCourse)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(@Context UriInfo uriinfo, Course course) throws SQLException {
        GymService gymService = new GymServiceImpl();
        course.setGym(gymService.getGym(idGym));
        
        CourseService courseService = new CourseServiceImpl();
        long idCourse = courseService.createCourse(course);
        
        return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getCourse").build(idCourse)).build();
    }

    @PUT
    @Path("{idCourse: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGym(@PathParam("idCourse") long idCourse, Course course) throws SQLException {
        CourseService courseService = new CourseServiceImpl();
        course.setId(idCourse);
        courseService.updateCourse(course);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{idCourse: [0-9]+}")
    public Response deleteGym(@PathParam("idCourse") long idCourse) throws SQLException {
        CourseService courseService = new CourseServiceImpl();
        courseService.deleteCourse(idCourse);
        return Response.noContent().build();
    }

    @Path("{idCourse: [0-9]+}/feedbacks")
    public FeedbackCourseRes getFeedbacksCourse(@PathParam("idCourse") long idCourse) { return new FeedbackCourseRes(idCourse); }

}
