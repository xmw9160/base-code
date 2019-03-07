package com.xmw.bean;

import java.util.List;

import com.xmw.enums.UserType;

import lombok.Data;

@Data
public class User {

    private String id;

    private String username;

    /**
     * 用户号码
     */
    private String svcnum;

    private String password;

    /**
     * 一个用户只能对应一个客户
     */
    private Cust cust;

    /**
     * 一个用户对应多个账户
     */
    private List<Acct> accts;

    /**
     * 用户类型: 普通用户和重要用户
     */
    private String type;

    public User(String id, String username) {
        super();
        this.id = id;
        this.username = username;
    }

    public User(){}
}
