package e1.Estados;

import e1.Buque;
import e1.Base;

public class Hundido implements EstadoBuque {
    private static final Hundido instancia = new Hundido();
    private Hundido() {}
    public static Hundido getInstancia() { return instancia; }

    @Override
    public void enLaBase(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no puede estar en la base.");
    }

    @Override
    public void ejercicioNaval(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no puede participar en ejercicios navales.");
    }

    @Override
    public void solicitarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no se puede solicitar reparación.");
    }

    @Override
    public void iniciarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no se puede iniciar reparación.");
    }

    @Override
    public void completarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no se puede completar reparación.");
    }

    @Override
    public void repararExpress(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no se puede realizar reparación express.");
    }

    @Override
    public void cancelarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no hay reparación que cancelar.");
    }

    @Override
    public void desmantelar(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está hundido, no se puede desmantelar.");
    }
}
