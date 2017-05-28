import java.util.ArrayList;

/**
 * Created by ahmadi on 12/7/16.
 */
public class LocationOfBST {
    private ArrayList<String> fileName;
    private ArrayList<String> summary;
    private ArrayList<Integer> lineNumber;
    public LocationOfBST(){
        fileName = new ArrayList<String>();
        summary = new ArrayList<String>();
        lineNumber = new ArrayList<Integer>();
    }
    public void addResource(String fileName, String summary, int lineNumber){
        this.fileName.add(fileName);
        this.summary.add(summary);
        this.lineNumber.add(new Integer(lineNumber));
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
    public int getLineNumber(int indexNumber){
        return lineNumber.get(indexNumber);
    }
    public ArrayList<Integer> getLineNumbers(){
        return lineNumber;
    }
    public void merge(LocationOfBST l){
        int j = l.getFileNames().size();
        for(int i = 0; i < j; i++){
            fileName.add(l.getFileName(i));
            summary.add(l.getSummary(i));
            lineNumber.add(l.getLineNumber(i));
        }
    }
    public void deleteFile(String fileName){
        if(this.fileName.isEmpty())
            return;
        int size = this.fileName.size();
        for(int i = 0; i < size; i++)
            if(this.fileName.get(i).compareTo(fileName) == 0){
                this.fileName.remove(i);
                summary.remove(i);
                lineNumber.remove(i);
                size--;
                i--;
            }
    }
    public String toString(){
        String s = "";
        if(fileName.isEmpty())
           return s;
        int size = fileName.size();
        for(int i = 0; i < size; i++){
            s = s + fileName.get(i) + "  " + Integer.toString(lineNumber.get(i)) + "  " + summary.get(i) + "\n";
        }
        return s;
    }
}
