package com.backend.service.Impl;

import com.backend.entity.AccountEntity;
import com.backend.mapper.AccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author: qxd
 * @date: 2024/2/23
 * @description:
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements Serializable {

    @Resource
    AccountMapper accountMapper;

    public AccountEntity findAccountByNameOrEmail(String username,String mail){
        return accountMapper.findAccountByNameOrEmail(username,mail);
    }
}
