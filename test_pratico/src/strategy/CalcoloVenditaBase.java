package strategy;

public class CalcoloVenditaBase extends CalcoloVenditaAstratto {

    @Override
    protected double applicaSconto(double totale) {
        return totale;
    }
}