package e3;

import e3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class e3 {
    private EuroCoinCollection collection;
    private EuroCoin euro1ES, euro2ES, cent20FR, euro1IT, cent50IT;

    @BeforeEach
    void setUp() {
        collection = new EuroCoinCollection();
        euro1ES = new EuroCoin(100, "plata", Pais.ES, "Escudo", 2020);
        euro2ES = new EuroCoin(200, "plata", Pais.ES, "Rey", 2019);
        cent20FR = new EuroCoin(20, "bronce", Pais.FR, "Estrella", 2018);
        euro1IT = new EuroCoin(100, "plata", Pais.IT, "Coliseo", 2021);
        cent50IT = new EuroCoin(50, "bronce", Pais.IT, "David", 2022);
    }

    @Test
    void testAddCoin() {
        assertTrue(collection.addCoin(euro1ES));
        assertTrue(collection.addCoin(cent20FR));
        assertFalse(collection.addCoin(euro1ES)); // Duplicate
        assertEquals(2, collection.totalCoins());
    }

    @Test
    void testAddNullCoin() {
        assertThrows(NullPointerException.class, () -> collection.addCoin(null));
    }

    @Test
    void testRemoveCoin() {
        collection.addCoin(euro1ES);
        collection.addCoin(cent20FR);
        assertTrue(collection.removeCoin(euro1ES));
        assertFalse(collection.removeCoin(euro1ES));
        assertEquals(1, collection.totalCoins());
    }

    @Test
    void testRemoveNonExistentCoin() {
        assertFalse(collection.removeCoin(euro1ES));
    }

    @Test
    void testTotalCoins() {
        assertEquals(0, collection.totalCoins());
        collection.addCoin(euro1ES);
        collection.addCoin(euro2ES);
        assertEquals(2, collection.totalCoins());
    }

    @Test
    void testTotalValue() {
        collection.addCoin(euro1ES);
        collection.addCoin(euro2ES);
        collection.addCoin(cent20FR);
        assertEquals(320, collection.totalValue());
    }

    @Test
    void testContains() {
        collection.addCoin(euro1ES);
        assertTrue(collection.contains(euro1ES));
        assertFalse(collection.contains(euro2ES));
    }

    @Test
    void testGetSortedByValue() {
        collection.addCoin(euro2ES);
        collection.addCoin(euro1ES);
        collection.addCoin(cent20FR);
        collection.addCoin(cent50IT);

        List<EuroCoin> sortedCoins = collection.getSortedByValue();
        assertEquals(20, sortedCoins.get(0).getValue());
        assertEquals(50, sortedCoins.get(1).getValue());
        assertEquals(100, sortedCoins.get(2).getValue());
        assertEquals(200, sortedCoins.get(3).getValue());
    }

    @Test
    void testToString() {
        collection.addCoin(euro1ES);
        collection.addCoin(cent20FR);
        String expected = "EuroCoinCollection:\n" + euro1ES + "\n" + cent20FR + "\n";
        assertEquals(expected, collection.toString());
    }

    @Nested
    class IteratorTests {
        @Test
        void testIterator() {
            collection.addCoin(euro1ES);
            collection.addCoin(euro2ES);
            collection.addCoin(cent20FR);

            Iterator<EuroCoin> iterator = collection.iterator();
            assertTrue(iterator.hasNext());
            assertEquals(euro1ES, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(euro2ES, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(cent20FR, iterator.next());
            assertFalse(iterator.hasNext());
        }

        @Test
        void testIteratorNoSuchElementException() {
            collection.addCoin(euro1ES);
            Iterator<EuroCoin> iterator = collection.iterator();
            iterator.next(); // First coin
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void testIteratorRemove() {
            collection.addCoin(euro1ES);
            collection.addCoin(euro2ES);
            Iterator<EuroCoin> iterator = collection.iterator();

            iterator.next(); // Move to euro1ES
            iterator.remove(); // Remove euro1ES
            assertFalse(collection.contains(euro1ES));
            assertTrue(collection.contains(euro2ES));
            assertEquals(1, collection.totalCoins());
        }

        @Test
        void testIteratorIllegalStateException() {
            collection.addCoin(euro1ES);
            Iterator<EuroCoin> iterator = collection.iterator();
            assertThrows(IllegalStateException.class, iterator::remove);
        }

        @Test
        void testIteratorConcurrentModificationException() {
            collection.addCoin(euro1ES);
            Iterator<EuroCoin> iterator = collection.iterator();
            collection.addCoin(euro2ES);
            assertThrows(ConcurrentModificationException.class, iterator::next);
        }
    }

    @Nested
    class EuroCoinIteratorTests {
        @Test
        void testIteratorWithCountry() {
            collection.addCoin(euro1ES);
            collection.addCoin(euro2ES);
            collection.addCoin(cent20FR);
            collection.addCoin(euro1IT);

            EuroCoinIterator iterator = (EuroCoinIterator) collection.iterator();
            iterator.setPais(Pais.ES);

            assertTrue(iterator.hasNext());
            assertEquals(euro1ES, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(euro2ES, iterator.next());
            assertFalse(iterator.hasNext());
        }

        @Test
        void testIteratorWithNullCountry() {
            collection.addCoin(euro1ES);
            collection.addCoin(cent20FR);
            collection.addCoin(euro1IT);

            EuroCoinIterator iterator = (EuroCoinIterator) collection.iterator();
            iterator.setPais(null);

            assertTrue(iterator.hasNext());
            assertEquals(euro1ES, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(cent20FR, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(euro1IT, iterator.next());
            assertFalse(iterator.hasNext());
        }

        @Test
        void testIteratorSetPaisAfterIteration() {
            collection.addCoin(euro1ES);
            collection.addCoin(cent20FR);
            collection.addCoin(euro1IT);

            EuroCoinIterator iterator = (EuroCoinIterator) collection.iterator();
            iterator.next(); // Move to euro1ES
            iterator.setPais(Pais.IT);

            assertTrue(iterator.hasNext());
            assertEquals(euro1IT, iterator.next());
            assertFalse(iterator.hasNext());
        }

        @Test
        void testIteratorRemoveWithCountryFilter() {
            collection.addCoin(euro1ES);
            collection.addCoin(euro2ES);
            collection.addCoin(cent20FR);

            EuroCoinIterator iterator = (EuroCoinIterator) collection.iterator();
            iterator.setPais(Pais.ES);

            iterator.next();
            iterator.remove();
            assertTrue(collection.contains(euro2ES));
            assertFalse(collection.contains(euro1ES));
            assertTrue(collection.contains(cent20FR));
            assertEquals(2, collection.totalCoins());
        }
    }
}