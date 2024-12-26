package e1.Aldea;

import e1.Tropas.Tropas;
import java.util.List;

public abstract class Aldea<T extends Tropas> {

    private String nombre;
    private int resistenciaMuralla;
    private List<T> ejercito;
    private int anho;


    public String getNombre() {
        return nombre;
    }
    public int getResistenciaMuralla() {
        return resistenciaMuralla;
    }
    public List<T> getEjercito() {
        return ejercito;
    }
    public int getAnho() {
        return anho;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setResistenciaMuralla(int resistenciaMuralla) {
        this.resistenciaMuralla = resistenciaMuralla;
    }
    public void setEjercito(List<T> ejercito) {
        this.ejercito = ejercito;
    }
    public void setAnho(int anho) {
        this.anho = anho;
    }

    public Aldea(String nombre, int resistenciaMuralla, List<T> ejercito, int anho) {
        this.nombre = nombre;
        this.resistenciaMuralla = resistenciaMuralla;
        this.ejercito = ejercito;
        this.anho = anho;
    }

    // Implementación concreta del cálculo del poder defensivo
    public abstract double calcularPoderDefensivo();

    // Implementación concreta del cálculo del poder ofensivo
    public abstract double calcularPoderOfensivo();

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" have the following soldiery:\n");
        for (T tropa : ejercito) {
            sb.append(tropa.toString()).append("\n");
        }

        return sb.toString();
    }

}