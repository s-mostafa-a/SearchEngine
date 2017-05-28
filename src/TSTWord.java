import java.util.ArrayList;

/**
 * Created by ahmadi on 12/9/16.
 */
public class TSTWord {
    private String word;
    private LocationOfTST location;
    private ArrayList<Character> chars;
    public TSTWord(String word, String fileName,String summary, int lineNumber){
        this.word = word;
        chars = new ArrayList<Character>();
        location = new LocationOfTST();
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
    public LocationOfTST getLocation(){
        return location;
    }
    public void addToTree(FirstTSTNode firstTSTNode){
        sliceWord();
        if (firstTSTNode.getRoot() == null){
            TSTNode node = new TSTNode(chars.get(0));
            node.addResource(this.getLocation());
            firstTSTNode.setRoot(node);
            node.setFather(firstTSTNode);
            TSTNode iteratorNode = node;
            if(chars.size() == 1)
                node.setEnd(true, this.getWord());
            if(chars.size() > 1)
                for (int i = 1; i < chars.size(); i++) {
                    TSTNode n = new TSTNode(chars.get(i));
                    n.addResource(this.getLocation());
                    iteratorNode.setMiddleChild(n);
                    n.setFather(iteratorNode);
                    if(i == chars.size() - 1)
                        iteratorNode.setEnd(true, this.getWord());
                    iteratorNode = iteratorNode.getMiddleChild();
                }

            return;
        }
        TSTNode iteratorNode = firstTSTNode.getRoot();
        TSTNode previous = null;
        int index = 0;
        int flag = 0;
        while (index < chars.size()){
            if(iteratorNode == null){
                iteratorNode = new TSTNode(chars.get(index));
                if(flag == 1)
                    previous.rightChild = iteratorNode;
                else if (flag == 2)
                    previous.leftChild = iteratorNode;
                else if (flag == 3)
                    previous.middleChild = iteratorNode;
                iteratorNode.setFather(previous);
            }
            previous = iteratorNode;
            if(chars.get(index).compareTo(iteratorNode.getChar()) < 0){
                iteratorNode = iteratorNode.rightChild;
                flag = 1;
            }
            else if(chars.get(index).compareTo(iteratorNode.getChar()) > 0){
                iteratorNode = iteratorNode.leftChild;
                flag = 2;
            }
            else if (chars.get(index).compareTo(iteratorNode.getChar()) == 0) {
                iteratorNode.addResource(this.getLocation());
                iteratorNode = iteratorNode.middleChild;
                index++;
                flag = 3;
            }
        }
        previous.setEnd(true, this.getWord());
    }
}
