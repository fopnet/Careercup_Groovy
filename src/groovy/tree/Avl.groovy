package tree

import tree.builder.AvlBuilder
import tree.builder.Balancing
import tree.builder.LeftSimpleRotation
import tree.builder.LeftDoubleRotation
import tree.builder.RightSimpleRotation
import tree.builder.RightDoubleRotation

/**
 * Created by Felipe on 29/03/2015.
 */
class Avl extends BinaryTree {

    Balancing chainBalance

    Avl (int data) {
        super(data)
        builderVisitor = new AvlBuilder()
        chainBalance = new LeftSimpleRotation(avl:this)
        chainBalance.left = new LeftDoubleRotation()
        chainBalance.right = new RightSimpleRotation()
        chainBalance.right.right = new RightDoubleRotation()

    }

    def balance(TreeNode node) {
        if (node) {
            if (!chainBalance.balance(node))
                balance(node.parent) // is at last ?
        }

        /*if (node.getBalanceFactor() < -1 && node.right && node.right.getBalanceFactor() < 0) { //verify se left bf is negative
            int degree = node.right.height-1
            leftSimpleRotation(node, node.right)
            // recalculate the height after rotation
            node.calculateHeight(degree)
        } else if (node.getBalanceFactor() > 1 && node.left && node.left.getBalanceFactor() > 0) { //verify se left bf is positive
            int degree = node.left.height-1
            rightSimpleRotation(node, node.left)
            // recalculate the height after rotation
            node.calculateHeight(degree)
        } else if (node.getBalanceFactor() < -1 && node.right && node.right.getBalanceFactor() > 0) {
            // double left rotation
            rightSimpleRotation(node.right, node.right.left )
            // recalculate the height after rotation, right straight line tree
            node.right.right.calculateHeight(node.right.height)

            // simple left rotation
            leftSimpleRotation(node, node.right)
            // recalculate the height after rotation
            node.calculateHeight(node.parent.height-1)
        } else if (node.getBalanceFactor() > 1 && node.left && node.left.getBalanceFactor() < 0) {
            // double right rotation
            leftSimpleRotation(node.left, node.left.right)
            // recalculate the height after rotation,  left straight line tree
            node.left.left.calculateHeight(node.left.height)

            // simple right rotation
            rightSimpleRotation(node, node.left)
            // recalculate the height after rotation
            node.calculateHeight(node.parent.height -1)
        } else {
            balance(node.parent) // is at last ?
        }*/
    }

    def rightSimpleRotation(TreeNode node, TreeNode childNode) {
        //1
        node.changeChildFromParent(childNode)

        //2
        node.left = childNode.right

        //3
        childNode.right = node

    }

    def leftSimpleRotation(TreeNode node, TreeNode childNode) {
        //1
        node.changeChildFromParent(childNode)

        //2
        node.right = childNode.left

        //3
        childNode.left = node
    }

