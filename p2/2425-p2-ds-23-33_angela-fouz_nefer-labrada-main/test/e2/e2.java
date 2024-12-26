package e2;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import e2.*;

public class e2 {

    @Test
    public void testGetColor() {
        // Test with a valid EuroCoin object
        EuroCoin coin = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        assertEquals("gold", coin.getColor());

        // Test with a different color
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);
        assertEquals("silver", coin2.getColor());

        // Test with a null color
        EuroCoin coin3 = new EuroCoin(3, null, Pais.IT, "design3", 2020);
        assertNull(coin3.getColor());
    }

    @Test
    public void testGetDesign() {
        // Test with a valid EuroCoin object
        EuroCoin coin = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        assertEquals("design1", coin.getDesign());

        // Test with a different design
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);
        assertEquals("design2", coin2.getDesign());

        // Test with a null design
        EuroCoin coin3 = new EuroCoin(3, "gold", Pais.IT, null, 2020);
        assertNull(coin3.getDesign());
    }

    @Test
    public void testAddAndTotalCoins() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);

        assertTrue(collection.addCoin(coin1));
        assertTrue(collection.addCoin(coin2));
        assertFalse(collection.addCoin(coin1));

        assertEquals(2, collection.totalCoins());
        assertEquals(3, collection.totalValue());

        // Caso negativo: intentamos agregar un duplicado
        assertFalse(collection.addCoin(new EuroCoin(1, "gold", Pais.FR, "design1", 2022)));
    }

    @Test
    public void testRemoveAndTotalCoins() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);

        collection.addCoin(coin1);
        collection.addCoin(coin2);

        // Eliminación de una moneda
        assertTrue(collection.removeCoin(coin1));
        assertEquals(1, collection.totalCoins());
        assertEquals(2, collection.totalValue());

        // Intentar eliminar una moneda que no está en la colección
        assertFalse(collection.removeCoin(coin1));
    }

    @Test
    public void testContains() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);

        collection.addCoin(coin1);
        assertTrue(collection.contains(coin1));
        assertFalse(collection.contains(coin2));

        // Verificar null
        assertFalse(collection.contains(null));
    }

    @Test
    public void testGetSortedByValue() {
        EuroCoinCollection collection = new EuroCoinCollection();
        collection.addCoin(new EuroCoin(2, "gold", Pais.FR, "design1", 2022));
        collection.addCoin(new EuroCoin(1, "silver", Pais.DE, "design2", 2021));

        List<EuroCoin> sorted = collection.getSortedByValue();
        assertEquals(1, sorted.get(0).getValue());
        assertEquals(2, sorted.get(1).getValue());

        // Agregar otra moneda con valor intermedio
        collection.addCoin(new EuroCoin(5, "bronze", Pais.ES, "design3", 2020));
        sorted = collection.getSortedByValue();
        assertEquals(1, sorted.get(0).getValue());
        assertEquals(2, sorted.get(1).getValue());
        assertEquals(5, sorted.get(2).getValue());
    }
    @Test
    public void testNullCountry() {
        EuroCoin coin = new EuroCoin(1, "gold", null, "design1", 2022);
        assertNull(coin.getCountry());
    }

    @Test
    public void testGetSortedByCountry() {
        EuroCoinCollection collection = new EuroCoinCollection();
        collection.addCoin(new EuroCoin(2, "gold", Pais.DE, "design1", 2022));
        collection.addCoin(new EuroCoin(1, "silver", Pais.FR, "design2", 2021));
        collection.addCoin(new EuroCoin(1, "silver", Pais.DE, "design3", 2020));

        List<EuroCoin> sorted = collection.getSortedByCountry();
        assertEquals("Germany", sorted.get(1).getCountry().getName());
        assertEquals(2, sorted.get(1).getValue());
        assertEquals("France", sorted.get(0).getCountry().getName());
        assertEquals(1, sorted.get(0).getValue());
        assertNotEquals("Spain", sorted.get(1).getCountry().getName()); //el pais no esta en la coleccion
    }



    @Test
    public void testGetSortedByYear() {
        EuroCoinCollection collection = new EuroCoinCollection();

        // Crear monedas con diferentes años
        EuroCoin coin2022 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2021 = new EuroCoin(1, "silver", Pais.DE, "design2", 2021);
        EuroCoin coin2020 = new EuroCoin(1, "bronze", Pais.ES, "design3", 2020);
        EuroCoin coin2020B = new EuroCoin(2, "silver", Pais.DE, "design4", 2020);
        EuroCoin coin2021B = new EuroCoin(2, "gold", Pais.IT, "design5", 2021);

        // Añadir monedas a la colección
        collection.addCoin(coin2022);
        collection.addCoin(coin2021);
        collection.addCoin(coin2020);
        collection.addCoin(coin2020B);
        collection.addCoin(coin2021B);


        // Obtener lista ordenada por año
        List<EuroCoin> sortedList = collection.getSortedByYear();

        // Verificar que las monedas estén ordenadas por año
        assertEquals(2020, sortedList.get(1).getYear());
        assertEquals(2021, sortedList.get(2).getYear());
        assertEquals(2021, sortedList.get(3).getYear());
        assertEquals(2022, sortedList.get(4).getYear());

        // Verificar que el orden relativo de las monedas con el mismo año no cambie
        assertEquals(coin2020B, sortedList.get(1)); // Primer 2020
        assertEquals(coin2021, sortedList.get(2)); // Segundo 2020
        assertEquals(coin2021B, sortedList.get(3)); // Primer 2021
        assertEquals(coin2022, sortedList.get(4)); // Segundo 2021

    }

    @Test
    public void testCollectionWithNullCoins() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);

        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(null);

        List<EuroCoin> sortedByValue = collection.getSortedByValue();
        assertNotNull(sortedByValue);
        assertEquals(2, sortedByValue.size());

        assertEquals(2, collection.totalCoins());
        assertEquals(3, collection.totalValue());  // Assuming coin1 value = 1 and coin2 value = 2
    }

    @Test
    public void testFullSorting() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "silver", Pais.DE, "design1", 2019);
        EuroCoin coin2 = new EuroCoin(5, "gold", Pais.FR, "design1", 2021);
        EuroCoin coin3 = new EuroCoin(2, "bronze", Pais.ES, "design3", 2020);

        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(coin3);

        List<EuroCoin> sortedByValue = collection.getSortedByValue();
        assertEquals(1, sortedByValue.get(0).getValue());
        assertEquals(2, sortedByValue.get(1).getValue());
        assertEquals(5, sortedByValue.get(2).getValue());

        List<EuroCoin> sortedByCountry = collection.getSortedByCountry();
        assertEquals("Germany", sortedByCountry.get(1).getCountry().getName());
        assertEquals("France", sortedByCountry.get(0).getCountry().getName());
        assertEquals("Spain", sortedByCountry.get(2).getCountry().getName()); //viva españa

        List<EuroCoin> sortedByYear = collection.getSortedByYear();
        assertEquals(2019, sortedByYear.get(0).getYear());
        assertEquals(2020, sortedByYear.get(1).getYear());
        assertEquals(2021, sortedByYear.get(2).getYear());
    }

    @Test
    public void testAddNullCoin() {
        EuroCoinCollection collection = new EuroCoinCollection();

        // Intentar añadir una moneda nula
        assertFalse("No debería permitir añadir una moneda nula", collection.addCoin(null));

        // Verificar que la colección está vacía
        assertEquals("La colección no debe tener monedas después de intentar añadir null", 0, collection.totalCoins());
        assertEquals("El valor total no debe cambiar al intentar añadir null", 0, collection.totalValue());

        // Crear una moneda válida
        EuroCoin coin = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);

        // Añadir una moneda válida
        assertTrue("Debería permitir añadir una moneda válida", collection.addCoin(coin));

        // Verificar que la colección contiene una moneda
        assertEquals("La colección debe tener 1 moneda después de añadir una válida", 1, collection.totalCoins());
        assertEquals("El valor total debe ser igual al valor de la moneda añadida", 1, collection.totalValue());

        // Intentar añadir la misma moneda nuevamente (debería fallar)
        assertFalse("No debería permitir añadir monedas duplicadas", collection.addCoin(coin));

        // Verificar que el tamaño y el valor total no cambian
        assertEquals("La colección no debe haber cambiado al intentar añadir un duplicado", 1, collection.totalCoins());
        assertEquals("El valor total no debe cambiar al intentar añadir un duplicado", 1, collection.totalValue());

        // Verificar que la moneda se haya añadido correctamente
        assertTrue("La colección debe contener la moneda añadida", collection.contains(coin));
    }

    @Test
    public void testRemoveNullCoin() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);

        collection.addCoin(coin);

        // Intentamos eliminar una moneda null, la colección no debería cambiar
        assertFalse(collection.removeCoin(null));
        assertEquals(1, collection.totalCoins());
        assertEquals(1, collection.totalValue());
    }

    @Test
    public void testContainsNullCoin() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);

        collection.addCoin(coin);

        // Verificamos que la colección no contiene null
        assertFalse(collection.contains(null));
        assertTrue(collection.contains(coin));
    }

    @Test
    public void testSortingWithNullCoins() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(2, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(1, "silver", Pais.DE, "design2", 2021);

        collection.addCoin(coin1);
        collection.addCoin(coin2);

        // Insertamos un valor null en la colección y probamos si el orden funciona sin errores
        collection.addCoin(null);

        List<EuroCoin> sortedByValue = collection.getSortedByValue();
        assertNotNull(sortedByValue);
        assertEquals(2, sortedByValue.size());  // null no debe afectar el tamaño de la lista ordenada
        assertEquals(1, sortedByValue.get(0).getValue());
        assertEquals(2, sortedByValue.get(1).getValue());

        List<EuroCoin> sortedByCountry = collection.getSortedByCountry();
        assertNotNull(sortedByCountry);
        assertEquals(2, sortedByCountry.size());  // null no debe afectar el tamaño de la lista ordenada
        //algunos paises
        assertEquals("France", sortedByCountry.get(0).getCountry().getName());
        assertEquals("Germany", sortedByCountry.get(1).getCountry().getName());

        List<EuroCoin> sortedByYear = collection.getSortedByYear();
        assertNotNull(sortedByYear);
        assertEquals(2, sortedByYear.size());  // null no debe afectar el tamaño de la lista ordenada
        assertEquals(2021, sortedByYear.get(0).getYear());
        assertEquals(2022, sortedByYear.get(1).getYear());
    }

    @Test
    public void testTotalCoinsAndTotalValueWithNull() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        collection.addCoin(coin1);

        // Agregar null no debe afectar los totales
        collection.addCoin(null);
        assertEquals(1, collection.totalCoins());
        assertEquals(1, collection.totalValue());
    }
    @Test
    public void testAddCoin() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);

        // Agregar monedas válidas
        assertTrue(collection.addCoin(coin1));
        assertTrue(collection.addCoin(coin2));
        assertEquals(2, collection.totalCoins());
        assertEquals(3, collection.totalValue());

        // Intentar agregar duplicado
        assertFalse(collection.addCoin(coin1));

        // Intentar agregar null
        assertFalse(collection.addCoin(null));
        assertEquals(2, collection.totalCoins());
    }

    @Test
    public void testRemoveCoin() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);

        // Eliminar moneda existente
        collection.addCoin(coin1);
        assertTrue(collection.removeCoin(coin1));
        assertEquals(0, collection.totalCoins());

        // Intentar eliminar moneda inexistente
        assertFalse(collection.removeCoin(coin1));

        // Intentar eliminar null
        assertFalse(collection.removeCoin(null));
    }


    @Test
    public void testSorting() {
        EuroCoinCollection collection = new EuroCoinCollection();
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);

        // Agregar monedas
        collection.addCoin(coin1);
        collection.addCoin(coin2);

        // Ordenar por valor
        List<EuroCoin> sortedByValue = collection.getSortedByValue();
        assertEquals(1, sortedByValue.get(0).getValue());
        assertEquals(2, sortedByValue.get(1).getValue());

        // Ordenar por país
        List<EuroCoin> sortedByCountry = collection.getSortedByCountry();
        assertEquals("France", sortedByCountry.get(0).getCountry().getName());

        // Ordenar por año
        List<EuroCoin> sortedByYear = collection.getSortedByYear();
        assertEquals(2021, sortedByYear.get(0).getYear());
        assertEquals(2022, sortedByYear.get(1).getYear());
    }

    @Test
    public void testEmptyCollection() {
        EuroCoinCollection collection = new EuroCoinCollection();

        // Verificar totales
        assertEquals(0, collection.totalCoins());
        assertEquals(0, collection.totalValue());

        // Verificar ordenamientos
        assertTrue(collection.getSortedByValue().isEmpty());
        assertTrue(collection.getSortedByCountry().isEmpty());
        assertTrue(collection.getSortedByYear().isEmpty());
    }

    @Test
    public void testEquality() {
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);

        // Mismas propiedades, son iguales
        assertEquals(coin1, coin2);
        assertEquals(coin1.hashCode(), coin2.hashCode());

        // Diferente valor
        EuroCoin coin3 = new EuroCoin(2, "gold", Pais.FR, "design1", 2022);
        assertNotEquals(coin1, coin3);
    }

    @Test
    public void testComparadorAno() {
        ComparadorAno comparador = new ComparadorAno();

        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021);
        EuroCoin coin3 = new EuroCoin(5, "bronze", Pais.ES, "design3", 2022);

        // Monedas con años distintos
        assertTrue(comparador.compare(coin1, coin2) > 0); // 2022 > 2021
        assertTrue(comparador.compare(coin2, coin1) < 0); // 2021 < 2022

        // Monedas con el mismo año
        assertEquals(0, comparador.compare(coin1, coin3)); // 2022 == 2022
    }

    @Test
    public void testComparadorPais() {
        ComparadorPais comparador = new ComparadorPais();

        // Monedas con diferentes países
        EuroCoin coinFR = new EuroCoin(1, "gold", Pais.FR, "design1", 2020);
        EuroCoin coinDE = new EuroCoin(1, "gold", Pais.DE, "design1", 2020);
        EuroCoin coinIT = new EuroCoin(1, "gold", Pais.IT, "design1", 2020);

        // Monedas con el mismo país pero diferentes valores
        EuroCoin coinFR_2 = new EuroCoin(2, "gold", Pais.FR, "design1", 2020);
        EuroCoin coinFR_1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2020);

        assertTrue(comparador.compare(coinFR_2, coinFR_1) < 0); // Valor descendente
        assertTrue(comparador.compare(coinFR_1, coinFR_2) > 0);

        // Monedas con el mismo país y valor, pero diferentes años
        EuroCoin coinFR_2021 = new EuroCoin(1, "gold", Pais.FR, "design1", 2021);
        EuroCoin coinFR_2020 = new EuroCoin(1, "gold", Pais.FR, "design1", 2020);

        assertTrue(comparador.compare(coinFR_2020, coinFR_2021) < 0); // Año ascendente
        assertTrue(comparador.compare(coinFR_2021, coinFR_2020) > 0);

        // Monedas idénticas
        EuroCoin coinFR_clone = new EuroCoin(1, "gold", Pais.FR, "design1", 2020);
        assertEquals(0, comparador.compare(coinFR, coinFR_clone)); // Iguales
    }

    @Test
    public void testEuroCoinEqualsAndHashCode() {
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022); // Igual a coin1
        EuroCoin coin3 = new EuroCoin(2, "silver", Pais.DE, "design2", 2021); // Diferente
        EuroCoin nullCoin = null;

        // Comparación con objeto igual
        assertTrue(coin1.equals(coin2));
        assertEquals(coin1.hashCode(), coin2.hashCode());

        // Comparación con objeto diferente
        assertFalse(coin1.equals(coin3));
        assertNotEquals(coin1.hashCode(), coin3.hashCode());

        // Comparación con null
        assertFalse(coin1.equals(nullCoin));

        // Comparación con objeto de otra clase
        assertFalse(coin1.equals("some string"));
    }

    @Test
    public void testEuroCoinToString() {
        EuroCoin coin = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        String expected = "EuroCoin{valor=1, color='gold', pais=France, diseño='design1', año=2022}";
        assertEquals(expected, coin.toString());
    }

    @Test
    public void testEuroCoinCompareTo() {
        EuroCoin coin1 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022);
        EuroCoin coin2 = new EuroCoin(2, "silver", Pais.FR, "design2", 2022); // Mayor valor
        EuroCoin coin3 = new EuroCoin(1, "silver", Pais.DE, "design1", 2022); // Diferente país
        EuroCoin coin4 = new EuroCoin(1, "gold", Pais.FR, "design1", 2022); // Igual a coin1

        // Comparación por valor
        assertTrue(coin1.compareTo(coin2) > 0);
        assertTrue(coin2.compareTo(coin1) < 0);

        // Comparación por país (si valor es igual)
        assertTrue(coin1.compareTo(coin3) < 0);

        // Comparación por diseño (si valor y país son iguales)
        assertEquals(0, coin1.compareTo(coin4));
    }
}