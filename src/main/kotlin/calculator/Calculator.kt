package calculator

import calculator.letters.LetterGraph

class Calculator {
    private val letterGraph: LetterGraph = LetterGraph()

    /**Returns generated alphabet string or "Impossible" if so.*/
    val currentAlphabetString: String
        get() = letterGraph.getAlphabet()

    /**Proceeds tho rows to determine the letter order in them.*/
    fun proceedRows(previousRow: String, currentRow: String) {
        val (previousLetter, currentLetter) = getFirstMismatchingChars(previousRow, currentRow)
        letterGraph.addEdge(previousLetter, currentLetter)
    }

    /**Returns pair of the first different characters in strings firstString and secondString.*/
    private fun getFirstMismatchingChars(firstString: String, secondString: String): Pair<Char, Char> {
        for (i in firstString.indices) {
            if (i == secondString.length) {                  //if the secondString is a substring of the firstString
                return Pair(firstString[i], '_')
            } else if (secondString[i] != firstString[i]) {  //if the mismatch found
                return Pair(firstString[i], secondString[i])
            }
        }

        return Pair('_', secondString[firstString.length])   //if the firstString is a substring of the secondString
    }
}