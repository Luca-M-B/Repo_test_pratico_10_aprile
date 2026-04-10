package classi;

public class Prodotto {

    private static int contatore = 0;
    private int idProdotto;
    private String nome;
    private String categoria;
    private double prezzoBase;

    public Prodotto(String nome, String categoria, double prezzoBase) {
        this.idProdotto = ++contatore;
        this.nome = nome;
        this.categoria = categoria;
        this.prezzoBase = prezzoBase;
    }

    public double getPrezzoBase() {
        return prezzoBase;
    }

    public String getNomeProdotto() {
        return nome;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public String getCategoria() {
        return categoria;
    }
}