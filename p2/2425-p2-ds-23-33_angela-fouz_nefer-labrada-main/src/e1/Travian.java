package e1;

import e1.Aldea.Aldea;


import java.util.ArrayList;
import java.util.List;

public class Travian {
    public List<String> simularBatalla(Aldea<?> atacante, Aldea<?> defensor) {

        List<String> resultado = new ArrayList<>();
        double poderAtaque = atacante.calcularPoderOfensivo();
        double poderDefensa = defensor.calcularPoderDefensivo();

        String inicio =
                "### Starts the battle ! --> " +
                        atacante.getNombre() +
                        " Attacks " +
                        defensor.getNombre() +
                        "! ###\n";
        resultado.add(inicio);

        resultado.add(atacante.toString());

        String attackPower = "Total " + atacante.getNombre() + " attack power "+ poderAtaque;
        resultado.add(attackPower);

        resultado.add(defensor.toString());

        String defensePower = "Total " + defensor.getNombre() + " defense power "+ poderDefensa;
        resultado.add(defensePower);


        if (poderAtaque > poderDefensa) {
            resultado.add(atacante.getNombre()+ " with an age of "+ atacante.getAnho()+ " WINS!\n");
        } else {
            resultado.add(defensor.getNombre()+ " with an age of "+ defensor.getAnho()+ " WINS!\n");
        }

        return resultado;
    }
}
