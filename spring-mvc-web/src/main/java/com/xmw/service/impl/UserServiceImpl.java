package com.xmw.service.impl;

import com.xmw.annotation.Qualifier;
import com.xmw.annotation.Service;
import com.xmw.dao.UserDao;
import com.xmw.service.UserService;

/**
 * UserServiceImpl
 *
 * @author mingwei.xia
 * @date 2018/9/25 10:28
 * @since V1.0
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Override
    public void insert() {
        System.out.println("UserServiceImpl.insert start");
        userDao.insert();
        System.out.println("UserServiceImpl.insert end");
    }
}
