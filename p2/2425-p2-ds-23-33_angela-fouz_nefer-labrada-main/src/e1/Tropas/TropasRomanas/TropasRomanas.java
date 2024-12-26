package e1.Tropas.TropasRomanas;

import e1.Tropas.Tropas;

public abstract class TropasRomanas extends Tropas {


    public TropasRomanas(int puntosAtaque, int puntosDefensa, boolean premium) {
        super(puntosAtaque, puntosDefensa, premium);
    }

    @Override
    public double calcularAtaque() {
         return puntosAtaque;
    }

    @Override
    public double calcularDefensa() {
        return puntosDefensa;
    }
}
