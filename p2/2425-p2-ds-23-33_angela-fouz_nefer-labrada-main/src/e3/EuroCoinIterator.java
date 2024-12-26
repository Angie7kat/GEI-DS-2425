package e3;

import java.util.*;

public class EuroCoinIterator implements Iterator<EuroCoin> {
    private final EuroCoinCollection collection;
    private Iterator<EuroCoin> internalIterator;
    private int expectedModCount;
    private EuroCoin lastReturned;
    private EuroCoin nextCoin;
    private Pais pais;

    public EuroCoinIterator(EuroCoinCollection collection) {
        this.collection = collection;
        this.internalIterator = collection.getCollection().iterator();
        this.expectedModCount = collection.getModCount();
        this.pais = null;
        advanceToNextMatchingCoin();
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        this.internalIterator = collection.getCollection().iterator();
        this.lastReturned = null;
        advanceToNextMatchingCoin();
    }

    private void advanceToNextMatchingCoin() {
        nextCoin = null;
        while (internalIterator.hasNext()) {
            EuroCoin candidate = internalIterator.next();
            if (pais == null || candidate.getCountry() == pais) {
                nextCoin = candidate;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        checkForConcurrentModification();
        return nextCoin != null;
    }

    @Override
    public EuroCoin next() {
        checkForConcurrentModification();
        if (nextCoin == null) {
            throw new NoSuchElementException();
        }
        lastReturned = nextCoin;
        advanceToNextMatchingCoin();
        return lastReturned;
    }

    @Override
    public void remove() {
        checkForConcurrentModification();
        if (lastReturned == null) {
            throw new IllegalStateException("remove() called without a previous next()");
        }
        collection.removeCoin(lastReturned);
        lastReturned = null;
        expectedModCount = collection.getModCount();
        internalIterator = collection.getCollection().iterator();
        advanceToNextMatchingCoin();
    }

    private void checkForConcurrentModification() {
        if (collection.getModCount() != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
}