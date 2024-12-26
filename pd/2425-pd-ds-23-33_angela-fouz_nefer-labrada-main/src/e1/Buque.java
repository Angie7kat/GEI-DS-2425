package e1;

import e1.Estados.EstadoBuque;
import e1.Estados.EnBase;

public class Buque {
    private String nombre;
    private TipoBuque tipo;
    private EstadoBuque estado;
    private int ejerciciosCompletados;
    private double vida;

    public Buque(String nombre, TipoBuque tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = EnBase.getInstancia();  // El buque empieza en estado "EnBase"
        this.ejerciciosCompletados = 0;
        this.vida = 100;  // La vida del buque empieza al 100
    }

    // Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public TipoBuque getTipo() {
        return tipo;
    }

    public void setEstado(EstadoBuque estado) {
        this.estado = estado;
    }

    public EstadoBuque getEstado() {
        return estado;
    }

    public void setEstadoBuque(EstadoBuque estado) {
        this.estado = estado;
    }

    public int getEjerciciosCompletados() {
        return ejerciciosCompletados;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public void incrementarEjerciciosCompletados() {
        this.ejerciciosCompletados++;
    }
}
