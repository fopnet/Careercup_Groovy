package t9keyboard

/**
 You have a list of words with ranking.
 Now you need to create a function that will take this list as input and provide a way so that a T9 keyboard
 can provide three top results of probable words based on rankings for the numbers punched in.
 Created by Felipe on 24/03/2015.
 */
class T9Keyboard extends Trie {

    @Override
    Node search(Object key) {
        def node = super.search(key)

        // if found
        if (node) {
           node.readData()
        }
        return node
    }

    /**
     * Returns the first three suggestion based on the string passed.
     */
    def suggestions(String word) {
        Node node = search(word)
        def suggestions = []
        if (node && !node.isLeaf()) {

            int i = 0;
            def it = node.queue.iterator() // iterator on descending rank order
//                for (int i = 0; i < 3 && i < node.children.size(); i++) {
            while (it.hasNext() && i<3) {
                Node suggestion = it.next();
                suggestions << suggestion.suggestion
                i++
            }
        }
        suggestions
    }

    static void main(String[] args) {

        def t = new T9Keyboard()
        t.add("trie")
        t.add("tree")
        t.add("i")
        t.add("it")
        t.add("ite")

        assertFound(t, "t")
        assertFound(t, "tr")
        assertFound(t,"tri")
        assertFound(t,"trie")
        assertFound(t,"tre")
        assertFound(t,"i")
        assertFound(t,"it")
        assertFound(t,"ite")

        assert null == t.search("triex")
        assert null == t.search("treexx")
        assert null == t.search("ix")

        assert t.suggestions("tr") == ['trie','tree']

        // increase tree rank
        assertFound(t,"tre")
        assertFound(t,"tre")
        assert t.suggestions("tr") == ['tree','trie']

        println "T9Keyboard test OK"
    }

    static def assertFound(def t, String key) {
        assert t.search(key)?.data == key
    }

}
