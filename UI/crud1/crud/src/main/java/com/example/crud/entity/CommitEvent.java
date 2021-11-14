package com.example.crud.entity;


public class CommitEvent {
    private int pid;
    String commitDate;
    int totalCommit;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTotalCommit() {
        return totalCommit;
    }

    public void setTotalCommit(int totalCommit) {
        this.totalCommit = totalCommit;
    }

    public String getCommitDate() {
        return commitDate;
    }
    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }
    @Override
    public String toString() {
        return "CommitEvent{" +
                ", pid=" + pid +
                ", commitDate='" + commitDate + '\'' +
                ", totalCommit=" + totalCommit +
                '}';
    }
}
