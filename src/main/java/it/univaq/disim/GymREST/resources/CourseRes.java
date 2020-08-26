package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.CourseService;
import it.univaq.disim.GymREST.business.GymService;
import it.univaq.disim.GymREST.business.impl.CourseServiceImpl;
import it.univaq.disim.GymREST.business.impl.GymServiceImpl;
import it.univaq.disim.GymREST.model.Course;
import it.univaq.disim.GymREST.security.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

public class CourseRes {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";

    private final long idGym;

    CourseRes(long idGym) {
        this.idGym = idGym;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(@QueryParam("name") String name) throws SQLException {
        CourseService courseService = new CourseServiceImpl(urlDB, userDB, pswDB);
        if ( name != null ){
            return Response.ok(courseService.getCoursesByName(name)).build();
        }

        return Response.ok(courseService.getCoursesByGym(idGym)).build();
    }

    @GET
    @Path("{idCourse: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(@PathParam("idCourse") long idCourse) throws SQLException {
        CourseService courseService = new CourseServiceImpl(urlDB, userDB, pswDB);
        return Response.ok(courseService.getCourse(idCourse)).build();
    }

    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(@Context SecurityContext securityContext, @Context UriInfo uriinfo, Course course) throws SQLException {
        if (securityContext.isUserInRole("gestore")) {
            course.setGym(idGym);

            CourseService courseService = new CourseServiceImpl(urlDB, userDB, pswDB);
            long idCourse = courseService.createCourse(course);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getCourse").build(idCourse)).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Auth
    @Path("{idCourse: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse, Course course) throws SQLException {
        if (securityContext.isUserInRole("gestore")) {

            CourseService courseService = new CourseServiceImpl(urlDB, userDB, pswDB);
            course.setId(idCourse);
            courseService.updateCourse(course);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Auth
    @Path("{idCourse: [0-9]+}")
    public Response deleteCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse) throws SQLException {
        if (securityContext.isUserInRole("gestore")) {

            CourseService courseService = new CourseServiceImpl(urlDB, userDB, pswDB);
            courseService.deleteCourse(idCourse);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("{idCourse: [0-9]+}/feedbacks")
    public FeedbackCourseRes getFeedbacksCourse(@PathParam("idCourse") long idCourse) {
        System.out.println("From CourseRes to FeedbackCourseRes");
        return new FeedbackCourseRes(idCourse);
    }

}
