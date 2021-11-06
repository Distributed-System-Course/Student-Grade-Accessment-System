package com.example.studentaccessment.bean;


//实体类UserBean,根据数据库表，生成getter和setter
public class UserBean
{
    private int id;
    private String name;
    private String password;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
