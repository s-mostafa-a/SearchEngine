/**
 * Created by ahmadi on 12/7/16.
 */
public class FirstBSTNode extends BSTNode {
    private BSTNode child;
    public FirstBSTNode(){
        super(null, null, null, 0);
        setFather(null);
        setLeftChild(null);
        setRightChild(null);
        child = null;
    }
    public void setRoot(BSTNode node){
        child = node;
    }
    public BSTNode getRoot(){
        return child;
    }
}
