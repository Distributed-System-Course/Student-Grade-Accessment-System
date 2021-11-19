package POJO.Event;

public class Commits {
    private String sha;
    private Author author;
    private String message;
    private boolean distinct;
    private String url;
    public void setSha(String sha){
        this.sha = sha;
    }
    public String getSha(){
        return this.sha;
    }
    public void setAuthor(Author author){
        this.author = author;
    }
    public Author getAuthor(){
        return this.author;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setDistinct(boolean distinct){
        this.distinct = distinct;
    }
    public boolean getDistinct(){
        return this.distinct;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }

}
