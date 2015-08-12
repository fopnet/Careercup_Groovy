package tree.builder

import tree.visitor.BinaryTreeVisitor
import tree.Avl
import tree.TreeNode
import tree.BinaryTree

/**
 * Created by Felipe on 30/03/2015.
 */
class TreeBuilder {

    BinaryTreeVisitor visitor
    BinaryTree tree

    /**
     * can not use this Constructor
     * @param tree
    TreeBuilder(BinaryTree tree, def visitor) {
        this(visitor)
        this.tree = tree
    }
     */

    TreeBuilder() {
    }

   /* TreeBuilder(BinaryTreeVisitor visitor) {
        this.visitor = visitor
    }*/

    /**
     * listener of node to update the root node of the tree
     */
    def parentPropertyChange = { evt ->
        // this node is became the root
        if (tree.root == evt.oldValue && !evt.newValue)
            tree.root = evt.source // point to new one
        // this root node, lost de parent pointer to tree
        else if (tree.root == evt.source && !evt.oldValue)
            tree.root = evt.newValue
    }

    def build (TreeNode parentNode, def data) {
        def node = new TreeNode(data: data, parent: parentNode, propertyChange: parentPropertyChange)
    }

    def visit(TreeNode parentNode, def data) {
        if (parentNode == null)
           build(parentNode, data)
        else
            visitor.visit(parentNode, data)
    }

}
