package org.mockito.junittesting;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator cal = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, cal.add(2,3));  // 5 == 2 + 3 - True
    }

    @Test
    public void testSubtract() {
        assertNotEquals(5, cal.subtract(2,3));  // 5 != 2 + 3 - True
    }

    @Test
    public void testMultiply() {
        assertTrue(5 > cal.multiply(2,3));  // 5 > 2 * 3 - False
    }

}