package com.example.crud.entity;

public class Student {
    private int id;
    private String studentname;
    private String project;
    private int totalCommits;
    private int totalAddLines;
    private int totalDeleteLines;
    private int totalchangelines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(int totalCommits) {
        this.totalCommits = totalCommits;
    }

    public int getTotalAddLines(){
        return totalAddLines;
    }

    public int getTotalDeleteLines(){
        return totalDeleteLines;
    }

    public int getTotalchangelines(){
        return totalchangelines;
    }


    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentname='" + studentname + '\'' +
                ", project='" + project + '\'' +
                ", totalCommits=" + totalCommits + '\'' +
                ", totalAddLines=" + totalAddLines + '\'' +
                ", totalDeleteLines=" + totalDeleteLines + '\'' +
                ", totalchangelines=" + totalchangelines +
                '}';
    }
}
