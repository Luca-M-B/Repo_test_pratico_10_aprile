package classi;

public class Utente {

    private static int contatore = 0;
    protected int idUtente;
    protected String nomeUtente;
    protected String tipoUtente; // se base, pro o admin

    public Utente(String nomeUtente, String tipoUtente) {
        this.idUtente = ++contatore; // per settare id nuovo
        this.nomeUtente = nomeUtente;
        this.tipoUtente = tipoUtente;
    }

    public void inserisciVendita() {
        System.out.println("Inserimento vendita effettuato da utente base.");
    }

    // Getter
    public int getIdUtente() {
        return idUtente;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getTipoUtente() {
        return tipoUtente;
    }
}