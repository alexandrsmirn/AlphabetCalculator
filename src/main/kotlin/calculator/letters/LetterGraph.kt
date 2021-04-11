package calculator.letters

import java.util.*
import kotlin.collections.HashMap

/**Creates a directed graph where the letters and the '_' symbol are its vertices, and the edges represent
 * the relation "greater than" in alphabetical order.
 * */
class LetterGraph {
    private enum class VertexStatus { UNVISITED, ENTERED, COMPLETED }
    private val visitedCharacters = Array<VertexStatus>(26) { VertexStatus.UNVISITED }
    private val adjacencyList = HashMap<Char, ArrayList<Char>>()
    private val alphabet = ArrayList<Char>()
    private val charIndex = { letter: Char -> letter.toInt() - 'a'.toInt() }

    /**Adds an edge from currentCharacter to previousCharacter to the graph*/
    fun addEdge(previousCharacter: Char, currentCharacter: Char) {
        //we assume the whitespace '_' symbol is less than all the others
        if (previousCharacter == '_') return
        if (!adjacencyList.containsKey(currentCharacter)) {
            adjacencyList[currentCharacter] = ArrayList()
        }
        adjacencyList[currentCharacter]!!.add(previousCharacter)
    }

    /**Returns letters in the alphabetical order. Uses topological sort to infer the order.*/
    fun getAlphabet(): String {
        for (character in adjacencyList.keys) {
            //line below means there is some symbol that is less than the '_' which is impossible
            if (character == '_') return "Impossible"
            if (visitedCharacters[charIndex(character)] == VertexStatus.UNVISITED &&
                !dfs(character)
            ) {
                return "Impossible"
            }
        }

        for (letter in 'a'..'z') {
            if (!alphabet.contains(letter)) {
                alphabet.add(letter)
            }
        }
        return alphabet.joinToString(separator = " ")
    }

    /**DFS implementation for the topological sort. Returns false if there is a cycle in the graph.*/
    private fun dfs(currentVertex: Char): Boolean {
        visitedCharacters[charIndex(currentVertex)] = VertexStatus.ENTERED

        if (adjacencyList.containsKey(currentVertex)) {
            for (targetVertex in adjacencyList[currentVertex]!!) {
                when (visitedCharacters[charIndex(targetVertex)]) {
                    VertexStatus.UNVISITED -> if (!dfs(targetVertex)) return false
                    VertexStatus.ENTERED -> return false
                    VertexStatus.COMPLETED -> { }
                }
            }
        }

        visitedCharacters[charIndex(currentVertex)] = VertexStatus.COMPLETED
        alphabet.add(currentVertex)
        return true
    }
}