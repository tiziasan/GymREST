/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.disim.gymws;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import wsdl.gymrest.disim.univaq.it.gymportalws.MsgErrore;
import wsdl.gymrest.disim.univaq.it.gymportalws.TipoListaRuolo;
import wsdl.gymrest.disim.univaq.it.gymportalws.TipoListaUtente;
import wsdl.gymrest.disim.univaq.it.gymportalws.TipoRuolo;
import wsdl.gymrest.disim.univaq.it.gymportalws.TipoUtente;


@WebService(serviceName = "GymPortalWSMain", portName = "GymPortalWSSOAP", endpointInterface = "wsdl.gymrest.disim.univaq.it.gymportalws.GymPortalWSInterface", targetNamespace = "http://it.univaq.disim.GymREST.wsdl/GymPortalWS.wsdl", wsdlLocation = "WEB-INF/wsdl/GymPortalWS.wsdl")
public class GymWSImpl {

    private static final String urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userDB = "gymportal";
    private static final String pswDB = "gymportal";


    public static final String GET_UTENTI= "SELECT * FROM user";
    public static final String ADD_RUOLO_UTENTE= "UPDATE user_role SET role_id=? WHERE user_id=?";
    public static final String GET_UTENTE = "SELECT * FROM user WHERE id=?";
    public static final String GET_RUOLI= "SELECT * FROM role";


    public wsdl.gymrest.disim.univaq.it.gymportalws.TipoListaUtente getUtenti() throws MsgErrore {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        TipoListaUtente listaUtenti = new TipoListaUtente();
        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(GET_UTENTI);) {

            while (rs.next()){

                TipoUtente utente = new TipoUtente();
                utente.setId(rs.getLong(1));
                utente.setEmail(rs.getString(2));
                utente.setLastname(rs.getString(3));
                utente.setName(rs.getString(4));
                utente.setPassword(rs.getString(5));
                utente.setUsername(rs.getString(6));

                listaUtenti.getUtente().add(utente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GymWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUtenti;

    }

    public void addRuoloUtente(long idUtente, long idRuolo) throws MsgErrore {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(ADD_RUOLO_UTENTE);) {

            st.setLong(2, idUtente);
            st.setLong(1, idRuolo);

            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public wsdl.gymrest.disim.univaq.it.gymportalws.TipoUtente getUtente(long id) throws MsgErrore {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        TipoUtente utente = new TipoUtente();

        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             PreparedStatement st = connection.prepareStatement(GET_UTENTE);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    utente.setId(rs.getLong(1));
                    utente.setEmail(rs.getString(2));
                    utente.setLastname(rs.getString(3));
                    utente.setName(rs.getString(4));
                    utente.setPassword(rs.getString(5));
                    utente.setUsername(rs.getString(6));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;

    }

    public wsdl.gymrest.disim.univaq.it.gymportalws.TipoListaRuolo getRuoli() throws MsgErrore {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        TipoListaRuolo listaRuoli = new TipoListaRuolo();
        try (Connection connection = DriverManager.getConnection(urlDB,userDB,pswDB);
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(GET_RUOLI);) {

            while (rs.next()){
                TipoRuolo ruolo = new TipoRuolo();
                ruolo.setId(rs.getLong(1));
                ruolo.setRole(rs.getString(2));

                listaRuoli.getRuolo().add(ruolo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GymWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaRuoli;

    }

}
