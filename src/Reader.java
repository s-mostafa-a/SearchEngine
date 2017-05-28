import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ahmadi on 12/13/16.
 */ public class Reader {
    TST tst;
    BST bst;
    Trie trie;
    String path, treeType;
    ArrayList<String> stopWordRead;
    JTextArea info;
    public Reader(String path){
        tst = new TST();
        bst = new BST();
        trie = new Trie();
        this.path = path;
        long time = System.currentTimeMillis();
        stopWordRead = addFile("StopWords.txt");
        addFiles(path);
        System.out.println(((double)(System.currentTimeMillis() - time) / 1000.0) + " s");
    }
    public Reader(String path, JTextArea info){
        tst = new TST();
        bst = new BST();
        trie = new Trie();
        this.path = path;
        long time = System.currentTimeMillis();
        stopWordRead = addFile("StopWords.txt");
        addFiles(path);
        String s = String.format(((double)(System.currentTimeMillis() - time) / 1000.0) + " s");
        info.setText(info.getText() + s);
        this.info = info;
    }
    public Reader(){
        tst = new TST();
        bst = new BST();
        trie = new Trie();
        long time = System.currentTimeMillis();
        path = "/Users/ahmadi/Desktop/___DS____/docs";
        stopWordRead = addFile("StopWords.txt");
        addFiles(path);
        System.out.println(((double)(System.currentTimeMillis() - time) / 1000.0) + " s");
    }
    private ArrayList<String> addFile(String fileName){

        int line = 0;
        String path = this.path + "/" + fileName;
        boolean flag = false;
        if(path.compareTo("/Users/ahmadi/Desktop/___DS____/docs/StopWords.txt") == 0)
            flag = true;
        ArrayList<String> arrayList = new ArrayList<String>();
        String temp = "";
        try {
            FileReader fileReader = new FileReader(String.format(path));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String r;
            while ((r = bufferedReader.readLine()) != null) {
                line++;
                Scanner scan = new Scanner(r);
                while (scan.hasNext()){
                    temp = scan.next();
                    temp = temp.toLowerCase();
                    if(!flag){
                        ///////////////////////////////////////////////////////////////////words
                        if(!stopWordRead.contains(temp)){
                            boolean f = false;
                            char[] chars = temp.toCharArray();
                            for(int i = 0; i < temp.length(); i++)
                                if ((chars[i] < 97) || (chars[i] > 122))
                                    f = true;
                            if(!f){
                            arrayList.add(temp);
                            TSTWord tstWord = new TSTWord(temp, fileName, r, line);
                            BSTNode bstNode = new BSTNode(temp, fileName, r, line);
                            TrieWord trieWord = new TrieWord(temp, fileName, r, line);
                            tst.addToTree(tstWord);
                            bst.addToTree(bstNode);
                            trie.addToTrie(trieWord);
                            //System.out.println(temp);            shows words of files

                            }
                        }
                    }
                    else{
                        arrayList.add(temp);
                        //System.out.println(temp);             shows stop words
                    }

                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            if (info != null)
                info.setText("Unable to open file");
            System.err.println("Unable to open file");
        } catch (IOException ex) {
            if (info != null)
            info.setText("Error reading file");
            System.err.println("Error reading file");
        }
        return arrayList;
    }
    private void addFiles(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
            if (listOfFiles[i].isFile()) {
                addFile(listOfFiles[i].getName());
            }
    }

    public void setTree(String tree){
        treeType = tree;
    }
    public String print(){
        return "";
    }
    public void setPath(String path){
        this.path = path;
    }
    public void search(String word ,String treeType){
        if(treeType.compareTo("tst") ==0){
            tst.search(word);
            if (info != null)
                tst.search(word, info);
        }
        else if(treeType.compareTo("bst") ==0){
            bst.search(word);
            if (info != null)
                bst.search(word, info);
        }
        else{
            trie.search(word);
            if (info != null)
                trie.search(word, info);
        }
    }
    public void showWords(String treeType){
        if(treeType.compareTo("bst") ==0){
            System.out.println("BST ------------> \n");
            bst.inOrderShow();
            if(info != null)
                bst.inOrderShow(info);
        }
        else if (treeType.compareTo("tst") ==0) {
            System.out.println("TST ------------> \n");
            tst.inOrderShow();
            if(info != null)
                tst.inOrderShow(info);
        }
        else{
            System.out.println("Trie ------------> \n");
            trie.inOrderShow();
            if(info != null)
                trie.inOrderShow(info);
        }
    }
    public void showFiles(String treeType){
        if (treeType.compareTo("tst") ==0) {
            System.out.println("TST ------------> \n");
            if(info != null)
                tst.showFiles(info);
            tst.showFiles();}
        else if (treeType.compareTo("bst") ==0) {
            System.out.println("BST ------------> \n");
            bst.showFiles();
            if(info != null)
                bst.showFiles(info);
        }
        else {
            System.out.println("Trie ------------> \n");
            trie.showFiles();
            if(info != null)
                trie.showFiles(info);
        }
    }
    public void addNewFile(String fileName){
        if (tst.showFiles().contains(fileName)){
            if(info != null)
                info.setText("File already exists!\n");
            System.err.println("File exists!\n");
        }
        else{
            addFile(fileName);
            if(info != null)
                info.setText(fileName + " successfully added!\n");
            System.out.println("File : " + fileName +" successfully added!");
        }
    }
    public void removeFile(String fileName){
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (!tst.showFiles().contains(fileName)){
            if(info != null)
                info.setText("File :" + fileName + " does not exist!\n");
            System.err.println("File : " + fileName +" does not exist!");
        }
        else{
            tst.deleteFile(fileName);
            bst.removeFromTree(fileName);
            trie.deleteFile(fileName);
            if (tst.showFiles().contains(fileName)){
                System.err.println("Could't delete file successfully!");
                if(info != null)
                    info.setText("Could not delete successfully!\n");
            }
            else {
                System.err.println("deleted file successfully!");
                if(info != null)
                    info.setText("deleted file successfully!\n");

            }
        }
    }
    public TST getTst(){return tst;}
    public BST getBst(){return bst;}
    public Trie getTrie(){return trie;}
}
