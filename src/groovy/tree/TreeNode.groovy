package tree

import groovy.transform.EqualsAndHashCode

/**
 * Created by Felipe on 29/03/2015.
 */
@EqualsAndHashCode(includes = ['data'])
class TreeNode {
    int data
    int height = 0
    TreeNode left
    TreeNode right
    @groovy.beans.Bindable TreeNode parent

    void setLeft(TreeNode leftNode) {
        this.left = leftNode
        leftNode?.parent = this
    }

    void setRight(TreeNode rightNode) {
        this.right = rightNode
        rightNode?.parent = this
    }

    void changeChildFromParent(TreeNode childNode) {
        if (parent) {
            // changing parent pointer to new child
            if  (parent.left == this)
                parent.left = childNode
            else
                parent.right = childNode
        } else //can be null, set the parent to null, as root
            childNode.parent = parent


    }

    void calculateHeight(int factor = 0) {
        height = factor
        def parentNode = parent
        if (parentNode != null) {
            parentNode.calculateHeight(++factor)
            parentNode = parentNode.parent
        }
    }

    def hasChildren() {
        right || left
    }

    def isLeaf() {
        height == 0 || !hasChildren()
    }

    def getBalanceFactor() {
        def leftHeight = left ? left.height + 1 : 0
        def rightHeight = right ? right.height  + 1: 0
        leftHeight - rightHeight
    }

}

