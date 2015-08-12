package tree

import tree.searchOrder.PreOrder
import tree.searchOrder.PostOrder
import tree.searchOrder.InOrder

import tree.builder.TreeBuilder
import tree.builder.BinaryTreeBuilder

/**
 * Created with IntelliJ IDEA.
 * User: eii5
 * Date: 15/04/15
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
class BinaryTree {
    TreeNode root = null

    TreeBuilder builderVisitor

    void setBuilderVisitor(TreeBuilder builderVisitor) {
        this.builderVisitor = builderVisitor
        this.builderVisitor.tree = this   // is necessary to wait child constructor run, in order to assign the tree reference
    }

    /*BinaryTree (def builderVisitor, int data) {
        this.setBuilderVisitor(builderVisitor)
        root = add(null, data)
    }

    BinaryTree() {}
    */

    BinaryTree (int data) {
        this.setBuilderVisitor(new BinaryTreeBuilder())
        root = add(null, data)
    }

    TreeNode add(int data) {
        add(root, data)
    }

    TreeNode add(TreeNode parentNode, int data) {
        def child = builderVisitor.visit(parentNode, data)
        child
    }

    def preOrderVisit(def cls) {
        new PreOrder(cls).visit(this.root)
    }
    def postOrderVisit(def cls) {
        new PostOrder(cls).visit(this.root)
    }
    def inOrderVisit(def cls) {
        new InOrder(cls).visit(this.root)
    }

    static void main(String[] args) {
        BinaryTree visitTree = new BinaryTree(8)
        visitTree.add(3)
        visitTree.add(1)
        visitTree.add(6)
        visitTree.add(4)
        visitTree.add(7)
        visitTree.add(10)
        visitTree.add(14)
        visitTree.add(13)

        // preparing pre order visit
        def preList = []
        def preCls = { TreeNode node ->
            preList << node.data
        }

        // ROOT > LEFT > RIGHT
        visitTree.preOrderVisit(preCls)
        assert preList == [8,3,1,6,4,7,10,14,13]
        println "Pre order visited"

        def inList = []
        def inCls = { TreeNode node ->
            inList << node.data
        }
        // LEFT > ROOT > RIGHT preparing in-order visit
        visitTree.inOrderVisit(inCls)
        assert inList == [1,3,4,6,7,8,10,13,14]
        println "In order visited"

        // preparing in-order visit
        def postList = []
        def postCls = { TreeNode node ->
            postList << node.data
        }
        //LEFT > RIGHT > ROOT
        visitTree.postOrderVisit(postCls)
        assert postList == [1,4,7,6,3,13,14,10,8]

        println "Post_Order visited"
    }

}
