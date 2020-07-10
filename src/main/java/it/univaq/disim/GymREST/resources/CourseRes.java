package it.univaq.disim.GymREST.resources;

import it.univaq.disim.GymREST.business.CourseService;
import it.univaq.disim.GymREST.business.impl.CourseServiceImpl;


public class CourseRes {

    private final long idGym;

    CourseRes(long idGym) {
        this.idGym = idGym;
    }

    private CourseService courseService = new CourseServiceImpl();

}
