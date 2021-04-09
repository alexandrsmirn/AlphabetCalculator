package calculator

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assumptions

internal class CalculatorTest {

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun oneRowTest() {
        val calculator = Calculator()
        assertEquals("a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z",
            calculator.alphabetString)
    }

    @Test
    fun twoStringsFirstSymbolMatchTest() {
        val calculator = Calculator()
        calculator.proceedRows("rfirst","rsecond")
        assertEquals("f, s, a, b, c, d, e, g, h, i, j, k, l, m, n, o, p, q, r, t, u, v, w, x, y, z",
            calculator.alphabetString)
    }

    @Test
    fun firstIsSubstrOfSecondTest() {
        val calculator = Calculator()
        calculator.proceedRows("alex","alexandr")
        assertEquals("a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z",
            calculator.alphabetString)
    }

    @Test
    fun secondIsSubstrOfFirstTest() {
        val calculator = Calculator()
        Assumptions.assumeFalse(calculator.proceedRows("alexandr","alex"))
    }

    @Test
    fun getAlphabetString() {
    }

    @Test
    fun proceedRow() {
    }
}