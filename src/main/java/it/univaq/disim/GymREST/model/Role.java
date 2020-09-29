package it.univaq.disim.GymREST.model;

public enum Role {

    CUSTOMER("CUSTOMER"),
    MANAGER("MANAGER");

    private String value;

    Role(String role) {
        setValue(role);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String CUSTOMER= "CUSTOMER";
        public static final String MANAGER= "MANAGER";
    }

}
