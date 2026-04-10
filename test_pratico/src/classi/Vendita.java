package classi;

import java.util.ArrayList;

import strategy.StrategiaCalcoloVendita;

public class Vendita {

    private static int contatore = 0;
    private int idVendita;
    private ArrayList<Prodotto> listaProdotti;
    private ArrayList<Integer> listaQuantita;
    private Spedizione spedizione;
    private double totaleVendita;

    private StrategiaCalcoloVendita strategiaCalcoloVendita;

    public Vendita(Spedizione spedizione) {
        this.idVendita = ++contatore;
        this.listaProdotti = new ArrayList<>();
        this.listaQuantita = new ArrayList<>();
        this.spedizione = spedizione;
    }

    public void aggiungiProdotto(Prodotto prodotto, int quantita) {
        listaProdotti.add(prodotto);
        listaQuantita.add(quantita);
    }

    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public ArrayList<Integer> getListaQuantita() {
        return listaQuantita;
    }

    public Spedizione getSpedizione() {
        return spedizione;
    }

    public void setStrategiaCalcoloVendita(StrategiaCalcoloVendita strategia) {
        this.strategiaCalcoloVendita = strategia;
    }

    public void calcolaTotale() {
        totaleVendita = strategiaCalcoloVendita.calcolaTotale(this);
    }

    public double getTotaleVendita() {
        return totaleVendita;
    }

    public void stampaDettagliVendita() {

        System.out.println("\n\tDETTAGLIO VENDITA");

        for (int i = 0; i < listaProdotti.size(); i++) {

            Prodotto prodotto = listaProdotti.get(i);
            int quantita = listaQuantita.get(i);

            System.out.println(
                    "Prodotto: " + prodotto.getNomeProdotto() + " | Quantità: " + quantita + " | Prezzo unitario: "
                            + prodotto.getPrezzoBase());
        }

        System.out.println("Tipo spedizione: " + spedizione.getTipoSpedizione());
        System.out.println("Totale finale: " + totaleVendita);
    }
}