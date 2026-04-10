package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestoreConnessioneDatabase {

    private static GestoreConnessioneDatabase istanza;

    private final String url = "jdbc:mysql://localhost:3306/gestionale_vendite";
    private final String utente = "root";
    private final String password = "";

    private Connection connessione;

    private GestoreConnessioneDatabase() {
        try {
            connessione = DriverManager.getConnection(url, utente, password);
            System.out.println("Connessione al database riuscita.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static GestoreConnessioneDatabase getIstanza() {
        if (istanza == null) {
            istanza = new GestoreConnessioneDatabase();
        }
        return istanza;
    }

    public Connection getConnessione() {
        return connessione;
    }
}