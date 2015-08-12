import groovy.transform.ToString

/**
 * Created by Felipe on 21/03/2015.
Example:


 |    a     |   j
 |   / \    |  /
 |  b   c   | h
 | /   / \  |
 |d   e   f |
 for e and d CCA is a
 for e and f CCA is c
 for e and c CCA is c
 for h and d CCA is null

 Constrains: O(1) additional memory
 */
class ClosestCommonAncestor {
    @ToString(includes = ['name'])
    static class Node {
        Node parent
        Node left
        Node right
        String name

        void setLeft(Node node) {
            node.parent = this
            this.left = node
        }

        void setRight(Node node) {
            node.parent = this
            this.right = node
        }
    }

    static void main (String[] args) {
        def a = new Node(name:'a',
                    left: new Node (name:'b'),
                    right: new Node(name:'c'))

        def c = a.right
        def e = new Node(name:'e')
        a.right.left = e
        def f= new Node(name:'f')
        a.right.right = f

        def d = new Node (name: 'd')
        a.left.left = d

        def j = new Node(name:'j', left: new Node(name:'h'))
        def h = j.left
        def nodes = [a, j]

        println getCCA(e,d)?.name
        println getCCA(e,f)?.name
        println getCCA(e,c)?.name
        println getCCA(h,d)?.name
    }

    /**
     * É preciso assumir que os nós estejam na mesma profundidade
     * para asssim tentar achar o pai em comum com a mesma quantidade de iterações.
     * @param x
     * @param y
     * @return
     */
    static Node getCCA(Node n1, Node n2){
        def n1Level = getNodeLevel(n1)
        def n2Level = getNodeLevel(n2)

        while(n1Level < n2Level) {
            n2 = n2.parent
            n2Level--
        }
        while(n2Level < n1Level) {
            n1 = n1.parent
            n1Level--
        }
        // Se o n1 == n2 == null ainda é o resultado correto
        while (n1 != n2) {
            n1 = n1.parent
            n2 = n2.parent
        }

        n1
    }

    static getNodeLevel(Node n1) {
        int level = 0
        while (n1.parent != null) {
            n1 = n1.parent
            level++
        }
        level
    }
}
