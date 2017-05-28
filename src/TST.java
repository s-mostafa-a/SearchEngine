import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ahmadi on 12/9/16.
 */
public class TST {
    FirstTSTNode firstTSTNode;
    public TST(){
        firstTSTNode = new FirstTSTNode();
    }
    public void addToTree(TSTWord word) {
        if (word.getWord() != null && !word.getWord().isEmpty())
            word.addToTree(firstTSTNode);
    }
    private ArrayList<TSTNode> findNodesContaining(String file){
        ArrayList<TSTNode> array = new ArrayList<TSTNode>();
        finderOfNodesContaining(file, firstTSTNode.getRoot(), array);
        return array;
    }
    private void finderOfNodesContaining(String file, TSTNode node, ArrayList<TSTNode> array){
        if (node == null)
            return;
        if(node.checkFiles(file))
            array.add(node);
        if(node.getLeftChild() != null)
            finderOfNodesContaining(file, node.getLeftChild(), array);
        if(node.getMiddleChild() != null)
            finderOfNodesContaining(file, node.getMiddleChild(), array);
        if(node.getRightChild() != null)
            finderOfNodesContaining(file, node.getRightChild(), array);
    }
    public void deleteFile(String file){
        ArrayList<TSTNode> array = findNodesContaining(file);
        for (int index = 0; index <= array.size() - 1; index++){
            TSTNode node = array.get(index);
            node.deleteFile(file);
        }
        //for (int index = array.size() - 1; index >= 0; index--){
        //    TSTNode node = array.get(index);
        //    node.deleteIfNotExists();
        //}
        deleteNotExistingNodes(firstTSTNode.getRoot());
    }
    public void deleteNotExistingNodes(TSTNode node){
        if (node == null)
            return;
        if(node.getLeftChild() != null)
            deleteNotExistingNodes(node.getLeftChild());
        if(node.getMiddleChild() != null)
            deleteNotExistingNodes(node.getMiddleChild());
        if(node.getRightChild() != null)
            deleteNotExistingNodes(node.getRightChild());
        node.deleteIfNotExists();
    }
    public ArrayList<String> showFiles(){
        ArrayList<String> files = new ArrayList<String>();
        inOrderShowFiles(firstTSTNode.getRoot(), files);
        int size = files.size();
        for (int i = 0; i < size; i++)
            System.out.printf(files.get(i) + "  ");
        System.out.println("");
        return files;
    }
    public ArrayList<String> showFiles(JTextArea info){
        ArrayList<String> files = new ArrayList<String>();
        info.setText("");
        inOrderShowFiles(firstTSTNode.getRoot(), files);
        int size = files.size();
        for (int i = 0; i < size; i++) {
            String s = files.get(i) + "  ";
            System.out.printf(s);
            info.setText(info.getText() + s + "\n");
        }
        System.out.println("");
        return files;
    }
    private void inOrderShowFiles(TSTNode node, ArrayList<String> files){
        if (node == null)
            return;
        if(node.getLeftChild() != null)
            inOrderShowFiles(node.getLeftChild(), files);
        if(node.getMiddleChild() != null)
            inOrderShowFiles(node.getMiddleChild(), files);
        node.returnFiles(files);
        if(node.getRightChild() != null)
            inOrderShowFiles(node.getRightChild(), files);
    }
    public void inOrderShow(){
        if (firstTSTNode.getRoot() == null)
            return;
        showInOrderOfSubTree(firstTSTNode.getRoot());
        System.out.println("Number of total words: " + (wordNum(firstTSTNode.getRoot())));
    }
    public void inOrderShow(JTextArea info){
        if (firstTSTNode.getRoot() == null)
            return;
        showInOrderOfSubTree(firstTSTNode.getRoot(), info);
        info.setText(info.getText() + "Number of total words: " + (wordNum(firstTSTNode.getRoot())));
        System.out.println("Number of total words: " + (wordNum(firstTSTNode.getRoot())));
    }
    public int wordNum(TSTNode node){
        int n = 0;
        if(node == null)
            return 0;
        n += wordNum(node.getLeftChild()) + wordNum(node.getRightChild()) + wordNum(node.getMiddleChild());
        if (node.getEnd())
            n++;
        return n;
    }
    private void showInOrderOfSubTree(TSTNode rootOfSubTree, JTextArea info){
        if(rootOfSubTree.getLeftChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getLeftChild(), info);

        System.out.println(rootOfSubTree);
        info.setText(info.getText() + rootOfSubTree + "\n");
        if(rootOfSubTree.getMiddleChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getMiddleChild(), info);
        if(rootOfSubTree.getRightChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getRightChild(), info);
    }
    private void showInOrderOfSubTree(TSTNode rootOfSubTree){
        if(rootOfSubTree.getLeftChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getLeftChild());

        System.out.println(rootOfSubTree);

        if(rootOfSubTree.getMiddleChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getMiddleChild());
        if(rootOfSubTree.getRightChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getRightChild());
    }
    public void search (String word){
        if(firstTSTNode.getRoot() == null) {
            System.out.println("root is empty!");
            return;
        }
        TSTNode find = findNode(word, firstTSTNode.getRoot());
        if(find != null)
            System.out.println(find);
        else
            System.out.println(word + " not found! \n");
    }
    public void search (String word, JTextArea info){
        if(firstTSTNode.getRoot() == null) {
            System.out.println("root is empty!");
            info.setText("root is empty!\n");
            return;
        }
        TSTNode find = findNode(word, firstTSTNode.getRoot());
        if(find != null){
            System.out.println(find);
            info.setText(info.getText() + find + "\n");
        }
        else {
            System.out.println(word + " not found!\n");
            info.setText(word + " not found!\n");
        }
    }
    private TSTNode findNode(String word, TSTNode rootOfSubTree){
        TSTNode node;
        if(rootOfSubTree.getEnd())
            if(rootOfSubTree.getWord().compareTo(word) == 0)
                return rootOfSubTree;
        if(rootOfSubTree.getMiddleChild() != null){
            node = findNode(word, rootOfSubTree.getMiddleChild());
            if (node != null)
                return node;
        }
        if(rootOfSubTree.getLeftChild() != null)
        {
            node = findNode(word, rootOfSubTree.getLeftChild());
            if (node != null)
                return node;
        }
        if(rootOfSubTree.getRightChild() != null)
        {
            node = findNode(word, rootOfSubTree.getRightChild());
            if (node != null)
                return node;
        }
        return null;
    }

}
