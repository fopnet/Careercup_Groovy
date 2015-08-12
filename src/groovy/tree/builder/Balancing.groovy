package tree.builder

import tree.TreeNode
import tree.Avl

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 17/04/15
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
abstract class Balancing {
    Balancing right
    Balancing left
    Avl avl

    Balancing() {
    }

   Balancing(Avl avl) {
        this.avl = avl
    }

    void setLeft(Balancing balance) {
        this.left = balance
        balance.avl = avl
    }

    void setRight(Balancing balance) {
        this.right = balance
        balance.avl = avl
    }

    abstract def balance(TreeNode node)
}