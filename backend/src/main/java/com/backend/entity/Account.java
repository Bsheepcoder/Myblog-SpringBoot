package com.backend.entity;

import lombok.Data;

@Data
public class Account {
    int id;
    String username;
    String password;
    String mail;
}