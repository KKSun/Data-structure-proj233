public class superTest {
    public static void main(String[] args){
        BSTree bst = new BSTree();
        for(int i = 0; i < 10000; i++){
            bst.insert(i);
        }

        bst.preOrder();
    }
}
