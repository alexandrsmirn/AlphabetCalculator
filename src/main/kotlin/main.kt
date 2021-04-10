import calculator.Calculator

fun main() {
    val alphabetCalculator = Calculator()

    val rowsCount: Int = readLine()?.toInt() ?:0
    var previousRow: String = readLine()!!

    for (rowNum in 2..rowsCount) {
        val currentRow: String = readLine()!!
        if (!alphabetCalculator.proceedRows(previousRow, currentRow)) {
            break
        }
        previousRow = currentRow
    }

    println(alphabetCalculator.currentAlphabetString)
}