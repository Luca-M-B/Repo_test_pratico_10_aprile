package strategy;

public class CalcoloVenditaAdmin extends CalcoloVenditaAstratto {

    private double percentualeSconto;

    public CalcoloVenditaAdmin(double percentualeSconto) {
        this.percentualeSconto = percentualeSconto;
    }

    @Override
    protected double applicaSconto(double totale) {
        return totale - (totale * percentualeSconto); // sconto variabile scelto da admin
    }
}