import java.util.ArrayList;

/**
 * Created by ahmadi on 12/9/16.
 */
public class TrieNode {
    protected TrieNode father;
    private char ch;
    private boolean end;
    private String word;
    private LocationOfTrie location;
    protected TrieNode[] children;
    public TrieNode(){
    }
    public TrieNode(Character character){
        children = new TrieNode[26];
        for(int i = 0; i <= 25; i++)
            children[i] = null;
        ch = character;
        end = false;
        location = new LocationOfTrie();
    }
    public void addResource(LocationOfTrie location){
        this.location.merge(location);
    }
    public boolean hasSource(){
        if(location.getFileNames().size() == 0)
            return false;
        return true;
    }
    public void setEnd(boolean b, String word){
        end = b;
        this.word = word;
    }
    public void setFather(TrieNode father){
        this.father = father;
    }
    public TrieNode getFather(){
        return father;
    }
    public void setChild(TrieNode node, int index){
        children[index] = node;
    }
    public TrieNode getChild(int index){
        if ((index > 25)||(index < 0)){
            System.err.println("out of index in children!");
            return null;
        }
        return children[index];
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
        int size = location.getFileNames().size();
        for (int i = 0; i < size; i++){
            if(!files.contains(location.getFileName(i))){
                files.add(location.getFileName(i));
            }
        }
    }
    public boolean checkFiles(String file){
        int size = location.getFileNames().size();
        for (int i = 0; i < size; i++){
            if(file.compareTo(location.getFileName(i)) == 0){
                return true;
            }
        }
        return false;
    }
    public void deleteFile(String file){/*
        int size = location.getFileNames().size();
        for(int i = 0; i < size; i++)
            if(location.getFileNames().get(i).compareTo(file) == 0){
                location.getLineNumbers().remove(i);
                location.getFileNames().remove(i);
                location.getSummary().remove(i);
                location.getWord().remove(i);
                size--;
                i--;
            }
            */
        location.deleteFile(file);
    }
    public void deleteIfNotExists(){
        if(location.getFileNames().size() == 0){
            for(int i = 0; i <= 25; i++)
                if(children[i] != null)
                    return;
            terminate();}
    }
    private int getNumberForChar(char ch){
        int n = (int) ch;
        if ((n < 97)||(n > 122)) {
            System.err.println("Unknowing char!");
            return -1;
        }
        return n - 97;
    }
    public void terminate(){
        if(father == null)
            return;
        father.children[getNumberForChar(ch)] = null;
        this.father = null;

    }
    public String toString() {
        if(end == true)
            return word + "\n" + location.toString(word);
        return "";
    }
}
