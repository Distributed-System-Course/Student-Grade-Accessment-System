package com.example.crud.entity;

public class CommitEvent {
    private int pid;
    private String name;
    private String commitDate;
    private String project;
    private int addLines;
    private int deleteLines;
    private int changeLines;
    private int total_commits;

    public int getPid(){
        return pid;
    }

    public String getName(){return name;}

    public String getCommitDate(){
        return commitDate;
    }

    public String getProject(){
        return project;
    }

    public int getAddLines(){
        return addLines;
    }

    public int getDeleteLines(){
        return deleteLines;
    }

    public int getChangeLines(){
        return changeLines;
    }

    public int getTotal_commits() {return total_commits;}

    public void setName(String name){this.name=name;}

    public String toString(){
        return "Commit{" +
                "name='" + pid + '\'' +
                ", date='" + commitDate + '\'' +
                ", project='" + project + '\'' +
                ", addLines='" + addLines + '\'' +
                ", deleteLines='" + deleteLines + '\'' +
                ", totalchangelines='" + changeLines +
                '}';
    }
}
