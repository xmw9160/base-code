package com.xmw.controller;

import com.xmw.annotation.Controller;
import com.xmw.annotation.Qualifier;
import com.xmw.annotation.RequestMapping;
import com.xmw.service.UserService;

/**
 * UserController
 *
 * @author mingwei.xia
 * @date 2018/9/25 10:27
 * @since V1.0
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/insert")
    public void insert() {
        userService.insert();
    }
}
