package e1.Estados;

import e1.Buque;
import e1.Base;

public class EnBase implements EstadoBuque {
    private static final EnBase instancia = new EnBase();
    private EnBase() {}
    public static EnBase getInstancia() { return instancia; }

    @Override
    public void enLaBase(Buque buque, Base base) {
        System.out.println(buque.getNombre() + " ya está en la base.");
    }

    @Override
    public void ejercicioNaval(Buque buque, Base base) {
        System.out.println("Enviando " + buque.getNombre() + " a un ejercicio naval.");
        buque.setEstadoBuque(EnEjercicio.getInstancia());
    }

    @Override
    public void solicitarReparacion(Buque buque, Base base) {
        System.out.println("Solicitando reparación para " + buque.getNombre() + ".");
        buque.setEstadoBuque(PendienteReparacion.getInstancia());
    }

    @Override
    public void iniciarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " no necesita iniciar reparación, ya está en buen estado.");
    }

    @Override
    public void completarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " no está en reparación.");
    }

    @Override
    public void repararExpress(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " no necesita reparación express.");
    }

    @Override
    public void cancelarReparacion(Buque buque, Base base) {
        System.out.println("No hay reparación que cancelar para " + buque.getNombre() + ".");
    }

    @Override
    public void desmantelar(Buque buque, Base base) {
        System.out.println("Desmantelando el buque " + buque.getNombre() + ".");
        buque.setEstadoBuque(Desmantelado.getInstancia());
    }
}
