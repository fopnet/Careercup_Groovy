package tree.builder

import tree.TreeNode
import tree.Avl

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 17/04/15
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
class LeftSimpleRotation extends Balancing {

    LeftSimpleRotation() {
//        left = new LeftDoubleRotation(avl)
//        right = new RightSimpleRotation(avl)
    }

    def balance(TreeNode node) {
        // left balance
        if (node.getBalanceFactor() < -1) {
            // simple left rotation
            if (node.right && node.right.getBalanceFactor() < 0) {
                int degree = node.right.height-1
                avl.leftSimpleRotation(node, node.right)
                // recalculate the height after rotation
                node.calculateHeight(degree)

                return true
            } else {
                left?.balance(node)
            }
        } else
            right?.balance(node)

        false
    }
}
