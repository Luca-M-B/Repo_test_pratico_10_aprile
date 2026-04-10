package classi;

public class Spedizione {

    private String tipoSpedizione;
    private double costoSpedizione;

    public Spedizione(String tipoSpedizione, double costoSpedizione) {
        this.tipoSpedizione = tipoSpedizione;
        this.costoSpedizione = costoSpedizione;
    }

    public double getCostoSpedizione() {
        return costoSpedizione;
    }

    public String getTipoSpedizione() {
        return tipoSpedizione;
    }
}