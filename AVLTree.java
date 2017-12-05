/**
 * Created by brencharlson on 17/11/21.
 */
public class AVLTree {
    private AVLTreeNode root;  //root

    /**
     * three traversal methods
     */
    private void inOrderPrint(AVLTreeNode tree) {
        if(tree != null){
            inOrderPrint(tree.left);
            System.out.print(tree.data+"("+tree.height+")" + " ");
            inOrderPrint(tree.right);
        }
    }

    public void inOrder(){
        inOrderPrint(root);
    }

    private void preOrderPrint(AVLTreeNode tree) {
        if(tree != null){
            System.out.print(tree.data+"("+tree.height+")" + " ");
            preOrderPrint(tree.left);
            preOrderPrint(tree.right);
        }

    }

    public void preOrder(){
        preOrderPrint(root);
    }

    private void postOrderPrint(AVLTreeNode tree) {
        if(tree != null){
            postOrderPrint(tree.left);
            postOrderPrint(tree.right);
            System.out.print(tree.data+"("+tree.height+")" + " ");
        }
    }

    public void postOrder(){
        postOrderPrint(root);
    }

    /**
     *  inner class Node for AVLTree
     */
    class AVLTreeNode {
        int data;                       //data
        int height;                     //height of the node
        AVLTreeNode left;
        AVLTreeNode right;

        public AVLTreeNode(int data, AVLTreeNode left, AVLTreeNode right) {  // constructor for node
            this.data = data;
            this.height = 0;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * constructor for the AVLTree class
     */
    public AVLTree(){
        root = null;
    }

    /**
     * get height of the tree
     */
    private int height(AVLTreeNode tree){
        if(tree != null){
            return (tree.height);
        }
        return 0;
    }

    public int height(){
        return height(root);
    }

    /**
     * compare 2 values get bigger one
     */
    public int max(int a, int b){
        return a>b ? a : b;
    }

    /**
     * search data of a node with recursive
     */
    private AVLTreeNode search(AVLTreeNode tree, int data){
        if(tree == null) {
            System.out.println("no data: " + data);
            return tree;
        }
        if(data > tree.data){
            return search(tree.right, data);
        }else if(data < tree.data){
            return search(tree.left, data);
        }else{
            System.out.println("got data: " + data);
            return tree;
        }
    }

    public AVLTreeNode search(int data){
        return search(root, data);
    }

    /**
     * get minimum of a tree
     */
    private AVLTreeNode minimum(AVLTreeNode tree){
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
    private AVLTreeNode maximum(AVLTreeNode tree){
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
     * LL rotation
     * return root after rotation
     */
    private AVLTreeNode leftLeftRotation(AVLTreeNode k2){
        AVLTreeNode k1;
        k1 = k2.left;

        k2.left = k1.right;
        k1.right = k2;

        k2.height = max(height(k2.left),height(k2.right)) + 1;
        k1.height = max(height(k1.left),k2.height) + 1;

        return k1;
    }

    /**
     * RR rotation
     * return root after rotation
     */
    private AVLTreeNode rightRightRotation(AVLTreeNode k1){
        AVLTreeNode k2;
        k2 = k1.right;

        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;

        return k2;
    }

    /**
     * LR rotation
     * return root after rotation
     */
    private AVLTreeNode leftRightRotation(AVLTreeNode k3){
        k3.left = rightRightRotation(k3.left);
        return leftLeftRotation(k3);
    }

    /**
     * RL rotation
     * return root after rotation
     */
    private AVLTreeNode rightLeftRotation(AVLTreeNode k3){
        k3.right = leftLeftRotation(k3.right);
        return rightRightRotation(k3);
    }

    /**
     * insert a data into an AVL tree and return root of this tree
     */
    private AVLTreeNode insert(AVLTreeNode tree, int data){
        if (tree == null){
            tree = new AVLTreeNode(data,null,null);
            return tree;
        }else{
            if(data < tree.data){
                tree.left = insert(tree.left,data); //insert into left tree when data < tree.data
                //adjust tree when tree is unbalancing
                if(height(tree.left) - height(tree.right) == 2){
                    if(data < tree.left.data){
                        tree = leftLeftRotation(tree);
                    }else{
                        tree = leftRightRotation(tree);
                    }
                }
            }else if(data > tree.data){
                tree.right = insert(tree.right, data);
                if(height(tree.right) - height(tree.left) == 2){
                    if(data > tree.right.data){
                        tree = rightRightRotation(tree);
                    }else{
                        tree = rightLeftRotation(tree);
                    }
                }
            }else{
                System.out.println("duplicate, insertion refused");
            }
        }

        tree.height = max(height(tree.left),height(tree.right)) + 1;
        return tree;
    }

    public void insert(int data){
        root = insert(root,data);
    }

    /**
     * delete a node x
     * return the root of the tree after deletion
     */
    private AVLTreeNode remove(AVLTreeNode tree, AVLTreeNode x) {
        if (tree == null || x == null)
            return null;

        if(x.data < tree.data){
            tree.left = remove(tree.left,x);
            // if remove cause dis-balance
            if(height(tree.right) - height(tree.left) == 2){
                AVLTreeNode right = tree.right;
                if(height(right.left) > height(right.right)){
                    tree = rightLeftRotation(tree);
                }else{
                    tree = rightRightRotation(tree);
                }
            }
        }else if(x.data > tree.data){
            tree.right = remove(tree.right,x);
            // if remove cause dis-balance
            if(height(tree.left) - height(tree.right) == 2){
                AVLTreeNode left = tree.left;
                if(height(left.right) > height(left.left)){
                    tree = leftRightRotation(tree);
                }else{
                    tree = leftLeftRotation(tree);
                }
            }
        }else{ // tree is the node we want to delete
            if((tree.left != null) && (tree.right != null)){
                if(height(tree.left) > height(tree.right)){
                    AVLTreeNode max = maximum(tree.left);
                    tree.data = max.data;
                    tree.left = remove(tree.left, max);
                }else{
                    AVLTreeNode min = maximum(tree.right);
                    tree.data = min.data;
                    tree.right = remove(tree.right,min);
                }
            }else{
                AVLTreeNode tmp = tree;
                tree = (tree.left != null) ? tree.left : tree.right;
                tmp = null;
            }
        }

        return tree;
    }

    public void remove(int data){
        AVLTreeNode x;

        if((x = search(root, data)) != null){
            root = remove(root,x);
        }
    }

}