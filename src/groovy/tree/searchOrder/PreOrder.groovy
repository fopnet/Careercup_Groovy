package tree.searchOrder

import tree.TreeNode

/**
 * Created by Felipe on 13/04/2015.
 *
 * ROOT > LEFT > RIGHT
 *  passing from left side of elements
 */
class PreOrder  extends Order {

    PreOrder(def Object closure) {
        super(closure)
    }

    def visit(TreeNode root){
        closure(root)
        if (root.left) {
            visit(root.left)
        }
        if (root.right) {
            visit(root.right)
        }
    }
}
