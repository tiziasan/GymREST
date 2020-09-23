package it.univaq.disim.GymREST.business;

public abstract class Service {

    protected static final String urlDB;
    protected static final String userDB;
    protected static final String pswDB;

    static {
        System.out.println("Load JDBC Driver");
        loadDriver();

        System.out.println("Init connection params");
        urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        userDB = "gymportal";
        pswDB = "gymportal";
    }

    protected static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
