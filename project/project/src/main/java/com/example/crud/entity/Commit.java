package com.example.crud.entity;

public class Commit {
    private String name;
    private String date;
    private String project;
    private int addLines;
    private int deleteLines;
    private int totalchangelines;

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
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

    public int getTotalchangelines(){
        return totalchangelines;
    }

    public String toString(){
        return "Commit{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", project='" + project + '\'' +
                ", addLines='" + addLines + '\'' +
                ", deleteLines='" + deleteLines + '\'' +
                ", totalchangelines='" + totalchangelines +
                '}';
    }
}
