package com.example.redshop.mapper;

import com.example.redshop.domain.Admin;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminMapper {

    Admin findByAdmin(Admin admin);


    int editPswdByAdmin(Admin admin);
}
