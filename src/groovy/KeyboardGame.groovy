/**
 Given a 4 X 4 game slot that has random alphabets in all the slots
 Write a function that takes the keyboard and the word as input and returns true if the word can be formed
 False otherwise.

 A word can be formed on the board by connecting alphabets adjacent to each other (horizontal, vertical and diagonally)
 Same alphabet should not be reused.

 * Created by Felipe on 21/03/2015.
 */
class KeyboardGame {

    static def canBeFormed(String input, String[][] board) {
        if (input.length() > board.length)
            return false;

        boolean[] visited = [false, false, false, false]

        board.each {it.sort(true)} //sorting the board

        findWord(board, visited, 0, input)
    }

    static boolean findWord(def board, boolean[] visited, int idxInput, String input) {
        for (int idxSlot = 0; idxSlot < board.length; idxSlot++) {
            if (visited[idxSlot])
                continue;

            def slot = board[idxSlot]

            def chr = input.charAt(idxInput).toUpperCase()
            //if (slot.contains(chr)) {
            if (Arrays.binarySearch(slot, chr.toString()) >=0) {
                visited[idxSlot] = true

                if (++idxInput >= input.length())
                    return true

                return findWord(board, visited, idxInput, input)
            }
        }

        false
    }

    static void main (String[] args) {
        String[][] board= [ ['S', 'T', 'F', 'M'], ['R', 'U', 'P', 'G'],
                            ['T', 'A', 'M', 'N'], ['E', 'O', 'N', 'I']
                          ]

        println "Input: puta ${canBeFormed('puta', board)}"
        println "Input: fop ${canBeFormed('fop', board)}"
    }


}
