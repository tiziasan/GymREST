package it.univaq.disim.GymREST.exceptions;

public class ErrorMessage {

    private int statusCode;
    private String statusMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(int SQLcode) {
        System.out.println("[SQL Error Code]: " + SQLcode);
        switch (SQLcode){
            case 1062:
                this.statusCode = 400;
                this.statusMessage = "Data Already Exist";
                break;
            default:
                this.statusCode = 500;
                this.statusMessage = "Server Error - SQLError: " + SQLcode;
                break;
        }
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "[Error Message] - " +
                "Status Code: " + statusCode +
                ", statusMessage: '" + statusMessage + '\'';
    }
}
