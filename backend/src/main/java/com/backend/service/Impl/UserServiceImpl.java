package com.backend.service.Impl;


import com.backend.entity.User;
import com.backend.mapper.UserMapper;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUser() {
        return userMapper.getUser();
    }
}
