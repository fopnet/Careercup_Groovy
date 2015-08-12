package t9keyboard

import groovy.transform.EqualsAndHashCode

/**
 * Created by Felipe on 24/03/2015.
 */
@EqualsAndHashCode(includes = ['data'])
class Node {
    static Comparator rankComparator = [compare:{a,b->  b.rank <=> a.rank }] as Comparator

    // priority queue
    def queue = new PriorityQueue<Node>(30, rankComparator)

    /*data to be save*/
    def data

    /*child nodes of this node*/
    def children = [:]

    // frequency of data access
    private int rank = 0

    Node parent

    def readData() {
        // remove this node from parent's children
        if (parent.queue.remove(this)) {
            rank++
            parent.queue.add( this )
        }
        data
    }

    def isLeaf (){
        children.isEmpty()
    }

    def addChild(def key) {
        def child = children[key] = new Node(data: (data + key), parent: this)
        queue.add(child)
        child
    }

    String getSuggestion() {
        Node child = queue.peek()
        if (child) {
            child.suggestion
        } else
            data

    }
}