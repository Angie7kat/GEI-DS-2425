package e3;

import java.util.*;

public class EuroCoinCollection implements Iterable<EuroCoin> {
    private final Set<EuroCoin> collection;
    private int modCount = 0;

    public EuroCoinCollection() {
        this.collection = new LinkedHashSet<>();
    }

    public boolean addCoin(EuroCoin coin) {
        if (coin == null) {
            throw new NullPointerException("Cannot add null coin");
        }
        boolean added = collection.add(coin);
        if (added) modCount++;
        return added;
    }

    public boolean removeCoin(EuroCoin coin) {
        boolean removed = collection.remove(coin);
        if (removed) modCount++;
        return removed;
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

    public List<EuroCoin> getSortedByValue() {
        List<EuroCoin> sortedList = new ArrayList<>(collection);
        Collections.sort(sortedList);
        return sortedList;
    }

    @Override
    public Iterator<EuroCoin> iterator() {
        return new EuroCoinIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EuroCoinCollection:\n");
        for (EuroCoin coin : collection) {
            sb.append(coin).append("\n");
        }
        return sb.toString();
    }

    public int getModCount() {
        return modCount;
    }

    public Set<EuroCoin> getCollection() {
        return collection;
    }
}