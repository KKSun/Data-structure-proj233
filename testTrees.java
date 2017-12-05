
/**
 * Created by brencharlson on 17/11/29.
 */
public class testTrees {
    public static void main(String[] args){
        System.out.println("+++++++++++++++++++++++++++Red Black Tree(preOrder)++++++++++++++++++++++++++++++++");
        RBTree RBT = new RBTree();
        System.out.println("--------insert data(color)--------");
        RBT.insert(12);
        RBT.insert(1);
        RBT.insert(9);
        RBT.insert(2);
        RBT.insert(0);
        RBT.insert(11);
        RBT.insert(7);
        RBT.insert(19);
        RBT.insert(19); // duplicate data insert
        RBT.insert(4);
        RBT.insert(15);
        RBT.insert(18);
        RBT.insert(5);
        RBT.insert(14);
        RBT.insert(13);
        RBT.insert(10);
        RBT.insert(16);
        RBT.insert(6);
        RBT.insert(3);
        RBT.insert(8);
        RBT.insert(17);
        RBT.preOrder();
        System.out.println();
        System.out.println("--------find min--------");
        RBT.minimum();
        System.out.println("--------find max--------");
        RBT.maximum();
        System.out.println("--------find 14--------");
        RBT.search(14);
        System.out.println("--------can't find 100--------");
        RBT.search(100);
        System.out.println("--------remove 19--------");
        RBT.remove(19);
        RBT.preOrder();
        System.out.println();
        System.out.println("--------remove 6--------");
        RBT.remove(6);
        RBT.preOrder();
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println();


        System.out.println("+++++++++++++++++++++++++Binary search tree(postOrder)++++++++++++++++++++++++++++++");
        BSTree BST = new BSTree();
        System.out.println("--------insert data--------");
        BST.insert(1);
        BST.insert(6);
        BST.insert(2);
        BST.insert(11);
        BST.insert(16);
        BST.insert(12);
        BST.insert(31);
        BST.insert(26);
        BST.insert(22);
        BST.insert(51);
        BST.insert(56);
        BST.insert(52);
        BST.insert(52);  //insert duplicate data
        BST.insert(511);
        BST.insert(516);
        BST.insert(512);
        BST.insert(531);
        BST.insert(626);
        BST.insert(622);
        BST.postOrder();
        System.out.println();
        System.out.println("--------find min--------");
        BST.minimum();
        System.out.println("--------find max--------");
        BST.maximum();
        System.out.println("--------find 622--------");
        BST.search(622);
        System.out.println("--------can't find -100--------");
        BST.search(-100);
        System.out.println("--------remove 12--------");
        BST.remove(12);
        BST.postOrder();
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println();


        System.out.println("++++++++++++++++++++++++++++++AVL tree(inOrder)+++++++++++++++++++++++++++++++++++");
        AVLTree avl = new AVLTree();
        System.out.println("--------insert data(height)--------");
        avl.insert(1);
        avl.insert(6);
        avl.insert(2);
        avl.insert(11);
        avl.insert(16);
        avl.insert(12);
        avl.insert(31);
        avl.insert(26);
        avl.insert(22);
        avl.insert(51);
        avl.insert(56);
        avl.insert(52);
        avl.insert(52);  //insert duplicate data
        avl.insert(511);
        avl.insert(516);
        avl.insert(512);
        avl.insert(531);
        avl.insert(626);
        avl.insert(622);
        avl.inOrder();
        System.out.println();
        System.out.println("--------find min--------");
        avl.minimum();
        System.out.println("--------find max--------");
        avl.maximum();
        System.out.println("--------find 622--------");
        avl.search(622);
        System.out.println("--------can't find -100--------");
        avl.search(-100);
        System.out.println("--------get height--------");
        System.out.println("the tree's height is " + avl.height());
        System.out.println("--------remove 512--------");
        avl.remove(512);
        avl.inOrder();
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }
}
