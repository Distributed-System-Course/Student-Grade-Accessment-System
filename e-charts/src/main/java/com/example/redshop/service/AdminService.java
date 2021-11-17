package com.example.redshop.service;

import com.example.redshop.domain.Admin;


public interface AdminService {

    Admin findByAdmin(Admin admin);


    int editPswdByAdmin(Admin admin);
}
