package com.example.ciperapp

object RailFenceCipher {

    fun encrypt(text: String, numRails: Int): String {
        if (numRails <= 1) return text

        val rail = Array(numRails) { StringBuilder() }
        var directionDown = false
        var row = 0

        for (char in text) {
            rail[row].append(char)
            if (row == 0 || row == numRails - 1) {
                directionDown = !directionDown
            }
            row += if (directionDown) 1 else -1
        }

        val result = StringBuilder()
        for (sb in rail) {
            result.append(sb)
        }

        return result.toString()
    }

    fun decrypt(text: String, numRails: Int): String {
        if (numRails <= 1) return text

        // Create a 2D array to mark the positions of the characters in the zigzag pattern
        val rail = Array(numRails) { CharArray(text.length) { ' ' } }

        var directionDown = false
        var row = 0
        var col = 0

        // Populate rail matrix with placeholders
        for (i in text.indices) {
            if (row == 0 || row == numRails - 1) {
                directionDown = !directionDown
            }
            rail[row][col++] = '*'
            row += if (directionDown) 1 else -1
        }

        // Fill rail matrix with characters from the ciphertext
        var index = 0
        for (i in 0 until numRails) {
            for (j in 0 until text.length) {
                if (rail[i][j] == '*' && index < text.length) {
                    rail[i][j] = text[index++]
                }
            }
        }

        // Read the rail matrix to construct the original message
        row = 0
        col = 0
        val result = StringBuilder()
        directionDown = false

        for (i in text.indices) {
            if (row == 0 || row == numRails - 1) {
                directionDown = !directionDown
            }
            result.append(rail[row][col++])
            row += if (directionDown) 1 else -1
        }

        return result.toString()
    }
}
