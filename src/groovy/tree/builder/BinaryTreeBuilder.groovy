package tree.builder

import tree.visitor.BinaryTreeVisitor

import tree.TreeNode
import tree.BinaryTree

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 15/04/15
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
class BinaryTreeBuilder extends TreeBuilder {

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
            }

            built
        } else
            builder.visit (parentNode.right, data)
    }

    /**
     * Constructor
     * @param tree
     */
    BinaryTreeBuilder() {
        this.visitor = new BinaryTreeVisitor(leftBuilder, rightBuilder)
    }



}
