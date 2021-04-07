import calculator.Calculator

fun main() {
    val rowsCount: Int = readLine()?.toInt() ?:0

    val previousRow: String = readLine()!!
    val alphabetCalculator = Calculator(previousRow)

    for (rowNum in 2..rowsCount) {
        val currentRow: String = readLine()!!
        if (!alphabetCalculator.proceedRow(currentRow)) {
            println("Impossible")
            return
        }
        //previousRow = currentRow
    }

    println(alphabetCalculator.alphabetString)
}