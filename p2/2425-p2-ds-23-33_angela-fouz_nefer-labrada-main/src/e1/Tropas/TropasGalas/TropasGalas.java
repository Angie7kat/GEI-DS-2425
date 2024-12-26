package e1.Tropas.TropasGalas;

import e1.Tropas.Tropas;

public abstract class TropasGalas extends Tropas {
    public TropasGalas(int puntosAtaque, int puntosDefensa, boolean premium) {
        super(puntosAtaque, puntosDefensa, premium);
    }

    public double calcularAtaque() {
            return puntosAtaque;
    }

    public double calcularDefensa() {
        return puntosDefensa;
    }
}
