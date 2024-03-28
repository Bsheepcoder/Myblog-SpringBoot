package com.backend.entity;

import com.backend.entity.Base.BaseAccountEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

@TableName(value = "account")
public class AccountEntity  extends BaseAccountEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }

}