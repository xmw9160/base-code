package com.xmw.java8.tomaptest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mingwei.xia
 * @date 2018/6/29 17:20
 * @since V1.0
 */
public class ToMapTest {
    public static void main(String[] args) {
        List<UserInfoVO> realNameList = Arrays.asList(
                UserInfoVO.builder().username("sss1").realname("asdfg").build(),
                UserInfoVO.builder().username("sss2").realname("asdf").build());
//        Map<String, String> userNameMap = realNameList.stream()
//                .filter(userInfoVO -> StringUtils.isNotBlank(userInfoVO.realname))
//                .collect(Collectors.toMap(UserInfoVO::getUsername, UserInfoVO::getRealname));
//        System.out.println(userNameMap);


        // java.lang.UnsupportedOperationException
//        realNameList.add(null);
//        realNameList.forEach((UserInfoVO info) -> System.out.println(info.getRealname()));

        List<UserInfoVO> list = new ArrayList<>();
        list.add(UserInfoVO.builder().username("sss1").realname("asd1").build());
        list.add(UserInfoVO.builder().username("sss2").realname("asdf3").build());
        list.add(null);
        // java.lang.NullPointerException
//        list.forEach(userInfoVO -> System.out.println(userInfoVO.realname));

        for (UserInfoVO userInfoVO : list) {
            System.out.println(userInfoVO.realname);
        }
    }
}
