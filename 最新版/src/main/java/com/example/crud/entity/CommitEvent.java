package com.example.crud.entity;

public class CommitEvent {
    private int pid;
    private String commitDate;
    private String project;
    private int addLines;
    private int deleteLines;
    private int totalChangeLines;

    public int getPid(){
        return pid;
    }

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

    public int getTotalChangeLines(){
        return totalChangeLines;
    }

    public void setDeleteLines(int deleteLines) {
        this.deleteLines = deleteLines;
    }

    public void setAddLines(int addLines) {
        this.addLines = addLines;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setTotalChangeLines(int totalChangeLines) {
        this.totalChangeLines = totalChangeLines;
    }

    public String toString(){
        return "CommitEvent{" +
                "pid='" + pid + '\'' +
                ", commitDate='" + commitDate + '\'' +
                ", project='" + project + '\'' +
                ", addLines='" + addLines + '\'' +
                ", deleteLines='" + deleteLines + '\'' +
                ", totalChangeLines='" + totalChangeLines +
                '}';
    }
}
