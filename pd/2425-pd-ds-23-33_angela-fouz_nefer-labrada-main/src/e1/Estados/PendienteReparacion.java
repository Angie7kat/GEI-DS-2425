package e1.Estados;

import e1.Buque;
import e1.Base;
import e1.TipoBuque;

public class PendienteReparacion implements EstadoBuque {
    private static final PendienteReparacion instancia = new PendienteReparacion();
    private PendienteReparacion() {}
    public static PendienteReparacion getInstancia() { return instancia; }

    @Override
    public void enLaBase(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está pendiente de reparación en la base.");
    }

    @Override
    public void ejercicioNaval(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está pendiente de reparación, no puede participar en ejercicios navales.");
    }

    @Override
    public void solicitarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ya está pendiente de reparación.");
    }

    @Override
    public void iniciarReparacion(Buque buque, Base base) {
        int costoReparacion = base.calcularCostoReparacion(buque);
        if (base.getFondos() >= costoReparacion) {
            System.out.println("Iniciando reparación para " + buque.getNombre() + ". Costo: " + costoReparacion);
            buque.setEstadoBuque(EnReparacion.getInstancia());
            base.reducirFondos(costoReparacion);
        } else {
            System.out.println("No hay fondos suficientes para iniciar la reparación de " + buque.getNombre() + ". Se necesitan " + costoReparacion + " créditos.");
        }
    }

    @Override
    public void completarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " aún no ha iniciado la reparación.");
    }

    @Override
    public void repararExpress(Buque buque, Base base) {
        if (buque.getTipo() == TipoBuque.DE || buque.getTipo() == TipoBuque.DD) {
            int costoReparacion = base.calcularCostoReparacion(buque);
            base.reducirFondos(costoReparacion);
            buque.setVida(100);
            buque.setEstadoBuque(EnBase.getInstancia());
            System.out.println("Reparación express completada para " + buque.getNombre() + ". Costo: " + costoReparacion);
            System.out.println("Fondos restantes: " + base.getFondos());
        } else {
            System.out.println("Solo los buques ultraligeros pueden recibir reparación express.");
        }
    }

    @Override
    public void cancelarReparacion(Buque buque, Base base) {
        System.out.println("Cancelando la solicitud de reparación para " + buque.getNombre() + ".");
        buque.setEstadoBuque(EnBase.getInstancia());
    }

    @Override
    public void desmantelar(Buque buque, Base base) {
        System.out.println("Cancelando la reparación y desmantelando el buque " + buque.getNombre() + ".");
        buque.setEstadoBuque(Desmantelado.getInstancia());
    }
}

