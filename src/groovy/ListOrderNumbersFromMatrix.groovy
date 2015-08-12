
/**
 * **
 * Given an m x n matrix where each row element is sorted, but the columns do not appear in sorted order, write a function to print each matrix element in sorted order.
 * Your function should print 5, 20, 40, 45, 50, 55, 60, 80, 90.
 * Add on: Assume that we are space-constrained such that we can only hold one row in memory at a time. Optimize your function to work under such constraints as efficiently as possible.
 *
 * User: EII5
 * Date: 18/03/15
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
class ListOrderNumbersFromMatrix {
    def static evaluate(matriz) {
        def minQueue = null
        def min = null

        matriz.each() { Queue row ->

            if (min == null)
                min = row.peek()

            if (row.peek() && row.peek() <= min)
                minQueue = row;
        }

        println minQueue.poll();

        if (!matriz.flatten().empty)
            evaluate(matriz)

    }

    static void main(String[] args) {
        def matriz = [
                [20,40,80] as LinkedList,
                [5,60,90] as LinkedList,
                [45,50,55] as LinkedList
        ]

        ListOrderNumbersFromMatrix.evaluate(matriz);
    }

}



