package it.univaq.disim.GymREST.wsdl;
import it.univaq.disim.GymREST.model.Role;
import it.univaq.disim.GymREST.model.User;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;


@WebService(serviceName = "GymPortalWSMain")
public class GymPortalWSInterfaceImpl implements GymPortalWSInterface{

    @Override
    public List<User> getUtenti() throws WebServiceException {
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);
        return users;
    }

    @Override
    public boolean addRuoloUtente(User user, Role role) throws WebServiceException {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteRuoloUtente(User user, Role role) throws WebServiceException {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}


