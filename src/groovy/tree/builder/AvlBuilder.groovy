package tree.builder

import tree.TreeNode
import tree.Avl
import tree.BinaryTree
import tree.visitor.BinaryTreeVisitor

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 15/04/15
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
class AvlBuilder extends TreeBuilder {

    Avl getAvl() {
       tree as Avl
    }

    // builder
    def leftBuilder = {TreeNode parentNode, TreeNode child, def data ->
        def builder = delegate as TreeBuilder
        if (child == null) {
            def built = builder.build(parentNode, data)
            parentNode.left = built

            // if the parentNode had not children
            if (parentNode.right == null) {
                //increaseHeight
                parentNode.calculateHeight(1)
                avl.balance(parentNode?.parent)
            }

            built
        } else
            builder.visit (parentNode.left, data)
    }


    def rightBuilder = {TreeNode parentNode, TreeNode child, def data ->
        def builder = delegate as TreeBuilder
        if (child == null) {
            def built = builder.build(parentNode, data)
            parentNode.right = built

            if (parentNode.left == null) {
                //increaseHeight
                parentNode.calculateHeight(1)
                avl.balance(parentNode?.parent)
            }

            built
        } else
            builder.visit (parentNode.right, data)
    }

    AvlBuilder() {
       this.visitor = new BinaryTreeVisitor(leftBuilder, rightBuilder)
    }
}
