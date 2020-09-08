package it.univaq.disim.GymREST.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getSqlErrorCode());
        System.out.println(errorMessage);
        return Response.status(errorMessage.getCode()).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
    }

}
