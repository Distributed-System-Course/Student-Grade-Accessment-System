package com.example.githubapi.controller;

import com.example.githubapi.entity.ApiRequest;
import com.example.githubapi.entity.ApiResult;
import com.example.githubapi.entity.RESTCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GithubApiController {
    @PostMapping("getStudentInfo")
    public ApiResult getStudentInfo(@RequestBody ApiRequest apiRequest){
        ApiResult apiResult=new ApiResult();
        String token="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";
        RESTCall restCall= new RESTCall();
        apiResult.setTable(restCall.returnTable(apiRequest.getApiUrl(),token));
        return apiResult;
    }

}
