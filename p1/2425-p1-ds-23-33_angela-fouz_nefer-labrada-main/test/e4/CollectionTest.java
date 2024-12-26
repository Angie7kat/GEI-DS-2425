package e4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollectionTest {
    private EuroCoinCollection collection;
    private EuroCoin coin1;
    private EuroCoin coin2;
    private EuroCoin coin3;
    private EuroCoin coinDuplicate;

    @BeforeEach
    public void setUp() {
        collection = new EuroCoinCollection();
        coin1 = new EuroCoin(200, "oro-plata", Pais.ES, "Felipe VI", 2020);
        coin2 = new EuroCoin(100, "oro", Pais.FR, "Marianne", 2019);
        coin3 = new EuroCoin(50, "bronce", Pais.DE, "Águila", 2018);
        coinDuplicate = new EuroCoin(200, "oro-plata", Pais.ES, "Felipe VI", 2021);
    }

    @Test
    public void testAddCoin() {
        assertTrue(collection.addCoin(coin1));
        assertTrue(collection.addCoin(coin2));

        assertFalse(collection.addCoin(coinDuplicate));
    }

    @Test
    public void testRemoveCoin() {
        collection.addCoin(coin1);
        collection.addCoin(coin2);

        assertTrue(collection.removeCoin(coin1));
        assertFalse(collection.contains(coin1));

        assertFalse(collection.removeCoin(coin3));
    }

    @Test
    public void testTotalCoins() {
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(coin3);

        assertEquals(3, collection.totalCoins());

        collection.addCoin(coinDuplicate);
        assertEquals(3, collection.totalCoins());
    }

    @Test
    public void testTotalValue() {
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(coin3);

        assertEquals(350, collection.totalValue());
    }

    @Test
    public void testContainsCoin() {
        collection.addCoin(coin1);
        assertTrue(collection.contains(coin1));
        assertFalse(collection.contains(coin2));
    }
}
