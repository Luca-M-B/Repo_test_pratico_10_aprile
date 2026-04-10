package strategy;

import classi.Vendita;

public abstract class CalcoloVenditaAstratto implements StrategiaCalcoloVendita {

    @Override
    public double calcolaTotale(Vendita vendita) {

        double totaleProdotti = calcolaTotaleProdotti(vendita);
        double costoSpedizione = calcolaCostoSpedizione(vendita, totaleProdotti);

        double totale = totaleProdotti + costoSpedizione;

        // delega alla sottoclasse
        return applicaSconto(totale);
    }

    private double calcolaTotaleProdotti(Vendita vendita) {

        double totale = 0;

        for (int i = 0; i < vendita.getListaProdotti().size(); i++) {

            double prezzo = vendita.getListaProdotti().get(i).getPrezzoBase();
            int quantita = vendita.getListaQuantita().get(i);

            totale += prezzo * quantita;
        }

        return totale;
    }

    private double calcolaCostoSpedizione(Vendita vendita, double totaleProdotti) {

        if (vendita.getSpedizione().getTipoSpedizione().equalsIgnoreCase("RAPIDA")) {
            return totaleProdotti * 0.10;
        } else {
            return totaleProdotti * 0.05;
        }
    }

    protected abstract double applicaSconto(double totale);
}