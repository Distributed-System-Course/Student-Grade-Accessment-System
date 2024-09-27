package com.example.studentaccessment.bean;

public class GroupBean
{
    private int id;
    private String GroupName;
    private String information;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getGroupName()
    {
        return GroupName;
    }

    public void setGroupName(String name)
    {
        this.GroupName = GroupName;
    }

    public String select(int groupID) { return information; }
}
