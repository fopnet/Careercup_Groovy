package tree.builder

import tree.TreeNode
import tree.Avl

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 17/04/15
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
class LeftDoubleRotation extends Balancing {

    LeftDoubleRotation() {
//        super(avl)
//        left = new LeftSimpleRotation(avl)
//        right = new RightSimpleRotation(avl)
    }

    @Override
    def balance(TreeNode node) {
        //left rotation
        if (node.getBalanceFactor() < -1) {
            // double left rotation
            if (node.right && node.right.getBalanceFactor() > 0) {
                // double left rotation
                avl.rightSimpleRotation(node.right, node.right.left )
                // recalculate the height after rotation, right straight line tree
                node.right.right.calculateHeight(node.right.height)

                // simple left rotation
                avl.leftSimpleRotation(node, node.right)
                // recalculate the height after rotation
                node.calculateHeight(node.parent.height-1)

                return true
            } else
                left?.balance(node)
        } else
            right?.balance(node)

        false
    }
}
