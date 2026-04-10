package facade;

import classi.Utente;
import classi.Admin;
import decorator.UtentePro;

public class GestoreApplicazione { // pattern facade per interagire con il gestionale

    public Utente creaUtente(String tipoUtente, String nomeUtente, int livelloAdmin) { // metodo per
                                                                                       // creare utenti

        if (tipoUtente.equalsIgnoreCase("BASE")) {
            return new Utente(nomeUtente, "BASE");
        } else if (tipoUtente.equalsIgnoreCase("PRO")) {
            Utente utenteBase = new Utente(nomeUtente, "BASE");
            return new UtentePro(utenteBase);
        } else if (tipoUtente.equalsIgnoreCase("ADMIN")) {
            return new Admin(nomeUtente, livelloAdmin);
        } else {
            System.out.println("Tipo utente non valido, verra creato utente BASE di default.");
            return new Utente(nomeUtente, "BASE");
        }

    }
}