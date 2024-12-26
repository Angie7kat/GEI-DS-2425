package e1;

import e1.Aldea.AldeaRomana;
import e1.Aldea.AldeaGalos;
import e1.Aldea.AldeaTeutanos;
import e1.Tropas.TropasGalas.Druida;
import e1.Tropas.TropasGalas.Falange;
import e1.Tropas.TropasGalas.RayoDeTeutates;
import e1.Tropas.TropasGalas.TropasGalas;
import e1.Tropas.TropasRomanas.EquitesImperatoris;
import e1.Tropas.TropasRomanas.Legionario;
import e1.Tropas.TropasRomanas.Pretoriano;
import e1.Tropas.TropasRomanas.TropasRomanas;
import e1.Tropas.TropasTeutonas.GuerreroDeHacha;
import e1.Tropas.TropasTeutonas.GuerreroDeMaza;
import e1.Tropas.TropasTeutonas.Paladin;
import e1.Tropas.TropasTeutonas.TropasTeutonas;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class e1 {

    @Test
    public void testSimularBatallaRomanosVsGalos() {
        // Crear ejército romano
        List<TropasRomanas> ejercitoRomano = new ArrayList<>();
        ejercitoRomano.add(new Legionario(false));  // No premium
        ejercitoRomano.add(new Pretoriano(true));   // Premium
        ejercitoRomano.add(new EquitesImperatoris(false));  // No premium

        // Crear ejército galo
        List<TropasGalas> ejercitoGalo = new ArrayList<>();
        ejercitoGalo.add(new Druida(false));
        ejercitoGalo.add(new RayoDeTeutates(true));  // Premium (armadura pesada)
        ejercitoGalo.add(new Falange(false));

        // Crear aldeas
        AldeaRomana aldeaRomana = new AldeaRomana("Aldea Romana", 300,  ejercitoRomano,200);
        AldeaGalos aldeaGalos = new AldeaGalos("Aldea Galos", 250,  ejercitoGalo,500);

        // Simulador
        Travian simulador = new Travian();
        String resultado = String.valueOf(simulador.simularBatalla(aldeaRomana, aldeaGalos));

        // Imprimir el resultado para revisión manual (opcional)
        System.out.println(resultado);

        // Validar que el resultado contenga información relevante
        assertTrue(resultado.contains("Aldea Romana Attacks Aldea Galos"));
    }

    @Test
    public void testSimularBatallaTeutonesVsRomanos() {
        // Crear ejército teutón
        List<TropasTeutonas> ejercitoTeuton = new ArrayList<>();
        ejercitoTeuton.add(new GuerreroDeHacha(false));
        ejercitoTeuton.add(new GuerreroDeMaza(true));  // Premium (mazas de metal)
        ejercitoTeuton.add(new Paladin(false));

        // Crear ejército romano
        List<TropasRomanas> ejercitoRomano = new ArrayList<>();
        ejercitoRomano.add(new Legionario(false));
        ejercitoRomano.add(new Pretoriano(false));
        ejercitoRomano.add(new EquitesImperatoris(false));

        // Crear aldeas
        AldeaTeutanos aldeaTeutanos = new AldeaTeutanos("Aldea Teutona", 200,  ejercitoTeuton,500);
        AldeaRomana aldeaRomana = new AldeaRomana("Aldea Romana", 400,  ejercitoRomano,200);

        // Simulador
        Travian simulador = new Travian();
        String resultado = String.valueOf(simulador.simularBatalla(aldeaTeutanos, aldeaRomana));

        // Validar que el resultado contenga información relevante
        assertTrue(resultado.contains("Aldea Teutona Attacks Aldea Romana"));
    }

    @Test
    public void testBonificacionTropasPremium() {
        // Probar una tropa con bonus premium
        TropasRomanas pretorianoPremium = new Pretoriano(true); // Premium
        TropasRomanas pretorianoNormal = new Pretoriano(false); // Normal

        // La defensa del Pretoriano Premium debería ser mayor
        assertEquals(71.5, pretorianoPremium.calcularDefensa(), 0.1); // 65 * 1.1 (bonus de armadura)
        assertEquals(65.0, pretorianoNormal.calcularDefensa(), 0.1); // Defensa base sin armadura
    }

    @Test
    public void testReduccionAtaqueRayoDeTeutates() {
        // Probar la penalización por usar armadura pesada en el Rayo de Teutates
        RayoDeTeutates rayoTeutatesPremium = new RayoDeTeutates(true); // Armadura pesada
        RayoDeTeutates rayoTeutatesNormal = new RayoDeTeutates(false); // Sin armadura

        System.out.println(rayoTeutatesPremium.calcularDefensa());

        // La defensa del Rayo de Teutates con armadura pesada debería ser mayor
        assertEquals(31.25, rayoTeutatesPremium.calcularDefensa(), 0.1); // 25 * 1.25 (bonus de armadura)
        // El ataque del Rayo de Teutates con armadura debería ser menor
        assertEquals(75.0, rayoTeutatesPremium.calcularAtaque(), 0.1); // 100 * 0.75 (penalización de ataque)
        assertEquals(100.0, rayoTeutatesNormal.calcularAtaque(), 0.1); // Ataque base sin penalización
    }

    @Test
    public void testSimularBatallaGanador() {
        // Crear ejército teutón
        List<TropasTeutonas> ejercitoTeuton = new ArrayList<>();
        ejercitoTeuton.add(new GuerreroDeHacha(false));
        ejercitoTeuton.add(new Paladin(false));

        // Crear ejército galo
        List<TropasGalas> ejercitoGalo = new ArrayList<>();
        ejercitoGalo.add(new Druida(false));
        ejercitoGalo.add(new Falange(false));

        // Crear aldeas
        AldeaTeutanos aldeaTeutona = new AldeaTeutanos("Teutonia", 300,  ejercitoTeuton,500);
        AldeaGalos aldeaGala = new AldeaGalos("Galos", 200,  ejercitoGalo,700);

        // Simulador
        Travian simulador = new Travian();
        String resultado = String.valueOf(simulador.simularBatalla(aldeaTeutona, aldeaGala));

        // Imprimir el resultado para revisión manual (opcional)
        System.out.println(resultado);

        // Validar que el ganador sea correcto según los puntos
        if (aldeaTeutona.calcularPoderOfensivo() > aldeaGala.calcularPoderDefensivo()) {
            assertTrue(resultado.contains("Teutonia with an age of 500 WINS!"));
        } else {
            assertTrue(resultado.contains("Galos with an age of 700 WINS!"));
        }
    }
}