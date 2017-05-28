import java.util.ArrayList;

/**
 * Created by ahmadi on 12/9/16.
 */
public class TrieWord {
    private String word;
    private LocationOfTrie location;
    private ArrayList<Character> chars;
    public TrieWord(String word, String fileName, String summary, int lineNumber){
        this.word = word;
        chars = new ArrayList<Character>();
        location = new LocationOfTrie();
        location.addResource(this.getWord(), fileName, summary, lineNumber);
    }
    public String getWord(){
        return word;
    }
    private void sliceWord(){
        int length = word.length();
        for (int i = 0; i < length; i++)
            chars.add(word.charAt(i));
    }
    private String getCharForNumber(int i) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        if (i > 25) {
            return null;
        }
        return Character.toString(alphabet[i]);
    }
    private int getNumberForChar(char ch){
        int n = (int) ch;
        if ((n < 97)||(n > 122)) {
            System.err.println("Unknowing char!");
            return -1;
        }
        return n - 97;
    }
    public LocationOfTrie getLocation(){
        return location;
    }
    public void addToTree(FirstTrieNode firstTSTNode){
        sliceWord();
        TrieNode iteratorNode = firstTSTNode;
        for(int  i = 0; i < chars.size(); i++){
            if (iteratorNode.getChild(getNumberForChar(chars.get(i))) == null){
                TrieNode node = new TrieNode(chars.get(i));
                iteratorNode.setChild(node, getNumberForChar(chars.get(i)));
                node.setFather(iteratorNode);
                iteratorNode = node;
            }
            else
                iteratorNode = iteratorNode.getChild(getNumberForChar(chars.get(i)));
            iteratorNode.addResource(this.location);
            if (i == chars.size() - 1)
                iteratorNode.setEnd(true, word);

        }
    }
}
