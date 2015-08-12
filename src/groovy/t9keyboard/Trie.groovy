package t9keyboard

/**
 * Created by Felipe on 28/03/2015.
 */
class Trie {

    /*root node with empty data*/
    def root = new Node(data:'')

    Node search(Object key) {
        def node = root

        key.find {
            node = node.children[it]

            node == null
        }

        node
    }

    def add(key) {
        def node = root
        key.each {
            /*find the relevant child node*/
            def child = node.children[it]

            /*if child does not exist, creates it*/
            if (child == null) {
                child = node.addChild(it)
            }
            node = child
        }

        node
    }

}
