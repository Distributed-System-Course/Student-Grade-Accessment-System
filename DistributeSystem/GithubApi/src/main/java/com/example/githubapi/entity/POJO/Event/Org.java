package com.example.githubapi.entity.POJO.Event;
public class Org {
    private int id;

    private String login;

    private String gravatar_id;

    private String url;

    private String avatar_url;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return this.login;
    }
    public void setGravatar_id(String gravatar_id){
        this.gravatar_id = gravatar_id;
    }
    public String getGravatar_id(){
        return this.gravatar_id;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getAvatar_url(){
        return this.avatar_url;
    }

}