    static void main(String[] args) {
        // tree [15-27-49-10-8-67-59-9-13-20-14]

        def tree = new Avl(15)

        def validate = { node, params ->
            assert node.data == params.data
            assert node.height == params.height
            assert node.balanceFactor == params.balanceFactor
            if (params.parent == null) {
                assert node.parent == params.parent
                assert node == tree.root
            } else {
                assert node != tree.root
                assert node.parent == params.parent
            }
        }

        // creating tree with root node
        TreeNode node15 = tree.root
        validate(node15, [data:15, parent:null, height:0, balanceFactor:0])

        // on second node the balance is not necessary
        TreeNode node27 = tree.add(27)
        validate(node27, [data:27, parent:node15, height:0, balanceFactor:0])
        validate(node15, [data:15, parent:null, height:1, balanceFactor:-1])

        TreeNode node49 = tree.add(49) // add node 49 with simple left rotation
        validate(node27, [data:27, parent:null, height:1, balanceFactor:0])
        validate(node49, [data:49, parent:node27, height:0, balanceFactor:0])
        validate(node15, [data:15, parent:node27, height:0, balanceFactor:0])

        TreeNode node10 = tree.add(10)  // add node 10 below on node 15
        validate(node27, [data:27, parent:null, height:2, balanceFactor:1])
        validate(node49, [data:49, parent:node27, height:0, balanceFactor:0])
        validate(node15, [data:15, parent:node27, height:1, balanceFactor:1])
        validate(node10, [data:10, parent:node15, height:0, balanceFactor:0])

        TreeNode node8 = tree.add(8) // add node 8 with simple right rotation
        validate(node27, [data:27, parent:null, height:2, balanceFactor:1])
        validate(node49, [data:49, parent:node27, height:0, balanceFactor:0])
        validate(node10, [data:10, parent:node27, height:1, balanceFactor:0])
        validate(node15, [data:15, parent:node10, height:0, balanceFactor:0])
        validate(node8,  [data:8, parent:node10, height:0, balanceFactor:0])

        TreeNode node67 = tree.add(67) // add node 67 without rotation
        validate(node27, [data:27, parent:null, height:2, balanceFactor:0])
        validate(node49, [data:49, parent:node27, height:1, balanceFactor:-1])
        validate(node67, [data:67, parent:node49, height:0, balanceFactor:0])
        validate(node10, [data:10, parent:node27, height:1, balanceFactor:0])
        validate(node15, [data:15, parent:node10, height:0, balanceFactor:0])
        validate(node8,  [data:8, parent:node10, height:0, balanceFactor:0])

        TreeNode node59 = tree.add(59) // add node 59 with double rotation left
        validate(node27, [data:27, parent:null, height:2, balanceFactor:0])
        validate(node59, [data:59, parent:node27, height:1, balanceFactor:0])
        validate(node49, [data:49, parent:node59, height:0, balanceFactor:0])
        validate(node67, [data:67, parent:node59, height:0, balanceFactor:0])
        validate(node10, [data:10, parent:node27, height:1, balanceFactor:0])
        validate(node15, [data:15, parent:node10, height:0, balanceFactor:0])
        validate(node8,  [data:8, parent:node10, height:0, balanceFactor:0])

        TreeNode node9 = tree.add(9) // add node 9 without rotation
        validate(node27, [data:27, parent:null, height:3, balanceFactor:1])
        validate(node10, [data:10, parent:node27, height:2, balanceFactor:1])
        validate(node59, [data:59, parent:node27, height:1, balanceFactor:0])
        validate(node67, [data:67, parent:node59, height:0, balanceFactor:0])
        validate(node49, [data:49, parent:node59, height:0, balanceFactor:0])
        validate(node15, [data:15, parent:node10, height:0, balanceFactor:0])
        validate(node8,  [data:8, parent:node10, height:1, balanceFactor:-1])
        validate(node9,  [data:9, parent:node8, height:0, balanceFactor:0])

        TreeNode node13 = tree.add(13) // add node 13 without rotation
        validate(node27, [data:27, parent:null, height:3, balanceFactor:1])
        validate(node59, [data:59, parent:node27, height:1, balanceFactor:0])
        validate(node49, [data:49, parent:node59, height:0, balanceFactor:0])
        validate(node67, [data:67, parent:node59, height:0, balanceFactor:0])
        validate(node10, [data:10, parent:node27, height:2, balanceFactor:0])
        validate(node15, [data:15, parent:node10, height:1, balanceFactor:1])
        validate(node8,  [data:8, parent:node10, height:1, balanceFactor:-1])
        validate(node13, [data:13, parent:node15, height:0, balanceFactor:0])
        validate(node9,  [data:9, parent:node8, height:0, balanceFactor:0])


        TreeNode node20 = tree.add(20) // add node 20 without rotation
        validate(node27, [data:27, parent:null, height:3, balanceFactor:1])
        validate(node59, [data:59, parent:node27, height:1, balanceFactor:0])
        validate(node49, [data:49, parent:node59, height:0, balanceFactor:0])
        validate(node67, [data:67, parent:node59, height:0, balanceFactor:0])
        validate(node10, [data:10, parent:node27, height:2, balanceFactor:0])
        validate(node15, [data:15, parent:node10, height:1, balanceFactor:0])
        validate(node8,  [data:8, parent:node10, height:1, balanceFactor:-1])
        validate(node13, [data:13, parent:node15, height:0, balanceFactor:0])
        validate(node9,  [data:9, parent:node8, height:0, balanceFactor:0])
        validate(node20, [data:20, parent:node15, height:0, balanceFactor:0])

        TreeNode node14 = tree.add(14) // add node 14 with a double right rotation on node27
        validate(node15, [data:15, parent:null, height:3, balanceFactor:0])
        validate(node27, [data:27, parent:node15, height:2, balanceFactor:-1])
        validate(node10, [data:10, parent:node15, height:2, balanceFactor:0])
        validate(node59, [data:59, parent:node27, height:1, balanceFactor:0])
        validate(node13, [data:13, parent:node10, height:1, balanceFactor:-1])
        validate(node8,  [data:8, parent:node10, height:1, balanceFactor:-1])
        validate(node20, [data:20, parent:node27, height:0, balanceFactor:0])
        validate(node49, [data:49, parent:node59, height:0, balanceFactor:0])
        validate(node67, [data:67, parent:node59, height:0, balanceFactor:0])
        validate(node14, [data:14, parent:node13, height:0, balanceFactor:0])
        validate(node9,  [data:9, parent:node8, height:0, balanceFactor:0])

        println "Avl tree balanced"


    }


}
