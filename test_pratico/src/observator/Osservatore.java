package observator;

import classi.Vendita;

public interface Osservatore {

    void aggiorna(Vendita vendita); // metodo chiamato quando avviene una vendita
}