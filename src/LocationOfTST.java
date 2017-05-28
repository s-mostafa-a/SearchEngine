import java.util.ArrayList;

/**
 * Created by ahmadi on 12/7/16.
 */
public class LocationOfTST {
    private ArrayList<String> fileName;
    private ArrayList<String> summary;
    private ArrayList<Integer> lineNumber;
    private ArrayList<String>words;
    private String endWord;
    public LocationOfTST(){
        fileName = new ArrayList<String>();
        summary = new ArrayList<String>();
        lineNumber = new ArrayList<Integer>();
        words = new ArrayList<String>();
    }
    public void addResource(String word,String fileName, String summary, int lineNumber){
        this.fileName.add(fileName);
        this.summary.add(summary);
        this.lineNumber.add(new Integer(lineNumber));
        this.words.add(word);
    }
    public String getSummary(int indexNumber){
        return summary.get(indexNumber);
    }
    public ArrayList<String> getSummary(){
        return summary;
    }
    public String getFileName(int indexNumber){
        return fileName.get(indexNumber);
    }
    public ArrayList<String> getFileNames(){
        return fileName;
    }
    public int getLineNumber(int indexNumber) {
        return lineNumber.get(indexNumber);
    }
    public String getWords(int indexNumber){
        return words.get(indexNumber);
    }
    public ArrayList<String> getWord(){
        return words;
    }
    public ArrayList<Integer> getLineNumbers(){
        return lineNumber;
    }
    public void merge(LocationOfTST l){
        int j = l.getFileNames().size();
        for(int i = 0; i < j; i++){
            fileName.add(l.getFileName(i));
            summary.add(l.getSummary(i));
            lineNumber.add(l.getLineNumber(i));
            words.add(l.getWords(i));
        }
    }
    public void deleteFile(String fileName){
        if(this.fileName.isEmpty())
            return;
        int size = this.fileName.size();
        for(int i = 0; i < size; i++)
            if(this.fileName.get(i).compareTo(fileName) == 0){
                words.remove(i);
                this.fileName.remove(i);
                summary.remove(i);
                lineNumber.remove(i);
                size--;
                i--;
            }
    }
    public String toString(String word){
        String s = "";
        int size = fileName.size();
        for(int i = 0; i < size; i++){
            if (word.compareTo(words.get(i)) == 0)
                s = s + fileName.get(i) + "  " + Integer.toString(lineNumber.get(i)) + "  " + summary.get(i) + "\n";
        }
        if(s == "")
            s = "\nThis word does not exist!\nIt is in tree just for holding tree's shape!\n";
        return s;
    }
}