package e1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import e1.Estados.*;

import java.util.ArrayList;
import java.util.List;

public class TestBaseNaval {

    private  Base base;
    private  Buque buque1, buque2, buque3, buque4;

    @Before
    public void setUp() {
        base = new Base();
        buque1 = new Buque("Buque 1", TipoBuque.DD);
        buque2 = new Buque("Buque 2", TipoBuque.CL);
        buque3 = new Buque("Buque 3", TipoBuque.CA);
        buque4 = new Buque("Buque 4", TipoBuque.BB);

        base.agregarBuque(buque1);
        base.agregarBuque(buque2);
        base.agregarBuque(buque3);
        base.agregarBuque(buque4);
    }

    @Test
    public void testAgregarBuque() {
        assertEquals(4, base.getBuquesInactivos().size());
        assertEquals(0, base.getBuquesActivos().size());
        assertEquals(0, base.getBuquesPerdidosEnCombate().size());
    }

    @Test
    public void testEnviarAMision() {
        base.enviarAMision();
        assertEquals(4, base.getBuquesActivos().size());
        assertEquals(0, base.getBuquesInactivos().size());
        for (Buque buque : base.getBuquesActivos()) {
            assertTrue(buque.getEstado() instanceof EnEjercicio);
        }
    }

    @Test
    public void testEjercicioNaval() {
        base.enviarAMision();

        List<Buque> lista = new ArrayList<>(base.getBuquesActivos());

        for (Buque buque : lista) {
            buque.getEstado().ejercicioNaval(buque, base);
        }

        for (Buque buque : base.getBuquesInactivos()) {
            assertTrue(buque.getEstado() instanceof PendienteReparacion);
        }
        for (Buque buque : base.getBuquesPerdidosEnCombate()) {
            assertTrue(buque.getEstado() instanceof Hundido);
        }
    }

    @Test
    public void testSolicitarReparacion() {
        buque1.setVida(50);
        base.repararBuque(buque1);
        assertTrue(buque1.getEstado() instanceof PendienteReparacion);
    }

    @Test
    public void testIniciarReparacion() {
        buque1.setVida(50);
        base.setFondos(1000000);
        base.repararBuque(buque1);

        ( buque1.getEstado()).iniciarReparacion(buque1, base);
        assertTrue(buque1.getEstado() instanceof EnReparacion);
    }

    @Test
    public void testIniciarReparacionMal() {
        buque1.setVida(50);
        base.repararBuque(buque1);

        ( buque1.getEstado()).iniciarReparacion(buque1, base);
        assertFalse(buque1.getEstado() instanceof EnReparacion);
    }

    @Test
    public void testCompletarReparacion() {
        buque1.setVida(50);
        base.setFondos(1000000);
        base.repararBuque(buque1);
        ((PendienteReparacion) buque1.getEstado()).iniciarReparacion(buque1, base);
        base.confirmarReparacion(buque1);
        assertTrue(buque1.getEstado() instanceof EnBase);
        assertEquals(100, buque1.getVida(), 0.01);
    }

    @Test
    public void testRepararExpress() {
        buque1.setVida(50);
        base.repararBuque(buque1);
        base.repararExpress(buque1);
        assertTrue(buque1.getEstado() instanceof EnBase);
        assertEquals(100, buque1.getVida(), 0.01);
    }

    @Test
    public void testCancelarReparacion() {
        buque1.setVida(50);
        base.repararBuque(buque1);
        base.cancelarReparacion(buque1);
        assertTrue(buque1.getEstado() instanceof EnBase);
    }

    @Test
    public void testDesmantelar() {
        base.desmantelar(buque1);
        assertTrue(buque1.getEstado() instanceof Desmantelado);
    }

    @Test
    public void testCalcularRecompensa() {
        int recompensa = base.calcularRecompensa(buque1);
        assertEquals(150000, recompensa);
    }

    @Test
    public void testCalcularCostoReparacion() {
        buque1.setVida(50);
        int costo = base.calcularCostoReparacion(buque1);
        assertEquals(8500, costo);
    }

    @Test
    public void testMoverBuqueAInactivos() {
        base.enviarAMision();
        base.moverBuqueAInactivos(buque1);
        assertTrue(base.getBuquesInactivos().contains(buque1));
        assertFalse(base.getBuquesActivos().contains(buque1));
    }

    @Test
    public void testMoverABuquesPerdidos() {
        base.enviarAMision();
        base.moverABuquesPerdidos(buque1);
        assertTrue(base.getBuquesPerdidosEnCombate().contains(buque1));
        assertFalse(base.getBuquesActivos().contains(buque1));
    }

    @Test
    public void testHayBuquesEnReparacion() {
        assertFalse(base.hayBuquesEnReparacion());
        buque1.setVida(50);
        base.setFondos(1000000);
        base.repararBuque(buque1);
        (buque1.getEstado()).iniciarReparacion(buque1, base);
        assertTrue(base.hayBuquesEnReparacion());
    }

}