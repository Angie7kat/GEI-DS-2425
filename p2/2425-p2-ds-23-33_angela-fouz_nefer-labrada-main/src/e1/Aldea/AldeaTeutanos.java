package e1.Aldea;

import e1.Tropas.TropasTeutonas.TropasTeutonas;
import java.util.List;

public class AldeaTeutanos extends Aldea<TropasTeutonas> {
    public AldeaTeutanos(String nombre, int resistenciaMuralla, List<TropasTeutonas> ejercito, int anho) {
        super(nombre, resistenciaMuralla, ejercito, anho);
    }

    public double calcularPoderDefensivo() {
        double poderDefensivo = 0;
        for (TropasTeutonas tropa : getEjercito()) {
            poderDefensivo += tropa.calcularDefensa() + (getResistenciaMuralla() * 2);
        }
        return poderDefensivo;
    }

    public double calcularPoderOfensivo() {
        double poderOfensivo = 0;
        for (TropasTeutonas tropa : getEjercito()) {
            poderOfensivo += tropa.calcularAtaque() * 0.95;
        }
        return poderOfensivo;
    }
}