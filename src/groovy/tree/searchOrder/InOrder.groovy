package tree.searchOrder

import tree.TreeNode

/**
 * Created by Felipe on 13/04/2015.
 *
 * LEFT > ROOT > RIGHT
 * ascending order
 * passing the underside of elements
 */
class InOrder extends Order {

    InOrder(def Object closure) {
        super(closure)
    }

    def visit(TreeNode root){
        if (root.left) {
            visit(root.left)
        }
        closure(root)
        if (root.right) {
            visit(root.right)
        }
    }
}
