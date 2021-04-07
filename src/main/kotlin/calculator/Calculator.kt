package calculator

import calculator.letters.LetterList

class Calculator(firstRow: String) { //TODO: мб сделать не в конструкторе а при первом вызове proceedRow
    private val usedCharacters: BooleanArray = BooleanArray(26) { false }
    private var previousRow: String
    private val letterOrder: LetterList = LetterList()
    val alphabetString: String
        get() = this.generateAlphabetString()

    init {
        previousRow = firstRow
        letterOrder.add(firstRow[0])
        val firstLetterIndex = firstRow[0].toInt() - 'a'.toInt()
        usedCharacters[firstLetterIndex] = true
    }

    private fun generateAlphabetString(): String {
        for (characterIndex in 0..25) {
            if (!usedCharacters[characterIndex]) {
                val character = (characterIndex + 'a'.toInt()).toChar()
                letterOrder.add(character)
            }
        }
        return letterOrder.joinToString(separator = ", ")
    }

    /**Determines if the alphabetic order keeps after proceeding current row*/
    fun proceedRow(currentRow: String) : Boolean {
        val (previousLetter, currentLetter) = getFirstMismatchChar(previousRow, currentRow)
        previousRow = currentRow

        when {
            currentLetter == '_'  -> return false
            previousLetter == '_' -> return true
        }

        val currentLetterIndex = currentLetter.toInt() - 'a'.toInt()
        return if (!usedCharacters[currentLetterIndex]) {
            usedCharacters[currentLetterIndex] = true
            letterOrder.addAfterLetter(previousLetter, currentLetter)
            true
        } else {
            letterOrder.checkLettersOrder(previousLetter, currentLetter)
        }
    }

    /**Returns pair of the first different characters in strings firstString and secondString*/
    private fun getFirstMismatchChar(firstString: String, secondString: String): Pair<Char, Char> {
        for (i in firstString.indices) {
            if (i == secondString.length) {                  //if the secondString is a substring of the firstString
                return Pair(firstString[i], '_')             //TODO: сделать красивее
            } else if (secondString[i] != firstString[i]) {
                return Pair(firstString[i], secondString[i])
            }
        }
        //if the firstString is a substring of the secondString
        return Pair('_', secondString[firstString.length])
    }
}