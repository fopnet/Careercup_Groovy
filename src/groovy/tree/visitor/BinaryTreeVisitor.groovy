package tree.visitor

import tree.TreeNode

/**
 * Created by Felipe on 30/03/2015.
 */
class BinaryTreeVisitor {

    def leftClosure
    def rightClosure

    BinaryTreeVisitor(def leftClosure, def rightClosure) {
        this.leftClosure = leftClosure
        this.rightClosure = rightClosure
    }

    def visit(TreeNode parentNode, def data) {
        if (parentNode == null)
            return null

        if (data <= parentNode.data)
            leftClosure.call (parentNode, parentNode.left, data)
        else
            rightClosure.call (parentNode, parentNode.right, data)
    }

}
