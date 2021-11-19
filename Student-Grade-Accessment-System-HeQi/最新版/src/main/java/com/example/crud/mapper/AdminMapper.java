package com.example.crud.mapper;

import com.example.crud.entity.Admin;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminMapper {

    Admin findByAdmin(Admin admin);


    int editPswdByAdmin(Admin admin);
}
