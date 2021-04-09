package calculator

import calculator.letters.LetterList

class Calculator {
    private val usedCharacters: BooleanArray = BooleanArray(26) { false }
    private val letterOrder: LetterList = LetterList()
    val alphabetString: String
        get() = this.generateAlphabetString()

    private fun generateAlphabetString(): String {
        for (characterIndex in 0..25) {
            if (!usedCharacters[characterIndex]) {
                val character = (characterIndex + 'a'.toInt()).toChar()
                letterOrder.add(character)
            }
        }
        return letterOrder.joinToString(separator = ", ")
    }

    /**Determines if the alphabetic order keeps after adding currentRow*/
    fun proceedRows(previousRow: String, currentRow: String) : Boolean {
        val (previousLetter, currentLetter) = getFirstMismatchChar(previousRow, currentRow)

        when {
            currentLetter == '_'  -> return false
            previousLetter == '_' -> return true
        }

        //val currentLetterIndex = currentLetter.toInt() - 'a'.toInt()
        val letterIndex: (Char) -> Int = { letter: Char -> letter.toInt() - 'a'.toInt()}
        if (!usedCharacters[letterIndex(currentLetter)]) {
            if (!usedCharacters[letterIndex(previousLetter)]) {
                usedCharacters[letterIndex(previousLetter)] = true
                letterOrder.add(previousLetter)
            }
            usedCharacters[letterIndex(currentLetter)] = true
            letterOrder.addAfterLetter(previousLetter, currentLetter)
            return true
        } else {
            return letterOrder.checkLettersOrder(previousLetter, currentLetter)
        }

        //первое отличие от старого
        /*when {
            !usedCharacters[letterIndex(previousLetter)] -> {
                usedCharacters[letterIndex(previousLetter)] = true
                letterOrder.add(previousLetter)
            }

            !usedCharacters[letterIndex(currentLetter)] -> {
                usedCharacters[letterIndex(currentLetter)] = true
                letterOrder.addAfterLetter(previousLetter, currentLetter)
                return true
            }

            else -> {
                letterOrder.checkLettersOrder(previousLetter, currentLetter)
            }
        }*/

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