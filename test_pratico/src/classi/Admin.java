package classi;

public class Admin extends Utente {

    private int livelloAdmin;

    public Admin(String nomeUtente, int livelloAdmin) {
        super(nomeUtente, "ADMIN");
        this.livelloAdmin = livelloAdmin;
    }

    public void gestisciUtenti() { // metodo per gestione utenti
        if (livelloAdmin >= 2) {
            System.out.println("Gestione utenti consentita.");
        } else {
            System.out.println("Livello utente insufficiente.");
        }
    }

    public void gestisciSistema() { // metodo per gestione prodotti e spedizioni
        if (livelloAdmin >= 3) {
            System.out.println("Gestione prodotti e spedizioni consentita.");
        } else {
            System.out.println("Liivello utente insufficiente.");
        }
    }

    public int getLivelloAdmin() {
        return livelloAdmin;
    }
}