import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ahmadi on 12/4/16.
 */
public class BSTNode {
    private String data;
    private BSTNode rightChild, leftChild;
    private BSTNode father;
    private LocationOfBST location = new LocationOfBST();
    private JTextArea info;
    public BSTNode(String data, String fileName, String summary, int lineNumber){
        this.data = data;
        leftChild = null;
        rightChild = null;
        location.addResource(fileName, summary, lineNumber);
    }
    public String getData(){
        return data;
    }
    //derakht
    public void setFather(BSTNode node){
        father = node;
    }
    public void setRightChild(BSTNode node){
        rightChild = node;
        if(node != null)
            rightChild.setFather(this);
    }
    public void setLeftChild(BSTNode node){
        leftChild = node;
        if(node != null)
            leftChild.setFather(this);
    }
    public BSTNode getFather(){
        return father;
    }
    public BSTNode getRightChild(){
        return rightChild;
    }
    public BSTNode getLeftChild(){
        return leftChild;
    }
    //list
    public void addResource(String fileName, String summary, int lineNumber){
        location.addResource(fileName, summary, lineNumber);
    }
    public LocationOfBST getLocation(){
        return location;
    }
    public void addResource(LocationOfBST l){
        location.merge(l);
    }
    public void deleteFile(String fileName){
        location.deleteFile(fileName);
        if(location.getFileNames().size() == 0)
           deleteNode();
    }
    private void deleteNode(){

        /// rishe
        BSTNode n;
        if(this.getFather() instanceof FirstBSTNode){
            if(this.getLeftChild() != null) {
                n = this.getNearestLeft();
                if(n != this.getLeftChild())
                    n.setLeftChild(this.getLeftChild());
                if(n != this.getRightChild())
                    n.setRightChild(this.getRightChild());
            }
            else if (this.getRightChild() != null){
                n = this.getNearestRight();
                if(n != this.getLeftChild())
                    n.setLeftChild(this.getLeftChild());
                else

                if(n != this.getRightChild())
                    n.setRightChild(this.getRightChild());
            }
            else {
                System.out.println("BST became null!");
                return;
            }
            ((FirstBSTNode) this.getFather()).setRoot(n);
            n.setFather(this.getFather());
            return;
        }
        /// barg
        else if ((this.getLeftChild() == null)&&(this.getRightChild() == null)){
            if(this.getFather().getLeftChild() == this){
                this.getFather().setLeftChild(null);
                this.setFather(null);
            }
            else if (this.getFather().getRightChild() == this){
                this.getFather().setRightChild(null);
                this.setFather(null);
            }
            else
                System.err.println("WTF");
            return;
        }
        // miani tak bache
        else if((getLeftChild() == null)&&(getRightChild() != null)){
            if (this == getFather().getRightChild()){
                getFather().setRightChild(this.getRightChild());
                getRightChild().setFather(getFather());
                this.setFather(null);
            }
            else if(this == getFather().getLeftChild()){
                getFather().setLeftChild(this.getRightChild());
                getRightChild().setFather(getFather());
                this.setFather(null);
            }

        }
        else if ((getLeftChild() != null)&&(getRightChild() == null)){
            if (this == getFather().getRightChild()){
                getFather().setRightChild(this.getLeftChild());
                getLeftChild().setFather(getFather());
                this.setFather(null);
            }
            else if(this == getFather().getLeftChild()){
                getFather().setLeftChild(this.getLeftChild());
                getLeftChild().setFather(getFather());
                this.setFather(null);
            }


        }
        /// miani por bache
        else {
            n = getNearestLeft();
            if(n != this.getLeftChild())
                n.setLeftChild(this.getLeftChild());
            if(n != this.getRightChild())
                n.setRightChild(this.getRightChild());
            //setting
            if(this.getFather().getLeftChild() == this)
                this.getFather().setLeftChild(n);
            else
                this.getFather().setRightChild(n);
            n.setFather(this.getFather());
        }
    }

    private BSTNode getNearestRight(){
        BSTNode itteratorNode, previous;
        itteratorNode = this.getRightChild();
        previous = null;
        while(itteratorNode != null){
            previous = itteratorNode;
            itteratorNode = itteratorNode.getLeftChild();
        }
        if(previous.getRightChild() != null){
            if (previous.getFather().getLeftChild() == previous)
                previous.getFather().setLeftChild(previous.getRightChild());
            else
                previous.getFather().setRightChild(previous.getRightChild());
        }
        else{
            if (previous.getFather().getLeftChild() == previous)
                previous.getFather().setLeftChild(null);
            else
                previous.getFather().setRightChild(null);
        }
        return previous;
    }
    private BSTNode getNearestLeft(){
        BSTNode itteratorNode, previous;
        itteratorNode = this.getLeftChild();
        previous = null;
        while(itteratorNode != null){
            previous = itteratorNode;
            itteratorNode = itteratorNode.getRightChild();
        }
        if(previous.getLeftChild() != null){
            if (previous.getFather().getLeftChild() == previous)
                previous.getFather().setLeftChild(previous.getLeftChild());
            else
                previous.getFather().setRightChild(previous.getLeftChild());
        }
        else{
            if (previous.getFather().getLeftChild() == previous)
                previous.getFather().setLeftChild(null);
            else
                previous.getFather().setRightChild(null);
        }
        return previous;
    }
    public void returnFiles(ArrayList<String> files){
        int size = location.getFileNames().size();
        for (int i = 0; i < size; i++){
            if(!files.contains(location.getFileName(i))){
                files.add(location.getFileName(i));
            }
        }
    }
    public String toString(){
        if(this.location.getFileNames().size() == 0)
            return "";
        return data + "\n" + location.toString();
    }

}
