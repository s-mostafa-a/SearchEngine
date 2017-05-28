import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ahmadi on 12/7/16.
 */
public class BST {
    private static FirstBSTNode firstNode;
    public BST(){
        firstNode = new FirstBSTNode();
    }
    public void addToTree(BSTNode node){
        int flag = 0;
        BSTNode previous = null;
        if(firstNode.getRoot() == null){
            firstNode.setRoot(node);
            node.setFather(firstNode);
            return;
        }
        BSTNode iteratorNode = firstNode.getRoot();
        while (iteratorNode != null){
            previous = iteratorNode;
            if (iteratorNode.getData().compareToIgnoreCase(node.getData()) < 0)
            {
                iteratorNode = iteratorNode.getRightChild();
                flag = 1;
            }
            else if (iteratorNode.getData().compareToIgnoreCase(node.getData()) > 0)
            {
                iteratorNode = iteratorNode.getLeftChild();
                flag = 2;
            }
            else{
                iteratorNode.addResource(node.getLocation());
                flag = 3;
                break;
            }
        }
        if (flag == 1) {
            previous.setRightChild(node);
            node.setFather(previous);
        }
        else if (flag == 2) {
            previous.setLeftChild(node);
            node.setFather(previous);
        }
        else if (flag == 3)
            ;//do nothing
    }
    public void showFiles(){
        ArrayList<String> files = new ArrayList<String>();
        inOrderShowFiles(firstNode.getRoot(), files);
        int size = files.size();
        for (int i = 0; i < size; i++)
            System.out.printf(files.get(i) + "  ");
        System.out.println("");
    }
    public void showFiles(JTextArea info){
        info.setText("");
        ArrayList<String> files = new ArrayList<String>();
        inOrderShowFiles(firstNode.getRoot(), files);
        int size = files.size();
        for (int i = 0; i < size; i++){
            System.out.printf(files.get(i) + "  ");
            info.setText(info.getText() + files.get(i) + "\n");
        }
        System.out.println("");
    }
    public int wordNum(BSTNode node){
        int n = 0;
        if(node == null)
            return 0;
        n += wordNum(node.getLeftChild()) + wordNum(node.getRightChild());
        n++;
        return n;
    }
    private void inOrderShowFiles(BSTNode node, ArrayList<String> files){
        if (node == null)
            return;
        if(node.getLeftChild() != null)
            inOrderShowFiles(node.getLeftChild(), files);
        node.returnFiles(files);
        if(node.getRightChild() != null)
            inOrderShowFiles(node.getRightChild(), files);
    }
    public void removeFromTree(String fileName){
        if(firstNode.getRoot() == null){
            System.out.println("Root not found to delete this file!");
            return;
        }
        removeFile(firstNode.getRoot(), fileName);
    }
    public void removeFromTree(String fileName, JTextArea info){
        if(firstNode.getRoot() == null){
            System.out.println("Root not found to delete this file!");
            info.setText(info.getText() + "Root not found to delete this file!");
            return;
        }
        removeFile(firstNode.getRoot(), fileName);
    }
    private void removeFile(BSTNode rootOfSubTree, String fileName){
        if(rootOfSubTree == null)
            return;
        if(rootOfSubTree.getLeftChild() != null)
            removeFile(rootOfSubTree.getLeftChild(), fileName);
        rootOfSubTree.deleteFile(fileName);
        if(rootOfSubTree.getRightChild() != null)
            removeFile(rootOfSubTree.getRightChild(), fileName);
    }
    public void inOrderShow(){
        if (firstNode.getRoot() == null)
            return;
        showInOrderOfSubTree(firstNode.getRoot());
        System.out.println("Number of total words: " + (wordNum(firstNode.getRoot())));
    }
    public void inOrderShow(JTextArea info){
        if (firstNode.getRoot() == null)
            return;
        showInOrderOfSubTree(firstNode.getRoot(), info);
        String s =String.format("Number of total words: " + (wordNum(firstNode.getRoot())));
        if(info != null)
            info.setText(info.getText() + s + "\n");
    }
    public void search(String word){
        BSTNode find = find(firstNode.getRoot(), word);
        if(find != null){
            System.out.println(find);

        }
        else
            System.out.println(word + " not found!");
    }
    public void search(String word, JTextArea info){
        BSTNode find = find(firstNode.getRoot(), word);
        if(find != null){
            System.out.println(find);
            info.setText(info.getText() + find + "\n");
        }
        else{
            System.out.println(word + " not found!");
            info.setText(word + " not found!" + "\n");
        }
    }
    public BSTNode find(BSTNode node, String word){
        if (node == null)
            return null;
        if (word.compareTo(node.getData()) == 0)
            return node;
        if(node.getData().compareTo(word) > 0)
            return find(node.getLeftChild(), word);
        else if (node.getData().compareTo(word) < 0)
            return find(node.getRightChild(), word);
        else
            return null;
    }
    private void showInOrderOfSubTree(BSTNode rootOfSubTree){
        if (rootOfSubTree == null)
            return;
        if(rootOfSubTree.getLeftChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getLeftChild());
        System.out.println(rootOfSubTree);
        if(rootOfSubTree.getRightChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getRightChild());
    }
    private void showInOrderOfSubTree(BSTNode rootOfSubTree, JTextArea info){
        if (rootOfSubTree == null)
            return;
        if(rootOfSubTree.getLeftChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getLeftChild());
        System.out.println(rootOfSubTree);
        info.setText(info.getText() + rootOfSubTree + "\n");
        if(rootOfSubTree.getRightChild() != null)
            showInOrderOfSubTree(rootOfSubTree.getRightChild());
    }

}
