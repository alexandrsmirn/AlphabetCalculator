package calculator.letters

import java.util.*

class LetterList : LinkedList<Char>() {
    fun addAfterLetter(storedLetter: Char, letterToAdd: Char) {
        val storedLetterIndex = this.indexOf(storedLetter)
        this.add(storedLetterIndex + 1, letterToAdd)
    }

    fun checkLettersOrder(firstLetter: Char, secondLetter: Char): Boolean {
        return this.indexOf(firstLetter) < this.indexOf(secondLetter)
    }
}