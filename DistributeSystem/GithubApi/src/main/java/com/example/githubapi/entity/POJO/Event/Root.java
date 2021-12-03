package com.example.githubapi.entity.POJO.Event;

import com.google.gson.annotations.SerializedName;

public class Root {
    private String id;

    private String type;

    private Actor actor;

    private Repo repo;

    private Payload payload;
    @SerializedName("public")//和关键字重名 需要注解
    private boolean ispublic;

    private String created_at;

    private Org org;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setActor(Actor actor){
        this.actor = actor;
    }
    public Actor getActor(){
        return this.actor;
    }
    public void setRepo(Repo repo){
        this.repo = repo;
    }
    public Repo getRepo(){
        return this.repo;
    }
    public void setPayload(Payload payload){
        this.payload = payload;
    }
    public Payload getPayload(){
        return this.payload;
    }
    public void setPublic(boolean ispublic){
        this.ispublic = ispublic;
    }
    public boolean getPublic(){
        return this.ispublic;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setOrg(Org org){
        this.org = org;
    }
    public Org getOrg(){
        return this.org;
    }

}