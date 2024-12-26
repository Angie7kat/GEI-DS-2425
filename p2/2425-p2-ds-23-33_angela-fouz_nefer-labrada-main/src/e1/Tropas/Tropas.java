package e1.Tropas;

public abstract class Tropas {
    protected int puntosAtaque;
    protected int puntosDefensa;
    protected boolean bufo;

    public abstract double calcularAtaque();
    public abstract double calcularDefensa();

    public Tropas(int puntosAtaque, int puntosDefensa, boolean bufo) {
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.bufo = bufo;
    }

    public boolean isPremium() {
        return bufo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [Ataque: " + puntosAtaque + ", Defensa: " + puntosDefensa + "]";
    }
}
