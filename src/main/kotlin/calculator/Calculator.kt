package calculator

import java.util.*
import calculator.letters.LetterList

class Calculator(firstRow: String) { //TODO: мб сделать не в конструкторе а при первом вызове proceedRow
    private val usedCharacters: BooleanArray
    private var previousRow: String
    private val letterOrder: LetterList

    init {
        usedCharacters = BooleanArray(26, {false})
        previousRow = firstRow
        letterOrder = LetterList()
    }

    fun proceedRow(currentRow: String) : Boolean {
        val letters = getFirstMismatchChar(previousRow, currentRow)
        val previousLetter = letters.first

        val currentLetter = letters.second
        val currentLetterIndex = currentLetter.toInt() - 'a'.toInt()

        //TODO: разобраться с символом ' '

        if (!usedCharacters[currentLetterIndex]) {
            usedCharacters[currentLetterIndex] = true
            letterOrder.addAfterLetter(previousLetter, currentLetter)
            return true
        }
        else {
            return letterOrder.checkLetter(previousLetter, currentLetter)
        }
    }

    /**returns index in str2 of first mismatch with str1*/
    private fun getFirstMismatchChar(firstString: String, secondString: String): Pair<Char, Char> {
        for (i in firstString.indices) {
            //guaranteed that there will be a mismatch due to a given input format
            if (secondString[i] != firstString[i]) {
                return Pair(firstString[i], secondString[i])
            }
        }

        //if the firstString is shorter than the secondString
        return Pair(' ', secondString[firstString.length])
    }
}

//Alexbndr
//Alex
//Alexandr