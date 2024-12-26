package e1.Aldea;

import e1.Tropas.TropasGalas.TropasGalas;
import java.util.List;

public class AldeaGalos extends Aldea<TropasGalas> {
    public AldeaGalos(String nombre, int resistenciaMuralla, List<TropasGalas> ejercito, int anho) {
        super(nombre, resistenciaMuralla, ejercito, anho);
    }


    public double calcularPoderDefensivo() {
        double poderDefensivo = 0;
        for (TropasGalas tropa : getEjercito()) {
            poderDefensivo += tropa.calcularDefensa() + (getResistenciaMuralla() * 1.5);
        }
        return poderDefensivo;
    }

    public double calcularPoderOfensivo() {
        double poderOfensivo = 0;
        for (TropasGalas tropa : getEjercito()) {
            poderOfensivo += tropa.calcularAtaque() * 1.2;
        }
        return poderOfensivo;
    }
}