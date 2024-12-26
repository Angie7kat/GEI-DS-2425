package e1.Tropas.TropasTeutonas;

public class GuerreroDeMaza extends TropasTeutonas{
    public GuerreroDeMaza(boolean bufo) {
        super(40,20,bufo);
    }

    @Override
    public double calcularAtaque(){
        if (bufo){
            return puntosAtaque * 1.25;
        } else return puntosAtaque;
    }
}
