package e3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdTest {

    private static Property p1;
    private static Ad a1, a2, a3, a4, a5, a6;

    @BeforeAll
    static void setUp() {

        p1 = new Property(PropertyType.APARTMENT,
                "01234567890123456789",
                "Aurelio Aguirre Galarraga 100, 1-A, A Coruna",
                "15190",
                80,
                2,
                1
                );

        Property p2 = new Property(PropertyType.APARTMENT,
                "56789012345678901234",
                "Aurelio Aguirre Galarraga 202, 5-D, A Coruna",
                "15190",
                100,
                3,
                2
        );

        /* Same cadaster as h2 but the entered address and meters are different. */
        Property p3 = new Property(PropertyType.APARTMENT,
                "56789012345678901234",
                "Aurelio Aguirre Galarraga 202, 5º D (A Coruna)",
                "15190",
                95,
                3,
                2
        );

        Property p4 = new Property(PropertyType.LOCAL,
                "78901234567890123456",
                "Aurelio Aguirre Galarraga 100, A Coruna",
                "15190",
                0,
                0,
                0
        ); // Incomplete data.

        a1 = new Ad("RE/MAX",
                p1,
                AdType.PURCHASE,
                249000
                );

        a2 = new Ad("Engel & Volkers",
                p2,
                AdType.PURCHASE,
                300000
        );

        a3 = new Ad("RE/MAX",
                p3,
                AdType.RENTAL,
                350000
        ); // Wrong price for a rental.

        a4 = new Ad("Engel & Volkers",
                p4,
                AdType.RENTAL,
                5000
        );

        a5 = new Ad( "null",
                p4,
                AdType.RENTAL,
                70000
        );

        a6 = new Ad( "null",
                p4,
                AdType.RENTAL,
                530
        );
    }

    @Test
    void isPropertyEqual() {
        assertFalse(a1.isPropertyEqual(a2));
        assertFalse(a1.isPropertyEqual(a3));
        assertTrue(a2.isPropertyEqual(a3));
        assertFalse(a2.isPropertyEqual(a4));
        assertTrue(a5.isPropertyEqual(a6));
        assertFalse(a2.isPropertyEqual(a6));
        assertFalse(a1.isPropertyEqual(a5));
    }

    @Test
    void isPriceNormal() {
        assertTrue(a1.isPriceNormal());
        assertTrue(a2.isPriceNormal());
        assertFalse(a3.isPriceNormal());
        assertFalse(a4.isPriceNormal());
        assertTrue(a5.isPriceNormal());
        assertFalse(a6.isPriceNormal());
    }

    @Test
    void priceMetersEuros() {
        assertEquals(3112.5, a1.priceMetersEuros(), 0.01);
        assertEquals(3000.0, a2.priceMetersEuros(), 0.01);

        assertThrows(ArithmeticException.class, () -> a4.priceMetersEuros());
    }

    @Test
    void testGetAgencia() {
        assertEquals("RE/MAX", a1.getAgencia());
        assertEquals("Engel & Volkers", a2.getAgencia());
        assertEquals("RE/MAX", a3.getAgencia());
        assertEquals("Engel & Volkers", a4.getAgencia());
        assertEquals("null", a5.getAgencia());
        assertEquals("null", a5.getAgencia());
    }

    @Test
    void testGetTipoAnuncio() {
        assertEquals(AdType.PURCHASE, a1.getTipoAnuncio());
        assertEquals(AdType.PURCHASE, a2.getTipoAnuncio());
        assertEquals(AdType.RENTAL, a3.getTipoAnuncio());
        assertEquals(AdType.RENTAL, a4.getTipoAnuncio());
        assertEquals(AdType.RENTAL, a5.getTipoAnuncio());
        assertEquals(AdType.RENTAL, a6.getTipoAnuncio());
    }

    @Test
    void dropPrice() {
        Ad tmp = new Ad(a1); // Copy constructor.
        tmp.dropPrice(5);
        assertEquals(236550, tmp.getPriceInEuros());
        assertThrows(IllegalArgumentException.class, () -> tmp.dropPrice(200));
    }

    @Test
    void testEquals() {
        assertNotEquals(a1, null);
        assertNotEquals(null, a1);
        assertEquals(a1, a1);
        assertNotEquals(a1, p1);
        assertNotEquals(a1, a2);
        assertNotEquals(a1, a3);
        assertNotEquals(a2, a4);
    }

    @Test
    void testHashCode() { //toma ya
        assertNotEquals(a1, null);
        assertNotEquals(null, a1);
        assertEquals(a1, a1);
        assertNotEquals(a1, p1);
        assertNotEquals(a1, a2);
        assertNotEquals(a1, a3);
        assertNotEquals(a2, a4);
    }
}