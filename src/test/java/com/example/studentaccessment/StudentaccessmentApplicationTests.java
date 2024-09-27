package com.example.studentaccessment;

import com.example.studentaccessment.bean.UserBean;
import com.example.studentaccessment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentaccessmentApplicationTests
{
    @Resource
    UserService userService;
    @Test
    void contextLoads()
    {
        UserBean userBean = userService.logIn("Mike","123");
        System.out.println("user Id is:");
        System.out.println(userBean.getId());
    }

}
