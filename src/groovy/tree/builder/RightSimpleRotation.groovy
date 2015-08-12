package tree.builder

import tree.TreeNode
import tree.Avl

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 17/04/15
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
class RightSimpleRotation extends Balancing {

    RightSimpleRotation() {
//        super(avl)
//        left = new LeftSimpleRotation(avl)
//        right = new RightDoubleRotation(avl)
    }

    @Override
    def balance(TreeNode node) {

        // right rotation
        if (node.getBalanceFactor() > 1) {
            // simple right rotation
            if (node.left && node.left.getBalanceFactor() > 0) { //verify se left bf is positive
                int degree = node.left.height-1
                avl.rightSimpleRotation(node, node.left)
                // recalculate the height after rotation
                node.calculateHeight(degree)

                return true
            } else
                right?.balance(node)
        } else
            left?.balance(node)

        false
    }
}
