package it.univaq.disim.GymREST.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Service {

    private Connection connection;

    public Service(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/gymportal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "gymportal", "gymportal");
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnetion() throws SQLException {
        connection.close();
        if (connection.isClosed()){
            System.out.println("Connection Closed");
        }
    }
}
