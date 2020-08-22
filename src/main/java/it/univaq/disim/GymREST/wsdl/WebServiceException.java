package it.univaq.disim.GymREST.wsdl;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.mycompany.java2.FaultInfoBean")
public class WebServiceException extends Exception {

    private FaultInfoBean faultInfo;

    public WebServiceException(Exception ex) {
        this(ex.getMessage());
    }

    public WebServiceException(String message) {
        this("Web service error: "+message, new FaultInfoBean(message));
    }

    public WebServiceException(String message, FaultInfoBean faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public WebServiceException(String message, FaultInfoBean faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public FaultInfoBean getFaultInfo() {
        return faultInfo;
    }
}
