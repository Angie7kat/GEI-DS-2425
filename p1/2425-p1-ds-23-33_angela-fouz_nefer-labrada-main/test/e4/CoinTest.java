package e4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CoinTest {

    private EuroCoin coin1;
    private EuroCoin coin2;
    private EuroCoin coin3;
    private EuroCoin coin4;
    private EuroCoin coin5;
    private EuroCoin coin6;
    private EuroCoin coin7;
    private EuroCoin coinDuplicate;

    @BeforeEach
    public void setUp() {
        coin1 = new EuroCoin(200, "oro-plata", Pais.ES, "Felipe VI", 2020);
        coin2 = new EuroCoin(100, "oro", Pais.FR, "Marianne", 2019);
        coin3 = new EuroCoin(50, "bronce", Pais.DE, "Águila", 2018);
        coin4 = new EuroCoin(54, "plata", Pais.DE, null, 2018);
        coin5 = new EuroCoin(1, "plata", Pais.DE, null, 2018);
        coin6 = new EuroCoin(0, null, Pais.DE, null, 2018);
        coin7 = new EuroCoin(220, null, Pais.DE, null, 1999);
        coinDuplicate = new EuroCoin(200, "oro-plata", Pais.ES, "Felipe VI", 2021);
    }

    @Test
    public void testEuroCoinEquality() {
        assertEquals(coin1, coinDuplicate);
        assertNotEquals(coin1, coin2);
        assertNotEquals(coin1, coin3);
        assertNotEquals(coin2, coin3);
        assertNotEquals(coin6, coin7);
    }

    @Test
    public void testEuroCoinHashCode() {
        assertEquals(coin1.hashCode(), coinDuplicate.hashCode());
        assertNotEquals(coin1.hashCode(), coin2.hashCode());
        assertNotEquals(coin1.hashCode(), coin3.hashCode());
        assertNotEquals(coin2.hashCode(), coin3.hashCode());
        assertNotEquals(coin6.hashCode(), coin7.hashCode());
        assertNotEquals(coin5.hashCode(), coin6.hashCode());
    }

    @Test
    public void testEuroCoinToString() {
        String expectedString1 = "EuroCoin{valor=200, color='oro-plata', pais=Spain, diseño='Felipe VI', año=2020}";
        assertEquals(expectedString1, coin1.toString());

        String expectedString2 = "EuroCoin{valor=100, color='oro', pais=France, diseño='Marianne', año=2019}";
        assertEquals(expectedString2, coin2.toString());

        String expectedString3 = "EuroCoin{valor=50, color='bronce', pais=Germany, diseño='Águila', año=2018}";
        assertEquals(expectedString3, coin3.toString());

        String expectedString4 = "EuroCoin{valor=54, color='plata', pais=Germany, diseño='null', año=2018}";
        assertEquals(expectedString4, coin4.toString());

        String expectedString5 = "EuroCoin{valor=1, color='plata', pais=Germany, diseño='null', año=2018}";
        assertEquals(expectedString5, coin5.toString());

        String expectedString6 = "EuroCoin{valor=0, color='null', pais=Germany, diseño='null', año=2018}";
        assertEquals(expectedString6, coin6.toString());

        String expectedString7 = "EuroCoin{valor=220, color='null', pais=Germany, diseño='null', año=1999}";
        assertEquals(expectedString7, coin7.toString());

        String notexpectedString7 = "EuroCoin{valor=200, color='null', pais=Spain, diseño='null', año=19999}";
        assertNotEquals(notexpectedString7, coin7.toString());
    }
}
