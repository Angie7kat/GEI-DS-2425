package e1;

import e1.Estados.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Base {
    private List<Buque> buquesActivos;
    private List<Buque> buquesInactivos;
    private List<Buque> buquesPerdidosEnCombate;
    private double fondos;

    public Base() {
        this.buquesActivos = new ArrayList<>();
        this.buquesInactivos = new ArrayList<>();
        this.buquesPerdidosEnCombate = new ArrayList<>();
        this.fondos = 0;
    }

    public void agregarBuque(Buque buque) {
        buquesInactivos.add(buque);
        System.out.println("Se ha agregado " + buque.getNombre() + " a los buques inactivos.");
    }

    public void agregarBuquesActivos(int cantidad) {
        for (int i = 0; i < cantidad && i < buquesInactivos.size(); i++) {
            Buque buque = buquesInactivos.get(i);
            buquesInactivos.remove(buque);
            buquesActivos.add(buque);
            System.out.println("Se ha enviado " + buque.getNombre() + " a los buques activos.");
        }
    }

    public void enviarAMision() {
        for (Buque buque : buquesInactivos) {
            buquesActivos.add(buque);
            buque.getEstado().ejercicioNaval(buque, this);
            System.out.println("Movido " + buque + " activo");
        }
        buquesInactivos.clear();
    }

    public void moverBuqueAInactivos(Buque buque) {
        buquesActivos.remove(buque);
        buquesInactivos.add(buque);
        System.out.println("El buque " + buque.getNombre() + " ha sido movido a los inactivos.");
    }

    public void asignarRecompensa(Buque buque) {
        int recompensa = calcularRecompensa(buque);
        double cantidad = this.fondos + recompensa;
        setFondos(cantidad);
        System.out.println("El buque " + buque.getNombre() + " ha recibido una recompensa de " + recompensa + " créditos.");
    }

    public int calcularRecompensa(Buque buque) {
        switch (buque.getTipo()) {
            case BB: return 500000;
            case CA: return 300000;
            case CV: return 400000;
            case DE: return 100000;
            case DD: return 150000;
            case CL: return 200000;
            case AV: return 250000;
            default: return 0;
        }
    }

    public void repararBuque(Buque buque) {
        buque.getEstado().solicitarReparacion(buque, this);
    }

    public void confirmarReparacion(Buque buque) {
        buque.getEstado().completarReparacion(buque, this);
    }

    public void repararExpress(Buque buque) {
        buque.getEstado().repararExpress(buque, this);
    }

    public void cancelarReparacion(Buque buque) {
        buque.getEstado().cancelarReparacion(buque, this);
    }

    public int calcularCostoReparacion(Buque buque) {
        int costoBase = getCostoBasePorTipo(buque.getTipo());
        double vidaFaltante = 100 - buque.getVida();
        double factorVida = vidaFaltante / 100.0;

        int costoTotal = (int) (costoBase * factorVida);

        return Math.max(costoTotal, costoBase / 10);
    }

    private int getCostoBasePorTipo(TipoBuque tipo) {
        switch (tipo) {
            case BB: return 59000;
            case CA: return 35000;
            case CV: return 45000;
            case DE: return 12000;
            case DD: return 17000;
            case CL: return 22000;
            case AV: return 27000;
            default: return 10000;
        }
    }

    public void mostrarFondos() {
        System.out.println("Fondos disponibles: " + fondos);
    }

    public void desmantelar(Buque buque) {
        buque.getEstado().desmantelar(buque, this);
    }

    public void listarBuquesActivos() {
        System.out.println("BUQUES ACTIVOS");
        System.out.println("----------------------------------");
        for (Buque buque : buquesActivos) {
            System.out.println("Nombre: " + buque.getNombre() + " | Tipo: " + buque.getTipo() + " | Estado: " + buque.getEstado().getClass().getSimpleName() + " | Vida: " + buque.getVida() + "%");
        }
    }

    public void listarBuquesInactivos() {
        System.out.println("BUQUES INACTIVOS");
        System.out.println("----------------------------------");
        for (Buque buque : buquesInactivos) {
            System.out.println("Nombre: " + buque.getNombre() + " | Razón: " + buque.getEstado().getClass().getSimpleName() + " | Misiones: " + buque.getEjerciciosCompletados());
        }
    }

    public void listarBuquesPerdidosEnCombate() {
        System.out.println("BUQUES PERDIDOS EN COMBATE");
        System.out.println("----------------------------------");
        for (Buque buque : buquesPerdidosEnCombate) {
            System.out.println("Nombre: " + buque.getNombre() + " | Tipo: " + buque.getTipo());
        }
    }

    public void reducirFondos(int cantidad) {
        this.fondos -= cantidad;
    }

    public double getFondos() {
        return this.fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    public void moverABuquesPerdidos(Buque buque) {
        buquesActivos.remove(buque);
        buquesPerdidosEnCombate.add(buque);
    }

    public void mostrarEstadoFlota() {
        System.out.println("ESTADO DE LA FLOTA");
        System.out.println("----------------------------------");
        listarBuquesActivos();
        listarBuquesInactivos();
        listarBuquesPerdidosEnCombate();
        mostrarFondos();
    }

    public boolean hayBuquesEnReparacion() {
        for (Buque buque : buquesInactivos) {
            if (buque.getEstado() instanceof EnReparacion) {
                return true;
            }
        }
        return false;
    }

    public List<Buque> getBuquesActivos() {
        return buquesActivos;
    }

    public List<Buque> getBuquesInactivos() {
        return buquesInactivos;
    }

    public List<Buque> getBuquesPerdidosEnCombate() {
        return buquesPerdidosEnCombate;
    }
}
