package e1.Tropas.TropasTeutonas;

import e1.Tropas.Tropas;

public abstract class TropasTeutonas extends Tropas {
    public TropasTeutonas(int puntosAtaque, int puntosDefensa, boolean bufo) {
        super(puntosAtaque, puntosDefensa, bufo);
    }

    @Override
    public double calcularAtaque(){
            return puntosAtaque;
    }

    @Override
    public double calcularDefensa(){
        return puntosDefensa;
    }
}
