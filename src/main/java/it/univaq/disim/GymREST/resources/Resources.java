package it.univaq.disim.GymREST.resources;

public abstract class Resources {

    static final String urlDB;
    static final String userDB;
    static final String pswDB;

    static {
        System.out.println("Init connection params");
        urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        userDB = "gymportal";
        pswDB = "gymportal";
    }

}
