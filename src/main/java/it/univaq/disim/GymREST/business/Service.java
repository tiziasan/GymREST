package it.univaq.disim.GymREST.business;

public abstract class Service {

    protected final String urlDB;
    protected final String userDB;
    protected final String pswDB;

    static {
        loadDriver();
    }

    public Service(String url, String user, String psw) {
        this.urlDB = url;
        this.userDB = user;
        this.pswDB = psw;
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
