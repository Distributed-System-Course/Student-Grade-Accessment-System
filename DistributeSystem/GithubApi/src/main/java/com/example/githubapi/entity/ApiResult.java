package com.example.githubapi.entity;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class ApiResult {
    ArrayList<LinkedTreeMap<String,Object>> table;

    public void setTable(ArrayList<LinkedTreeMap<String, Object>> table) {
        this.table = table;
    }

    public ArrayList<LinkedTreeMap<String, Object>> getTable() {
        return table;
    }
}
