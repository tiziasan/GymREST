package it.univaq.disim.GymREST.exceptions;

public class ErrorMessage {

    private int code;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(int SQLcode) {
        System.out.println("[SQL Error Code]: " + SQLcode);
        switch (SQLcode){
            case 1062:
                this.code = 400;
                this.message = "Data Already Exist";
                break;
            case 1452:
                this.code = 400;
                this.message = "Cannot add or update - The related data doesn't exist";
                break;
            default:
                this.code = 500;
                this.message = "Server Error - SQLError: " + SQLcode;
                break;
        }
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "[Error Message] - " +
                "Code: " + code +
                ", Message: '" + message + '\'';
    }
}
