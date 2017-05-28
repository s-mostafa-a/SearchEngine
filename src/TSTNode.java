import java.util.ArrayList;

/**
 * Created by ahmadi on 12/9/16.
 */
public class TSTNode {
    public TSTNode rightChild, leftChild, middleChild, father;
    private char ch;
    private boolean end;
    private String word;
    private LocationOfTST locationOfTST;
    public TSTNode(Character character){
        rightChild = null;
        leftChild = null;
        middleChild = null;
        ch = character;
        end = false;
        locationOfTST = new LocationOfTST();
    }
    public void addResource(LocationOfTST locationOfTST){
        this.locationOfTST.merge(locationOfTST);
    }
    public boolean hasSource(){
        if(locationOfTST.getFileNames().size() == 0)
            return false;
        return true;
    }
    public void setEnd(boolean b, String word){
        end = b;
        this.word = word;
    }
    public void setFather(TSTNode father){
        this.father = father;
    }
    public TSTNode getFather(){
        return father;
    }
    public void setRightChild(TSTNode node){
        rightChild = node;
    }
    public void setMiddleChild(TSTNode node){
        middleChild = node;
    }
    public void setLeftChild(TSTNode node){
        leftChild = node;
    }
    public TSTNode getRightChild(){
        return rightChild;
    }
    public TSTNode getMiddleChild(){
        return middleChild;
    }
    public TSTNode getLeftChild(){
        return leftChild;
    }
    public char getChar(){
        return ch;
    }
    public boolean getEnd(){
        return end;
    }
    public String getWord(){
        return word;
    }
    public void returnFiles(ArrayList<String> files){
        int size = locationOfTST.getFileNames().size();
        for (int i = 0; i < size; i++){
            if(!files.contains(locationOfTST.getFileName(i))){
                files.add(locationOfTST.getFileName(i));
            }
        }
    }
    public boolean checkFiles(String file){
        int size = locationOfTST.getFileNames().size();
        for (int i = 0; i < size; i++){
            if(file.compareTo(locationOfTST.getFileName(i)) == 0){
                return true;
            }
        }
        return false;
    }
    public void deleteFile(String file){/*
        int size = locationOfTST.getFileNames().size();
        for(int i = 0; i < size; i++)
            if(locationOfTST.getFileNames().get(i).compareTo(file) == 0){
                locationOfTST.getLineNumbers().remove(i);
                locationOfTST.getFileNames().remove(i);
                locationOfTST.getSummary().remove(i);
                locationOfTST.getWord().remove(i);
                size--;
                i--;
            }
    */
        locationOfTST.deleteFile(file);
    }
    public void deleteIfNotExists(){
        if(locationOfTST.getFileNames().size() == 0){
            if(leftChild != null)
                return;
            if(rightChild != null)
                return;
            terminate();
        }
    }
    public void terminate(){
        if(father == null)
            return;
        if(father.middleChild == this)
            father.middleChild = null;
        else if(father.rightChild == this)
            father.rightChild = null;
        else if (father.leftChild == this)
            father.leftChild = null;
        else ;
        //System.err.println("in bacheye babash nist!");
        this.father = null;

    }
    public String toString() {
        if(end == true)
            return word + "\n" + locationOfTST.toString(word);
        return "";
    }
}
