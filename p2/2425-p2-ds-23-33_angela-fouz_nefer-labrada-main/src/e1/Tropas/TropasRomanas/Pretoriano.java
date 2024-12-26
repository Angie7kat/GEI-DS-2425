package e1.Tropas.TropasRomanas;

import e1.Tropas.Tropas;

public class Pretoriano extends TropasRomanas {
    public Pretoriano(boolean bufo) {
        super(30,65,bufo);
    }

    @Override
    public double calcularDefensa(){
        if (bufo){
            return puntosDefensa * 1.1;
        } else return puntosDefensa;
    }
}
