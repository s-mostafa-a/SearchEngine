import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ahmadi on 12/11/16.
 */
public class Trie {
    FirstTrieNode firstTrieNode;
    public Trie(){
        firstTrieNode = new FirstTrieNode();
    }
    public void addToTrie(TrieWord word) {
        if (word.getWord() != null && !word.getWord().isEmpty())
            word.addToTree(firstTrieNode);
    }



    public void inOrderShow(){
        if (firstTrieNode == null)
            return;
        showInOrderOfSubTree(firstTrieNode);
        System.out.println("Number of total words: " + (wordNum(firstTrieNode)));
    }
    public void inOrderShow(JTextArea info){
        if (firstTrieNode == null)
            return;
        showInOrderOfSubTree(firstTrieNode, info);
        System.out.println("Number of total words: " + (wordNum(firstTrieNode)));
        info.setText("Number of total words: " + (wordNum(firstTrieNode)));
    }
    private void showInOrderOfSubTree(TrieNode rootOfSubTree, JTextArea info){
        for(int i = 0; i <= 25; i++)
            if(rootOfSubTree.children[i] != null){
                showInOrderOfSubTree(rootOfSubTree.children[i], info);
            }
        System.out.println(rootOfSubTree);
        info.setText(info.getText() + rootOfSubTree + "\n");
    }
    private void showInOrderOfSubTree(TrieNode rootOfSubTree){
        for(int i = 0; i <= 25; i++)
            if(rootOfSubTree.children[i] != null){
                showInOrderOfSubTree(rootOfSubTree.children[i]);
            }
        System.out.println(rootOfSubTree);
    }
    public int wordNum(TrieNode node){
        int n = 0;
        if(node == null)
            return 0;
        for(int i = 0; i <= 25; i++)
            n += wordNum(node.children[i]);
        if (node.getEnd())
            n++;
        return n;
    }
    public void showFiles(){
        ArrayList<String> files = new ArrayList<String>();
        inOrderShowFiles(firstTrieNode, files);
        int size = files.size();
        for (int i = 0; i < size; i++)
            System.out.printf(files.get(i) + "  ");
        System.out.println("");
    }
    public void showFiles(JTextArea info){
        info.setText("");
        ArrayList<String> files = new ArrayList<String>();
        inOrderShowFiles(firstTrieNode, files);
        int size = files.size();
        for (int i = 0; i < size; i++){
            System.out.printf(files.get(i) + "  ");
            info.setText(info.getText() + files.get(i) + "  ");
        }
        info.setText(info.getText() + "\n");
        System.out.println("");
    }
    private void inOrderShowFiles(TrieNode node, ArrayList<String> files){
        if (node == null)
            return;
        for (int i = 0; i <= 25; i++)
            if(node.getChild(i) != null)
                inOrderShowFiles(node.getChild(i), files);
        node.returnFiles(files);
    }




    private ArrayList<TrieNode> findNodesContaining(String file){
        ArrayList<TrieNode> array = new ArrayList<TrieNode>();
        finderOfNodesContaining(file, firstTrieNode, array);
        return array;
    }
    private void finderOfNodesContaining(String file, TrieNode node, ArrayList<TrieNode> array){
        if (node == null)
            return;
        if(node.checkFiles(file))
            array.add(node);
        for(int i = 0; i <= 25; i++)
        if(node.children[i] != null)
            finderOfNodesContaining(file, node.children[i], array);
    }
    public void deleteFile(String file){
        ArrayList<TrieNode> array = findNodesContaining(file);
        for (int index = 0; index <= array.size() - 1; index++){
            TrieNode node = array.get(index);
            node.deleteFile(file);
        }
        //for (int index = array.size() - 1; index >= 0; index--){
        //    TrieNode node = array.get(index);
        //    node.deleteIfNotExists();
        //}
        deleteNotExistingNodes(firstTrieNode);

    }
    public void deleteNotExistingNodes(TrieNode node){
        if (node == null)
            return;
        for (int i = 0; i <= 25; i++)
        if(node.children[i] != null)
            deleteNotExistingNodes(node.children[i]);
        node.deleteIfNotExists();
    }







    public void search (String word){
        if(firstTrieNode == null) {
            System.out.println("root is empty!");
            return;
        }
        TrieNode find = findNode(word, firstTrieNode);
        if(find != null)
            System.out.println(find);
        else
            System.out.println(word + " not found! \n");
    }
    public void search (String word, JTextArea info){
        if(firstTrieNode == null) {
            info.setText("root is empty!\n");
            System.out.println("root is empty!");
            return;
        }
        TrieNode find = findNode(word, firstTrieNode);
        if(find != null) {
            System.out.println(find);
            info.setText(info.getText() + find + "\n");
        }
        else {
            System.out.println(word + " not found! \n");
            info.setText(word + " not found!\n");
        }
    }
    private TrieNode findNode(String word, TrieNode rootOfSubTree){
        TrieNode node;
        if(rootOfSubTree.getEnd())
            if(rootOfSubTree.getWord().compareTo(word) == 0)
                return rootOfSubTree;
        for(int i = 0; i <= 25; i++)
        if(rootOfSubTree.children[i] != null){
            node = findNode(word, rootOfSubTree.children[i]);
            if (node != null)
                return node;
        }
        return null;
    }



}
