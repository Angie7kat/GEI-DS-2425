package e2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EuroCoinCollection {

    private final List<EuroCoin> coins;

    public EuroCoinCollection() {
        this.coins = new ArrayList<>();
    }

    public boolean addCoin(EuroCoin coin) {
        if (coin == null || coins.contains(coin)) {
            return false;
        }
        return coins.add(coin);
    }

    public boolean removeCoin(EuroCoin coin) {
        return coins.remove(coin);
    }

    public boolean contains(EuroCoin coin) {
        // Comprobar si la colección contiene la moneda
        for (EuroCoin c : coins) {
            if (c.equals(coin)) {
                return true;
            }
        }
        return false;
    }

    public int totalCoins() {
        int count = 0;
        for (EuroCoin coin : coins) {
                count++;
        }
        return count;
    }

    public int totalValue() {
        int totalValue = 0;
        for (EuroCoin coin : coins) {
                totalValue += coin.getValue();
        }
        return totalValue;
    }

    public List<EuroCoin> getSortedByValue() {
        // Ordenar las monedas por valor
        List<EuroCoin> sortedList = new ArrayList<>(coins); // Crear una copia de la lista
        sortedList.sort(new Comparator<EuroCoin>() {
            @Override
            public int compare(EuroCoin o1, EuroCoin o2) {
                if (o1 == null) return 0;
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });
        return sortedList;
    }

    public List<EuroCoin> getSortedByCountry() {
        // Ordenar las monedas por país
        List<EuroCoin> sortedList = new ArrayList<>(coins); // Crear una copia de la lista
        sortedList.sort(new Comparator<EuroCoin>() {
            @Override
            public int compare(EuroCoin o1, EuroCoin o2) {
                if (o1 == null) return 0;
                return o1.getCountry().getName().compareTo(o2.getCountry().getName());
            }
        });
        return sortedList;
    }

    public List<EuroCoin> getSortedByYear() {
        // Ordenar las monedas por año
        List<EuroCoin> sortedList = new ArrayList<>(coins); // Crear una copia de la lista
        sortedList.sort(new Comparator<EuroCoin>() {
            @Override
            public int compare(EuroCoin o1, EuroCoin o2) {
                if (o1 == null) return 0;
                return Integer.compare(o1.getYear(), o2.getYear());
            }
        });
        return sortedList;
    }
}