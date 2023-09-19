package com.backend;

import com.backend.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.getUser();
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }
}
