package e1.Estados;

import e1.Buque;
import e1.Base;

public class Desmantelado implements EstadoBuque {
    private static final Desmantelado instancia = new Desmantelado();
    private Desmantelado() {}
    public static Desmantelado getInstancia() { return instancia; }

    @Override
    public void enLaBase(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ha sido desmantelado y ya no está en servicio.");
    }

    @Override
    public void ejercicioNaval(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ha sido desmantelado y no puede participar en ejercicios navales.");
    }

    @Override
    public void solicitarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ha sido desmantelado y no puede ser reparado.");
    }

    @Override
    public void iniciarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ha sido desmantelado y no se puede iniciar una reparación.");
    }

    @Override
    public void completarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ha sido desmantelado y no se puede completar ninguna reparación.");
    }

    @Override
    public void repararExpress(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ha sido desmantelado y no puede recibir reparación express.");
    }

    @Override
    public void cancelarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ha sido desmantelado y no hay reparación que cancelar.");
    }

    @Override
    public void desmantelar(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " ya ha sido desmantelado.");
    }
}
