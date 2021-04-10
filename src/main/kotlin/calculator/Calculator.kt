package calculator

import calculator.letters.LetterList

class Calculator {
    private val usedCharacters: BooleanArray = BooleanArray(26) { false }
    private val letterOrder: LetterList = LetterList()
    private var hasPossibleAlphabet = true

    /**Returns generated alphabet string or "Impossible" if so.*/
    val currentAlphabetString: String
        get() = if (hasPossibleAlphabet) this.generateAlphabetString() else "Impossible"

    /**Generates alphabet string based on proceeded rows.*/
    private fun generateAlphabetString(): String {
        for (characterIndex in 0..25) {
            if (!usedCharacters[characterIndex]) {
                val character = (characterIndex + 'a'.toInt()).toChar()
                letterOrder.add(character)
            }
        }
        return letterOrder.joinToString(separator = " ")
    }

    /**Determines if the alphabetic order keeps after adding currentRow.*/
    fun proceedRows(previousRow: String, currentRow: String) : Boolean {
        val (previousLetter, currentLetter) = getFirstMismatchingChars(previousRow, currentRow)

        //assume '_' symbol is always the first in the alphabetic order
        when {
            !hasPossibleAlphabet  -> return false
            previousLetter == '_' -> return true
            currentLetter == '_'  -> {
                hasPossibleAlphabet = false
                return false
            }
        }

        val currentLetterIndex = currentLetter.toInt() - 'a'.toInt()
        val previousLetterIndex = previousLetter.toInt() - 'a'.toInt()

        if (!usedCharacters[currentLetterIndex]) {
            if (!usedCharacters[previousLetterIndex]) {
                usedCharacters[previousLetterIndex] = true
                letterOrder.add(previousLetter)
            }
            usedCharacters[currentLetterIndex] = true
            letterOrder.addAfterLetter(previousLetter, currentLetter)
        } else {
            hasPossibleAlphabet = letterOrder.checkLettersOrder(previousLetter, currentLetter)
        }

        return hasPossibleAlphabet
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