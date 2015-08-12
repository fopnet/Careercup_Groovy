package tree.searchOrder

import tree.TreeNode

/**
 * Created by Felipe on 13/04/2015.
 * LEFT > RIGHT > ROOT
 * Polish way - passing from right side of elements
 */
class PostOrder extends Order {

    PostOrder(def Object closure) {
        super(closure)
    }

    def visit(TreeNode root){
        if (root.left) {
            visit(root.left)
        }
        if (root.right) {
            visit(root.right)
        }
        closure(root)
    }
}
