package e2;
import java.util.Comparator;

public class ComparadorPais implements Comparator<EuroCoin> {
    @Override
    public int compare(EuroCoin coin1, EuroCoin coin2) {
        if (coin1 == null || coin2 == null) {
            throw new NullPointerException("Las monedas no pueden ser nulas");
        }
        if (coin1.getCountry() == null || coin2.getCountry() == null) {
            throw new NullPointerException("El país de las monedas no puede ser nulo");
        }

        int countryComparison = coin1.getCountry().getName().compareTo(coin2.getCountry().getName());
        if (countryComparison != 0) return countryComparison;

        int valueComparison = Integer.compare(coin2.getValue(), coin1.getValue());
        if (valueComparison != 0) return valueComparison;

        return Integer.compare(coin1.getYear(), coin2.getYear());
    }
}