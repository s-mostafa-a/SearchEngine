/**
 * Created by ahmadi on 12/9/16.
 */
public class FirstTSTNode extends TSTNode {
    private TSTNode child;
    public FirstTSTNode(){
        super(' ');
        child = null;
    }
    public void setRoot(TSTNode node){
        child = node;
    }
    public TSTNode getRoot(){
        return child;
    }
}