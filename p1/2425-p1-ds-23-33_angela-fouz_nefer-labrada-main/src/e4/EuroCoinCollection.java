package e4;

import java.util.HashSet;
import java.util.Set;

public class EuroCoinCollection {
    private Set<EuroCoin> collection;

    public EuroCoinCollection() {
        this.collection = new HashSet<>();
    }

    public boolean addCoin(EuroCoin coin) {
        return collection.add(coin);
    }

    public boolean removeCoin(EuroCoin coin) {
        return collection.remove(coin);
    }

    public int totalCoins() {
        return collection.size();
    }

    public int totalValue() {
        return collection.stream().mapToInt(EuroCoin::getValue).sum();
    }

    public boolean contains(EuroCoin coin) {
        return collection.contains(coin);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EuroCoinCollection:\n");
        for (EuroCoin coin : collection) {
            sb.append(coin).append("\n");
        }
        return sb.toString();
    }
}
