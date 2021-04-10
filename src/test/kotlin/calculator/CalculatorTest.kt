package calculator

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CalculatorTest {
    private var calculator = Calculator()

    @BeforeEach
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun oneRowTest() {
        assertEquals("a b c d e f g h i j k l m n o p q r s t u v w x y z",
            calculator.currentAlphabetString)
    }

    @Test
    fun twoStringsFirstSymbolMatchTest() {
        calculator.proceedRows("rfirst","rsecond")
        assertEquals("f s a b c d e g h i j k l m n o p q r t u v w x y z",
            calculator.currentAlphabetString)
    }

    @Test
    fun firstIsSubstrOfSecondTest() {
        calculator.proceedRows("alex","alexandr")
        assertEquals("a b c d e f g h i j k l m n o p q r s t u v w x y z",
            calculator.currentAlphabetString)
    }

    @Test
    fun secondIsSubstrOfFirstTest() {
        calculator.proceedRows("alexandr","alex")
        assertEquals("Impossible", calculator.currentAlphabetString)
    }

    @Test
    fun threeRowsImpossibleTest() {
        calculator.proceedRows("abc", "abb")
        calculator.proceedRows("abb", "acb")
        assertEquals("Impossible", calculator.currentAlphabetString)
    }

    @Test
    fun threeRowsNoMatchTest() {
        calculator.proceedRows("abc", "def")
        calculator.proceedRows("def", "ghi")
        assertEquals("a d g b c e f h i j k l m n o p q r s t u v w x y z",
            calculator.currentAlphabetString)

    }

    @Test
    fun allLettersTest() {
        var letter = 'b'
        while (letter <= 'z') {
            calculator.proceedRows((letter - 1).toString(), letter.toString())
            letter ++
        }
        assertEquals("a b c d e f g h i j k l m n o p q r s t u v w x y z",
            calculator.currentAlphabetString)
    }

    @Test
    fun allLettersReverseTest() {
        var letter = 'z'
        while (letter > 'a') {
            calculator.proceedRows(letter.toString(), (letter - 1).toString())
            letter--
        }
        assertEquals("z y x w v u t s r q p o n m l k j i h g f e d c b a",
            calculator.currentAlphabetString)
    }

    @Test
    fun graphWithTwoEdgedVertexWithoutCyclesTest() {
        val rows = arrayOf( "abcd",
                            "cbda",
                            "cbfb",
                            "cbfa",
                            "cbad")
       for (i in 1 until rows.size) {
           calculator.proceedRows(rows[i - 1], rows[i])
       }
        assertEquals("b d f a c e g h i j k l m n o p q r s t u v w x y z",
            calculator.currentAlphabetString)
    }

    @Test
    fun graphWithTwoEdgedVertexWithCycleTest() {
        val rows = arrayOf( "abcd",
                            "cbda",
                            "cbfb",
                            "cbfa",
                            "cbad",
                            "cbba" )
        for (i in 1 until rows.size) {
            calculator.proceedRows(rows[i - 1], rows[i])
        }
        assertEquals("Impossible", calculator.currentAlphabetString)
    }

}