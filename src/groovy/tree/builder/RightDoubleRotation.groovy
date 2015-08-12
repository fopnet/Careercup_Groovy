package tree.builder

import tree.TreeNode
import tree.Avl

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 17/04/15
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
class RightDoubleRotation extends Balancing {

    RightDoubleRotation() {
//        super(avl)
//        left = new LeftDoubleRotation(avl)
//        right = new RightSimpleRotation(avl)
    }

    @Override
    def balance(TreeNode node) {
        // right rotation
        if (node.getBalanceFactor() > 1) {
            // double right rotation
            if (node.left && node.left.getBalanceFactor() < 0) {
                // double right rotation
                avl.leftSimpleRotation(node.left, node.left.right)
                // recalculate the height after rotation,  left straight line tree
                node.left.left.calculateHeight(node.left.height)

                // simple right rotation
                avl.rightSimpleRotation(node, node.left)
                // recalculate the height after rotation
                node.calculateHeight(node.parent.height -1)

                return true
            } else
                right?.balance(node)
        } else
            left?.balance(node)

        false
    }
}
