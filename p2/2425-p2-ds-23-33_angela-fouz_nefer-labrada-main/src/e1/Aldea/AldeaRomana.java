package e1.Aldea;

import e1.Tropas.TropasRomanas.TropasRomanas;
import java.util.List;

public class AldeaRomana extends Aldea<TropasRomanas> {
    public AldeaRomana(String nombre, int resistenciaMuralla, List<TropasRomanas> ejercito, int anho) {
        super(nombre, resistenciaMuralla, ejercito, anho);
    }

    public double calcularPoderDefensivo() {
        double poderDefensivo = 0;
        for (TropasRomanas tropa : getEjercito()) {
            poderDefensivo += tropa.calcularDefensa() + (getResistenciaMuralla() * 2);
        }
        return poderDefensivo;
    }

    public double calcularPoderOfensivo() {
        double poderOfensivo = 0;
        for (TropasRomanas tropa : getEjercito()) {
            poderOfensivo += tropa.calcularAtaque() * 1.1;
        }
        return poderOfensivo;
    }
}