package com.example.crud.service;


import com.example.crud.entity.Admin;

public interface AdminService {

    Admin findByAdmin(Admin admin);


    int editPswdByAdmin(Admin admin);
}
