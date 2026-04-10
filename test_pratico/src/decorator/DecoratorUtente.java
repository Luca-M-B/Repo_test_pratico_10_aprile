package decorator;

import classi.Utente;

public abstract class DecoratorUtente extends Utente {

    protected Utente utenteDecorato;

    public DecoratorUtente(Utente utenteDecorato) {
        super(utenteDecorato.getNomeUtente(), utenteDecorato.getTipoUtente());
        this.utenteDecorato = utenteDecorato;
    }

    @Override
    public void inserisciVendita() {
        utenteDecorato.inserisciVendita();
    }
}