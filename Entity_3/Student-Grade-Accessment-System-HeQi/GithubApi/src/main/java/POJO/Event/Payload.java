package POJO.Event;
import java.util.List;
public class Payload {
    private int push_id;

    private int size;

    private int distinct_size;

    private String ref;

    private String head;

    private String before;

    private List<Commits> commits ;

    public void setPush_id(int push_id){
        this.push_id = push_id;
    }
    public int getPush_id(){
        return this.push_id;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
    public void setDistinct_size(int distinct_size){
        this.distinct_size = distinct_size;
    }
    public int getDistinct_size(){
        return this.distinct_size;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRef(){
        return this.ref;
    }
    public void setHead(String head){
        this.head = head;
    }
    public String getHead(){
        return this.head;
    }
    public void setBefore(String before){
        this.before = before;
    }
    public String getBefore(){
        return this.before;
    }
    public void setCommits(List<Commits> commits){
        this.commits = commits;
    }
    public List<Commits> getCommits(){
        return this.commits;
    }

}
