package com.example.crud.entity;

public class Project {
    private  int pid;
    private String pname;
    private String student1;
    private String student2;
    private String student3;
    private int id;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getStudent1() {
        return student1;
    }

    public void setStudent1(String student1) {
        this.student1 = student1;
    }

    public String getStudent2() {
        return student2;
    }

    public void setStudent2(String student2) {
        this.student2 = student2;
    }

    public String getStudent3() {
        return student3;
    }

    public void setStudent3(String student3) {
        this.student3 = student3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", intr='" + student1 + '\'' +
                ", cprice=" + student2 +
                ", tprice=" + student3 +
                ", id=" + id +
                '}';
    }
}
