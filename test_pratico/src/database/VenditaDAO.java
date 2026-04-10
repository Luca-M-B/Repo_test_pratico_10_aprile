package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import classi.Vendita;

public class VenditaDAO {

    public void salvaVendita(Vendita vendita) {

        try {
            Connection conn = GestoreConnessioneDatabase
                    .getIstanza()
                    .getConnessione();

            String sql = "INSERT INTO vendite(totale, tipo_spedizione) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, vendita.getTotaleVendita());
            ps.setString(2, vendita.getSpedizione().getTipoSpedizione());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}