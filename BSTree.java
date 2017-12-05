/**
 * Created by brencharlson on 17/11/21.
 */
public class BSTree {
    private BSTNode root;

    /**
     * three traversal methods
     */
    private void inOrderPrint(BSTNode tree){
        if(tree != null){
            inOrderPrint(tree.left);
            System.out.print(tree.data + " ");
            inOrderPrint(tree.right);
        }
    }

    public void inOrder(){
        inOrderPrint(root);
    }

    private void preOrderPrint(BSTNode tree) {
        if(tree != null){
            System.out.print(tree.data + " ");
            preOrderPrint(tree.left);
            preOrderPrint(tree.right);
        }

    }

    public void preOrder(){
        preOrderPrint(root);
    }

    private void postOrderPrint(BSTNode tree) {
        if(tree != null){
            postOrderPrint(tree.left);
            postOrderPrint(tree.right);
            System.out.print(tree.data + " ");
        }
    }

    public void postOrder(){
        postOrderPrint(root);
    }

    /**
     * inner class Node for BST
     */
    class BSTNode{
        int data;
        BSTNode left;
        BSTNode right;

        public BSTNode(int data, BSTNode left, BSTNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * constructor for BST
     */
    public BSTree(){
        root = null;
    }

    /**
     * search in a tree to find certain data
     * return the node containing certain data
     */
    private BSTNode search(BSTNode tree, int data){
        if(tree == null){
            System.out.println("no data: " + data);
            return tree;
        }
        if(data > tree.data){
            return search(tree.right, data);
        }else if(data < tree.data){
            return search(tree.left, data);
        }else{
            System.out.println("find data: " + data);
            return tree;
        }
    }

    public BSTNode search(int data){
        return search(root,data);
    }


    /**
     * get minimum of a tree
     */
    private BSTNode minimum(BSTNode tree){
        if (tree == null)
            return null;
        while(tree.left != null)
            tree = tree.left;
        return tree;
    }

    public void minimum(){
        System.out.println("minimum is "+ minimum(root).data);
    }

    /**
     * get maximum of a tree
     */
    private BSTNode maximum(BSTNode tree){
        if (tree == null)
            return null;
        while(tree.right != null)
            tree = tree.right;
        return tree;
    }

    public void maximum(){
        System.out.println("maximum is "+ maximum(root).data);
    }

    /**
     * insert data into a tree
     * return root of the tree
     */
    private BSTNode insert(BSTNode tree, int data){
        if(tree == null){
            tree = new BSTNode(data,null,null);
            return tree;
        }else{
            if(data < tree.data){
                tree.left = insert(tree.left,data);
            }else if(data > tree.data){
                tree.right = insert(tree.right,data);
            }else{
                System.out.println("duplicate, insertion refused");
            }
        }
        return tree;
    }

    public void insert(int data){
        root = insert(root,data);
    }

    /**
     * delete a node x
     * return the root of the tree
     */
    private BSTNode remove(BSTNode tree ,int target)
    {
        if(search(tree,target) == null){
            return tree;
        }
        BSTNode current = tree;
        BSTNode parent = null;
        boolean isLeftChild = false;

        while(current.data != target){
            parent = current;
            if(current.data > target){
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
        }

        if(current.left == null && current.right == null){
            if(current == root){
                root = null;
            }
            if(isLeftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        else if(current.right == null){
            if (current == root){
                root = current.left;
            }
            if(isLeftChild){
                parent.left = current.left;
            }else{
                parent.right = (current.left);
            }
        }
        else if(current.left == null){
            if(current == root){
                root = current.right;
            }
            if(isLeftChild){
                parent.left = (current.right);
            }else{
                parent.right = (current.right);
            }

            //need to find a successor or the node
        }else {
            BSTNode successor = getCurrentSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = (successor);
            } else {
                parent.right = (successor);
            }
        }

        return tree;
    }


    private BSTNode getCurrentSuccessor(BSTNode node){
        BSTNode successor = null;
        BSTNode successorparent = null;
        BSTNode current = node.left;

        while(current != null){
            successorparent = successor;
            successor = current;
            current = current.right;
        }

        if(successor != node.left){
            successorparent.right = (successor.left);
            successor.left = (node.left);
            successor.right = (node.right);
        }

        return successor;
    }

    public void remove(int data) {
        remove(root,data);
    }
}