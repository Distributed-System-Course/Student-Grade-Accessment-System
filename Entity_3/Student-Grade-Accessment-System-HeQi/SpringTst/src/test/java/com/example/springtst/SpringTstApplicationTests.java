package com.example.springtst;

import com.example.springtst.bean.UserBean;
import com.example.springtst.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringTstApplicationTests {
    @Resource
    UserService userService;
    @Test
    void contextLoads() {
        UserBean userBean = userService.logIn("root","123456");
        System.out.println("user Id is ");
        System.out.println(userBean.getId());
    }

}
