package e2;

import java.util.Comparator;

public class ComparadorAno implements Comparator<EuroCoin> {
    @Override
    public int compare(EuroCoin coin1, EuroCoin coin2) {
        return Integer.compare(coin1.getYear(), coin2.getYear()); // año ascendente
    }
}