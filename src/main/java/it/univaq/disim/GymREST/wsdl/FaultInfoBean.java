package it.univaq.disim.GymREST.wsdl;

/**
 *
 * @author MasterWeb
 */
public class FaultInfoBean {

    private String message;
    private int code;

    public FaultInfoBean() {
        message = "";
        code = 0;
    }

    public FaultInfoBean(String message) {
        this.message = message;
        this.code = 500;
    }

    public FaultInfoBean(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
