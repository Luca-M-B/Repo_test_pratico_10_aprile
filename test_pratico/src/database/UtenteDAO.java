package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import classi.Utente;
import classi.Admin;
import decorator.UtentePro;

public class UtenteDAO { // gestione utenti

    public void salvaUtente(Utente utente) { // salva utenti su db

        try {
            Connection conn = GestoreConnessioneDatabase
                    .getIstanza()
                    .getConnessione();

            String sql = "INSERT INTO utenti(nome, tipo, livello_admin) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, utente.getNomeUtente());
            ps.setString(2, utente.getTipoUtente());

            if (utente instanceof Admin) {
                ps.setInt(3, ((Admin) utente).getLivelloAdmin());
            } else {
                ps.setInt(3, 0);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Utente> caricaUtenti() { // carica utenti da db

        ArrayList<Utente> listaUtenti = new ArrayList<>();

        try {
            Connection conn = GestoreConnessioneDatabase
                    .getIstanza()
                    .getConnessione();

            String sql = "SELECT * FROM utenti";

            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {

                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                int livello = rs.getInt("livello_admin");

                if (tipo.equalsIgnoreCase("ADMIN")) {

                    listaUtenti.add(new Admin(nome, livello));

                } else if (tipo.equalsIgnoreCase("PRO")) {

                    Utente base = new Utente(nome, "BASE");
                    listaUtenti.add(new UtentePro(base));

                } else {

                    listaUtenti.add(new Utente(nome, "BASE"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUtenti;
    }
}