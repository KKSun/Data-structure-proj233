/**
 * Created by brencharlson on 17/11/21.
 */

/**
 * characteristics of Red Black tree
 * (1) easch node has a color Red/Black
 * (2) root is Black
 * (3) null leaf is Black
 * (4) if a node is Red, its child should be Black
 * (5) all route from a node to its offspring node has same number of Black nodes
 */
public class RBTree {
    private RBTNode root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    class RBTNode{
        boolean color;
        int data;
        RBTNode left;
        RBTNode right;
        RBTNode parent;

        /**
         * constructor for RBTNode
         */
        public RBTNode(int data, boolean color, RBTNode parent, RBTNode left, RBTNode right) {
            this.data = data;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public RBTree(){
        root = null;
    }

    /**
     * basic operations of RBTree
     */
    private RBTNode parentOf(RBTNode node) {
        return node!=null ? node.parent : null;
    }
    private boolean colorOf(RBTNode node) {
        return node!=null ? node.color : BLACK;
    }
    private boolean isRed(RBTNode node) {
        return (node != null) && (node.color == RED);
    }
    private boolean isBlack(RBTNode node) {
        return !isRed(node);
    }
    private void setBlack(RBTNode node) {
        if (node!=null)
            node.color = BLACK;
    }
    private void setRed(RBTNode node) {
        if (node!=null)
            node.color = RED;
    }
    private void setParent(RBTNode node, RBTNode parent) {
        if (node!=null)
            node.parent = parent;
    }
    private void setColor(RBTNode node, boolean color) {
        if (node!=null)
            node.color = color;
    }

    /**
     * three tree traversal methods
     */
    private void inOrderPrint(RBTNode tree){
        if(tree != null){
            inOrderPrint(tree.left);
            String color = (tree.color)?"(B)":"(R)";
            System.out.print(tree.data + color + " ");
            inOrderPrint(tree.right);
        }
    }

    public void inOrder(){
        inOrderPrint(root);
    }

    private void preOrderPrint(RBTNode tree) {
        if(tree != null){
            String color = (tree.color)?"(B)":"(R)";
            System.out.print(tree.data + color + " ");
            preOrderPrint(tree.left);
            preOrderPrint(tree.right);
        }

    }

    public void preOrder(){
        preOrderPrint(root);
    }

    private void postOrderPrint(RBTNode tree) {
        if(tree != null){
            postOrderPrint(tree.left);
            postOrderPrint(tree.right);
            String color = (tree.color)?"(B)":"(R)";
            System.out.print(tree.data + color + " ");
        }
    }

    public void postOrder(){
        postOrderPrint(root);
    }

    /**
     * recursively search data
     */
    private RBTNode search(RBTNode tree,int data){
        if (tree == null){
            System.out.println("no data: " + data);
            return null;
        }

        if(data < tree.data){
            return search(tree.left, data);
        }else if(data > tree.data){
            return search(tree.right, data);
        }else{
            System.out.println("find it: " + data);
            return tree;
        }
    }

    public RBTNode search(int data){
        return search(root,data);
    }

    /**
     * search minimum
     * return minimum note
     */
    private RBTNode minimum(RBTNode tree){
        if(tree == null){
            return null;
        }
        while(tree.left != null){
            tree = tree.left;
        }
        return tree;
    }

    public void minimum(){
        System.out.println("minimum is "+ minimum(root).data);
    }

    /**
     * search maximum
     * return maximum note
     */
    private RBTNode maximum(RBTNode tree){
        if(tree == null){
            return null;
        }
        while(tree.right != null){
            tree = tree.right;
        }
        return tree;
    }

    public void maximum(){
        System.out.println("maximum is "+ maximum(root).data);
    }

    /**
     * leftRotation(a) demonstrate
     *
     *         |
     *         a
     *        / \
     *      la   b
     *          / \
     *         lb rb
     *
     *         to
     *
     *         |
     *         b
     *        / \
     *       a  rb
     *      / \
     *     la lb
     *
     */
    private void leftRotation(RBTNode a){
        RBTNode b = a.right;
        a.right = b.left;
        if (b.left != null){
            b.left.parent = a;
        }

        b.parent = a.parent;

        if(a.parent == null){
            this.root = b;
        }else{
            if(a.parent.left == a){
                a.parent.left = b;
            }else{
                a.parent.right = b;
            }
        }

        b.left = a;
        a.parent = b;
    }

    /**
     * rightRotation(b) demonstrate
     *
     *         |
     *         b
     *        / \
     *       a  rb
     *      / \
     *     la ra
     *
     *         to
     *
     *         |
     *         a
     *        / \
     *      la   b
     *          / \
     *         ra rb
     *
     */
    private void rightRotation(RBTNode b){
        RBTNode a = b.left;
        b.left = a.right;
        if(a.right != null){
            a.right.parent = b;
        }

        a.parent = b.parent;

        if(b.parent == null){
            this.root = a;
        }else{
            if(b == b.parent.right){
                b.parent.right = a;
            }else{
                b.parent.left = a;
            }
        }

        a.right = b;
        b.parent = a;
    }

    /**
     * insert
     */
    private void insert(RBTNode node){
        RBTNode prt = null;
        RBTNode curr = this.root;

        while(curr != null){
            prt = curr;
            if(node.data < curr.data){
                curr =curr.left;
            }else if(node.data > curr.data){
                curr = curr.right;
            }else{
                System.out.println("duplicate, insertion refused");
                return;
            }
        }

        node.parent = prt;
        if(prt != null){
            if(node.data < prt.data){
                prt.left = node;
            }else{
                prt.right = node;
            }
        }else{
            this.root = node;
        }

        node.color = RED;

        insertFixUp(node);
    }

    public void insert(int data){
        RBTNode node = new RBTNode(data,BLACK,null,null,null);
        insert(node);
    }

    /**
     * insert fix up
     */
    private void insertFixUp(RBTNode node){
        RBTNode parent = parentOf(node);
        RBTNode grandparent;

        // parent exist and parent is red
        while((parent != null) && isRed(parent)){
            grandparent = parentOf(parent);

            //if parent is grandparent's left child
            if(parent == grandparent.left){
                RBTNode uncle = grandparent.right;
                if(uncle != null && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandparent);
                    node = grandparent;
                    continue;
                }

                //uncle black and node is a right child
                if(parent.right == node){
                    RBTNode tmp;
                    leftRotation(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //uncle black and node is a left child
                setBlack(parent);
                setRed(grandparent);
                rightRotation(grandparent);
            }else{
                // parent is grandparent right child
                RBTNode uncle = grandparent.left;
                if(uncle != null && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandparent);
                    node = grandparent;
                    continue;
                }

                if(parent.left == node){
                    RBTNode tmp;
                    rightRotation(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                setBlack(parent);
                setRed(grandparent);
                leftRotation(grandparent);
            }
        }

        setBlack(this.root);
    }

    /**
     * remove
     */
    private void remove(RBTNode node){
        RBTNode child, parent;
        boolean color;

        //both children of the deleted node exist
        if ( (node.left!=null) && (node.right!=null) ) {
            RBTNode replace = node;
            replace = replace.right;
            while (replace.left != null) {
                replace = replace.left;
            }

            //if node is not root
            if (parentOf(node) != null) {
                if (parentOf(node).left == node) {
                    parentOf(node).left = replace;
                } else {
                    parentOf(node).right = replace;
                }
            } else {
                this.root = replace;
            }

            child = replace.right;
            parent = parentOf(replace);
            color = colorOf(replace);

            if (parent == node) {
                parent = replace;
            } else {
                //child is node null
                if (child != null) {
                    setParent(child, parent);
                }
                parent.left = child;
                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK) {
                removeFixUp(child, parent);
            }
            node = null;
            return;
        }
        if(node.left != null) {
            child = node.left;
        }else{
            child = node.right;
        }

        parent = node.parent;
        color = node.color;

        if (child != null){
            child.parent = parent;
        }

        if(parent != null){
            if(parent.left == node){
                parent.left = child;
            }else{
                parent.right = child;
            }
        }else {
            this.root = child;
        }

        if (color == BLACK){
            removeFixUp(child, parent);
        }

        node = null;
    }

    public void remove(int data){
        RBTNode node = search(root,data);
        if(node != null){
            remove(node);
        }
    }

    /**
     * remove fix up
     * reshape the tree into a RBTree after remove
     */
    private void removeFixUp(RBTNode node, RBTNode parent){
        RBTNode anotherChild;

        while((node == null || isBlack(node)) && (node != this.root)){
            if(parent.left == node){
                anotherChild = parent.right;
                // case 1 : if node's brother is red
                if(isRed(anotherChild)){
                    setBlack(anotherChild);
                    setRed(parent);
                    leftRotation(parent);
                    anotherChild = parent.right;
                }

                // case 2 : node's brother is black and brother's children are black
                if ((anotherChild.left==null || isBlack(anotherChild.left)) &&
                        (anotherChild.right==null || isBlack(anotherChild.right))) {
                    setRed(anotherChild);
                    node = parent;
                    parent = parentOf(node);
                }else{
                    if (anotherChild.right==null || isBlack(anotherChild.right)) {
                        // case 3: node's brother is black with a red left child and a black right child
                        setBlack(anotherChild.left);
                        setRed(anotherChild);
                        rightRotation(anotherChild);
                        anotherChild = parent.right;
                    }
                    //case 4: node's brother is black with a red right child
                    setColor(anotherChild, colorOf(parent));
                    setBlack(parent);
                    setBlack(anotherChild.right);
                    leftRotation(parent);
                    node = this.root;
                    break;
                }
            }else{
                anotherChild = parent.left;
                // 4 cases are similar with code above
                if (isRed(anotherChild)) {
                    setBlack(anotherChild);
                    setRed(parent);
                    rightRotation(parent);
                    anotherChild = parent.left;
                }

                if ((anotherChild.left==null || isBlack(anotherChild.left)) &&
                        (anotherChild.right==null || isBlack(anotherChild.right))) {
                    setRed(anotherChild);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (anotherChild.left==null || isBlack(anotherChild.left)) {
                        setBlack(anotherChild.right);
                        setRed(anotherChild);
                        leftRotation(anotherChild);
                        anotherChild = parent.left;
                    }

                    setColor(anotherChild, colorOf(parent));
                    setBlack(parent);
                    setBlack(anotherChild.left);
                    rightRotation(parent);
                    node = this.root;
                    break;
                }
            }
        }

        if (node!=null)
            setBlack(node);
    }
}