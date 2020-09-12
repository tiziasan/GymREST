package it.univaq.disim.GymREST;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import it.univaq.disim.GymREST.resources.*;
import it.univaq.disim.GymREST.security.SecurityFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author didattica
 */
@ApplicationPath("rest")
public class RESTApp extends Application {

    private final Set<Class<?>> classes;

    public RESTApp() {
        HashSet<Class<?>> c = new HashSet<Class<?>>();
        //aggiungiamo tutte le *root resurces* (cioè quelle
        //con l'annotazione Path) che vogliamo pubblicare
        c.add(GymRes.class);
		c.add(CourseRes.class);
        c.add(UserRes.class);
        c.add(FavoriteRes.class);
        c.add(FeedbackCourseRes.class);
        c.add(FeedbackGymRes.class);
        c.add(AuthRes.class);
        c.add(CorsFilter.class);

        //aggiungiamo il provider Jackson per poter
        //usare i suoi servizi di serializzazione e 
        //deserializzazione JSON
        c.add(JacksonJsonProvider.class);

        //necessario se vogliamo una (de)serializzazione custom di qualche classe    
        //c.add(ObjectMapperContextResolver.class);

        //esempio di autoenticazione con JWT
        //c.add(AuthLevel1Filter.class);
        c.add(SecurityFilter.class);
        classes = Collections.unmodifiableSet(c);
    }

    //l'override di questo metodo deve restituire il set
    //di classi che Jersey utilizzerà per pubblicare il
    //servizio. Tutte le altre, anche se annotate, verranno
    //IGNORATE
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
