package e2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class e2 {
    private Stock accionApple;
    private Stock accionMicrosoft;
    private SimpleClient clienteSimple;
    private DetailedClient clienteDetallado;
    private SpecificStockClient clienteEspecifico;

    private final ByteArrayOutputStream contenidoSalida = new ByteArrayOutputStream();
    private final PrintStream salidaOriginal = System.out;

    @BeforeEach
    void configurar() {
        System.setOut(new PrintStream(contenidoSalida));

        accionApple = new Stock("AAPL");
        accionMicrosoft = new Stock("MSFT");

        clienteSimple = new SimpleClient("InversorSimple");
        clienteDetallado = new DetailedClient("InversorDetallado");
        clienteEspecifico = new SpecificStockClient("InversorApple", "AAPL");
    }

    @Test
    void probarAdjuntarDesadjuntarObservadores() {
        accionApple.attach(clienteSimple);
        accionApple.attach(clienteDetallado);
        accionApple.attach(clienteEspecifico);

        assertEquals(3, accionApple.getObserversCount());
        accionApple.detach(clienteSimple);
        assertEquals(2, accionApple.getObserversCount());
        accionApple.detachAll();
        assertEquals(0, accionApple.getObserversCount());
    }

    @Test
    void probarActualizacionAccion() {
        accionApple.attach(clienteSimple);
        accionApple.attach(clienteDetallado);
        accionApple.attach(clienteEspecifico);

        accionApple.updateStock(150.0, 155.0, 145.0, 1000000);

        String salida = contenidoSalida.toString();
        assertTrue(salida.contains("InversorSimple - Actualización para: AAPL con precio de cierre: 150.0"));
        assertTrue(salida.contains("InversorDetallado - Actualización detallada de AAPL:"));
        assertTrue(salida.contains("InversorApple - Actualización para AAPL: Precio de cierre = 150.0"));
    }

    @Test
    void probarClienteAccionEspecifica() {
        accionMicrosoft.attach(clienteEspecifico);
        accionMicrosoft.updateStock(200.0, 205.0, 195.0, 500000);

        String salida = contenidoSalida.toString();
        assertFalse(salida.contains("InversorApple"));
    }

    @Test
    void probarFormatoDatosAccion() {
        accionApple.updateStock(150.0, 155.0, 145.0, 1000000);
        String datosAccion = accionApple.getFormattedData();
        assertEquals("AAPL: Cierre=150.0, Valor Alto=155.0, Valor Bajo=145.0, Volumen=1000000", datosAccion);
    }

    @Test
    void probarActualizacionMultiplesAcciones() {
        SimpleClient clienteMultipleAcciones = new SimpleClient("InversorMultiplesAcciones");
        accionApple.attach(clienteMultipleAcciones);
        accionMicrosoft.attach(clienteMultipleAcciones);

        accionApple.updateStock(150.0, 155.0, 145.0, 1000000);
        accionMicrosoft.updateStock(200.0, 205.0, 195.0, 500000);

        String salida = contenidoSalida.toString();
        assertTrue(salida.contains("InversorMultiplesAcciones - Actualización para: AAPL con precio de cierre: 150.0"));
        assertTrue(salida.contains("InversorMultiplesAcciones - Actualización para: MSFT con precio de cierre: 200.0"));
    }

    @Test
    void probarLongitudSimboloAccion() {
        assertThrows(IllegalArgumentException.class, () -> new Stock("TOOLONG"));
        assertDoesNotThrow(() -> new Stock("OK"));
    }

    @Test
    void probarActualizacionPrecioMaximoMinimo() {
        accionApple.attach(clienteDetallado);
        accionApple.updateStock(150.0, 160.0, 140.0, 1000000);
        accionApple.updateStock(155.0, 165.0, 135.0, 1100000);

        String salida = contenidoSalida.toString();
        assertTrue(salida.contains("Valor Alto: 165.0"));
        assertTrue(salida.contains("Valor Bajo: 135.0"));
    }

    @Test
    void probarDiferentesTiposClientes() {
        accionApple.attach(clienteSimple);
        accionApple.attach(clienteDetallado);
        accionApple.attach(clienteEspecifico);

        accionApple.updateStock(150.0, 155.0, 145.0, 1000000);

        String salida = contenidoSalida.toString();
        assertTrue(salida.contains("InversorSimple - Actualización para: AAPL con precio de cierre: 150.0"));
        assertFalse(salida.contains("InversorSimple - Actualización detallada"));
        assertTrue(salida.contains("InversorDetallado - Actualización detallada de AAPL:"));
        assertTrue(salida.contains("Volumen: 1000000"));
        assertTrue(salida.contains("InversorApple - Actualización para AAPL: Precio de cierre = 150.0"));
    }

    @Test
    void probarNuevoTipoCliente() {
        class ClienteVolumen implements Observer {
            private final String nombre;

            public ClienteVolumen(String nombre) {
                this.nombre = nombre;
            }

            @Override
            public void update(Subject subject) {
                if (subject instanceof Stock accion) {
                    System.out.println(nombre + " - Actualización de volumen para " + accion.getSymbol() + ": " + accion.getVolume());
                }
            }
        }

        ClienteVolumen clienteVolumen = new ClienteVolumen("RastreadorVolumen");
        accionApple.attach(clienteVolumen);
        accionApple.updateStock(150.0, 155.0, 145.0, 1000000);

        String salida = contenidoSalida.toString();
        assertTrue(salida.contains("RastreadorVolumen - Actualización de volumen para AAPL: 1000000"));
    }

    @AfterEach
    void restaurarFlujos() {
        System.setOut(salidaOriginal);
    }
}