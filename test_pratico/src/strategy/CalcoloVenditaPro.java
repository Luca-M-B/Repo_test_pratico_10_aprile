package strategy;

public class CalcoloVenditaPro extends CalcoloVenditaAstratto {

    @Override
    protected double applicaSconto(double totale) {
        return totale - (totale * 0.10); // sconto 10%
    }
}