package it.univaq.disim.GymREST.exceptions;


public class ServiceException extends Exception {

    private int sqlErrorCode;

    public ServiceException(int sqlErrorCode) {
        this.sqlErrorCode = sqlErrorCode;
    }

    public int getSqlErrorCode() {
        return sqlErrorCode;
    }

    public void setSqlErrorCode(int sqlErrorCode) {
        this.sqlErrorCode = sqlErrorCode;
    }

}
