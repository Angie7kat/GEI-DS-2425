package e1.Tropas.TropasGalas;

public class RayoDeTeutates extends TropasGalas{

    public RayoDeTeutates(boolean bufo){
        super(100,25,bufo);
    }

    @Override
    public double calcularAtaque(){
        if(bufo){
            return puntosAtaque * 0.75;
        } else return puntosAtaque;
    }

    @Override
    public double calcularDefensa(){
        if(bufo){
            return puntosDefensa * 1.25;
        } else return puntosDefensa;
    }
}
