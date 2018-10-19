package com.xmw.dao.impl;

import com.xmw.annotation.Repository;
import com.xmw.dao.UserDao;

/**
 * UserDaoImpl
 *
 * @author mingwei.xia
 * @date 2018/9/25 10:34
 * @since V1.0
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
    @Override
    public void insert() {
        System.out.println("execute UserDaoImpl.insert");
    }
}
