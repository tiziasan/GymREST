package it.univaq.disim.GymREST.wsdl;

import it.univaq.disim.GymREST.model.Role;
import it.univaq.disim.GymREST.model.User;

import java.util.List;

public interface GymPortalWSInterface {

    List<User> getUtenti() throws WebServiceException;
    boolean addRuoloUtente(User user, Role role) throws WebServiceException;
    void deleteRuoloUtente(User user, Role role) throws WebServiceException;

}
