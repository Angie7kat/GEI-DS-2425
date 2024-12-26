package e1.Estados;

import e1.Buque;
import e1.Base;
import java.util.Random;

public class EnEjercicio implements EstadoBuque {
    private static final EnEjercicio instancia = new EnEjercicio();
    private EnEjercicio() {}
    public static EnEjercicio getInstancia() { return instancia; }

    @Override
    public void enLaBase(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en ejercicio, no puede estar en la base.");
    }

    @Override
    public void ejercicioNaval(Buque buque, Base base) {
        Random random = new Random();
        int dano = random.nextInt(41) + 10;  // Daño aleatorio entre 10 y 50
        System.out.println("El buque " + buque.getNombre() + " ha recibido un daño de " + dano + "%.");

        double nuevaVida = buque.getVida() - dano;
        if (nuevaVida <= 0) {
            buque.setVida(0);
            System.out.println("El buque " + buque.getNombre() + " se ha hundido.");
            buque.setEstadoBuque(Hundido.getInstancia());
            base.moverABuquesPerdidos(buque);
        } else {
            buque.setVida(nuevaVida);
            System.out.println("El buque " + buque.getNombre() + " ha sobrevivido al ejercicio con " + buque.getVida() + "% de vida restante.");
            buque.incrementarEjerciciosCompletados();  // Incrementamos el contador de ejercicios completados
            base.asignarRecompensa(buque);
            base.moverBuqueAInactivos(buque);
            buque.setEstadoBuque(PendienteReparacion.getInstancia());
        }
    }

    @Override
    public void solicitarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en ejercicio, no puede solicitar reparación.");
    }

    @Override
    public void iniciarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en ejercicio, no puede iniciar reparación.");
    }

    @Override
    public void completarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en ejercicio, no puede completar reparación.");
    }

    @Override
    public void repararExpress(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en ejercicio, no puede recibir reparación express.");
    }

    @Override
    public void cancelarReparacion(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en ejercicio, no hay reparación que cancelar.");
    }

    @Override
    public void desmantelar(Buque buque, Base base) {
        System.out.println("El buque " + buque.getNombre() + " está en ejercicio, no puede ser desmantelado.");
    }
}
