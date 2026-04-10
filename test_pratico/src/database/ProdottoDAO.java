package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import classi.Prodotto;

public class ProdottoDAO {

    public ArrayList<Prodotto> caricaProdotti() { // carica prodotti da db

        ArrayList<Prodotto> listaProdotti = new ArrayList<>();

        try {
            Connection conn = GestoreConnessioneDatabase
                    .getIstanza()
                    .getConnessione();

            String sql = "SELECT * FROM prodotti";

            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {

                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                double prezzo = rs.getDouble("prezzo");

                Prodotto prodotto = new Prodotto(nome, categoria, prezzo);

                listaProdotti.add(prodotto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaProdotti;
    }
}