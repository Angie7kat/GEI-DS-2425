package e1.Estados;

import e1.Buque;
import e1.Base;

public class EnReparacion implements EstadoBuque {
    private static final EnReparacion instancia = new EnReparacion();
    private EnReparacion() {}
    public static EnReparacion getInstancia() { return instancia; }

    @Override
    public void enLaBase(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en reparación en la base.");
    }

    @Override
    public void ejercicioNaval(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en reparación, no puede participar en ejercicios navales.");
    }

    @Override
    public void solicitarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ya está en reparación.");
    }

    @Override
    public void iniciarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ya está en reparación.");
    }

    @Override
    public void completarReparacion(Buque buque, Base base) {
        int costoReparacion = base.calcularCostoReparacion(buque);
        base.reducirFondos(costoReparacion);
        buque.setVida(100);
        buque.setEstadoBuque(EnBase.getInstancia());
        System.out.println("Reparación completada para " + buque.getNombre() + ". Costo: " + costoReparacion);
        System.out.println("Fondos restantes: " + base.getFondos());
    }

    @Override
    public void repararExpress(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ya está en reparación normal, no se puede aplicar reparación express.");
    }

    @Override
    public void cancelarReparacion(Buque buque, Base base) {
        System.out.println("Cancelando la reparación en curso para " + buque.getNombre() + ".");
        buque.setEstadoBuque(EnBase.getInstancia());
    }

    @Override
    public void desmantelar(Buque buque, Base base) {
        System.out.println("Cancelando la reparación y desmantelando el buque " + buque.getNombre() + ".");
        buque.setEstadoBuque(Desmantelado.getInstancia());
    }
}
