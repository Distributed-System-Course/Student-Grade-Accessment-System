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

    public void setTotal_commits(int total_commits) {
        this.total_commits = total_commits;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setAddLines(int addLines) {
        this.addLines = addLines;
    }

    public void setDeleteLines(int deleteLines) {
        this.deleteLines = deleteLines;
    }

    public void setChangeLines(int changeLines) {
        this.changeLines = changeLines;
    }

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
