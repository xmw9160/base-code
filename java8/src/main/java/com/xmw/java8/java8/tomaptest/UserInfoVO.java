package com.xmw.java8.java8.tomaptest;

import lombok.Builder;
import lombok.Data;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/6/29 17:22
 * @since V1.0
 */
@Data
@Builder
public class UserInfoVO {
    public String username;
    public String realname;
}
