package observator;

import classi.Vendita;

public class OsservatoreAdmin implements Osservatore { // osservatore istanziabile

    private String nomeAdmin;

    public OsservatoreAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    @Override
    public void aggiorna(Vendita vendita) {
        System.out.println("Admin " + nomeAdmin + " notificato: nuova vendita di "
                + vendita.getTotaleVendita() + " euro");
    }
}