package decorator;

import classi.Utente;

public class UtentePro extends DecoratorUtente {

    public UtentePro(Utente utenteDecorato) {
        super(utenteDecorato);
        this.tipoUtente = "PRO";
    }

    @Override
    public void inserisciVendita() {
        System.out.println("Utente PRO: inserimento vendita avanzato.");
        super.inserisciVendita();
    }

    public void modificaVendita() { // funzionalità aggiuntiva del pro
        System.out.println("Modifica vendita consentita (utente PRO).");
    }

    public void visualizzaReportAvanzato() { // funzionalità aggiuntiva pro
        System.out.println("Visualizzazione report avanzato.");
    }
}