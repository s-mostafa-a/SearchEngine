import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ahmadi on 12/13/16.
 */
public class Terminal {
    BST bst;
    TST tst;
    Trie trie;
    Scanner scanner;
    Reader reader;
    public Terminal(){
        bst = new BST();
        tst = new TST();
        trie = new Trie();
        boolean exit = false, search =false, list = false, add =false, update = false;
        scanner = new Scanner(System.in);
        System.out.println("Type folder path:\n>>");
        reader = new Reader(scanner.next());
        //divides words
        while (!exit){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String input = scanner.next();
            if (input.compareTo("search") == 0){
                search(scanner.next());
            }
            else if (input.compareTo("exit") == 0){
                exit = true;
            }
            else if (input.compareTo("list") == 0){
                list(scanner.next());
            }
            else if (input.compareTo("add") == 0){
                add(scanner.next());
            }
            else if (input.compareTo("delete") == 0){
                delete(scanner.next());
            }
            else if (input.compareTo("update") == 0){
                update(scanner.next());
            }
            else
                System.err.println("Unknown input!");
        }
    }
    public void search(String kind){
        if(kind.compareTo("-w") == 0)
            searchWord(scanner.next());//word
        else if (kind.compareTo("-s") == 0){
            String sentence = scanner.nextLine();
            char[] chs = sentence.toCharArray();
            ArrayList<String> words = new ArrayList<String>();
            int length = sentence.length();
            String word = "";
            int index = 0;
            while (index < length){
                if(chs[index] == ' '){
                    if((word.compareTo("") != 0)&&(word.compareTo(" ") != 0))
                        words.add(word);
                    word = "";
                }
                else {
                    word = word + chs[index];
                    if((index == length - 1)&&(!word.isEmpty())){
                        words.add(word);
                    }
                }
                index ++;
            }
            int wordsNum = words.size();
            System.out.println("select tree type:\nbst\ntst\ntrie");
            String treeType = scanner.next();
            if (((treeType.compareTo("bst") !=0)&&(treeType.compareTo("tst") !=0))&&(treeType.compareTo("trie") !=0)) {
                System.out.println("Unknown tree type!");
                return;
            }
            for(int i = 0; i < wordsNum; i++){
                searchWord(words.get(i), treeType);
            }

        }
    }
    public void list(String kind){
        System.out.println("select tree type:\nbst\ntst\ntrie");
        String treeType = scanner.next();
        if (((treeType.compareTo("bst") !=0)&&(treeType.compareTo("tst") !=0))&&(treeType.compareTo("trie") !=0)) {
            System.out.println("Unknown tree type!");
            return;
        }
        long time = System.currentTimeMillis();
        if(kind.compareTo("-w") == 0){
            reader.showWords(treeType);
        }
        else if (kind.compareTo("-f") == 0)
            reader.showFiles(treeType);
        else
            System.err.println("Unknown list type!");
        System.out.println((double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
    }
    public void add(String fileName){
        long time = System.currentTimeMillis();
        reader.addNewFile(fileName);
        System.out.println((double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
    }
    public void update(String fileName){
        long time = System.currentTimeMillis();
        delete(fileName);
        add(fileName);
        System.out.println((double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
    }
    public void delete(String fileName){
        long time = System.currentTimeMillis();
        reader.removeFile(fileName);
        System.out.println((double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
    }
    private void searchWord(String word, String treeType){
        long time = System.currentTimeMillis();
        reader.search(word.toLowerCase(), treeType);
        System.out.println((double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
    }
    private void searchWord(String word){
        System.out.println("select tree type:\nbst\ntst\ntrie");
        String treeType = scanner.next();
        if (((treeType.compareTo("bst") !=0)&&(treeType.compareTo("tst") !=0))&&(treeType.compareTo("trie") !=0)) {
            System.out.println("Unknown tree type!");
            return;
        }
        long time = System.currentTimeMillis();
        reader.search(word.toLowerCase(), treeType);
        System.out.println((double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
    }
}
