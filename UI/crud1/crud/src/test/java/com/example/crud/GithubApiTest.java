package com.example.crud;
import com.google.gson.internal.LinkedTreeMap;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GitHub;
import org.springframework.boot.test.context.SpringBootTest;
import com.RESTCall;

import java.io.IOException;
import java.util.ArrayList;

public class GithubApiTest {
    public static void main(String[] args) {
        RESTCall restCall=new com.RESTCall();
        ArrayList<LinkedTreeMap<String,Object>> table=restCall.returnTable(
                "https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/events",
            null);
        for (LinkedTreeMap<String,Object> row:table){
            for (String key : row.keySet()){
                System.out.print(key+"\t\t\t\t\t\t");
            }
            System.out.println();
            for (String key : row.keySet()){
                System.out.print(row.get(key)+"\t");
            }
            System.out.println();
        }
    }
}
