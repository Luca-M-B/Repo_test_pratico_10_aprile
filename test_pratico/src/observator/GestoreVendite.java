package observator;

import java.util.ArrayList;

import classi.Vendita;

public class GestoreVendite {

    private ArrayList<Osservatore> listaOsservatori;
    private Vendita ultimaVendita;

    public GestoreVendite() {
        this.listaOsservatori = new ArrayList<>();
    }

    public void aggiungiOsservatore(Osservatore osservatore) {
        listaOsservatori.add(osservatore);
    }

    public void rimuoviOsservatore(Osservatore osservatore) {
        listaOsservatori.remove(osservatore);
    }

    private void notificaOsservatori() {
        for (Osservatore osservatore : listaOsservatori) {
            osservatore.aggiorna(ultimaVendita);
        }
    }

    public void registraVendita(Vendita vendita) { // metodo chiamato a vendita effettuata per notificare observator
        System.out.println("Nuova vendita registrata.");
        this.ultimaVendita = vendita;
        notificaOsservatori();
    }
}